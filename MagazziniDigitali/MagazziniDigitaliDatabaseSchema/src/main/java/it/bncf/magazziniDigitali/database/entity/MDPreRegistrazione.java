/**
 * 
 */
package it.bncf.magazziniDigitali.database.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author massi
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name = "MDPreRegistrazione")
public class MDPreRegistrazione implements Serializable {

	@Id
	private String id;

	private Integer progressivo;

	private Timestamp dataPreIscrizione;

	private String utenteEmail;

	private String utenteNome;

	private String utenteCognome;

	private String utenteCodiceFiscale;

	private String utenteNote;

	private String istituzionePIva;

	private String istituzioneNome;

	private String istituzioneIndirizzo;

	private Regioni idRegione;

	private String istituzioneUrl;
	
	private String istituzioneTelefono;
	
	private String istituzioneNomeContatto;
	
	private String istituzioneNote;

	private Integer altaRisoluzione;

	private Integer tesiDottorato;

	private String rivisteAperte;

	private String rivisteRistrette;

	private String ebookAperte;

	private String ebookRistrette;

	private String altro;

	private Integer emailValidata;

	private MDIstituzione idIstituzione;

	private MDUtenti idUtente;

	private Timestamp dataEmailValidata1;

	private String checkIdFase;

	private Timestamp dataEmailValidata2;

