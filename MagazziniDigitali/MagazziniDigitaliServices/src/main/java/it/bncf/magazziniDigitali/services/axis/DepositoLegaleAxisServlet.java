package it.bncf.magazziniDigitali.services.axis;


import java.io.File;

import javax.servlet.ServletException;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.axis.transport.http.AxisServlet;

public class DepositoLegaleAxisServlet extends AxisServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2900302459278341373L;

	public DepositoLegaleAxisServlet() {
		super();
	}

	/**
	 * @see org.apache.axis.transport.http.AxisServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		init(this.getServletContext().getInitParameter("nomeCatalogo"));
	}

	public static void init(String nomeCatalogos) throws ServletException {
		String pathProperties = "";
		String[] st = null;
		String nomeCatalogo = null;
		File f = null;

		try {
			if (!Configuration.isInizialize()) {
				
				st = nomeCatalogos.split("\\|");
				for (int x=0;x<st.length; x++){
					nomeCatalogo = st[x];
					if (nomeCatalogo != null && nomeCatalogo.startsWith("file://"))
						pathProperties = nomeCatalogo.replace("file:///", "");
					else {
						pathProperties = System.getProperty("catalina.base")
								+ File.separator;
						if (nomeCatalogo == null)
							pathProperties += "conf/teca_digitale";
						else
							pathProperties += nomeCatalogo;
					}
					f = new File(pathProperties);
					if (f.exists()){
						Configuration.init(pathProperties);
					}
				}
			}
		} catch (ConfigurationException e) {
			throw new ServletException(e.getMessage(), e);
		}
	}

}
