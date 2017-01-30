/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl;

import java.util.Vector;

/**
 * @author massi
 *
 */
public class ReaderTtlJournal extends ReaderTtlAnalyze{
	
	private String endPage = null;
	
	private String startPage = null;
	
	private String bid = null;
	
	private Vector<String> titolo = null;
	
	private Vector<String> autore = null;
	
	private Vector<String> pubblicazione = null;
	
	private Vector<String> soggetto = null;
	
	private Vector<String> descrizione = null;
	
	private Vector<String> contributo = null;
	
	private Vector<String> data = null;
	
	private Vector<String> tipoRisorsa = null;
	
	private Vector<String> formato = null;
	
	private Vector<String> fonte = null;
	
	private Vector<String> lingua = null;
	
	private Vector<String> relazione = null;
	
	private Vector<String> copertura = null;
	
	private Vector<String> gestioneDiritti = null;

	/**
	 * 
	 */
	public ReaderTtlJournal(){
	}
	
	public void analyze (String id , Vector<String> lines) {
		this.id = id;
		for (int x=0; x<lines.size(); x++){
			if (lines.get(x).startsWith("prism:endingPage")){
				endPage = analyze(lines.get(x));
			} else if (lines.get(x).startsWith("prism:startingPage")){
				startPage = analyze(lines.get(x));
			} else if (lines.get(x).startsWith("dc:identifier")){
				bid = analyze(lines.get(x));
			} else if (lines.get(x).startsWith("dc:title")){
				if (titolo == null){
					titolo = new Vector<String>();
				}
				titolo.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:creator")){
				if (autore == null){
					autore = new Vector<String>();
				}
				autore.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:publisher")){
				if (pubblicazione == null){
					pubblicazione = new Vector<String>();
				}
				pubblicazione.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:subject")){
				if (soggetto == null){
					soggetto = new Vector<String>();
				}
				soggetto.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:description")){
				if (descrizione == null){
					descrizione = new Vector<String>();
				}
				descrizione.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:contributor")){
				if (contributo == null){
					contributo = new Vector<String>();
				}
				contributo.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:date")){
				if (data == null){
					data = new Vector<String>();
				}
				data.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:type")){
				if (tipoRisorsa == null){
					tipoRisorsa = new Vector<String>();
				}
				tipoRisorsa.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:format")){
				if (formato == null){
					formato = new Vector<String>();
				}
				formato.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:source")){
				if (fonte == null){
					fonte = new Vector<String>();
				}
				fonte.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:language")){
				if (lingua == null){
					lingua = new Vector<String>();
				}
				lingua.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:relation")){
				if (relazione == null){
					relazione = new Vector<String>();
				}
				relazione.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:coverage")){
				if (copertura == null){
					copertura = new Vector<String>();
				}
				copertura.add(analyze(lines.get(x)));
			} else if (lines.get(x).startsWith("dc:rights")){
				if (gestioneDiritti == null){
					gestioneDiritti = new Vector<String>();
				}
				gestioneDiritti.add(analyze(lines.get(x)));
			}
		}
	}

	/**
	 * @return the endPage
	 */
	public String getEndPage() {
		return endPage;
	}

	/**
	 * @return the startPage
	 */
	public String getStartPage() {
		return startPage;
	}

	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * @return the titolo
	 */
	public Vector<String> getTitolo() {
		return titolo;
	}

	/**
	 * @return the autore
	 */
	public Vector<String> getAutore() {
		return autore;
	}

	/**
	 * @return the pubblicazione
	 */
	public Vector<String> getPubblicazione() {
		return pubblicazione;
	}

	/**
	 * @return the soggetto
	 */
	public Vector<String> getSoggetto() {
		return soggetto;
	}

	/**
	 * @return the descrizione
	 */
	public Vector<String> getDescrizione() {
		return descrizione;
	}

	/**
	 * @return the contributo
	 */
	public Vector<String> getContributo() {
		return contributo;
	}

	/**
	 * @return the data
	 */
	public Vector<String> getData() {
		return data;
	}

	/**
	 * @return the tipoRisorsa
	 */
	public Vector<String> getTipoRisorsa() {
		return tipoRisorsa;
	}

	/**
	 * @return the formato
	 */
	public Vector<String> getFormato() {
		return formato;
	}

	/**
	 * @return the fonte
	 */
	public Vector<String> getFonte() {
		return fonte;
	}

	/**
	 * @return the lingua
	 */
	public Vector<String> getLingua() {
		return lingua;
	}

	/**
	 * @return the relazione
	 */
	public Vector<String> getRelazione() {
		return relazione;
	}

	/**
	 * @return the copertura
	 */
	public Vector<String> getCopertura() {
		return copertura;
	}

	/**
	 * @return the gestioneDiritti
	 */
	public Vector<String> getGestioneDiritti() {
		return gestioneDiritti;
	}
	
}
