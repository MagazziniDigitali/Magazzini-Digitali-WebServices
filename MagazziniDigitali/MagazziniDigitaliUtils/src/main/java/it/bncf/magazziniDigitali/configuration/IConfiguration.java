/**
 * 
 */
package it.bncf.magazziniDigitali.configuration;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

/**
 * @author massi
 *
 */
public class IConfiguration {

	private Logger log = LogManager.getLogger(IConfiguration.class);

	protected String fileConfiguration = null;

	protected String pathProperties = null;

	/**
	 * @throws MDConfigurationException 
	 * 
	 */
	public IConfiguration(String fileConfiguration) throws MDConfigurationException {
		setFileConfiguration(fileConfiguration);
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
			
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(), e);
		}
	}

	public Integer getConfigInteger(String key) throws MDConfigurationException {
		Integer value = null;
		
		try {
			if (Configuration.getValue(key) != null) {
				value = new Integer(Configuration.getValue(key));
			}
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(), e);
		}

		return value;
	}

	public boolean getConfigBoolean(String key) throws MDConfigurationException {
		boolean value = false;
		
		try {
			value = new Boolean(Configuration.getValue(key));
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(), e);
		}

		return value;
	}

}
