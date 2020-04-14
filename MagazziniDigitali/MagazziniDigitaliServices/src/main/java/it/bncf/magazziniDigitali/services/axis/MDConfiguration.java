/**
 * 
 */
package it.bncf.magazziniDigitali.services.axis;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.nodi.MDNodiBusiness;
import it.bncf.magazziniDigitali.businessLogic.rigths.MDRigthsBusiness;
import it.bncf.magazziniDigitali.businessLogic.software.MDSoftwareBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.services.implement.software.AuthenticationSoftware;
import it.depositolegale.www.login.Authentication;
import it.depositolegale.www.nodi.Nodo;
import it.depositolegale.www.software.Software;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.SHA256Tools;

/**
 * @author massi
 *
 */
public class MDConfiguration extends IMDConfiguration<Software> {

	private static Logger log = LogManager.getLogger(MDConfiguration.class);
	
	/**
	 * @throws MDConfigurationException 
	 * 
	 */
	public MDConfiguration(String nomeSW, String fileConfiguration, String ipClient) throws MDConfigurationException {
		super(nomeSW, fileConfiguration, ipClient, null);
	}

	public MDConfiguration(String nomeSW, String fileConfiguration) throws MDConfigurationException {
		super(nomeSW, fileConfiguration);
	}

	/**
	 * 
	 * @see it.bncf.magazziniDigitali.configuration.IMDConfiguration#isSoftwareInizialize()
	 */
	@Override
	protected boolean isSoftwareInizialize() {
		return (software!= null);
	}

	/**
	 * 
	 * @see it.bncf.magazziniDigitali.configuration.IMDConfiguration#readConfiguration()
	 */
	@Override
	protected void readConfiguration(String ipClient) throws MDConfigurationException{
		Authentication authentication = null;
		SHA256Tools sha256Tools = null;

		try {
			sha256Tools = new SHA256Tools();
			authentication = new Authentication();
			authentication.setLogin(
					Configuration.getValue("software."+nomeSW+".login"));
			authentication.setPassword(
					sha256Tools.checkSum(
							Configuration.getValue("software."+nomeSW+".password").getBytes()));
			software = 
					AuthenticationSoftware.AuthenticationSoftwareOperation(authentication, ipClient);
		} catch (NoSuchAlgorithmException e) {
			throw new MDConfigurationException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new MDConfigurationException(e.getMessage(), e);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(), e);
		} catch (RemoteException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @see it.bncf.magazziniDigitali.configuration.IMDConfiguration#getMDSoftware()
	 */
	@Override
	public MDSoftware getMDSoftware() throws MDConfigurationException {
		MDSoftwareBusiness mdSoftwareBusiness = null;
		MDSoftware mdSoftware = null;
		
		try {
			mdSoftwareBusiness = new MDSoftwareBusiness();
			mdSoftware = mdSoftwareBusiness.findById(software.getId());
		} catch (HibernateException e) {
			throw  new MDConfigurationException(e.getMessage(),e);
		} catch (HibernateUtilException e) {
			throw  new MDConfigurationException(e.getMessage(),e);
		}
		return mdSoftware;
	}

	/**
	 * 
	 * @throws MDConfigurationException 
	 * @see it.bncf.magazziniDigitali.configuration.IMDConfiguration#getSoftwareConfig(java.lang.String)
	 */
	@Override
	protected Object getSoftwareConfig(String key) throws MDConfigurationException {
		Object result = null;
		
		if (software!= null){
			if (software.getSoftwareConfig() != null && 
					software.getSoftwareConfig().length>0){
				for (int x=0; x<software.getSoftwareConfig().length; x++){
					if (software.getSoftwareConfig()[x].getNome().equals(key)){
						if (software.getSoftwareConfig()[x].getValue() != null){
							result = software.getSoftwareConfig()[x].getValue();
						} else if (software.getSoftwareConfig()[x].getNodo() != null){
							result = software.getSoftwareConfig()[x].getNodo();
						}
						break;
					}
				}
			}
			if (result== null){
				throw new MDConfigurationException("La chiave di configurazione ["+key+"] non è presente per il Software ["+software.getNome()+"]");
			}
		} else {
			throw new MDConfigurationException("Richiedere la autenticazione del Software");
		}
		return result;
	}

	@Override
	public MDNodi getSoftwareConfigMDNodi(String key) throws MDConfigurationException{
		Object result = null;
		Nodo nodo = null;
		MDNodiBusiness mdNodiBusiness = null;

		try {
			result = getSoftwareConfig(key);
			if (result != null){
				nodo = (Nodo) result;
				mdNodiBusiness = new MDNodiBusiness();
				
				return mdNodiBusiness.findById(nodo.getId());
			}else{
				return null;
			}
		} catch (HibernateException e) {
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (HibernateUtilException e) {
			throw  new MDConfigurationException(e.getMessage(),e);
		}
	}

	@Override
	public MDRigths getMDRigths() throws MDConfigurationException {
		MDRigthsBusiness mdRigthsBusiness = null;
		MDRigths mdRigths = null;
		
		try {
			mdRigthsBusiness = new MDRigthsBusiness();
			
			mdRigths = mdRigthsBusiness.findById(software.getRigth().getId());
		} catch (HibernateException e) {
			throw  new MDConfigurationException(e.getMessage(),e);
		} catch (HibernateUtilException e) {
			throw  new MDConfigurationException(e.getMessage(),e);
		}
		return mdRigths;
	}

	@Override
	public Software getSoftware() {
		return software;
	}

}
