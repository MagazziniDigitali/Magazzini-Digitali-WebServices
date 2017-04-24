package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileAnalyze;

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
import gov.loc.premis.v3.StringPlusAuthority;
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

	public boolean publishSolr(File object, AddDocumentMD admd, java.io.File pathTar, IMDConfiguration<?> configuration, 
			boolean elabTarPremis, String name, Logger logPublish, String objectIdentifierPremis) throws SolrException{
		boolean ris = false;
		java.io.File fTxt = null;
		boolean isValid = false;
		
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
				if (publicSolrObjectCharacteristics(object.getObjectCharacteristics())){
					isValid = true;
				}
			}

			if (object.getOriginalName() != null){
				isValid = true;
				params.add(ItemMD.ORIGINALFILENAME, object.getOriginalName().getValue());
				filename = object.getOriginalName().getValue();
				if (filename.trim().toLowerCase().startsWith("http://") ||
						filename.trim().toLowerCase().startsWith("https://")){
					params.add(ItemMD.URL, filename);
				}
				if (elabTarPremis){
					checkIdMadre();
				}
			}

			if (object.getLinkingRightsStatementIdentifier() != null){
				if (publicSolrLinkingRights(object.getLinkingRightsStatementIdentifier())){
					isValid = true;
				}
			}

			if (object.getStorage() != null){
				if (publicSolrStorage(object.getStorage())){
					isValid = true;
				}
			}

			if (object.getRelationship() != null){
				if (publicSolrRelationship(object.getRelationship())){
					isValid = true;
				}
			}
			
			if (mimeType != null && mimeType.equals("text/plain")){
				if (size>0){
					isValid = true;
					publicSolrOcr(pathTar);
				}
			}else if (mimeType != null && mimeType.equals("application/x-tar")){
				isValid = true;
				insertNodi();
			}
			if ((fileType != null && fileType.equals("bagit"))) {
				isValid = true;
				if (filename.endsWith(".pdf") || filename.endsWith(".epub")){
					fTxt = new java.io.File(pathTar.getAbsolutePath() + java.io.File.separator + filename+".txt");
					if (fTxt.exists()){
						publicSolrOcr(fTxt.getAbsolutePath());
					}
				}
			}
			if (isValid){
				admd.add(params.getParams(), new ItemMD());
	
				if (!elabTarPremis){
					checkAllegati(pathTar, admd, configuration,  name,  logPublish,  objectIdentifierPremis);
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
			if (objectIdentifier.getObjectIdentifierType().getValue().equals(PremisXsd.UUID_MD_OBJ)){
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
			if (((StringPlusAuthority)object.getContent().get(0).getValue()).getValue().equals("FileType")){
				fileType = (String) object.getContent().get(1).getValue();
				switch (fileType) {
				case "premis":
					params.add(ItemMD.TIPOCONTENITORE, "admtape");
					break;
				case "agent":
					params.add(ItemMD.TIPOCONTENITORE, "admtape");
					break;
				case "rights":
					params.add(ItemMD.TIPOCONTENITORE, "admtape");
					break;
				case "event":
					params.add(ItemMD.TIPOCONTENITORE, "admtape");
					break;
				case "registro":
					params.add(ItemMD.TIPOCONTENITORE, "admtape");
					break;
				default:
					params.add(ItemMD.TIPOCONTENITORE, fileType);
					break;
				}
				params.add(ItemMD.FILETYPE, fileType);
			}
			if (((StringPlusAuthority)object.getContent().get(0).getValue()).getValue().equals("ActualFileName")){
				params.add(ItemMD.ACTUALFILENAME, (String) object.getContent().get(1).getValue());
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_CONTENITORE);
			}
		}
		
	}
	
	protected boolean publicSolrObjectCharacteristics(List<ObjectCharacteristicsComplexType> objects){
		ObjectCharacteristicsComplexType object = null;
		boolean isValid = false;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getCompositionLevel()!= null){
				params.add(ItemMD.COMPOSITIONLEVEL, object.getCompositionLevel().getValue().toString());
			}
			if (object.getFixity()!= null &&
					object.getFixity().size()>0){
				isValid = true;
				if (object.getFixity().get(0).getMessageDigestAlgorithm().getValue().equals("SHA-1")){
					params.add(ItemMD.SHA1, object.getFixity().get(0).getMessageDigest());
				}
			}
			if (object.getSize()!= null){
				isValid = true;
				params.add(ItemMD.SIZE, object.getSize());
				size = object.getSize();
			}
			if (object.getFormat()!= null){
				if (publicSolrFormat(object.getFormat())){
					isValid = true;
				}
			}
		}
		return isValid;
	}

	protected boolean publicSolrFormat(List<FormatComplexType> objects){
		FormatComplexType object = null;
		boolean isValid = false;

		for (int y=0; y<objects.size(); y++){
			object = objects.get(y);
			if (object.getContent() != null){
				for(int x=0; x<object.getContent().size(); x++){
					if (object.getContent().get(x).getValue() instanceof FormatDesignationComplexType){
						if (publicSolrFormatDesignationComplexType((FormatDesignationComplexType)object.getContent().get(x).getValue())){
							isValid = true;
						}
					} else if (object.getContent().get(x).getValue() instanceof FormatRegistryComplexType){
						if (publicSolrFormatRegistryComplexType((FormatRegistryComplexType)object.getContent().get(x).getValue())){
							isValid = true;
						}
					} 
				}
			}
		}
		return isValid;
	}

	protected boolean publicSolrFormatRegistryComplexType(FormatRegistryComplexType object){
		boolean isValid = false;
		if (object.getFormatRegistryName().getValue().equals("PRONOM")){
			isValid = true;
			params.add(ItemMD.PROMON, object.getFormatRegistryKey().getValue());
		}
		return isValid;
	}

	protected boolean publicSolrFormatDesignationComplexType(FormatDesignationComplexType object){
		params.add(ItemMD.MIMETYPE, object.getFormatName().getValue());
		mimeType =object.getFormatName().getValue();
		return true;
	}

	protected boolean publicSolrLinkingRights(List<LinkingRightsStatementIdentifierComplexType> objects){
		LinkingRightsStatementIdentifierComplexType object = null;
		boolean isValid = false;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getLinkingRightsStatementIdentifierType().getValue().equals(PremisXsd.UUID_MD_RG)){
				params.add(ItemMD.RIGHTS, object.getLinkingRightsStatementIdentifierValue());
				isValid = true;
			}
		}
		return isValid;
	}

	protected boolean publicSolrStorage(List<StorageComplexType> objects){
		StorageComplexType object = null;
		boolean isValid = false;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getContent() != null){
				for (int y=0; y <object.getContent().size(); y++){
					if (object.getContent().get(y).getValue() instanceof ContentLocationComplexType){
						if (publicSolrContentLocation((ContentLocationComplexType) object.getContent().get(y).getValue())){
							isValid = true;
						}
					}
				}
			}
		}
		return isValid;
	}

	protected boolean publicSolrContentLocation(ContentLocationComplexType object){
		boolean isValid = false;
		if (object.getContentLocationType().getValue().equals("tarindex") && object.getContentLocationValue() != null){
			params.add(ItemMD.TARINDEX, object.getContentLocationValue());
			isValid = true;
		}
		return isValid;
	}

	protected boolean publicSolrRelationship(List<RelationshipComplexType> objects){
		RelationshipComplexType object = null;
		boolean isValid = false;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getRelationshipType() != null){
				params.add(ItemMD.RELATIONSHIPTYPE, object.getRelationshipType().getValue());
				isValid = true;
			}
			if (object.getRelatedObjectIdentifier() != null){
				if (publicSolrRelatedObjectIdentification(object.getRelatedObjectIdentifier())){
					isValid = true;
				}
			}
		}
		return isValid;
	}

	protected boolean publicSolrRelatedObjectIdentification(List<RelatedObjectIdentifierComplexType> objects){
		RelatedObjectIdentifierComplexType object = null;
		boolean isValid = false;
		for (int x=0;x<objects.size(); x++){
			object = objects.get(x);
			if (object.getRelatedObjectIdentifierType().getValue().equals(PremisXsd.UUID_MD)){
				params.add(ItemMD._ROOT_, object.getRelatedObjectIdentifierValue());
				isValid = true;
			}
		}
		return isValid;
	}
}