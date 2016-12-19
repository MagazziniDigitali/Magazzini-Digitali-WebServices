/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public abstract class SolrObjectFileAnalyze<F, OICT, SPCT, OCCT, FCT, LRSICT, FRCT, FDCT, SCT, CLCT, RCT, ROICT>  extends SolrObjectFileMets {

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

	public abstract boolean publishSolr(F object, AddDocumentMD admd, java.io.File pathTar, IMDConfiguration<?> configuration) throws SolrException;

	protected void publicSolrOcr(java.io.File pathTar) throws SolrException{
		java.io.File fName = null;
		FileReader fr = null;
		BufferedReader br = null;
		String line= null;
		
		try {
			fName = new java.io.File(pathTar.getAbsolutePath()+
					java.io.File.separator+
					filename);
			if (fName.exists()){
				fr = new FileReader(fName);
				br = new BufferedReader(fr);
				while((line = br.readLine())!=null){
					if (!line.trim().equals("")){
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
		} finally{
			try {
				if (br != null){
					br.close();
				}

				if (fr != null){
					fr.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			}
		}
		
	}

	protected abstract void publicSolrRelationship(List<RCT> objects);

	protected abstract void publicSolrRelatedObjectIdentification(List<ROICT> objects);

	protected abstract void publicSolrStorage(List<SCT> objects);

	protected abstract void publicSolrContentLocation(CLCT object);

	protected abstract void publicSolrLinkingRights(List<LRSICT> objects);
	
	protected abstract void publicSolrObjectCharacteristics(List<OCCT> objects);

	protected abstract void publicSolrFormat(List<FCT> objects);

	protected abstract void publicSolrFormatRegistryComplexType(FRCT object);

	protected abstract void publicSolrFormatDesignationComplexType(FDCT object);

	protected abstract void publicSolrObjectIdentifier(List<OICT> objectIdentifiers);

	protected abstract void publicSolrSignificantProperties(List<SPCT> objects);
}
