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

	public static void init(String nomeCatalogo) throws ServletException {
		String pathProperties = "";

		try {
			if (!Configuration.isInizialize()) {
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

				Configuration.init(pathProperties);
			}
		} catch (ConfigurationException e) {
			throw new ServletException(e.getMessage(), e);
		}
	}

}
