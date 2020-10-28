/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileMrc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.SolrObjectFileJson;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.depositolegale.md.BibliographicLevel;
import mx.randalf.protocol.sbn.metadata.RandalfMetadata;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class SolrObjectFileMrc extends SolrObjectFileJson {

	private Logger log = LogManager.getLogger(SolrObjectFileMrc.class);

	/**
	 * 
	 */
	public SolrObjectFileMrc() {
	}

	protected void publicSolrMrc(File pathTar, AddDocumentMD admd, IMDConfiguration<?> configuration, File fTar)
			throws SolrException, XsdException {
		File fName = null;
		FileInputStream fileInputStream = null;
		MarcReader marcReader = null;
		Record record = null;
		RandalfMetadata randalfMetadata = null;

		try {
			if (fTar != null) {
				fName = fTar;
			} else {
				fName = new File(pathTar.getAbsolutePath() + File.separator + filename);
			}
			if (fName.exists()) {
				fileInputStream = new FileInputStream(fName);
				marcReader = new MarcStreamReader(fileInputStream, "UTF-8");
				while (marcReader.hasNext()) {
					record = marcReader.next();
					randalfMetadata = new RandalfMetadata(record, null);
					params.getParams().clear();
					params.add(ItemMD.ID, objectIdentifier + "-DC-" + randalfMetadata.getIdentifiers().get(0));
					params.add(ItemMD._ROOT_, objectIdentifier);
					params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_DOCUMENTO);
					params.add(ItemMD.ORIGINALFILENAME, filename);

					if (randalfMetadata.getLevel() != null) {
						params.add(ItemMD.TIPODOCUMENTO,
								(randalfMetadata.getLevel().equals(BibliographicLevel.M.value())
										? ItemMD.TIPODOCUMENTO_LIBRODIGITALIZZATO
										: ItemMD.TIPODOCUMENTO_PERIODICODIGITALIZZATO));
						readString(ItemMD.BID, randalfMetadata.getIdentifiers());
						readString(ItemMD.TITOLO, randalfMetadata.getTitles());
						readString(ItemMD.AUTORE, randalfMetadata.getCreators());
						readString(ItemMD.PUBBLICAZIONE, randalfMetadata.getPublishers());
						readString(ItemMD.SOGGETTO, randalfMetadata.getSubjects());
						readString(ItemMD.DESCRIZIONE, randalfMetadata.getDescriptions());
						readString(ItemMD.CONTRIBUTO, randalfMetadata.getContributors());
						readString(ItemMD.DATA, randalfMetadata.getDates());
						readString(ItemMD.TIPORISORSA, randalfMetadata.getTypes());
						readString(ItemMD.FORMATO, randalfMetadata.getFormats());
						readString(ItemMD.FONTE, randalfMetadata.getSources());
						readString(ItemMD.LINGUA, randalfMetadata.getLanguages());
						readString(ItemMD.RELAZIONE, randalfMetadata.getRelations());
						readString(ItemMD.COPERTURA, randalfMetadata.getCoverages());
						readString(ItemMD.GESTIONEDIRITTI, randalfMetadata.getRights());
						readString(ItemMD.URL, randalfMetadata.getUrls());
					}
					admd.add(params.getParams(), new ItemMD());
				}
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new XsdException(e.getMessage(),e);
		} catch (SolrException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new XsdException(e.getMessage(),e);
			}
		}

	}

	private void readString(String key, List<String> values) {
		if (values != null) {
			for (String value : values) {
				params.add(key, value);
			}
		}
	}
}
