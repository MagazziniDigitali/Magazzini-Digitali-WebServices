package it.bncf.magazziniDigitali.services.axis;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.axis.transport.http.AxisServlet;

import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;

public class DepositoLegaleAxisServlet extends AxisServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2900302459278341373L;

	public static MDConfiguration mdConfiguration = null;

	public DepositoLegaleAxisServlet() {
		super();
	}

	/**
	 * @see org.apache.axis.transport.http.AxisServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		try {
			super.init();
			ServletContext sc =  this.getServletContext();
			String nomeCatalogo = sc.getInitParameter("nomeCatalogo");
			DepositoLegaleAxisServlet.mdConfiguration = new MDConfiguration("IA",nomeCatalogo);
			System.out.println("DepositoLegaleAxisServlet.init()");
		} catch (MDConfigurationException e) {
			throw new ServletException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ServletException(e.getMessage(), e);
		}
	}
}
