/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.ReaderTtl;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.ReaderTtlPerson;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.exception.ReaderTtlException;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrObjectFileTtl extends SolrObjectFileEvent {

	/**
	 * 
	 */
	public SolrObjectFileTtl() {
	}

	protected void publicSolrTtl(File fTtl, AddDocumentMD admd) throws SolrException {
		ReaderTtl readerTtl = null;
		ReaderTtlPerson readerTtlPerson = null;
		
		try {
			readerTtl = new ReaderTtl(fTtl);
			params.getParams().clear();
			params.add(ItemMD.ID, objectIdentifier+"-DC");
			params.add(ItemMD._ROOT_, objectIdentifier);
			params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_DOCUMENTO);
			params.add(ItemMD.ORIGINALFILENAME, filename);

			read(ItemMD.BID, readerTtl.getArticle().getBid());
			read(ItemMD.BIDURL, readerTtl.getArticle().getId());
			read(ItemMD.TITOLO, readerTtl.getArticle().getTitolo());

			if (readerTtl.getArticle().getAutore() != null){
				for (int x=0; x<readerTtl.getArticle().getAutore().size(); x++){
					readerTtlPerson = readerTtl.getArticle().getPersons(readerTtl.getArticle().getAutore().get(x));
					read(ItemMD.AUTOREID, readerTtl.getArticle().getAutore().get(x));
					if(readerTtlPerson != null){
						read(ItemMD.AUTORE, readerTtlPerson.getName());
					} else {
						read(ItemMD.AUTORE, readerTtl.getArticle().getAutore().get(x));
					}
				}
			}

			read(ItemMD.PUBBLICAZIONE, readerTtl.getArticle().getPubblicazione());
			read(ItemMD.SOGGETTO, readerTtl.getArticle().getSoggetto());
			read(ItemMD.DESCRIZIONE, readerTtl.getArticle().getDescrizione());
			read(ItemMD.CONTRIBUTO, readerTtl.getArticle().getContributo());
			read(ItemMD.DATA, readerTtl.getArticle().getData());
			read(ItemMD.TIPORISORSA, readerTtl.getArticle().getTipoRisorsa());
			read(ItemMD.FORMATO, readerTtl.getArticle().getFormato());
			read(ItemMD.FONTE, readerTtl.getArticle().getFonte());
			read(ItemMD.LINGUA, readerTtl.getArticle().getLingua());
			read(ItemMD.RELAZIONE, readerTtl.getArticle().getRelazione());
			read(ItemMD.COPERTURA, readerTtl.getArticle().getCopertura());
			read(ItemMD.GESTIONEDIRITTI, readerTtl.getArticle().getGestioneDiritti());

			if (readerTtl.getArticle().getJournal() != null){
				read(ItemMD.JOURNALBID, readerTtl.getArticle().getJournal().getBid());
				read(ItemMD.JOURNALBIDURL, readerTtl.getArticle().getJournal().getId());
				read(ItemMD.JOURNALTITOLO, readerTtl.getArticle().getJournal().getTitolo());
			}
			
			read(ItemMD.PAGESTART, readerTtl.getArticle().getStartPage());
			read(ItemMD.PAGEEND, readerTtl.getArticle().getEndPage());
			
			admd.add(params.getParams(), new ItemMD());
		} catch (FileNotFoundException e) {
			throw new SolrException(e.getMessage(), e);
		} catch (IOException e) {
			throw new SolrException(e.getMessage(), e);
		} catch (ReaderTtlException e) {
			throw new SolrException(e.getMessage(), e);
		} catch (SolrException e) {
			throw e;
		}
	}

}
