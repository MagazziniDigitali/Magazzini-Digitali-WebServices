/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.depositolegale.md.BibliographicLevel;
import it.depositolegale.md.Holdings;
import it.depositolegale.md.Md;
import it.magazziniDigitali.xsd.md.MdXsd;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.converter.xsl.ConverterXsl;
import mx.randalf.converter.xsl.exception.ConvertXslException;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class SolrObjectFileMets extends SolrObjectFileMag{

	private Logger log = Logger.getLogger(SolrObjectFileMets.class);

	/**
	 * 
	 */
	public SolrObjectFileMets() {
	}

	@SuppressWarnings("unchecked")
	protected void publicSolrMets(java.io.File pathTar, AddDocumentMD admd, IMDConfiguration<?> configuration) throws SolrException{
		ByteArrayOutputStream dc = null;
		MdXsd mdXsd = null;
		Md md = null;
		Object obj = null;
		java.io.File fName = null;
		FileInputStream fInput = null;
		
		try {
			
			fName = new java.io.File(pathTar.getAbsolutePath()+
					java.io.File.separator+ filename);
			if (fName.exists()){
				fInput = new FileInputStream(fName);
				params.getParams().clear();
				params.add(ItemMD.ID, objectIdentifier+"-DC");
				params.add(ItemMD._ROOT_, objectIdentifier);
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_DOCUMENTO);
				params.add(ItemMD.ORIGINALFILENAME, filename);

				dc = new ByteArrayOutputStream();
				ConverterXsl.convertXsl(
//						configuration.getSoftwareConfigString("mets.xlsTansf"),
						Configuration.getValue("demoni.Publish.mets.xslTransf.dc"), 
						fInput, dc);
				mdXsd = new MdXsd();
				obj = mdXsd.read(new ByteArrayInputStream(dc.toByteArray()));

				if (obj instanceof JAXBElement){
					md = ((JAXBElement<Md>)obj).getValue();
				} else {
					md = (Md) obj;
				}
				
				if (md != null){
					if (md.getBib() != null){
						if (md.getBib().getLevel() != null){
							params.add(ItemMD.TIPODOCUMENTO, 
									(md.getBib().getLevel().equals(BibliographicLevel.M)
											?ItemMD.TIPODOCUMENTO_LIBRODIGITALIZZATO:
												ItemMD.TIPODOCUMENTO_PERIODICODIGITALIZZATO));
						}
						read(ItemMD.BID, md.getBib().getIdentifier());
						read(ItemMD.TITOLO, md.getBib().getTitle());
						read(ItemMD.AUTORE, md.getBib().getCreator());
						read(ItemMD.PUBBLICAZIONE, md.getBib().getPublisher());
						read(ItemMD.SOGGETTO, md.getBib().getSubject());
						read(ItemMD.DESCRIZIONE, md.getBib().getDescription());
						read(ItemMD.CONTRIBUTO, md.getBib().getContributor());
						read(ItemMD.DATA, md.getBib().getDate());
						read(ItemMD.TIPORISORSA, md.getBib().getType());
						read(ItemMD.FORMATO, md.getBib().getFormat());
						read(ItemMD.FONTE, md.getBib().getSource());
						read(ItemMD.LINGUA, md.getBib().getLanguage());
						read(ItemMD.RELAZIONE, md.getBib().getRelation());
						read(ItemMD.COPERTURA, md.getBib().getCoverage());
						read(ItemMD.GESTIONEDIRITTI, md.getBib().getRights());
						
						read(md.getBib().getHoldings());
					}
					if (md.getAgent() != null){
						for(int x=0; x<md.getAgent().size(); x++){
							for(int y=0; y<md.getAgent().get(x).getAgentIdentifier().size(); y++){
								params.add(ItemMD.PROVENIENZAOGGETTO, 
										md.getAgent().get(x).
											getAgentIdentifier().get(y).
											getAgentIdentifierValue());
							}
						}
					}
				}
//					params.add(ItemMD.KEYWORDSDOC, baos.toString());
				admd.add(params.getParams(), new ItemMD());
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (SolrException e) {
			throw e;
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (ConvertXslException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (XsdException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} finally{
			try {
				if (fInput != null){
					fInput.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			}
		}
		
	}

	private void read(List<Holdings> values){
		for (int x=0; x<values.size(); x++){
			if (values.get(x).getInventoryNumber() != null){
				params.add(ItemMD.INVENTARIO, values.get(x).getInventoryNumber());
			}
			if (values.get(x).getLibrary() != null){
				params.add(ItemMD.BIBLIOTECA, values.get(x).getInventoryNumber());
			}
			
			if (values.get(x).getPrecisInv() != null){
				params.add(ItemMD.PIECEGR, values.get(x).getPrecisInv());
			}
			if (values.get(x).getShelfmark() != null){
				for (int y=0; y<values.get(x).getShelfmark().size(); y++){
					params.add(ItemMD.COLLOCAZIONE, 
							values.get(x).getShelfmark().get(y).getContent());
				}
			}
			
		}
	}
}
