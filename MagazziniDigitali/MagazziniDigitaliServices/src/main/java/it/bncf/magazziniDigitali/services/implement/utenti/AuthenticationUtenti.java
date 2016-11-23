/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.utenti;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.utenti.MDUtentiBusiness;
import it.bncf.magazziniDigitali.database.entity.MDUtenti;
import it.bncf.magazziniDigitali.services.implement.istituzioni.IstituzioniTools;
import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.bncf.magazziniDigitali.services.implement.tools.ToolsServices;
import it.depositolegale.www.errorMsg.ErrorMsg;
import it.depositolegale.www.errorMsg.ErrorType_type;
import it.depositolegale.www.utenti.DatiUtente;
import it.depositolegale.www.utenti.Utenti;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class AuthenticationUtenti {

	private static Logger log = Logger.getLogger(AuthenticationUtenti.class);

	/**
	 * 
	 */
	public AuthenticationUtenti() {
	}

	public static Utenti AuthenticationUtentiOperation(it.depositolegale.www.loginUtenti.AuthenticationUtenti authentication) throws RemoteException{
		Utenti utenti = null;
		
		MDUtentiBusiness mdUtentiBusiness = null;
		HashTable<String, Object> dati = null;

		List<MDUtenti> mdUtentis = null;

		MDUtenti mdUtenti = null;
		Vector<ErrorMsg> errorMsgs = null;
		
		try {

			errorMsgs = new Vector<ErrorMsg>();

			utenti = new Utenti();
			utenti.setAuthenticationUtenti(authentication);
			mdUtentiBusiness = new MDUtentiBusiness();
			dati = new HashTable<String, Object>();
			dati.put("login", authentication.getAuthentication().getLogin());
			mdUtentis = mdUtentiBusiness.find(dati, 0, 0);
			if (mdUtentis != null){
				if (mdUtentis.size()==1){
					mdUtenti = mdUtentis.get(0);
					if (mdUtenti.getPassword().equals(authentication.getAuthentication().getPassword())){
						if (mdUtenti.getIpAutorizzati()!= null &&
								!mdUtenti.getIpAutorizzati().trim().equals("")){
							if (ToolsServices.testIP(mdUtenti.getIpAutorizzati(), authentication.getIpClient())){
								if (SoftwareTools.checkSoftware(authentication.getSoftware(), 
										authentication.getAuthentication().getLogin())){
									utenti.setDatiUtente(genDatiUtente(mdUtenti));
								} else {
									errorMsgs.add(new ErrorMsg(ErrorType_type.SOFTWARE_ERROR, "Le credenziali del Software non sono valide"));
								}
							} else {
								errorMsgs.add(new ErrorMsg(ErrorType_type.IPERROR, "IP Chiamante non Autorizzato"));
							}
						} else {
							errorMsgs.add(new ErrorMsg(ErrorType_type.IPERROR, "Non risultano IP Autorizzati per questo Software"));
						}
					} else {
						errorMsgs.add(new ErrorMsg(ErrorType_type.PASSWORDERROR, "La Password non è corretta"));
					}
				} else if (mdUtentis.size()>1){
					errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, "Risultano più Software per questo Login"));
				} else {
					errorMsgs.add(new ErrorMsg(ErrorType_type.LOGINERROR, "Il login indicato non è Presente"));
				}
			} else {
				errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, "Problemi del reperire le informazioni"));
			}
			if (errorMsgs.size()>0){
				utenti.setErrorMsg(errorMsgs.toArray(new ErrorMsg[errorMsgs.size()]));
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		}
		return utenti;
	}

	private static DatiUtente genDatiUtente(MDUtenti mdUtenti){
		DatiUtente datiUtente = null;
		
		datiUtente = new DatiUtente();
		datiUtente.setId(mdUtenti.getId());
		datiUtente.setCognome(mdUtenti.getCognome());
		datiUtente.setNome(mdUtenti.getNome());
		datiUtente.setAmministratore(mdUtenti.getAmministratore());

		if (mdUtenti.getIpAutorizzati()!= null){
			datiUtente.setIpAccreditati(mdUtenti.getIpAutorizzati().split(","));
		}

		if (mdUtenti.getIdIstituzione() != null){
			datiUtente.setIstituzione(IstituzioniTools.genIstituzione(mdUtenti.getIdIstituzione()));
		}
		return datiUtente;
	}

}
