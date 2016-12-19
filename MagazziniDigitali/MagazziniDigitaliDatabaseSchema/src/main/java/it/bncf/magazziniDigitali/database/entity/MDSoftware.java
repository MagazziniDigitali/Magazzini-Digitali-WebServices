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
@Table(name="MDSoftware")
public class MDSoftware implements Serializable {

	@Id
	private String id;

	private String nome;

	private String login;

	private String password;

	private String ipAutorizzati;

	private Integer bibliotecaDepositaria;

	private MDIstituzione idIstituzione;
	
	private String idIstituzioneID;

	private MDRigths idRigths;

	private String idRigthsID;

	private String note;
	
	public MDSoftware() {
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
	 * @return the ipAutorizzati
	 */
	public String getIpAutorizzati() {
		return ipAutorizzati;
	}

	/**
	 * @param ipAutorizzati the ipAutorizzati to set
	 */
	public void setIpAutorizzati(String ipAutorizzati) {
		this.ipAutorizzati = ipAutorizzati;
	}

	/**
	 * @return the idIstituzione
	 */
	public MDIstituzione getIdIstituzione() {
		return idIstituzione;
	}

	/**
	 * @param idIstituzione the idIstituzione to set
	 */
	public void setIdIstituzione(MDIstituzione idIstituzione) {
		this.idIstituzione = idIstituzione;
		if (idIstituzione != null){
			idIstituzioneID = idIstituzione.getId();
		}
	}

	/**
	 * @return the idRigths
	 */
	public MDRigths getIdRigths() {
		return idRigths;
	}

	/**
	 * @param idRigths the idRigths to set
	 */
	public void setIdRigths(MDRigths idRigths) {
		this.idRigths = idRigths;
		if (idRigths != null){
			idRigthsID =idRigths.getId();
		}
	}

	/**
	 * @return the idIstituzioneID
	 */
	public String getIdIstituzioneID() {
		return idIstituzioneID;
	}

	/**
	 * @return the idRigthsID
	 */
	public String getIdRigthsID() {
		return idRigthsID;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the bibliotecaDepositaria
	 */
	public Integer getBibliotecaDepositaria() {
		return bibliotecaDepositaria;
	}

	/**
	 * @param bibliotecaDepositaria the bibliotecaDepositaria to set
	 */
	public void setBibliotecaDepositaria(Integer bibliotecaDepositaria) {
		this.bibliotecaDepositaria = bibliotecaDepositaria;
	}
}
