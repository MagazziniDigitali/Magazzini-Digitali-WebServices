/**
 * 
 */
package it.bncf.magazziniDigitali.configuration;

import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDCompositionLevelDAO;
import it.bncf.magazziniDigitali.database.entity.MDCompositionLevel;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public abstract class IMDConfiguration<S extends Serializable> {

	private Logger log = Logger.getLogger(IMDConfiguration.class);

	private String fileConfiguration = null;

	protected String nomeSW = null;

	protected String sysPassword = null;

	protected String pathProperties = null;
	
	/**
	 * Variabile utilizzata per la registrazione delle informazioni scaricate dal 
	 * Server di Autenticazione del Software
	 */
	protected S software;

	/**
	 * @throws MDConfigurationException 
	 * 
	 */
	public IMDConfiguration(String nomeSW, String fileConfiguration) 
			throws MDConfigurationException {
		this.nomeSW = nomeSW;
		setFileConfiguration(fileConfiguration);
	}

	/**
	 * @throws MDConfigurationException 
	 * 
	 */
	public IMDConfiguration(String nomeSW, String fileConfiguration, String sysPassword) 
			throws MDConfigurationException {
		this.nomeSW = nomeSW;
		this.sysPassword = sysPassword;
		setFileConfiguration(fileConfiguration);
	}

	public String getFileConfiguration() {
		return fileConfiguration;
	}

	public void init() throws MDConfigurationException{
		setFileConfiguration(this.fileConfiguration);
	}

	public void setFileConfiguration(String fileConfiguration) 
			throws MDConfigurationException {
		String pathProperties = null;
		String[] st = null;
		File path = null;
		try {
			this.fileConfiguration = fileConfiguration;
			
			if (!Configuration.isInizialize()) {
				st = fileConfiguration.split("\\|");
				for (int x=0; x<st.length; x++){
					fileConfiguration = st[x];

					pathProperties = null;
					if (fileConfiguration != null && fileConfiguration.startsWith("file://"))
						pathProperties = fileConfiguration.replace("file:///", "");
					else {
						if (System.getProperty("catalina.base") != null){
							pathProperties = System.getProperty("catalina.base")
									+ File.separator;
							if (fileConfiguration == null)
								pathProperties += "conf/teca_digitale";
							else
								pathProperties += fileConfiguration;
						}
					}
	
					if (pathProperties != null){
						path = new File(pathProperties);
						if (path.exists()){
							Configuration.init(path.getAbsolutePath());
							this.pathProperties = path.getAbsolutePath();
							break;
						}
					}
				}
			}
			
			if (Configuration.isInizialize() &&
					!isSoftwareInizialize()){
				readConfiguration();
			}
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			throw e;
		}
	}

	protected abstract boolean isSoftwareInizialize();

	protected abstract void readConfiguration() throws MDConfigurationException;

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
