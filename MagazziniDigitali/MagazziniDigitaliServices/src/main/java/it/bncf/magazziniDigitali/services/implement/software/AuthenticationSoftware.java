/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.software;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.software.MDSoftwareBusiness;
import it.bncf.magazziniDigitali.businessLogic.software.MDSoftwareConfigBusiness;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.database.entity.MDSoftwareConfig;
import it.bncf.magazziniDigitali.services.implement.istituzioni.IstituzioniTools;
import it.bncf.magazziniDigitali.services.implement.tools.ToolsServices;
import it.depositolegale.www.errorMsg.ErrorMsg;
import it.depositolegale.www.errorMsg.ErrorType_type;
import it.depositolegale.www.login.Authentication;
import it.depositolegale.www.nodi.Nodo;
import it.depositolegale.www.nodi.NodoRsync;
import it.depositolegale.www.rigths.RightType_type;
import it.depositolegale.www.rigths.Rigth;
import it.depositolegale.www.software.Software;
import it.depositolegale.www.software.SoftwareConfig;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class AuthenticationSoftware {
	
	private static Logger log = Logger.getLogger(AuthenticationSoftware.class);

	/**
	 * 
	 */
	public AuthenticationSoftware() {
	}

	public static Software AuthenticationSoftwareOperation(Authentication authentication) throws RemoteException{
		Software software = null;
		MDSoftwareBusiness mdSoftwareBusiness = null;
		HashTable<String, Object> dati = null;
		List<MDSoftware> mdSoftwares = null;
		MDSoftware mdSoftware = null;
		Vector<ErrorMsg> errorMsgs = null;
		
		try {

			errorMsgs = new Vector<ErrorMsg>();

			software = new Software();
			software.setAuthentication(authentication);
			mdSoftwareBusiness = new MDSoftwareBusiness();
			dati = new HashTable<String, Object>();
			dati.put("login", authentication.getLogin());
			mdSoftwares = mdSoftwareBusiness.find(dati, 0, 0);
			if (mdSoftwares != null){
				if (mdSoftwares.size()==1){
					mdSoftware = mdSoftwares.get(0);
					if (mdSoftware.getPassword().equals(authentication.getPassword())){
						if (mdSoftware.getIpAutorizzati()!= null &&
								!mdSoftware.getIpAutorizzati().trim().equals("")){
							if (ToolsServices.testIP(mdSoftware.getIpAutorizzati())){
								software.setId(mdSoftware.getId());
								software.setNome(mdSoftware.getNome());
								software.setIstituzione(IstituzioniTools.genIstituzione(mdSoftware.getIdIstituzione()));
								software.setRigth(genRigth(mdSoftware.getIdRigths()));
								software.setSoftwareConfig(genSoftwareConfigs(mdSoftware));
							} else {
								log.error("Al software ["+
										authentication.getLogin()+
										"] è stato negato l'accesso dall'IP ["+
										ToolsServices.getRemoteIP()+
										"]");
								errorMsgs.add(new ErrorMsg(ErrorType_type.IPERROR, "IP Chiamante non Autorizzato"));
							}
						} else {
							errorMsgs.add(new ErrorMsg(ErrorType_type.IPERROR, "Non risultano IP Autorizzati per questo Software"));
						}
					} else {
						errorMsgs.add(new ErrorMsg(ErrorType_type.PASSWORDERROR, "La Password non è corretta"));
					}
				} else if (mdSoftwares.size()>1){
					errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, "Risultano più Software per questo Login"));
				} else {
					errorMsgs.add(new ErrorMsg(ErrorType_type.LOGINERROR, "Il login indicato non è Presente"));
				}
			} else {
				errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, "Problemi del reperire le informazioni"));
			}
			if (errorMsgs.size()>0){
				software.setErrorMsg(errorMsgs.toArray(new ErrorMsg[errorMsgs.size()]));
			}
		} catch (HibernateException e) {
			throw new RemoteException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new RemoteException(e.getMessage(), e);
		}
		return software;
	}

	private static SoftwareConfig[] genSoftwareConfigs(MDSoftware mdSoftware) throws HibernateException, HibernateUtilException{
		Vector<SoftwareConfig> softwareConfigs = null;
		MDSoftwareConfigBusiness mdSoftwareConfigBusiness = null;
		HashTable<String , Object> dati = null;
		List<MDSoftwareConfig> mdSoftwareConfigs = null;
		SoftwareConfig softwareConfig = null;
		
		try {
			mdSoftwareConfigBusiness = new MDSoftwareConfigBusiness();
			dati = new HashTable<String, Object>();
			dati.put("idSoftware", mdSoftware);
			mdSoftwareConfigs = mdSoftwareConfigBusiness.find(dati, 0, 0);
			
			if (mdSoftwareConfigs != null &&
					mdSoftwareConfigs.size()>0){
				softwareConfigs = new Vector<SoftwareConfig>();
				for (int x=0; x<mdSoftwareConfigs.size(); x++){
					softwareConfig = new SoftwareConfig();
					softwareConfig.setId(mdSoftwareConfigs.get(x).getId());
					softwareConfig.setNome(mdSoftwareConfigs.get(x).getNome());
					softwareConfig.setDescrizione(mdSoftwareConfigs.get(x).getDescrizione());
					if (mdSoftwareConfigs.get(x).getValue()!= null && 
							!mdSoftwareConfigs.get(x).getValue().trim().equals("")){
						softwareConfig.setValue(mdSoftwareConfigs.get(x).getValue());
					} else {
						softwareConfig.setNodo(genNodo(mdSoftwareConfigs.get(x).getIdNodo()));
					}
					softwareConfigs.add(softwareConfig);	
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}
		return (softwareConfigs!= null?softwareConfigs.toArray(new SoftwareConfig[softwareConfigs.size()]):null);
	}

	private static Nodo genNodo(MDNodi mdNodi){
		Nodo nodo = null;
		if (mdNodi != null){
			nodo = new Nodo();
			nodo.setId(mdNodi.getId());
			nodo.setNome(mdNodi.getNome());
			nodo.setDescrizioni(mdNodi.getDescrizione());
			nodo.setRsync(new NodoRsync(mdNodi.getRsync(), mdNodi.getRsyncPassword()));
			if (mdNodi.getUrlCheckStorage()!= null){
				try {
					nodo.setUrlCheckStorage(new URI(mdNodi.getUrlCheckStorage()));
				} catch (MalformedURIException e) {
					e.printStackTrace();
				}
			}
		}
		return nodo;
	}
	private static Rigth genRigth(MDRigths mdRigths){
		Rigth rigth = null;
		
		if (mdRigths != null){
			rigth = new Rigth();
			rigth.setId(mdRigths.getId());
			rigth.setNome(mdRigths.getNome());
			rigth.setType(RightType_type.fromValue(mdRigths.getIdModalitaAccesso().getId()));
		}
		return rigth;
	}
}
