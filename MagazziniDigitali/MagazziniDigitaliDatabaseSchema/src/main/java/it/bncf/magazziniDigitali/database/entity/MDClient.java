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
@Table(name="MDFilesTmp")
public class MDClient implements Serializable {

	@Id
	private String id;

	private String ipClient;

	private String passwordClient;

	public MDClient() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the ipClient
	 */
	public String getIpClient() {
		return ipClient;
	}

	/**
	 * @param ipClient the ipClient to set
	 */
	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}

	/**
	 * @return the passwordClient
	 */
	public String getPasswordClient() {
		return passwordClient;
	}

	/**
	 * @param passwordClient the passwordClient to set
	 */
	public void setPasswordClient(String passwordClient) {
		this.passwordClient = passwordClient;
	}

}
