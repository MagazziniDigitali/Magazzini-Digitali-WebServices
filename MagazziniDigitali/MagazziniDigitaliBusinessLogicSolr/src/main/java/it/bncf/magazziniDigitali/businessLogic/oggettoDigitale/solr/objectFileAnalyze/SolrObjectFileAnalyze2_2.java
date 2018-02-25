package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileAnalyze;

import java.util.List;

import org.apache.log4j.Logger;

import info.lc.xmlns.premis_v2.CHECKSUMTYPEDefinition;
import info.lc.xmlns.premis_v2.ContentLocationComplexType;
import info.lc.xmlns.premis_v2.File;
import info.lc.xmlns.premis_v2.FixityComplexType;
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

public class SolrObjectFileAnalyze2_2 extends
		SolrObjectFileAnalyze<File, ObjectIdentifierComplexType, SignificantPropertiesComplexType, 
		ObjectCharacteristicsComplexType, FormatComplexType, LinkingRightsStatementIdentifierComplexType, FormatRegistryComplexType, FormatDesignationComplexType, StorageComplexType, ContentLocationComplexType, RelationshipComplexType, RelatedObjectIdentificationComplexType> {

	private Logger log = Logger.getLogger(SolrObjectFileAnalyze2_2.class);

	public SolrObjectFileAnalyze2_2() {
	}

	public boolean publishSolr(File object, AddDocumentMD admd, java.io.File pathTar, IMDConfiguration<?> configuration,
			boolean elabTarPremis, String name, Logger logPublish, String objectIdentifierPremis) throws SolrException {
		boolean ris = false;
		java.io.File fTxt = null;
		boolean isValid = false;

		try {
			params.getParams().clear();
			size = 0;
			filename = null;
			objectIdentifier = null;
			mimeType = null;

			if (object.getObjectIdentifier() != null) {
				publicSolrObjectIdentifier(object.getObjectIdentifier());
			}

			if (object.getSignificantProperties() != null && object.getSignificantProperties().size() > 0) {
				publicSolrSignificantProperties(object.getSignificantProperties());
			} else {
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_FILE);
			}

			if (object.getObjectCharacteristics() != null) {
				if (publicSolrObjectCharacteristics(object.getObjectCharacteristics())) {
					isValid = true;
				}
			}

			if (object.getOriginalName() != null) {
				isValid = true;
				params.add(ItemMD.ORIGINALFILENAME, object.getOriginalName().getValue());
				filename = object.getOriginalName().getValue();
				if (filename.trim().toLowerCase().startsWith("http://")
						|| filename.trim().toLowerCase().startsWith("https://")) {
					params.add(ItemMD.URL, filename);
				}
				if (elabTarPremis) {
					checkIdMadre();
				}
			}

			if (object.getLinkingRightsStatementIdentifier() != null) {
				if (publicSolrLinkingRights(object.getLinkingRightsStatementIdentifier())) {
					isValid = true;
				}
			}

			if (object.getStorage() != null) {
				if (publicSolrStorage(object.getStorage())) {
					isValid = true;
				}
			}

			if (object.getRelationship() != null) {
				if (publicSolrRelationship(object.getRelationship())) {
					isValid = true;
				}
			}

			if (mimeType != null && mimeType.equals("text/plain")) {
				if (size > 0) {
					isValid = true;
					publicSolrOcr(pathTar);
				}
			} else if (mimeType != null && mimeType.equals("application/x-tar")) {
				isValid = true;
				insertNodi();
			}
			if ((fileType != null && fileType.equals("bagit"))) {
				isValid = true;
				if (filename.endsWith(".pdf") || filename.endsWith(".epub")) {
					fTxt = new java.io.File(pathTar.getAbsolutePath() + java.io.File.separator + filename + ".txt");
					if (fTxt.exists()) {
						publicSolrOcr(fTxt.getAbsolutePath());
					}
				}
			}
			if (isValid) {
				admd.add(params.getParams(), new ItemMD());

				if (!elabTarPremis) {
					checkAllegati(pathTar, admd, configuration, name, logPublish, objectIdentifierPremis);
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

	protected void publicSolrObjectIdentifier(List<ObjectIdentifierComplexType> objectIdentifiers) {
		ObjectIdentifierComplexType objectIdentifier;
		for (int x = 0; x < objectIdentifiers.size(); x++) {
			objectIdentifier = objectIdentifiers.get(x);
			if (objectIdentifier.getObjectIdentifierType().equals(PremisXsd.UUID_MD_OBJ)) {
				params.add(ItemMD.ID, objectIdentifier.getObjectIdentifierValue());
				params.add(ItemMD.OBJECTIDENTIFIER, objectIdentifier.getObjectIdentifierValue());
				this.objectIdentifier = objectIdentifier.getObjectIdentifierValue();
			}
		}

	}

	protected void publicSolrSignificantProperties(List<SignificantPropertiesComplexType> objects) {
		SignificantPropertiesComplexType object;

		for (int x = 0; x < objects.size(); x++) {
			object = objects.get(x);
			if (object.getContent().get(0).getValue().equals("FileType")) {
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
			if (object.getContent().get(0).getValue().equals("ActualFileName")) {
				params.add(ItemMD.ACTUALFILENAME, (String) object.getContent().get(1).getValue());
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_CONTENITORE);
			}
		}

	}

	protected boolean publicSolrObjectCharacteristics(List<ObjectCharacteristicsComplexType> objects) {
		ObjectCharacteristicsComplexType object = null;
		boolean isValid = false;
		for (int x = 0; x < objects.size(); x++) {
			object = objects.get(x);
			if (object.getCompositionLevel() != null) {
				params.add(ItemMD.COMPOSITIONLEVEL, object.getCompositionLevel().toString());
			}
			if (object.getFixity() != null && object.getFixity().size() > 0) {
				isValid = true;
				for (FixityComplexType fixity: object.getFixity()) {
					if (fixity.getMessageDigestAlgorithm().equals(CHECKSUMTYPEDefinition.SHA_1.value())) {
						params.add(ItemMD.SHA1, fixity.getMessageDigest());
					} else if (fixity.getMessageDigestAlgorithm().equals(CHECKSUMTYPEDefinition.SHA_256.value())) {
						params.add(ItemMD.SHA256, fixity.getMessageDigest());
					} else if (fixity.getMessageDigestAlgorithm().equals(CHECKSUMTYPEDefinition.MD_5.value())) {
						params.add(ItemMD.MD5, fixity.getMessageDigest());
					}
				}
			}
			if (object.getSize() != null) {
				isValid = true;
				params.add(ItemMD.SIZE, object.getSize());
				size = object.getSize();
			}
			if (object.getFormat() != null) {
				if (publicSolrFormat(object.getFormat())) {
					isValid = true;
				}
			}
		}
		return isValid;
	}

	protected boolean publicSolrFormat(List<FormatComplexType> objects) {
		FormatComplexType object = null;
		boolean isValid = false;

		for (int y = 0; y < objects.size(); y++) {
			object = objects.get(y);
			if (object.getContent() != null) {
				for (int x = 0; x < object.getContent().size(); x++) {
					if (object.getContent().get(x).getValue() instanceof FormatDesignationComplexType) {
						if (publicSolrFormatDesignationComplexType(
								(FormatDesignationComplexType) object.getContent().get(x).getValue())) {
							isValid = true;
						}
					} else if (object.getContent().get(x).getValue() instanceof FormatRegistryComplexType) {
						if (publicSolrFormatRegistryComplexType(
								(FormatRegistryComplexType) object.getContent().get(x).getValue())) {
							isValid = true;
						}
					}
				}
			}
		}
		return isValid;
	}

	protected boolean publicSolrFormatRegistryComplexType(FormatRegistryComplexType object) {
		boolean isValid = false;
		if (object.getFormatRegistryName().equals("PRONOM")) {
			isValid = true;
			params.add(ItemMD.PROMON, object.getFormatRegistryKey());
		}
		return isValid;
	}

	protected boolean publicSolrFormatDesignationComplexType(FormatDesignationComplexType object) {
		params.add(ItemMD.MIMETYPE, object.getFormatName());
		mimeType = object.getFormatName();
		return true;
	}

	protected boolean publicSolrLinkingRights(List<LinkingRightsStatementIdentifierComplexType> objects) {
		LinkingRightsStatementIdentifierComplexType object = null;
		boolean isValid = false;
		for (int x = 0; x < objects.size(); x++) {
			object = objects.get(x);
			if (object.getLinkingRightsStatementIdentifierType().equals(PremisXsd.UUID_MD_RG)) {
				params.add(ItemMD.RIGHTS, object.getLinkingRightsStatementIdentifierValue());
				isValid = true;
			}
		}
		return isValid;
	}

	protected boolean publicSolrStorage(List<StorageComplexType> objects) {
		StorageComplexType object = null;
		boolean isValid = false;
		for (int x = 0; x < objects.size(); x++) {
			object = objects.get(x);
			if (object.getContent() != null) {
				for (int y = 0; y < object.getContent().size(); y++) {
					if (object.getContent().get(y).getValue() instanceof ContentLocationComplexType) {
						if (publicSolrContentLocation(
								(ContentLocationComplexType) object.getContent().get(y).getValue())) {
							isValid = true;
						}
					}
				}
			}
		}
		return isValid;
	}

	protected boolean publicSolrContentLocation(ContentLocationComplexType object) {
		boolean isValid = false;
		if (object.getContentLocationType().equals("tarindex") && object.getContentLocationValue() != null) {
			params.add(ItemMD.TARINDEX, object.getContentLocationValue());
			isValid = true;
		}
		return isValid;
	}

	protected boolean publicSolrRelationship(List<RelationshipComplexType> objects) {
		RelationshipComplexType object = null;
		boolean isValid = false;
		for (int x = 0; x < objects.size(); x++) {
			object = objects.get(x);
			if (object.getRelationshipType() != null) {
				params.add(ItemMD.RELATIONSHIPTYPE, object.getRelationshipType());
				isValid = true;
			}
			if (object.getRelatedObjectIdentification() != null) {
				if (publicSolrRelatedObjectIdentification(object.getRelatedObjectIdentification())) {
					isValid = true;
				}
			}
		}
		return isValid;
	}

	protected boolean publicSolrRelatedObjectIdentification(List<RelatedObjectIdentificationComplexType> objects) {
		RelatedObjectIdentificationComplexType object = null;
		boolean isValid = false;
		for (int x = 0; x < objects.size(); x++) {
			object = objects.get(x);
			if (object.getRelatedObjectIdentifierType().equals(PremisXsd.UUID_MD)) {
				params.add(ItemMD._ROOT_, object.getRelatedObjectIdentifierValue());
				isValid = true;
			}
		}
		return isValid;
	}
}