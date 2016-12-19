package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.util.List;

import org.apache.log4j.Logger;

import gov.loc.premis.v3.ContentLocationComplexType;
import gov.loc.premis.v3.File;
import gov.loc.premis.v3.FormatComplexType;
import gov.loc.premis.v3.FormatDesignationComplexType;
import gov.loc.premis.v3.FormatRegistryComplexType;
import gov.loc.premis.v3.LinkingRightsStatementIdentifierComplexType;
import gov.loc.premis.v3.ObjectCharacteristicsComplexType;
import gov.loc.premis.v3.ObjectIdentifierComplexType;
import gov.loc.premis.v3.RelatedObjectIdentifierComplexType;
import gov.loc.premis.v3.RelationshipComplexType;
import gov.loc.premis.v3.SignificantPropertiesComplexType;
import gov.loc.premis.v3.StorageComplexType;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.solr.exception.SolrException;

public class SolrObjectFileAnalyze3_0 extends SolrObjectFileAnalyze<File, ObjectIdentifierComplexType, SignificantPropertiesComplexType,
		ObjectCharacteristicsComplexType, FormatComplexType, LinkingRightsStatementIdentifierComplexType, FormatRegistryComplexType,
		FormatDesignationComplexType, StorageComplexType, ContentLocationComplexType, RelationshipComplexType, RelatedObjectIdentifierComplexType> {

	private Logger log = Logger.getLogger(SolrObjectFileAnalyze3_0.class);

	public SolrObjectFileAnalyze3_0() {
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

	protected void publicSolrObjectIdentifier(List<ObjectIdentifierComplexType> objectIdentifiers){
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

	protected void publicSolrSignificantProperties(List<SignificantPropertiesComplexType> objects){
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
	
	protected void publicSolrObjectCharacteristics(List<ObjectCharacteristicsComplexType> objects){
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

	protected void publicSolrFormat(List<FormatComplexType> objects){
		FormatComplexType object = null;
		for (int y=0; y<objects.size(); y++){
			object = objects.get(y);
			if (object.getContent() != null){
				for(int x=0; x<object.getContent().size(); x++){
					if (object.getContent().get(x).getValue() instanceof FormatDesignationComplexType){
						publicSolrFormatDesignationComplexType((FormatDesignationComplexType)object.getContent().get(x).getValue());
					} else if (object.getContent().get(x).getValue() instanceof FormatRegistryComplexType){
						publicSolrFormatRegistryComplexType((FormatRegistryComplexType)object.getContent().get(x).getValue());
					} 
				}
			}
		}
	}

	protected void publicSolrFormatRegistryComplexType(FormatRegistryComplexType object){
		if (object.getFormatRegistryName().equals("PRONOM")){
			params.add(ItemMD.PROMON, object.getFormatRegistryKey().getValue());
		}
	}

	protected void publicSolrFormatDesignationComplexType(FormatDesignationComplexType object){
		params.add(ItemMD.MIMETYPE, object.getFormatName().getValue());
		mimeType =object.getFormatName().getValue();
	}

	protected void publicSolrLinkingRights(List<LinkingRightsStatementIdentifierComplexType> objects){
		LinkingRightsStatementIdentifierComplexType object = null;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getLinkingRightsStatementIdentifierType().equals(PremisXsd.UUID_MD_RG)){
				params.add(ItemMD.RIGHTS, object.getLinkingRightsStatementIdentifierValue());
			}
		}
	}

	protected void publicSolrStorage(List<StorageComplexType> objects){
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

	protected void publicSolrContentLocation(ContentLocationComplexType object){
		if (object.getContentLocationType().equals("tarindex") && object.getContentLocationValue() != null){
			params.add(ItemMD.TARINDEX, object.getContentLocationValue());
		}
	}

	protected void publicSolrRelationship(List<RelationshipComplexType> objects){
		RelationshipComplexType object = null;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getRelationshipType() != null){
				params.add(ItemMD.RELATIONSHIPTYPE, object.getRelationshipType().getValue());
			}
			if (object.getRelatedObjectIdentifier() != null){
				publicSolrRelatedObjectIdentification(object.getRelatedObjectIdentifier());
			}
		}
	}

	protected void publicSolrRelatedObjectIdentification(List<RelatedObjectIdentifierComplexType> objects){
		RelatedObjectIdentifierComplexType object = null;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getRelatedObjectIdentifierType().equals(PremisXsd.UUID_MD)){
				params.add(ItemMD._ROOT_, object.getRelatedObjectIdentifierValue());
			}
		}
	}
}