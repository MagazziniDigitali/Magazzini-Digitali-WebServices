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
@Table(name = "MDSoftwareConfig")
public class MDSoftwareConfig implements Serializable {

	@Id
	private String id;

	private MDSoftware idSoftware;

	private String idSoftwareID;

	private String nome;

	private String descrizione;

	private String value;

	private MDNodi idNodo;

	private String idNodoID;

	public MDSoftwareConfig() {
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the idIstituzione
	 */
	public MDSoftware getIdSoftware() {
		return idSoftware;
	}

	/**
	 * @param idIstituzione
	 *            the idIstituzione to set
	 */
	public void setIdSoftware(MDSoftware idSoftware) {
		this.idSoftware = idSoftware;
		if (this.idSoftware != null) {
			this.idSoftwareID = idSoftware.getId();
		}
	}

	/**
	 * @return the idIstituzioneID
	 */
	public String getIdSoftwareID() {
		return idSoftwareID;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the idNodo
	 */
	public MDNodi getIdNodo() {
		return idNodo;
	}


	/**
	 * @param idNodo
	 *            the idNodo to set
	 */
	public void setIdNodo(MDNodi idNodo) {
		this.idNodo = idNodo;
		if (idNodo != null) {
			this.idNodoID = idNodo.getId();
		}
	}


	/**
	 * @return the idNodoID
	 */
	public String getIdNodoID() {
		return idNodoID;
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
}
