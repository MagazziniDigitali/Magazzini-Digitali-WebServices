/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.database.dao.MDNodiDAO;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public abstract class SolrObjectFileAnalyze<F, OICT, SPCT, OCCT, FCT, LRSICT, FRCT, FDCT, SCT, CLCT, RCT, ROICT>
		extends SolrObjectFileDC {

	private Logger log = Logger.getLogger(getClass());

	protected long size = 0;
	protected String mimeType = null;
	protected String fileType = null;

	/**
	 * 
	 */
	public SolrObjectFileAnalyze() {
		params = new Params();
	}

	public abstract boolean publishSolr(F object, AddDocumentMD admd, java.io.File pathTar,
			IMDConfiguration<?> configuration, boolean elabTarPremis, String name, Logger logPublish, 
			String objectIdentifierPremis) throws SolrException;

	protected void publicSolrOcr(java.io.File pathTar) throws SolrException {
		publicSolrOcr(pathTar.getAbsolutePath() + java.io.File.separator + filename);
	}

	protected void publicSolrOcr(String pathTar) throws SolrException {
		java.io.File fName = null;
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;

		try {
			fName = new java.io.File(pathTar);
			if (fName.exists()) {
				fr = new FileReader(fName);
				br = new BufferedReader(fr);
				while ((line = br.readLine()) != null) {
					if (!line.trim().equals("")) {
						params.add(ItemMD.KEYWORDSDOC, line);
					}
				}
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}

				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			}
		}

	}

	protected void checkAllegati(File pathTar, AddDocumentMD admd, IMDConfiguration<?> configuration, String name, Logger logPublish, String objectIdentifierPremis)
			throws SolrException, XsdException {
		File fTtl = null;
		
		if (filename != null){
			if ( filename.endsWith(".xml")) {
				if ((fileType != null && fileType.equals("mets"))) {
					publicSolrMets(pathTar, admd, configuration);
				} else if ((fileType != null && fileType.equals("mag"))) {
					publicSolrMag(pathTar, admd, configuration);
				} else if ((fileType != null && fileType.equals("registro"))) {
					publicSolrRegistro(pathTar, admd, configuration);
				}
			} else if(filename.endsWith(".premis")){
				if (fileType != null){
					if (fileType.equals("premis")) {
						publicSolrPremis(pathTar, admd, configuration, name, logPublish, objectIdentifierPremis);
					} else if (fileType.equals("agent")){
						publicSolrAgent(pathTar, admd, name);
					} else if (fileType.equals("rights")){
						publicSolrRights(pathTar, admd, name);
					} else if (fileType.equals("event")){
						publicSolrEvent(pathTar, admd, name);
					}
				}
			} else if ((fileType != null && fileType.equals("bagit"))) {
				if (filename.endsWith(".pdf") || filename.endsWith(".epub")){
					if (filename.endsWith(".pdf")){
						fTtl = new File(pathTar.getAbsolutePath()+File.separator+filename.replace(".pdf", ".ttl"));
					} else {
						fTtl = new File(pathTar.getAbsolutePath()+File.separator+filename.replace(".epub", ".ttl"));
					}
					if (fTtl.exists()){
						publicSolrTtl(fTtl, admd);
					} else {
						if (filename.endsWith(".pdf")){
							fTtl = new File(pathTar.getAbsolutePath()+File.separator+filename.replace(".pdf", ".xml"));
						} else {
							fTtl = new File(pathTar.getAbsolutePath()+File.separator+filename.replace(".epub", ".xml"));
						}
						if (fTtl.exists()){
							publicSolrDc(fTtl, admd);
						}
					}
				}
			}
		}

	}

	protected void checkIdMadre(){
		String idMadre = null;
		int pos =0;

		pos = filename.lastIndexOf("/");
		if (pos >-1){
			idMadre = filename.substring(pos+1);
		} else {
			idMadre = filename;
		}
		pos = idMadre.indexOf(".");
		if (pos >-1){
			idMadre = idMadre.substring(0, pos);
		}
		params.add(ItemMD._ROOT_, idMadre);

	}

	protected void insertNodi() throws SolrException{
		MDNodiDAO mdNodiDAO = null;
		List<MDNodi> mdNodis = null;

		try {
			mdNodiDAO = new MDNodiDAO();
			mdNodis = mdNodiDAO.findActive();

			for( MDNodi mdNodi: mdNodis){
				params.add(ItemMD.NODO, mdNodi.getCode());
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		}
	}
	protected abstract boolean publicSolrRelationship(List<RCT> objects);

	protected abstract boolean publicSolrRelatedObjectIdentification(List<ROICT> objects);

	protected abstract boolean publicSolrStorage(List<SCT> objects);

	protected abstract boolean publicSolrContentLocation(CLCT object);

	protected abstract boolean publicSolrLinkingRights(List<LRSICT> objects);

	protected abstract boolean publicSolrObjectCharacteristics(List<OCCT> objects);

	protected abstract boolean publicSolrFormat(List<FCT> objects);

	protected abstract boolean publicSolrFormatRegistryComplexType(FRCT object);

	protected abstract boolean publicSolrFormatDesignationComplexType(FDCT object);

	protected abstract void publicSolrObjectIdentifier(List<OICT> objectIdentifiers);

	protected abstract void publicSolrSignificantProperties(List<SPCT> objects);
}
