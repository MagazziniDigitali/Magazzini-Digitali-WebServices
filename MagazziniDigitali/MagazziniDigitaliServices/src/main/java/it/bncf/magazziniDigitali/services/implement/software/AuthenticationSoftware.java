/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.software;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.software.MDSoftwareBusiness;
import it.bncf.magazziniDigitali.businessLogic.software.MDSoftwareConfigBusiness;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.database.entity.MDSoftwareConfig;
import it.bncf.magazziniDigitali.services.implement.istituzioni.IstituzioniTools;
import it.bncf.magazziniDigitali.services.implement.software.exception.AuthenticationSoftwareException;
import it.bncf.magazziniDigitali.services.implement.tools.ToolsServices;
import it.depositolegale.www.errorMsg.ErrorMsg;
import it.depositolegale.www.errorMsg.ErrorType_type;
import it.depositolegale.www.login.Authentication;
import it.depositolegale.www.nodi.Nodo;
import it.depositolegale.www.nodi.NodoStorage;
import it.depositolegale.www.nodi.NodoStorageRsync;
import it.depositolegale.www.nodi.NodoStorageS3;
import it.depositolegale.www.nodi.NodoStorageTipo;
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
	
	private static Logger log = LogManager.getLogger(AuthenticationSoftware.class);

	/**
	 * 
	 */
	public AuthenticationSoftware() {
	}

	public static Software AuthenticationSoftwareOperation(Authentication authentication) throws RemoteException{
		return AuthenticationSoftwareOperation(authentication, null);
	}

	public static Software AuthenticationSoftwareOperation(Authentication authentication, String ipClient) throws RemoteException{
		Software software = null;
		MDSoftwareBusiness mdSoftwareBusiness = null;
		HashTable<String, Object> dati = null;
		List<MDSoftware> mdSoftwares = null;
		MDSoftware mdSoftware = null;
		Vector<ErrorMsg> errorMsgs = null;
		boolean testIp = false;
		
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
							if (ipClient==null){
								testIp = ToolsServices.testIP(mdSoftware.getIpAutorizzati());
							} else {
								testIp = ToolsServices.testIP(mdSoftware.getIpAutorizzati(), ipClient);
							}
							if (testIp){
								software.setId(mdSoftware.getId());
								software.setNome(mdSoftware.getNome());
								software.setIstituzione(IstituzioniTools.genIstituzione(mdSoftware.getIdIstituzione()));
								software.setRigth(genRigth(mdSoftware.getIdRigths()));
								software.setSoftwareConfig(genSoftwareConfigs(mdSoftware));
							} else {
								log.error("\n"+"Al software ["+
										authentication.getLogin()+
										"] è stato negato l'accesso dall'IP ["+
										(ipClient==null?ToolsServices.getRemoteIP():ipClient)+
										"]");
								errorMsgs.add(new ErrorMsg(ErrorType_type.IPERROR, "Al software ["+
										authentication.getLogin()+
										"] è stato negato l'accesso dall'IP ["+
										(ipClient==null?ToolsServices.getRemoteIP():ipClient)+
										"]"));
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
					errorMsgs.add(new ErrorMsg(ErrorType_type.LOGINERROR, "Il login ["+authentication.getLogin()+"] indicato non è Presente"));
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
		} catch (AuthenticationSoftwareException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
		}
		return software;
	}

	private static SoftwareConfig[] genSoftwareConfigs(MDSoftware mdSoftware) throws HibernateException, HibernateUtilException, AuthenticationSoftwareException{
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
		} catch (AuthenticationSoftwareException e) {
			throw e;
		}
		return (softwareConfigs!= null?softwareConfigs.toArray(new SoftwareConfig[softwareConfigs.size()]):null);
	}

	private static Nodo genNodo(MDNodi mdNodi) throws AuthenticationSoftwareException{
		Nodo nodo = null;
		if (mdNodi != null){
			nodo = new Nodo();
			nodo.setId(mdNodi.getId());
			nodo.setNome(mdNodi.getNome());
			nodo.setDescrizioni(mdNodi.getDescrizione());
			nodo.setStorage(genNodoStorage(mdNodi));
		}
		return nodo;
	}

	private static NodoStorage genNodoStorage(MDNodi mdNodi) throws AuthenticationSoftwareException {
		NodoStorage nodoStorage = null;
		nodoStorage = new NodoStorage();

		switch (mdNodi.getTipo()) {
			case "F":
				nodoStorage.setFileSystem(mdNodi.getPathStorage());
				break;

			case "M":
				nodoStorage.setRsync(genNodoStorageRsync(mdNodi));
				break;

			case "S":
				nodoStorage.setS3(genNodoStorageS3(mdNodi));
				break;
	
			default:
				throw new AuthenticationSoftwareException("La tipologia di Nodo ["+
							mdNodi.getTipo()+"] non risulta essere gestita");
		}
		nodoStorage.setTipo(NodoStorageTipo.fromValue(mdNodi.getTipo()));
		return nodoStorage;
	}

	private static NodoStorageS3 genNodoStorageS3(MDNodi mdNodi) {
		NodoStorageS3 nodoStorageS3 = null;
		
		nodoStorageS3 = new NodoStorageS3();
		
		nodoStorageS3.setUrlS3(mdNodi.getS3Url());
		nodoStorageS3.setRegion(mdNodi.getS3Region());
		nodoStorageS3.setAccessKey(mdNodi.getS3AccessKey());
		nodoStorageS3.setSecretKey(mdNodi.getS3SecretKey());
		nodoStorageS3.setBucketName(mdNodi.getS3BucketName());
		return nodoStorageS3;
	}

	private static NodoStorageRsync genNodoStorageRsync(MDNodi mdNodi) throws AuthenticationSoftwareException {
		NodoStorageRsync nodoStorageRsync = null;

		try {
			nodoStorageRsync = new NodoStorageRsync();
			nodoStorageRsync.setUrlRsync(mdNodi.getRsync());
			nodoStorageRsync.setPassword(mdNodi.getRsyncPassword());
			nodoStorageRsync.setUrlCheckStorage(new URI(mdNodi.getUrlCheckStorage()));
		} catch (MalformedURIException e) {
			throw new AuthenticationSoftwareException(e.getMessage(),e);
		}
		return null;
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
