/**
 * 
 */
package it.bncf.magazziniDigitali.services.showObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.server.AxisServer;
import org.apache.catalina.core.DefaultInstanceManager;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.jasper.compiler.TldCache;
import org.apache.log4j.Logger;
import org.apache.tomcat.websocket.server.WsServerContainer;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.ticket.MDTicketBusiness;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDTicket;
import it.bncf.magazziniDigitali.services.axis.MDConfiguration;
import it.bncf.magazziniDigitali.services.implement.userLibrary.AuthenticationUserLibrary;
import it.bncf.magazziniDigitali.utils.GenFileDest;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class ShowObject extends HttpServlet {

	private int maxCol = 4;

	private Logger log = Logger.getLogger(ShowObject.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -1622811153507052587L;

	/**
	 * 
	 */
	public ShowObject() {
	}

	/**
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Enumeration<String> parameters = null;
		String parameter = null;
		boolean showInfo = false;
		String ticket = null;
		String line = null;

		try {
			if (req.getPathInfo() != null && !req.getPathInfo().trim().equals("/")) {
				line = req.getPathInfo().trim().substring(1);
				if (line.indexOf("/") > -1) {
					ticket = line.substring(0, line.indexOf("/"));
				} else {
					ticket = line;
				}
			}
			parameters = req.getParameterNames();
			while (parameters.hasMoreElements()) {
				parameter = parameters.nextElement();
				if (parameter.trim().equalsIgnoreCase("showInfo")) {
					showInfo = true;
				} else if (parameter.trim().equalsIgnoreCase("ticket")
						|| parameter.trim().equalsIgnoreCase("idTicket")) {
					ticket = req.getParameter(parameter);
				} else if ((req.getParameter(parameter) == null || req.getParameter(parameter).trim().equals(""))
						&& ticket == null) {
					ticket = parameter;
				}
			}

			if (showInfo) {
				showInfo(req, res);
			} else if (ticket != null) {
				showTicket(req, res, ticket);
			} else {
				showMsgError(res, "Non risulta indicato l'oggetto da visualizzare");
			}
		} catch (ServletException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	private void showTicket(HttpServletRequest req, HttpServletResponse res, String ticket)
			throws ServletException, IOException {
		MDConfiguration mdConfiguration = null;
		MDTicketBusiness mdTicketBusiness = null;
		MDTicket mdTicket = null;
		GregorianCalendar gc = null;

		try {
			mdConfiguration = new MDConfiguration("SO", req.getServletContext().getInitParameter("nomeCatalogo"),
					"127.0.0.1");
			mdTicketBusiness = new MDTicketBusiness();
			mdTicket = mdTicketBusiness.findById(ticket);
			if (mdTicket != null) {
				if (checkIP(mdTicket, req, mdConfiguration)) {
					gc = new GregorianCalendar();
					if (mdTicket.getDataStart().getTime() < gc.getTimeInMillis()) {
						if (mdTicket.getDataEnd().getTime() >= gc.getTimeInMillis()) {
							findObject(req, res, mdTicket.getActualFileName(), mdTicket.getOriginalFileName(),
									mdConfiguration, mdTicket);
						} else {
							showMsgError(res, "Il ticket [" + ticket + "] risulta essere scaduto rifare la richiesta");
						}
					} else {
						showMsgError(res, "Il ticket [" + ticket
								+ "] non risulta ancora valido nella data attuale riprovare più tardi");
					}
				} else {
					showMsgError(res, "La stazione chiamante del ticket [" + ticket
							+ "] non corrisponde alla stazione richiedente");
				}
			} else {
				showMsgError(res, "Il ticket [" + ticket + "] non risulta presente in base dati");
			}
		} catch (HibernateException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (ServletException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw new ServletException(e.getMessage(), e);
		}

	}

	private boolean checkIP(MDTicket mdTicket, HttpServletRequest req, MDConfiguration mdConfiguration)
			throws ServletException {
		boolean result = false;
		String remoteAddr = null;

		try {
			remoteAddr = getRemoteAddr(req).trim();

			if (mdTicket.getModalitaAccesso().equals("A")) {
				result = mdTicket.getIpClient().trim().equals(remoteAddr);
			} else if (mdTicket.getModalitaAccesso().equals("B")) {
				result = mdTicket.getIpClient().trim().equals(remoteAddr);
			} else if (mdTicket.getModalitaAccesso().equals("C")) {
				if (mdTicket.getTipoOggetto().equals("contenitore")) {
					result = mdTicket.getIpClient().trim().equals(remoteAddr);
				} else if (mdTicket.getIdIstituzione().getId().trim().equals(mdConfiguration.getSoftwareConfigString("istituzioneMD.id"))){
					result = mdTicket.getIpClient().trim().equals(remoteAddr);
				} else if (!AuthenticationUserLibrary.checkMimeTypeBib(mdTicket.getMimeType())) {
					result = mdTicket.getIpClient().trim().equals(remoteAddr);
				} else {
					result = mdConfiguration.getSoftwareConfigString("docker.ip").equals(remoteAddr);
				}
			}
		} catch (MDConfigurationException e) {
			throw new ServletException(e.getMessage(), e);
		}
		return result;
	}

	private String getRemoteAddr(HttpServletRequest req) {
		String ipAddress = null;

		ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = req.getRemoteAddr();
		}
		return ipAddress;
	}

	private void findObject(HttpServletRequest req, HttpServletResponse res, String actualFileName,
			String originalFileName, MDConfiguration mdConfiguration, MDTicket mdTicket) throws ServletException {

		File fTar = null;
		FileInputStream fis = null;
		TarArchiveInputStream tais = null;
		TarArchiveEntry tae = null;
		boolean trovato = false;
		String fileName = null;

		try {

			fTar = GenFileDest.genFileDest(mdConfiguration.getSoftwareConfigMDNodi("validate.nodo"), actualFileName);

			if (fTar.exists()) {

				if (originalFileName.indexOf("/") > -1) {
					fileName = originalFileName.substring(originalFileName.indexOf("/") + 1);
				} else {
					fileName = originalFileName;
				}
				fis = new FileInputStream(fTar);
				if (mdTicket.getTipoOggetto().equals("contenitore")) {
					res.setHeader("Expires", "0");
					res.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
					res.setHeader("Pragma", "public");

					res.setContentType(mdTicket.getMimeType());
					res.setHeader("Content-disposition", "attachment; filename=" + fileName);
					IOUtils.copy(fis, res.getOutputStream());

					trovato = true;
				} else {
					tais = (TarArchiveInputStream) new ArchiveStreamFactory().createArchiveInputStream("tar", fis);
					while ((tae = (TarArchiveEntry) tais.getNextEntry()) != null) {
						if (tae.getName().equals(originalFileName)) {
							// System.out.println(tae.getName());
							res.setHeader("Expires", "0");
							res.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
							res.setHeader("Pragma", "public");

							res.setContentType(mdTicket.getMimeType());

							if (mdTicket.getModalitaAccesso().equals("A") || mdTicket.getModalitaAccesso().equals("B")
									|| (mdTicket.getModalitaAccesso().equals("C")
											&& (mdTicket.getTipoOggetto().equals("contenitore")
													|| mdTicket.getIdIstituzione().getId().trim()
															.equals(mdConfiguration
																	.getSoftwareConfigString("istituzioneMD.id"))
													|| !AuthenticationUserLibrary
															.checkMimeTypeBib(mdTicket.getMimeType())))) {
								res.setHeader("Content-disposition", "attachment; filename=" + fileName);
							}

							IOUtils.copy(tais, res.getOutputStream());
							// res.getOutputStream().close();
							trovato = true;

							break;
						}
					}
				}
				if (!trovato) {
					showMsgError(res, "Non risulta l'oggetto cercato nel file archivio dello Storage");
				}
			} else {
				showMsgError(res, "Non risulta il file archivio sullo storage");
			}
		} catch (MDConfigurationException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (IOException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (ArchiveException e) {
			throw new ServletException(e.getMessage(), e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (tais != null) {
					tais.close();
				}
			} catch (IOException e) {
				throw new ServletException(e.getMessage(), e);
			}
		}
	}

	private void showMsgError(HttpServletResponse res, String msgError) throws IOException {
		PrintWriter out = null;

		try {
			res.setContentType("text/html");
			// Now obtain a PrintWriter to insert HTML into
			out = res.getWriter();
			out.println("<html><head><title>Messaggi di Errore</title></head>");

			out.println("<style>");
			out.println("h1{color: red; text-align: center;}");
			out.println("</style>");
			out.println("<body>");
			out.println("<h1>" + msgError + "</h1>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			throw e;
		}

	}

	private void showInfo(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = null;
		String header = null;
		Enumeration<String> headers = null;
		Enumeration<String> values = null;
		String[] values2 = null;
		String value = null;
		boolean primo = false;

		try {
			res.setContentType("text/html");
			// Now obtain a PrintWriter to insert HTML into
			out = res.getWriter();
			out.println("<html><head><title>" + "Hello World!</title></head>");

			out.println("<style>");
			out.println("table{" + "  border: 1px solid;" + "  border-spacing: 1px;" + "  width: 100%;" + "}");
			out.println("th{background-color: gray;}");
			out.println("</style>");
			out.println("<body>");

			out.println("<table>");
			print(out, 0, "BASIC_AUTH", HttpServletRequest.BASIC_AUTH);
			print(out, 0, "CLIENT_CERT_AUTH", HttpServletRequest.CLIENT_CERT_AUTH);
			print(out, 0, "DIGEST_AUTH", HttpServletRequest.DIGEST_AUTH);
			print(out, 0, "FORM_AUTH", HttpServletRequest.FORM_AUTH);

			print(out, 0, "getAuthType", req.getAuthType());
			print(out, req.getCookies());

			if (req.getHeaderNames() != null) {
				print(out, 0, "Header");
				headers = req.getHeaderNames();
				while (headers.hasMoreElements()) {
					header = headers.nextElement();
					values = req.getHeaders(header);
					primo = true;
					while (values.hasMoreElements()) {
						value = values.nextElement();
						print(out, 1, (primo ? header : null), value);
						primo = false;
					}
				}
			}

			print(out, 0, "getMethod", req.getMethod());
			print(out, 0, "getPathInfo", req.getPathInfo());
			print(out, 0, "getPathTranslated", req.getPathTranslated());
			print(out, 0, "getContextPath", req.getContextPath());
			print(out, 0, "getQueryString", req.getQueryString());
			print(out, 0, "getRemoteUser", req.getRemoteUser());
			print(out, req.getUserPrincipal());
			print(out, 0, "getRequestedSessionId", req.getRequestedSessionId());
			print(out, 0, "getRequestURI", req.getRequestURI());
			print(out, 0, "getRequestURL", req.getRequestURL().toString());
			print(out, 0, "getServletPath", req.getServletPath());
			print(out, req.getSession());
			print(out, 0, "changeSessionId", req.changeSessionId());
			print(out, 0, "isRequestedSessionIdValid", req.isRequestedSessionIdValid());
			print(out, 0, "isRequestedSessionIdFromURL", req.isRequestedSessionIdFromURL());
			// print(out, 0, "authenticate", req.authenticate(res));

			if (req.getAttributeNames() != null) {
				print(out, 0, "Attribute");
				headers = req.getAttributeNames();
				while (headers.hasMoreElements()) {
					header = headers.nextElement();
					print(out, 1, header, (String) req.getAttribute(header));
				}
			}

			print(out, 0, "getCharacterEncoding", req.getCharacterEncoding());
			print(out, 0, "getContentLength", req.getContentLength());
			print(out, 0, "getContentLengthLong", req.getContentLengthLong());
			print(out, 0, "getContentType", req.getContentType());

			if (req.getParameterNames() != null) {
				print(out, 0, "Parameter");
				headers = req.getParameterNames();
				while (headers.hasMoreElements()) {
					header = headers.nextElement();
					values2 = req.getParameterValues(header);
					primo = true;
					for (int x = 0; x < values2.length; x++) {
						value = values2[x];
						print(out, 1, (primo ? header : null), value);
						primo = false;
					}
				}
			}

			print(out, 0, "getProtocol", req.getProtocol());
			print(out, 0, "getScheme", req.getScheme());
			print(out, 0, "getServerName", req.getServerName());
			print(out, 0, "getServerPort", req.getServerPort());
			print(out, 0, "getRemoteAddr", req.getRemoteAddr());
			print(out, 0, "getRemoteHost", req.getRemoteHost());
			print(out, 0, "getLocale", req.getLocale().toString());
			// print(out, 0, "getLocales", req.getLocales());
			print(out, 0, "isSecure", req.isSecure());
			// print(out, 0, "getRequestDispatcher",
			// req.getRequestDispatcher("."));
			print(out, 0, "getRemotePort", req.getRemotePort());
			print(out, 0, "getLocalName", req.getLocalName());
			print(out, 0, "getLocalAddr", req.getLocalAddr());
			print(out, 0, "getLocalPort", req.getLocalPort());
			// print(out, 0, "getServletContext", req.getServletContext());
			print(out, 0, "isAsyncStarted", req.isAsyncStarted());
			print(out, 0, "isAsyncSupported", req.isAsyncSupported());
			// if (req.isAsyncStarted()){
			// print(out, 0, "getAsyncContext", req.getAsyncContext());
			// }
			// print(out, 0, "getDispatcherType", req.getDispatcherType());

			out.println("</table>");
			out.println("</body></html>");
		} catch (IOException e) {
			throw e;
			// } catch (ServletException e) {
			// throw e;
		}
	}

	private void print(PrintWriter out, HttpSession session) {
		Enumeration<String> names = null;
		String name = null;
		if (session != null) {
			print(out, 0, "Session");
			print(out, 1, "getId", session.getId());

			names = session.getAttributeNames();
			while (names.hasMoreElements()) {
				name = names.nextElement();
				print(out, 2, name, (String) session.getAttribute(name));
			}

			print(out, 1, "getCreationTime", session.getCreationTime());
			print(out, 1, "getLastAccessedTime", session.getLastAccessedTime());
			print(out, 1, "getMaxInactiveInterval", session.getMaxInactiveInterval());
			print(out, 1, session.getServletContext());
			print(out, 1, "getCreationTime", session.getCreationTime());
			print(out, 1, "isNew", session.isNew());
		}
	}

	private void print(PrintWriter out, int rientro, ServletContext servletContext) {
		Enumeration<String> attributes = null;
		String attribute = null;
		Enumeration<String> parameters = null;
		String parameter = null;

		if (servletContext != null) {
			print(out, rientro, "ServletContext");
			rientro++;

			attributes = servletContext.getAttributeNames();
			print(out, rientro, "Attribute");
			while (attributes.hasMoreElements()) {
				attribute = attributes.nextElement();
				if (servletContext.getAttribute(attribute) instanceof File) {
					print(out, rientro, attribute, ((File) servletContext.getAttribute(attribute)).getAbsolutePath());
				} else if (servletContext.getAttribute(attribute) instanceof StandardRoot) {
					print(out, rientro, attribute,
							((StandardRoot) servletContext.getAttribute(attribute)).getClass().getName());
				} else if (servletContext.getAttribute(attribute) instanceof AxisServer) {
					print(out, rientro, attribute,
							((AxisServer) servletContext.getAttribute(attribute)).getClass().getName());
				} else if (servletContext.getAttribute(attribute) instanceof DefaultInstanceManager) {
					print(out, rientro, attribute,
							((DefaultInstanceManager) servletContext.getAttribute(attribute)).getClass().getName());
				} else if (servletContext.getAttribute(attribute) instanceof WsServerContainer) {
					print(out, rientro, attribute,
							((WsServerContainer) servletContext.getAttribute(attribute)).getClass().getName());
				} else if (servletContext.getAttribute(attribute) instanceof TldCache) {
					print(out, rientro, attribute,
							((TldCache) servletContext.getAttribute(attribute)).getClass().getName());
				} else if (servletContext.getAttribute(attribute) instanceof String) {
					print(out, rientro, attribute, ((String) servletContext.getAttribute(attribute)));
				} else {
					print(out, rientro, attribute,
							((Object) servletContext.getAttribute(attribute)).getClass().getName());
				}
			}
			print(out, rientro, "", servletContext.getContextPath());
			print(out, rientro, "", servletContext.getEffectiveMajorVersion());
			print(out, rientro, "", servletContext.getEffectiveMinorVersion());

			parameters = servletContext.getAttributeNames();
			print(out, rientro, "Parameter");
			while (parameters.hasMoreElements()) {
				parameter = parameters.nextElement();
				print(out, (rientro + 1), parameter, servletContext.getInitParameter(parameter));
			}
			print(out, rientro, "getMajorVersion", servletContext.getMajorVersion());
			print(out, rientro, "getMinorVersion", servletContext.getMinorVersion());
			print(out, rientro, "getServerInfo", servletContext.getServerInfo());
			print(out, rientro, "getServletContextName", servletContext.getServletContextName());
			print(out, rientro, "getVirtualServerName	", servletContext.getVirtualServerName());
		}
	}

	private void print(PrintWriter out, Principal userPrincipal) {
		if (userPrincipal != null) {
			print(out, 0, "UserPrincipal", userPrincipal.getName());
		}
	}

	private void print(PrintWriter out, Cookie[] cookies) {
		if (cookies != null && cookies.length > 0) {
			print(out, 0, "Cookies");
			for (int x = 0; x < cookies.length; x++) {
				print(out, 1, "getName", cookies[x].getName());
				print(out, 1, "getValue", cookies[x].getValue());
				print(out, 1, "getComment", cookies[x].getComment());
				print(out, 1, "getDomain", cookies[x].getDomain());
				print(out, 1, "getMaxAge", cookies[x].getMaxAge());
				print(out, 1, "getPath", cookies[x].getPath());
				print(out, 1, "getSecure", cookies[x].getSecure());
				print(out, 1, "getVersion", cookies[x].getVersion());
			}
		}
	}

	private void print(PrintWriter out, int rientro, String key) {
		out.println("<tr>");

		for (int x = 0; x < rientro; x++) {
			out.println("<th>&nbsp;</th>");
		}

		out.println("<th colspan=\"" + (maxCol - rientro) + "\">" + key + "</th></tr>");
	}

	private void print(PrintWriter out, int rientro, String key, Boolean value) {
		print(out, rientro, key, value.toString());
	}

	private void print(PrintWriter out, int rientro, String key, Integer value) {
		print(out, rientro, key, value.toString());
	}

	private void print(PrintWriter out, int rientro, String key, Long value) {
		print(out, rientro, key, value.toString());
	}

	private void print(PrintWriter out, int rientro, String key, String value) {
		int colSpanTd = 1;

		if (value != null) {
			if (rientro == 0) {
				colSpanTd = maxCol - 1;
			}
			out.println("<tr>");

			for (int x = 0; x < rientro; x++) {
				out.println("<th>&nbsp;</th>");
			}
			out.println("<th>" + (key == null ? "&nbsp;" : key) + "</th>" + "<td colspan=\"" + colSpanTd + "\">" + value
					+ "</td>" + "</tr>");
		}
	}
}
