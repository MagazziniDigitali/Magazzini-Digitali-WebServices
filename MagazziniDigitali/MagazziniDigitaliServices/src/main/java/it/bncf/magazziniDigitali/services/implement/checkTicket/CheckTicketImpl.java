package it.bncf.magazziniDigitali.services.implement.checkTicket;

import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.GregorianCalendar;
import java.util.Vector;

import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.ticket.MDTicketBusiness;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDTicket;
import it.bncf.magazziniDigitali.services.axis.DepositoLegaleAxisServlet;
import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.bncf.magazziniDigitali.services.implement.userLibrary.AuthenticationUserLibraryException;
import it.depositolegale.www.checkTicket.CheckTicket;
import it.depositolegale.www.checkTicketOutput.CheckTicketOutput;
import it.depositolegale.www.checkTicketOutput.CheckTicketOutputTipo;
import it.depositolegale.www.errorMsg.ErrorMsg;
import it.depositolegale.www.errorMsg.ErrorType_type;
import mx.randalf.hibernate.exception.HibernateUtilException;

public class CheckTicketImpl {

	public CheckTicketImpl() {
	}

	public static CheckTicketOutput checkTicket(CheckTicket input) throws RemoteException{
		MDTicketBusiness mdTicketBusiness = null;
		MDTicket mdTicket = null;
		Vector<ErrorMsg> errorMsg= null;
		GregorianCalendar gc = null;
		String originalFileName = null;
		String url = null;
		CheckTicketOutput output = null;

		try {
			output = new CheckTicketOutput();
			output.setCheckTicket(input);
			errorMsg = new Vector<ErrorMsg>();
			if (SoftwareTools.checkSoftware(input.getSoftware(), 
					input.getSoftware().getAuthentication().getLogin())){
				mdTicketBusiness = new MDTicketBusiness();
				mdTicket = mdTicketBusiness.findById(input.getTicket());
				if (mdTicket != null) {
					if (mdTicket.getIpClient().equals(input.getIpClient())){
						if (mdTicket.getModalitaAccesso().equals("B") ||
								mdTicket.getModalitaAccesso().equals("C")) {
							gc = new GregorianCalendar();
							if (mdTicket.getDataStart().getTime() < gc.getTimeInMillis()) {
								if (mdTicket.getDataEnd().getTime() >= gc.getTimeInMillis()) {
									originalFileName = mdTicket.getOriginalFileName();
									if (originalFileName.toLowerCase().trim().startsWith("http://") ||
											originalFileName.toLowerCase().trim().startsWith("https://")){
										output.setTipo(CheckTicketOutputTipo.WARC);
										url = DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.memorieUrl") +
												originalFileName;
										output.setUrl(url);
									} else if (originalFileName.toLowerCase().trim().endsWith("pdf")){
										output.setTipo(CheckTicketOutputTipo.PDF);
										url = appendUri(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.internalUrl"),
												"idTicket="+input.getTicket());
										output.setUrl(url);
									} else if (originalFileName.toLowerCase().trim().endsWith("epub")){
										output.setTipo(CheckTicketOutputTipo.EBOOK);
										url = appendUri(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.internalUrl"),
												"idTicket="+input.getTicket());
										output.setUrl(url);
									} else {
										errorMsg.add(new ErrorMsg(ErrorType_type.ERROR,"Il ticket [" + 
												input.getTicket() + 
												"] il formato dell'oggetto non è gestito"));
									}
								} else {
									errorMsg.add(new ErrorMsg(ErrorType_type.ERROR,"Il ticket [" + 
											input.getTicket() + 
											"] risulta essere scaduto rifare la richiesta"));
								}
							} else {
								errorMsg.add(new ErrorMsg(ErrorType_type.ERROR,"Il ticket [" + 
										input.getTicket() +
										"] non risulta ancora valido nella data attuale riprovare più tardi"));
							}
						} else {
							errorMsg.add(new ErrorMsg(ErrorType_type.ERROR,"La stazione chiamante del ticket [" + 
									input.getTicket() + 
									"] non corrisponde alla stazione richiedente"));
						}
					} else {
						errorMsg.add(new ErrorMsg(ErrorType_type.ERROR,"Il client ticket [" + input.getTicket() + 
								"] non risulta presente in base dati"));
					}
				} else {
					errorMsg.add(new ErrorMsg(ErrorType_type.ERROR,"Il ticket [" + input.getTicket() + 
							"] non risulta presente in base dati"));
				}
			}
			
			if (errorMsg.size()>0){
				output.setErrorMsg(errorMsg.toArray(new ErrorMsg[errorMsg.size()]));
			}
		} catch (HibernateException e) {
			throw new RemoteException(e.getMessage(),e);
		} catch (HibernateUtilException e) {
			throw new RemoteException(e.getMessage(),e);
		} catch (MDConfigurationException e) {
			throw new RemoteException(e.getMessage(),e);
		} catch (AuthenticationUserLibraryException e) {
			throw new RemoteException(e.getMessage(),e);
		} catch (Exception e) {
			throw new RemoteException(e.getMessage(),e);
		}
		return output;
	}

    private static String appendUri(String uri, String appendQuery) throws AuthenticationUserLibraryException  {
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

			newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
			        oldUri.getPath(), newQuery, oldUri.getFragment());
		} catch (URISyntaxException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}

        return newUri.toString();
    }

}
