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
@Table(name="MDUtenti")
public class MDUtenti implements Serializable {

	@Id
	private String id;

	private String login;

	private String password;

	private String cognome;

	private String nome;

	private Integer amministratore;

	private String ipAutorizzati;

	private MDIstituzione idIstituzione;

	private String idIstituzioneID;

	private String note;
	
	private String codiceFiscale;
	
	private String email;

	private Integer superAdmin;
	
	public MDUtenti() {
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
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	 * @return the amministratore
	 */
	public Integer getAmministratore() {
		return amministratore;
	}

	/**
	 * @param amministratore the amministratore to set
	 */
	public void setAmministratore(Integer amministratore) {
		this.amministratore = amministratore;
	}

	/**
	 * @return the ipAutorizzato
	 */
	public String getIpAutorizzati() {
		return ipAutorizzati;
	}

	/**
	 * @param ipAutorizzato the ipAutorizzato to set
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
		if (this.idIstituzione !=null){
			this.idIstituzioneID = idIstituzione.getId();
		}
	}

	/**
	 * @return the idIstituzioneID
	 */
	public String getIdIstituzioneID() {
		return idIstituzioneID;
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
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the superAdmin
	 */
	public Integer getSuperAdmin() {
		return superAdmin;
	}

	/**
	 * @param superAdmin the superAdmin to set
	 */
	public void setSuperAdmin(Integer superAdmin) {
		this.superAdmin = superAdmin;
	}
}
