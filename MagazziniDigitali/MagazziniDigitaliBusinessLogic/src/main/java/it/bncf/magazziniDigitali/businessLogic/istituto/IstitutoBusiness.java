/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.istituto;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;


/**
 * @author massi
 *
 */
public class IstitutoBusiness {

	private String idIstituto = null;

	private boolean configurata=false;

	private String nome = null;

	private String pathTmp = null;

	private String password = null;

	/**
	 * @throws ConfigurationException 
	 * 
	 */
	public IstitutoBusiness(String idIstituto) throws ConfigurationException {
		this.idIstituto = idIstituto;

		if (Configuration.getValue("istituto."+idIstituto+".nome")!= null){
			configurata=true;
			nome =  Configuration.getValue("istituto."+idIstituto+".nome");
			pathTmp =  Configuration.getValue("istituto."+idIstituto+".pathTmp");
			password =  Configuration.getValue("istituto."+idIstituto+".password");
		}
	}

	public boolean isConfigurata() {
		return configurata;
	}

	public String getIdIstituto() {
		return idIstituto;
	}

	public String getNome() {
		return nome;
	}

	public String getPathTmp() {
		return pathTmp;
	}

	public String getPassword() {
		return password;
	}
}
