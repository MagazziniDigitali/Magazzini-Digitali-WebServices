package it.bncf.magazziniDigitali.services.implement.userLibrary;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.NamingException;

import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.istituzione.MDIstituzioneBusiness;
import it.bncf.magazziniDigitali.businessLogic.ticket.MDTicketBusiness;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.external.user.IExternalUser;
import it.bncf.magazziniDigitali.external.user.exception.ExternalUserException;
import it.bncf.magazziniDigitali.external.user.md.ExternalUserMd;
import it.bncf.magazziniDigitali.services.axis.DepositoLegaleAxisServlet;
import it.depositolegale.www.authenticationUserInput.Rights;
import it.depositolegale.www.authenticationUserInput.RightsRightsDisseminateRightsDisseminateType;
import it.depositolegale.www.authenticationUserInput.UserInput;
import mx.randalf.hibernate.exception.HibernateUtilException;

public class AuthenticationUserLibraryGenTicket {

	public AuthenticationUserLibraryGenTicket() {
	}

	/**
	 * Metodo utilizzato per fare i test sulla richiesta e per la scelta della tipologia di url da creare
	 * 
	 * @param userInput
	 * @param rights
	 * @param actualFileName
	 * @param originalFileName
	 * @return
	 * @throws AuthenticationUserLibraryException
	 */
	protected static String genTicket(UserInput userInput, Rights rights, String actualFileName,
			String originalFileName) throws AuthenticationUserLibraryException {
		String url = null;

		try {
			if (rights.getRightsDisseminate().getRightsDisseminateType()
					.equals(RightsRightsDisseminateRightsDisseminateType.A)) {
				if (checkLogin(userInput, rights)) {
					url = genTicket(userInput, rights, actualFileName, originalFileName,
							DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.externalUrl"));
				} else {
					throw new AuthenticationUserLibraryException("Utente e password non validi riprovare");
				}
			}
			if (rights.getRightsDisseminate().getRightsDisseminateType()
					.equals(RightsRightsDisseminateRightsDisseminateType.B)) {
				url = genTicket(userInput, rights, actualFileName, originalFileName,
						DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.externalUrl"));
			} else if (rights.getRightsDisseminate().getRightsDisseminateType()
					.equals(RightsRightsDisseminateRightsDisseminateType.C)) {
				if (checkLogin(userInput, rights)) {
					if (userInput.getTypeAuth().equalsIgnoreCase("editore")){
						url = genTicket(userInput, rights, actualFileName, originalFileName,
								DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.externalUrl"));
					} else {
						if (checkMimeTypeBib(userInput.getMimeType())){
							url = genTicket(userInput, rights, actualFileName, originalFileName,
									DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.dockerUrl"));
						} else {
							url = genTicket(userInput, rights, actualFileName, originalFileName,
									DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.externalUrl"));
						}
					}
				} else {
					throw new AuthenticationUserLibraryException("Utente e password non validi riprovare");
				}
			}
		} catch (AuthenticationUserLibraryException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}
		return url;
	}

	/**
	 * Metodo utilizzato per la registrazione del recod nella tabella Ticket e per la generazione dell'URL
	 * 
	 * @param userInput
	 * @param rights
	 * @param actualFileName
	 * @param originalFileName
	 * @param mUrl
	 * @return
	 * @throws AuthenticationUserLibraryException
	 */
	private static String genTicket(UserInput userInput, Rights rights, String actualFileName, String originalFileName,
			String mUrl) throws AuthenticationUserLibraryException {
		MDTicketBusiness mdTicketBusiness = null;
		HashTable<String, Object> dati = null;
		GregorianCalendar gc = null;
		String url = null;
		String idTicket = null;
		MDIstituzioneBusiness mdIstituzioneBusiness = null;

		try {
			mdTicketBusiness = new MDTicketBusiness();

			dati = new HashTable<String, Object>();
			dati.put("objectIdentifier", userInput.getIdentifier());
			gc = new GregorianCalendar();
			dati.put("dataStart", new Timestamp(gc.getTimeInMillis()));
			gc.add(Calendar.MINUTE,
					new Integer(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.period")));
			dati.put("dataEnd", new Timestamp(gc.getTimeInMillis()));
			dati.put("modalitaAccesso", rights.getRightsDisseminate().getRightsDisseminateType().getValue());
			dati.put("idRights", rights.getRightsIdentifier().getRightsIdentifierValue());
			dati.put("actualFileName", actualFileName);
			dati.put("originalFileName", originalFileName);

			mdIstituzioneBusiness = new MDIstituzioneBusiness();
			if (userInput.getAgent() != null && userInput.getAgent().getAgentIdentifier() != null) {
				dati.put("idIstituzione", mdIstituzioneBusiness.findById(userInput.getAgent().getAgentIdentifier()));
			} else if (userInput.getTypeAuth().equals("editore")){
				dati.put("idIstituzione", mdIstituzioneBusiness.findById(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("istituzioneMD.id")));
			}
			if (userInput.getAuthentication() != null && userInput.getAuthentication().getLogin() != null) {
				dati.put("loginUtente", userInput.getAuthentication().getLogin());
			}
			dati.put("mimeType", userInput.getMimeType());
			dati.put("tipoOggetto", userInput.getTipoOggetto());
			dati.put("ipClient", userInput.getIpClient());
			idTicket = mdTicketBusiness.save(dati);

			url = appendUri(mUrl, "idTicket=" + idTicket);
		} catch (NumberFormatException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (SecurityException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}
		return url;
	}

	/**
	 * Metodo utilizzato per la creazione dell'URL
	 * @param uri
	 * @param appendQuery
	 * @return
	 * @throws AuthenticationUserLibraryException
	 */
	private static String appendUri(String uri, String appendQuery) throws AuthenticationUserLibraryException {
		URI oldUri = null;
		String newQuery = null;
		URI newUri = null;

		try {
			oldUri = new URI(uri);
			newQuery = oldUri.getQuery();
			if (newQuery == null) {
				newQuery = appendQuery;
			} else {
				newQuery += "&" + appendQuery;
			}

			newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(), oldUri.getPath(), newQuery,
					oldUri.getFragment());
		} catch (URISyntaxException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}

		return newUri.toString();
	}

	private static boolean checkLogin(UserInput userInput, Rights rights) throws AuthenticationUserLibraryException {
		MDIstituzioneBusiness mdIstituzioneBusiness = null;
		MDIstituzione mdIstituzione = null;
		IExternalUser externalUser = null;
		boolean result = false;

		try {
			if (rights.getRightsDisseminate().getRightsDisseminateType()
					.equals(RightsRightsDisseminateRightsDisseminateType.A) || userInput.getTypeAuth().equals("editore")) {
				externalUser = new ExternalUserMd(DepositoLegaleAxisServlet.mdConfiguration,
						userInput.getDepositante());
				result = externalUser.isValid(null, userInput.getAuthentication().getLogin(),
						userInput.getAuthentication().getPassword(), userInput.getIpClient());
			} else {
				mdIstituzioneBusiness = new MDIstituzioneBusiness();
				mdIstituzione = mdIstituzioneBusiness.findById(userInput.getAgent().getAgentIdentifier());

				if (mdIstituzione != null) {
					if (mdIstituzione.getLibreriaApiUtente() != null) {
						externalUser = (IExternalUser) Class.forName(mdIstituzione.getLibreriaApiUtente())
								.newInstance();
						result = externalUser.isValid(mdIstituzione.getInterfacciaApiUtente(),
								userInput.getAuthentication().getLogin(), userInput.getAuthentication().getPassword(),
								userInput.getIpClient());
					} else {
						throw new AuthenticationUserLibraryException(
								"Le librerie relative alla chiamata remota non sono state indicate");
					}

				} else {
					throw new AuthenticationUserLibraryException("L'istituzione indicata non esiste verificare");
				}
			}
		} catch (HibernateException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (ExternalUserException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (AuthenticationUserLibraryException e) {
			throw e;
		}
		return result;
	}

	public static boolean checkMimeTypeBib(String mimeType) {
		boolean result = false;
		switch (mimeType) {
		case "application/pdf":
			result = true;
			break;
		case "application/epub+zip":
			result = true;
			break;
		case "application/epub":
			result = true;
			break;
		default:
			result = false;
			break;
		}
		return result;
	}
}
