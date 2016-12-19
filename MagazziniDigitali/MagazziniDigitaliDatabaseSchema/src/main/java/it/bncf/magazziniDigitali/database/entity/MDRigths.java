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
@Table(name="MDRigths")
public class MDRigths implements Serializable {

	@Id
	private String id;

	private String nome;

	private MDModalitaAccesso idModalitaAccesso;

	private String idModalitaAccessoID;

	public MDRigths() {
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
	 * @return the idModalitaAccesso
	 */
	public MDModalitaAccesso getIdModalitaAccesso() {
		return idModalitaAccesso;
	}

	/**
	 * @param idModalitaAccesso the idModalitaAccesso to set
	 */
	public void setIdModalitaAccesso(MDModalitaAccesso idModalitaAccesso) {
		this.idModalitaAccesso = idModalitaAccesso;
		if (idModalitaAccesso != null){
			idModalitaAccessoID = idModalitaAccesso.getId();
		}
	}

	/**
	 * @return the idModalitaAccessoID
	 */
	public String getIdModalitaAccessoID() {
		return idModalitaAccessoID;
	}

	/**
	 * @param idModalitaAccessoID the idModalitaAccessoID to set
	 */
	public void setIdModalitaAccessoID(String idModalitaAccessoID) {
		this.idModalitaAccessoID = idModalitaAccessoID;
	}
}
