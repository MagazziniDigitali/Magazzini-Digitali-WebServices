/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson;

import java.io.File;
import java.io.FileNotFoundException;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileDC.SolrObjectFileDC;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema.Book;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrObjectFileJson extends SolrObjectFileDC {

	/**
	 * 
	 */
	public SolrObjectFileJson() {
	}

	protected void publicSolrJson(File pathTar, AddDocumentMD admd) throws SolrException {
		File fName = null;
		Book book = null;
		
		try {
			fName = new java.io.File(pathTar.getAbsolutePath());
			if (fName.exists()){
				book = Book.fromJson(fName);
				params.getParams().clear();
				params.add(ItemMD.ID, objectIdentifier+"-DC");
				params.add(ItemMD._ROOT_, objectIdentifier);
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_DOCUMENTO);
				params.add(ItemMD.ORIGINALFILENAME, filename);

				if (book.getTitle() != null) {
					params.add(ItemMD.TITOLO, book.getTitle().trim());
				}
				if (book.getUrl() != null) {
					params.add(ItemMD.URL, book.getUrl().trim());
				}
				if (book.getDescription() != null) {
					params.add(ItemMD.DESCRIZIONE, book.getDescription().trim());
				}
				if (book.getPublisher() != null) {
					params.add(ItemMD.PUBBLICAZIONE, book.getPublisher().trim());
				}
				if (book.getLanguage() != null && book.getLanguage().length > 0) {
					for (int x = 0; x < book.getLanguage().length; x++) {
						params.add(ItemMD.LINGUA, book.getLanguage()[x].trim());
					}
				}
				if (book.getSubject() != null && book.getSubject().length > 0) {
					for (int x = 0; x < book.getSubject().length; x++) {
						params.add(ItemMD.SOGGETTO, book.getSubject()[x].trim());
					}
				}
				if (book.getRights() != null) {
					params.add(ItemMD.RIGHTS, book.getRights().trim());
				}
				if (book.getDate() != null) {
					params.add(ItemMD.DATA, book.getDate());
				}
				if (book.getCreator() != null && book.getCreator().size() > 0) {
					for (int x = 0; x < book.getCreator().size(); x++) {
						params.add(ItemMD.AUTORE, book.getCreator().get(x).getName().trim());
					}
				}
				if (book.getIdentifiers() != null && book.getIdentifiers().size() > 0) {
					for (int x = 0; x < book.getIdentifiers().size(); x++) {
						params.add(ItemMD.BID, book.getIdentifiers().get(x).getID());
					}
				}
				
				admd.add(params.getParams(), new ItemMD());
			}
		} catch (SolrException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw new SolrException(e.getMessage(), e);
		}
	}
}
