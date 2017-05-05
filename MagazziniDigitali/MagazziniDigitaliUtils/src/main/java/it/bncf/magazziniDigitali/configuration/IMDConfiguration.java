/**
 * 
 */
package it.bncf.magazziniDigitali.configuration;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDCompositionLevelDAO;
import it.bncf.magazziniDigitali.database.entity.MDCompositionLevel;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import mx.randalf.configuration.Configuration;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public abstract class IMDConfiguration<S extends Serializable> extends IConfiguration{

	protected String nomeSW = null;

	protected String ipClient = null;

	protected String sysPassword = null;
	
	/**
	 * Variabile utilizzata per la registrazione delle informazioni scaricate dal 
	 * Server di Autenticazione del Software
	 */
	protected S software;

	/**
	 * @throws MDConfigurationException 
	 * 
	 */
	public IMDConfiguration(String nomeSW, String fileConfiguration, String ipClient, String sysPassword) 
			throws MDConfigurationException {
		super(fileConfiguration);
		this.nomeSW = nomeSW;
		this.ipClient = ipClient;
		this.sysPassword = sysPassword;
		initConfig();
	}

	/**
	 * @throws MDConfigurationException 
	 * 
	 */
	public IMDConfiguration(String nomeSW, String fileConfiguration) 
			throws MDConfigurationException {
		super(fileConfiguration);
		this.nomeSW = nomeSW;
		initConfig();
	}

	/**
	 * @throws MDConfigurationException 
	 * 
	 */
	public IMDConfiguration(String nomeSW, String fileConfiguration, String sysPassword) 
			throws MDConfigurationException {
		super(fileConfiguration);
		this.nomeSW = nomeSW;
		this.sysPassword = sysPassword;
		initConfig();
	}

	public String getFileConfiguration() {
		return fileConfiguration;
	}

	public void init() throws MDConfigurationException{
		setFileConfiguration(this.fileConfiguration);
		initConfig();
	}

	private void initConfig() throws MDConfigurationException{
		if (Configuration.isInizialize() &&
				!isSoftwareInizialize()){
			readConfiguration(ipClient);
		}

	}

	protected abstract boolean isSoftwareInizialize();

	protected void readConfiguration() throws MDConfigurationException{
		readConfiguration(null);
	}

	protected abstract void readConfiguration(String ipClient) throws MDConfigurationException;

	public abstract MDSoftware getMDSoftware() throws MDConfigurationException;

	public abstract MDRigths getMDRigths() throws MDConfigurationException;

	protected abstract Object getSoftwareConfig(String key) throws MDConfigurationException;

	public abstract MDNodi getSoftwareConfigMDNodi(String key) throws MDConfigurationException;

	public abstract S getSoftware();
	
	public String getSoftwareConfigString(String key) throws MDConfigurationException{
		Object result = null;

		result = getSoftwareConfig(key);
		if (result != null){
			return (String)result;
		}else{
			return null;
		}
	}

	public Boolean getSoftwareConfigBoolean(String key) throws MDConfigurationException{
		Object result = null;

		result = getSoftwareConfig(key);
		if (result != null){
			return (((String)result).equals("1") ||((String)result).equalsIgnoreCase("true"));
		}else{
			return null;
		}
	}

	public Integer getSoftwareConfigInteger(String key) throws MDConfigurationException{
		Object result = null;

		result = getSoftwareConfig(key);
		if (result != null){
			return new Integer((String)result);
		}else{
			return null;
		}
	}

	public BigInteger getCompositionLevel(String key) throws MDConfigurationException{
		BigInteger result = new BigInteger("0");
		MDCompositionLevelDAO mdCompositionLevelDAO = null;
		List<MDCompositionLevel> mdCompositionLevels = null;
		
		try {
			mdCompositionLevelDAO = new MDCompositionLevelDAO();
			mdCompositionLevels = mdCompositionLevelDAO.find(key, null);
			
			if (mdCompositionLevels != null){
				for (int x=0; x<mdCompositionLevels.size(); x++){
					if (mdCompositionLevels.get(x).getKey().equals(key)){
						result = new BigInteger(mdCompositionLevels.get(x).getValue().toString());
						break;
					}
				}
			}
		} catch (HibernateException e) {
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (HibernateUtilException e) {
			throw new MDConfigurationException(e.getMessage(),e);
		}
		//TODO Da implementare con DB
//	    <entry key="demoni.Validate.compositionLevel[image/jp2]">1</entry>
//	    <entry key="demoni.Validate.compositionLevel[image/jpeg]">1</entry>
//	    <entry key="demoni.Validate.compositionLevel[application/x-gzip]">1</entry>
//	    <entry key="demoni.Validate.compositionLevel[multipart/x-gzip]">1</entry>
//	    <entry key="demoni.Validate.compositionLevel[application/x-compressed]">1</entry>
//	    <entry key="demoni.Validate.compositionLevel[application/x-zip-compressed]">1</entry>
//	    <entry key="demoni.Validate.compositionLevel[application/zip]">1</entry>
//	    <entry key="demoni.Validate.compositionLevel[multipart/x-zip]">1</entry>
		
		return result;
	}
}
