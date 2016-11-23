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
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrObjectFileAnalyze  extends SolrObjectFileMets {

	private Logger log = Logger.getLogger(getClass());
	
	private long size = 0;
	private String mimeType = null;
	private String fileType = null;
	
	/**
	 * 
	 */
	public SolrObjectFileAnalyze() {
		params = new Params();
	}

	public boolean publishSolr(File object, AddDocumentMD admd, java.io.File pathTar, IMDConfiguration<?> configuration) throws SolrException{
		boolean ris = false;
		String[] st = null;
		
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
			}else if (mimeType != null && mimeType.equals("application/x-tar")){
				st = configuration.getSoftwareConfigString("solrIndex.nodi").split(",");
//				st = Configuration.getValue("demoni.SolrIndex.nodi").split(",");
				for (int x=0; x<st.length; x++){
					params.add(ItemMD.NODO,st[x]);
				}
			}
			admd.add(params.getParams(), new ItemMD());
			if ((filename != null && filename.endsWith(".xml"))){
				if ((fileType != null && fileType.equals("mets"))){
					publicSolrMets(pathTar, admd, configuration);
				} else if ((fileType != null && fileType.equals("mag"))){
					publicSolrMag(pathTar, admd, configuration);
				}
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
		if (object.getContentLocationType().equals("tarindex") && object.getContentLocationValue() != null){
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
			if (object.getFixity()!= null &&
					object.getFixity().size()>0){
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
