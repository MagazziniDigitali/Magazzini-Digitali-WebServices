/**
 * 
 */
package it.bncf.magazziniDigitali.database.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author massi
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name="MDIstituzione")
public class MDIstituzione implements Serializable {

	@Id
	private String id;

	private String nome;

	private String pathTmp;

	private String pathTar;

	private String password;

	private String url;

	private String urlLogo;

	private String uuid;

	private String machineUuid;

	private String softwareUuid;

	private String rightUuid;

	private String ipAuthentication;

	private String ipDownload;

	public MDIstituzione() {
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the pathTmp
	 */
	public String getPathTmp() {
		return pathTmp;
	}

	/**
	 * @param pathTmp the pathTmp to set
	 */
	public void setPathTmp(String pathTmp) {
		this.pathTmp = pathTmp;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the urlLogo
	 */
	public String getUrlLogo() {
		return urlLogo;
	}

	/**
	 * @param urlLogo the urlLogo to set
	 */
	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the machineUuid
	 */
	public String getMachineUuid() {
		return machineUuid;
	}

	/**
	 * @param machineUuid the machineUuid to set
	 */
	public void setMachineUuid(String machineUuid) {
		this.machineUuid = machineUuid;
	}

	/**
	 * @return the softwareUuid
	 */
	public String getSoftwareUuid() {
		return softwareUuid;
	}

	/**
	 * @param softwareUuid the softwareUuid to set
	 */
	public void setSoftwareUuid(String softwareUuid) {
		this.softwareUuid = softwareUuid;
	}

	/**
	 * @return the rightUuid
	 */
	public String getRightUuid() {
		return rightUuid;
	}

	/**
	 * @param rightUuid the rightUuid to set
	 */
	public void setRightUuid(String rightUuid) {
		this.rightUuid = rightUuid;
	}

	/**
	 * @return the ipAuthentication
	 */
	public String getIpAuthentication() {
		return ipAuthentication;
	}

	/**
	 * @param ipAuthentication the ipAuthentication to set
	 */
	public void setIpAuthentication(String ipAuthentication) {
		this.ipAuthentication = ipAuthentication;
	}

	/**
	 * @return the ipDownload
	 */
	public String getIpDownload() {
		return ipDownload;
	}

	/**
	 * @param ipDownload the ipDownload to set
	 */
	public void setIpDownload(String ipDownload) {
		this.ipDownload = ipDownload;
	}

	/**
	 * @return the pathTar
	 */
	public String getPathTar() {
		return pathTar;
	}

	/**
	 * @param pathTar the pathTar to set
	 */
	public void setPathTar(String pathTar) {
		this.pathTar = pathTar;
	}
}
