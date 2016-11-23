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
@Table(name="MDNodi")
public class MDNodi implements Serializable {

	@Id
	private String id;

	private String nome;

	private String descrizione;

	private String rsync;

	private String rsyncPassword;

	private String urlCheckStorage;

	private String pathStorage;

	private Integer active;

	public MDNodi() {
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
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the rsync
	 */
	public String getRsync() {
		return rsync;
	}

	/**
	 * @param rsync the rsync to set
	 */
	public void setRsync(String rsync) {
		this.rsync = rsync;
	}

	/**
	 * @return the rsyncPassword
	 */
	public String getRsyncPassword() {
		return rsyncPassword;
	}

	/**
	 * @param rsyncPassword the rsyncPassword to set
	 */
	public void setRsyncPassword(String rsyncPassword) {
		this.rsyncPassword = rsyncPassword;
	}

	/**
	 * @return the urlCheckStorage
	 */
	public String getUrlCheckStorage() {
		return urlCheckStorage;
	}

	/**
	 * @param urlCheckStorage the urlCheckStorage to set
	 */
	public void setUrlCheckStorage(String urlCheckStorage) {
		this.urlCheckStorage = urlCheckStorage;
	}

	/**
	 * @return the pathStorage
	 */
	public String getPathStorage() {
		return pathStorage;
	}

	/**
	 * @param pathStorage the pathStorage to set
	 */
	public void setPathStorage(String pathStorage) {
		this.pathStorage = pathStorage;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}

}