	public MDPreRegistrazione() {
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
	 * @return the dataPreIscrizione
	 */
	public Timestamp getDataPreIscrizione() {
		return dataPreIscrizione;
	}

	/**
	 * @param dataPreIscrizione the dataPreIscrizione to set
	 */
	public void setDataPreIscrizione(Timestamp dataPreIscrizione) {
		this.dataPreIscrizione = dataPreIscrizione;
	}

	/**
	 * @return the utenteNome
	 */
	public String getUtenteNome() {
		return utenteNome;
	}

	/**
	 * @param utenteNome the utenteNome to set
	 */
	public void setUtenteNome(String utenteNome) {
		this.utenteNome = utenteNome;
	}

	/**
	 * @return the utenteCognome
	 */
	public String getUtenteCognome() {
		return utenteCognome;
	}

	/**
	 * @param utenteCognome the utenteCognome to set
	 */
	public void setUtenteCognome(String utenteCognome) {
		this.utenteCognome = utenteCognome;
	}

	/**
	 * @return the utenteCodiceFiscale
	 */
	public String getUtenteCodiceFiscale() {
		return utenteCodiceFiscale;
	}

	/**
	 * @param utenteCodiceFiscale the utenteCodiceFiscale to set
	 */
	public void setUtenteCodiceFiscale(String utenteCodiceFiscale) {
		this.utenteCodiceFiscale = utenteCodiceFiscale;
	}

	/**
	 * @return the utenteNote
	 */
	public String getUtenteNote() {
		return utenteNote;
	}

	/**
	 * @param utenteNote the utenteNote to set
	 */
	public void setUtenteNote(String utenteNote) {
		this.utenteNote = utenteNote;
	}

	/**
	 * @return the istituzionePIva
	 */
	public String getIstituzionePIva() {
		return istituzionePIva;
	}

	/**
	 * @param istituzionePIva the istituzionePIva to set
	 */
	public void setIstituzionePIva(String istituzionePIva) {
		this.istituzionePIva = istituzionePIva;
	}

	/**
	 * @return the istituzioneNome
	 */
	public String getIstituzioneNome() {
		return istituzioneNome;
	}

	/**
	 * @param istituzioneNome the istituzioneNome to set
	 */
	public void setIstituzioneNome(String istituzioneNome) {
		this.istituzioneNome = istituzioneNome;
	}

	/**
	 * @return the istituzioneIndirizzo
	 */
	public String getIstituzioneIndirizzo() {
		return istituzioneIndirizzo;
	}

	/**
	 * @param istituzioneIndirizzo the istituzioneIndirizzo to set
	 */
	public void setIstituzioneIndirizzo(String istituzioneIndirizzo) {
		this.istituzioneIndirizzo = istituzioneIndirizzo;
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
	}

	/**
	 * @return the istituzioneUrl
	 */
	public String getIstituzioneUrl() {
		return istituzioneUrl;
	}

	/**
	 * @param istituzioneUrl the istituzioneUrl to set
	 */
	public void setIstituzioneUrl(String istituzioneUrl) {
		this.istituzioneUrl = istituzioneUrl;
	}

	/**
	 * @return the istituzioneTelefono
	 */
	public String getIstituzioneTelefono() {
		return istituzioneTelefono;
	}

	/**
	 * @param istituzioneTelefono the istituzioneTelefono to set
	 */
	public void setIstituzioneTelefono(String istituzioneTelefono) {
		this.istituzioneTelefono = istituzioneTelefono;
	}

	/**
	 * @return the istituzioneNomeContatto
	 */
	public String getIstituzioneNomeContatto() {
		return istituzioneNomeContatto;
	}

	/**
	 * @param istituzioneNomeContatto the istituzioneNomeContatto to set
	 */
	public void setIstituzioneNomeContatto(String istituzioneNomeContatto) {
		this.istituzioneNomeContatto = istituzioneNomeContatto;
	}

	/**
	 * @return the istituzioneNote
	 */
	public String getIstituzioneNote() {
		return istituzioneNote;
	}

	/**
	 * @param istituzioneNote the istituzioneNote to set
	 */
	public void setIstituzioneNote(String istituzioneNote) {
		this.istituzioneNote = istituzioneNote;
	}

	/**
	 * @return the altaRisoluzione
	 */
	public Integer getAltaRisoluzione() {
		return altaRisoluzione;
	}

	/**
	 * @param altaRisoluzione the altaRisoluzione to set
	 */
	public void setAltaRisoluzione(Integer altaRisoluzione) {
		this.altaRisoluzione = altaRisoluzione;
	}

	/**
	 * @return the tesiDottorato
	 */
	public Integer getTesiDottorato() {
		return tesiDottorato;
	}

	/**
	 * @param tesiDottorato the tesiDottorato to set
	 */
	public void setTesiDottorato(Integer tesiDottorato) {
		this.tesiDottorato = tesiDottorato;
	}

	/**
	 * @return the rivisteAperte
	 */
	public String getRivisteAperte() {
		return rivisteAperte;
	}

	/**
	 * @param rivisteAperte the rivisteAperte to set
	 */
	public void setRivisteAperte(String rivisteAperte) {
		this.rivisteAperte = rivisteAperte;
	}

	/**
	 * @return the rivisteRistrette
	 */
	public String getRivisteRistrette() {
		return rivisteRistrette;
	}

	/**
	 * @param rivisteRistrette the rivisteRistrette to set
	 */
	public void setRivisteRistrette(String rivisteRistrette) {
		this.rivisteRistrette = rivisteRistrette;
	}

	/**
	 * @return the ebookAperte
	 */
	public String getEbookAperte() {
		return ebookAperte;
	}

	/**
	 * @param ebookAperte the ebookAperte to set
	 */
	public void setEbookAperte(String ebookAperte) {
		this.ebookAperte = ebookAperte;
	}

	/**
	 * @return the ebookRistrette
	 */
	public String getEbookRistrette() {
		return ebookRistrette;
	}

	/**
	 * @param ebookRistrette the ebookRistrette to set
	 */
	public void setEbookRistrette(String ebookRistrette) {
		this.ebookRistrette = ebookRistrette;
	}

	/**
	 * @return the altro
	 */
	public String getAltro() {
		return altro;
	}

	/**
	 * @param altro the altro to set
	 */
	public void setAltro(String altro) {
		this.altro = altro;
	}

	/**
	 * @return the emailValidata
	 */
	public Integer getEmailValidata() {
		return emailValidata;
	}

	/**
	 * @param emailValidata the emailValidata to set
	 */
	public void setEmailValidata(Integer emailValidata) {
		this.emailValidata = emailValidata;
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
	}

	/**
	 * @return the idUtente
	 */
	public MDUtenti getIdUtente() {
		return idUtente;
	}

	/**
	 * @param idUtente the idUtente to set
	 */
	public void setIdUtente(MDUtenti idUtente) {
		this.idUtente = idUtente;
	}

	/**
	 * @return the progressivo
	 */
	public Integer getProgressivo() {
		return progressivo;
	}

	/**
	 * @param progressivo the progressivo to set
	 */
	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	/**
	 * @return the utenteEmail
	 */
	public String getUtenteEmail() {
		return utenteEmail;
	}

	/**
	 * @param utenteEmail the utenteEmail to set
	 */
	public void setUtenteEmail(String utenteEmail) {
		this.utenteEmail = utenteEmail;
	}

	/**
	 * @return the dataEmailValidata1
	 */
	public Timestamp getDataEmailValidata1() {
		return dataEmailValidata1;
	}

	/**
	 * @param dataEmailValidata1 the dataEmailValidata1 to set
	 */
	public void setDataEmailValidata1(Timestamp dataEmailValidata1) {
		this.dataEmailValidata1 = dataEmailValidata1;
	}

	/**
	 * @return the checkIdFase
	 */
	public String getCheckIdFase() {
		return checkIdFase;
	}

	/**
	 * @param checkIdFase the checkIdFase to set
	 */
	public void setCheckIdFase(String checkIdFase) {
		this.checkIdFase = checkIdFase;
	}

	/**
	 * @return the dataEmailValidata2
	 */
	public Timestamp getDataEmailValidata2() {
		return dataEmailValidata2;
	}

	/**
	 * @param dataEmailValidata2 the dataEmailValidata2 to set
	 */
	public void setDataEmailValidata2(Timestamp dataEmailValidata2) {
		this.dataEmailValidata2 = dataEmailValidata2;
	}

}
