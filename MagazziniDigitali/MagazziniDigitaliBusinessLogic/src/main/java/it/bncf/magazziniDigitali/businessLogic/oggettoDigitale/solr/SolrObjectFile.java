/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import info.lc.xmlns.premis_v2.ContentLocationComplexType;
import info.lc.xmlns.premis_v2.File;
import info.lc.xmlns.premis_v2.FormatComplexType;
import info.lc.xmlns.premis_v2.FormatDesignationComplexType;
import info.lc.xmlns.premis_v2.FormatRegistryComplexType;
import info.lc.xmlns.premis_v2.LinkingRightsStatementIdentifierComplexType;
import info.lc.xmlns.premis_v2.ObjectCharacteristicsComplexType;
import info.lc.xmlns.premis_v2.ObjectIdentifierComplexType;
import info.lc.xmlns.premis_v2.RelatedObjectIdentificationComplexType;
import info.lc.xmlns.premis_v2.RelationshipComplexType;
import info.lc.xmlns.premis_v2.SignificantPropertiesComplexType;
import info.lc.xmlns.premis_v2.StorageComplexType;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.depositolegale.md.BibliographicLevel;
import it.depositolegale.md.Holdings;
import it.depositolegale.md.Md;
import it.magazziniDigitali.xsd.md.MdXsd;
import it.magazziniDigitali.xsd.premis.PremisXsd;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBElement;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.converter.xsl.ConverterXsl;
import mx.randalf.converter.xsl.exception.ConvertXslException;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;
import org.purl.dc.elements._1.SimpleLiteral;

/**
 * @author massi
 *
 */
public class SolrObjectFile {

	private Logger log = Logger.getLogger(getClass());
	private Params params;
	
	private long size = 0;
	private String filename = null;
	private String objectIdentifier = null;
	private String mimeType = null;
	private String fileType = null;
	
	/**
	 * 
	 */
	public SolrObjectFile() {
		params = new Params();
	}

