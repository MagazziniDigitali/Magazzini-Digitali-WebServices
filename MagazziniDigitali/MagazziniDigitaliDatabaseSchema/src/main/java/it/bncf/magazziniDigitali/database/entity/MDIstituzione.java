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
@Table(name = "MDIstituzione")
public class MDIstituzione implements Serializable {

	@Id
	private String id;

	private String login;

	private String password;

	private String ipAutorizzati;

	private String nome;

	private String indirizzo;

	private String telefono;

	private String nomeContatto;

	private Integer bibliotecaDepositaria;
	
	private Integer istitutoCentrale;
	
	private String ipAccreditati;
	
	private String interfacciaApiUtente;

	private String libreriaApiUtente;

	private String emailBagit;

	private String pathTmp;

	private String note;

	private String url;

	private Regioni idRegione;

	private Integer idRegioneID;

	public MDIstituzione() {
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
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the nomeContatto
	 */
	public String getNomeContatto() {
		return nomeContatto;
	}

	/**
	 * @param nomeContatto the nomeContatto to set
	 */
	public void setNomeContatto(String nomeContatto) {
		this.nomeContatto = nomeContatto;
	}

	/**
	 * @return the interfacciaApiUtente
	 */
	public String getInterfacciaApiUtente() {
		return interfacciaApiUtente;
	}

	/**
	 * @param interfacciaApiUtente the interfacciaApiUtente to set
	 */
	public void setInterfacciaApiUtente(String interfacciaApiUtente) {
		this.interfacciaApiUtente = interfacciaApiUtente;
	}

	/**
	 * @return the libreriaApiUtente
	 */
	public String getLibreriaApiUtente() {
		return libreriaApiUtente;
	}

	/**
	 * @param libreriaApiUtente the libreriaApiUtente to set
	 */
	public void setLibreriaApiUtente(String libreriaApiUtente) {
		this.libreriaApiUtente = libreriaApiUtente;
	}

	/**
	 * @return the emailBagit
	 */
	public String getEmailBagit() {
		return emailBagit;
	}

	/**
	 * @param emailBagit the emailBagit to set
	 */
	public void setEmailBagit(String emailBagit) {
		this.emailBagit = emailBagit;
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

	/**
	 * @return the istitutoCentrale
	 */
	public Integer getIstitutoCentrale() {
		return istitutoCentrale;
	}

	/**
	 * @param istitutoCentrale the istitutoCentrale to set
	 */
	public void setIstitutoCentrale(Integer istitutoCentrale) {
		this.istitutoCentrale = istitutoCentrale;
	}

	/**
	 * @return the ipAccreditati
	 */
	public String getIpAccreditati() {
		return ipAccreditati;
	}

	/**
	 * @param ipAccreditati the ipAccreditati to set
	 */
	public void setIpAccreditati(String ipAccreditati) {
		this.ipAccreditati = ipAccreditati;
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
	 * @return the idRegione
	 */
	public Regioni getIdRegione() {
		return idRegione;
	}

	/**
	 * @param idRegione the idRegione to set
	 */
	public void setIdRegione(Regioni idRegione) {
		this.idRegione = idRegione;
		if (this.idRegione != null){
			this.idRegioneID = this.idRegione.getId();
		}
	}

	/**
	 * @return the idRegioneID
	 */
	public Integer getIdRegioneID() {
		return idRegioneID;
	}

	/**
	 * @param idRegioneID the idRegioneID to set
	 */
	public void setIdRegioneID(Integer idRegioneID) {
		this.idRegioneID = idRegioneID;
	}
}
