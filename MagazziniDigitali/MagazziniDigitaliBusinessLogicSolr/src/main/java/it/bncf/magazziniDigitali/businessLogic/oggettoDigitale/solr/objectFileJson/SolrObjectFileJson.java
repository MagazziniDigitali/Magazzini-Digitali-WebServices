/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson;

import java.io.File;
import java.io.FileNotFoundException;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileDC.SolrObjectFileDC;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema.Book;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema.CreatorType;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema.IdentifierType;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
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

	protected void publicSolrJson(File pathTar, AddDocumentMD admd, IMDConfiguration<?> configuration) throws SolrException {
		File fName = null;
		Book book = null;
		String autore = null;
		
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
				if (book.getRights() != null) { // TODO: aggiornare il rights della Madre "open access" "closed access"
					if (book.getRights().trim().equalsIgnoreCase("open access")){
						params.add(ItemMD.RIGHTS, configuration.getSoftwareConfigString("rights.openAccess.id"));
					} else {
						params.add(ItemMD.RIGHTS, configuration.getSoftwareConfigString("rights.closedAccess.id"));
					}
				}
				if (book.getDate() != null) {
					params.add(ItemMD.DATA, book.getDate());
				}
				if (book.getCreator() != null && book.getCreator().size() > 0) {
					for (int x = 0; x < book.getCreator().size(); x++) {
						autore = book.getCreator().get(x).getName().trim();
						if (!book.getCreator().get(x).getType().value().equals(CreatorType.author.value())){
							autore += " ("+book.getCreator().get(x).getType().value()+")";
						}
						params.add(ItemMD.AUTORE, autore);
					}
				}
				if (book.getIdentifiers() != null && book.getIdentifiers().size() > 0) {
					for (int x = 0; x < book.getIdentifiers().size(); x++) {
						params.add(ItemMD.BID, book.getIdentifiers().get(x).getID());
						if (book.getIdentifiers().get(x).getType() != null &&
								book.getIdentifiers().get(x).getType().value().equals(IdentifierType.URL.value())){
							params.add(ItemMD.URL, book.getIdentifiers().get(x).getID().trim());
						}
					}
				}
				
				admd.add(params.getParams(), new ItemMD());
			}
		} catch (SolrException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw new SolrException(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			throw new SolrException(e.getMessage(), e);
		}
	}
}