	public boolean publishSolr(File object, AddDocumentMD admd, java.io.File pathTar) throws SolrException{
		boolean ris = false;
		
		try {
			params.getParams().clear();
			size = 0;
			filename = null;
			objectIdentifier = null;
			mimeType = null;
			
			if (object.getObjectIdentifier() != null){
				publicSolrObjectIdentifier(object.getObjectIdentifier());
			}

			if (object.getSignificantProperties() != null && object.getSignificantProperties().size()>0){
				publicSolrSignificantProperties(object.getSignificantProperties());
			} else {
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_FILE);
			}

			if (object.getObjectCharacteristics() != null){
				publicSolrObjectCharacteristics(object.getObjectCharacteristics());
			}

			if (object.getOriginalName() != null){
				params.add(ItemMD.ORIGINALFILENAME, object.getOriginalName().getValue());
				filename = object.getOriginalName().getValue();
			}

			if (object.getLinkingRightsStatementIdentifier() != null){
				publicSolrLinkingRights(object.getLinkingRightsStatementIdentifier());
			}

			if (object.getStorage() != null){
				publicSolrStorage(object.getStorage());
			}

			if (object.getRelationship() != null){
				publicSolrRelationship(object.getRelationship());
			}
			
			if (mimeType != null && mimeType.equals("text/plain")){
				if (size>0){
					publicSolrOcr(pathTar);
				}
			}
			admd.add(params.getParams(), new ItemMD());
			if ((filename != null && filename.endsWith(".xml")) && 
					(fileType != null && fileType.equals("mets"))){
				publicSolrMets(pathTar, admd);
			}
			ris = true;
		} catch (SolrException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		}
		return ris;
	}

	@SuppressWarnings("unchecked")
	private void publicSolrMets(java.io.File pathTar, AddDocumentMD admd) throws SolrException{
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
				ConverterXsl.convertXsl(Configuration.getValue("demoni.Publish.mets.xslTransf.dc"), 
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
	private void read(String key, List<SimpleLiteral> values){
		List<String> sValues = null;
		if (values != null){
			for(int x=0; x<values.size(); x++){
				sValues = ((SimpleLiteral)values.get(x)).getContent();
				for(int y=0; y<sValues.size(); y++){
					params.add(key, sValues.get(y));
				}
			}
		}
	}
	
	private void publicSolrOcr(java.io.File pathTar) throws SolrException{
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

	private void publicSolrRelationship(List<RelationshipComplexType> objects){
		RelationshipComplexType object = null;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getRelationshipType() != null){
				params.add(ItemMD.RELATIONSHIPTYPE, object.getRelationshipType());
			}
			if (object.getRelatedObjectIdentification() != null){
				publicSolrRelatedObjectIdentification(object.getRelatedObjectIdentification());
			}
		}
	}

	private void publicSolrRelatedObjectIdentification(List<RelatedObjectIdentificationComplexType> objects){
		RelatedObjectIdentificationComplexType object = null;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getRelatedObjectIdentifierType().equals(PremisXsd.UUID_MD)){
				params.add(ItemMD._ROOT_, object.getRelatedObjectIdentifierValue());
			}
		}
	}

	private void publicSolrStorage(List<StorageComplexType> objects){
		StorageComplexType object = null;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getContent() != null){
				for (int y=0; y <object.getContent().size(); y++){
					if (object.getContent().get(y).getValue() instanceof ContentLocationComplexType){
						publicSolrContentLocation((ContentLocationComplexType) object.getContent().get(y).getValue());
					}
				}
			}
		}
	}

	private void publicSolrContentLocation(ContentLocationComplexType object){
		if (object.getContentLocationType().equals("tarindex")){
			params.add(ItemMD.TARINDEX, object.getContentLocationValue());
		}
	}
	private void publicSolrLinkingRights(List<LinkingRightsStatementIdentifierComplexType> objects){
		LinkingRightsStatementIdentifierComplexType object = null;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getLinkingRightsStatementIdentifierType().equals(PremisXsd.UUID_MD_RG)){
				params.add(ItemMD.RIGHTS, object.getLinkingRightsStatementIdentifierValue());
			}
		}
	}
	
	private void publicSolrObjectCharacteristics(List<ObjectCharacteristicsComplexType> objects){
		ObjectCharacteristicsComplexType object = null;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getCompositionLevel()!= null){
				params.add(ItemMD.COMPOSITIONLEVEL, object.getCompositionLevel().toString());
			}
			if (object.getFixity()!= null){
				if (object.getFixity().get(0).getMessageDigestAlgorithm().equals("SHA-1")){
					params.add(ItemMD.SHA1, object.getFixity().get(0).getMessageDigest());
				}
			}
			if (object.getSize()!= null){
				params.add(ItemMD.SIZE, object.getSize());
				size = object.getSize();
			}
			if (object.getFormat()!= null){
				publicSolrFormat(object.getFormat());
			}
		}
	}

	private void publicSolrFormat(List<FormatComplexType> objects){
		FormatComplexType object = null;
		for (int y=0; y<objects.size(); y++){
			object = objects.get(y);
			if (object.getContent() != null){
				for(int x=0; x<object.getContent().size(); x++){
					if (object.getContent().get(x).getValue() instanceof FormatDesignationComplexType){
						publicSolrFormat((FormatDesignationComplexType)object.getContent().get(x).getValue());
					} else if (object.getContent().get(x).getValue() instanceof FormatRegistryComplexType){
						publicSolrFormat((FormatRegistryComplexType)object.getContent().get(x).getValue());
					} 
				}
			}
		}
	}

	private void publicSolrFormat(FormatRegistryComplexType object){
		if (object.getFormatRegistryName().equals("PRONOM")){
			params.add(ItemMD.PROMON, object.getFormatRegistryKey());
		}
	}

	private void publicSolrFormat(FormatDesignationComplexType object){
		params.add(ItemMD.MIMETYPE, object.getFormatName());
		mimeType =object.getFormatName();
	}

	private void publicSolrObjectIdentifier(List<ObjectIdentifierComplexType> objectIdentifiers){
		ObjectIdentifierComplexType objectIdentifier;
		for (int x=0; x<objectIdentifiers.size(); x++){
			objectIdentifier = objectIdentifiers.get(x);
			if (objectIdentifier.getObjectIdentifierType().equals(PremisXsd.UUID_MD_OBJ)){
				params.add(ItemMD.ID, objectIdentifier.getObjectIdentifierValue());
				params.add(ItemMD.OBJECTIDENTIFIER, objectIdentifier.getObjectIdentifierValue());
				this.objectIdentifier = objectIdentifier.getObjectIdentifierValue();
			}
		}
		
	}

	private void publicSolrSignificantProperties(List<SignificantPropertiesComplexType> objects){
		SignificantPropertiesComplexType object;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getContent().get(0).getValue().equals("FileType")){
				params.add(ItemMD.TIPOCONTENITORE, (String) object.getContent().get(1).getValue());
				params.add(ItemMD.FILETYPE, (String) object.getContent().get(1).getValue());
				fileType = (String) object.getContent().get(1).getValue();
			}
			if (object.getContent().get(0).getValue().equals("ActualFileName")){
				params.add(ItemMD.ACTUALFILENAME, (String) object.getContent().get(1).getValue());
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_CONTENITORE);
			}
		}
		
	}
}
