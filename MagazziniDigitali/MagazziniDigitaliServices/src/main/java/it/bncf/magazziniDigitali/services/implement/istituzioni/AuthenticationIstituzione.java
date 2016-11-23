/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.istituzioni;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.istituzione.MDIstituzioneBusiness;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.bncf.magazziniDigitali.services.implement.tools.ToolsServices;
import it.depositolegale.www.errorMsg.ErrorMsg;
import it.depositolegale.www.errorMsg.ErrorType_type;
import it.depositolegale.www.istituzione.MdIstituzione;
import it.depositolegale.www.loginUtenti.AuthenticationUtenti;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class AuthenticationIstituzione {
	
	private static Logger log = Logger.getLogger(AuthenticationIstituzione.class);

	/**
	 * 
	 */
	public AuthenticationIstituzione() {
	}

	public static MdIstituzione AuthenticationSoftwareOperation(AuthenticationUtenti authenticationUtenti) throws RemoteException{
		MdIstituzione output = null;
		MDIstituzioneBusiness mdIstituzioneBusiness = null;
		HashTable<String, Object> dati = null;
		List<MDIstituzione> mdIstituziones = null;
		MDIstituzione mdIstituzione = null;
		Vector<ErrorMsg> errorMsgs = null;
		
		try {

			errorMsgs = new Vector<ErrorMsg>();

			output = new MdIstituzione();
			output.setAuthenticationUtenti(authenticationUtenti);
			mdIstituzioneBusiness = new MDIstituzioneBusiness();
			dati = new HashTable<String, Object>();
			dati.put("login", authenticationUtenti.getAuthentication().getLogin());
			mdIstituziones = mdIstituzioneBusiness.find(dati, 0, 0);
			if (mdIstituziones != null){
				if (mdIstituziones.size()==1){
					mdIstituzione = mdIstituziones.get(0);
					if (mdIstituzione.getPassword().equals(authenticationUtenti.getAuthentication().getPassword())){
						if (mdIstituzione.getIpAutorizzati()!= null &&
								!mdIstituzione.getIpAutorizzati().trim().equals("")){
							if (ToolsServices.testIP(mdIstituzione.getIpAutorizzati())){
								if (SoftwareTools.checkSoftware(authenticationUtenti.getSoftware(), 
										authenticationUtenti.getAuthentication().getLogin())){
									output.setIstituzione(IstituzioniTools.genIstituzione(mdIstituzione));
								} else {
									errorMsgs.add(new ErrorMsg(ErrorType_type.SOFTWARE_ERROR, "Le credenziali del Software non sono valide"));
								}
							} else {
								log.error("Al software ["+
										authenticationUtenti.getAuthentication().getLogin()+
										"] è stato negato l'accesso dall'IP ["+
										ToolsServices.getRemoteIP()+
										"]");
								errorMsgs.add(new ErrorMsg(ErrorType_type.IPERROR, "IP Chiamante del Software non Autorizzato"));
							}
						} else {
							errorMsgs.add(new ErrorMsg(ErrorType_type.IPERROR, "Non risultano IP Autorizzati per questa Istituzione"));
						}
					} else {
						errorMsgs.add(new ErrorMsg(ErrorType_type.PASSWORDERROR, "La Password non è corretta"));
					}
				} else if (mdIstituziones.size()>1){
					errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, "Risultano più Istituzioni per questo Login"));
				} else {
					errorMsgs.add(new ErrorMsg(ErrorType_type.LOGINERROR, "Il login indicato non è Presente"));
				}
			} else {
				errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, "Problemi del reperire le informazioni"));
			}
			if (errorMsgs.size()>0){
				output.setErrorMsg(errorMsgs.toArray(new ErrorMsg[errorMsgs.size()]));
			}
		} catch (HibernateException e) {
			throw new RemoteException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new RemoteException(e.getMessage(), e);
		}
		return output;
	}
}
