package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.nodi.ENodi;
import it.bncf.magazziniDigitali.nodi.Nodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.tools.MD5Tools;

public class OggettoDigitalePublishPremis extends OggettoDigitalePublishTar {

	private Logger log = LogManager.getLogger(OggettoDigitalePublishPremis.class);

	public OggettoDigitalePublishPremis(Logger logPublish, String name) {
		super(logPublish, name);
	}

	protected boolean sendFilePremis(File fInput
			, MDFilesTmp mdFilesTmp
			, MDFilesTmpBusiness mdFileTmpBusiness
			, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
			//File fOutput, 
//			,
//			, String application
			, String objectIdentifierMaster
//			, MDSoftware idIstituto
			, String objectIdentifierPremis
			, IMDConfiguration<?> configuration
			, Nodi nodi
			)
					throws SQLException, MDConfigurationException 
	{
		boolean result = false;
		GregorianCalendar gcStart = null;
		GregorianCalendar gcEnd = null;
//		FileInputStream fis = null;

		try {
			if (mdFilesTmp.getCopyPremisDataStart() == null
					&& mdFilesTmp.getCopyPremisDataEnd() == null) {
				gcStart = new GregorianCalendar();
//				fis = new FileInputStream(fInput);
				result = nodi.copyFile(fInput, fInput.length(), MD5Tools.readMD5File(fInput.getAbsolutePath()), ENodi.PREMIS);
//				result = (Utils.copyFileValidate(fInput.getAbsolutePath(),
//						fOutput.getAbsolutePath()));
				gcEnd = new GregorianCalendar();
				mdFileTmpBusiness.updateCopyPremis(
						mdFilesTmp.getId(),
						gcStart,
						gcEnd,
						true,
						null,
						null,
						mdFilesTmp.getIdSoftware().getIdIstituzione().getId(), 
						configuration.getSoftwareConfigMDNodi("nodo"), 
						configuration.getMDSoftware().getId()); 
				premisElab
						.addEvent(
								"copyPremis",
								gcStart,
								gcEnd,
								fInput.getAbsolutePath() + " => "
										+ nodi.genFilePremisDest(),
								(result ? "OK" : "KO"),
								(result ? null
										: new String[] { "Riscontrato un problema durante la copia del file ["+
								fInput.getAbsolutePath()+"] in ["+nodi.genFilePremisDest()+"]"}),
								null, 
								configuration.getMDSoftware(),
								objectIdentifierMaster);
			} else {
				result = mdFilesTmp.getCopyPremisEsito();
				premisElab.addEvent("copyPremis", mdFilesTmp.getCopyPremisDataStart(), 
						mdFilesTmp.getCopyPremisDataEnd(), fInput.getAbsolutePath()
						+ " => " + nodi.genFilePremisDest(), (result ? "OK"
						: "KO"), null, null, configuration.getMDSoftware(),
						objectIdentifierMaster);
			}
		} catch (FileNotFoundException e) {
			mdFileTmpBusiness.updateCopyPremis(
					mdFilesTmp.getId(),
					gcStart,
					gcEnd,
					false,
					new Exception[] { e },
					null,
					mdFilesTmp.getIdSoftware().getIdIstituzione().getId(), 
					configuration.getSoftwareConfigMDNodi("nodo"), 
					configuration.getMDSoftware().getId()); 
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ nodi.genFilePremisDest(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (MDConfigurationException e) {
			mdFileTmpBusiness.updateCopyPremis(
					mdFilesTmp.getId(),
					gcStart,
					gcEnd,
					false,
					new Exception[] { e },
					null,
					mdFilesTmp.getIdSoftware().getIdIstituzione().getId(), 
					configuration.getSoftwareConfigMDNodi("nodo"), 
					configuration.getMDSoftware().getId()); 
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ nodi.genFilePremisDest(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			mdFileTmpBusiness.updateCopyPremis(
					mdFilesTmp.getId(),
					gcStart,
					gcEnd,
					false,
					new Exception[] { e },
					null,
					mdFilesTmp.getIdSoftware().getIdIstituzione().getId(), 
					configuration.getSoftwareConfigMDNodi("nodo"), 
					configuration.getMDSoftware().getId()); 
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ nodi.genFilePremisDest(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (NodiException e) {
			mdFileTmpBusiness.updateCopyPremis(
					mdFilesTmp.getId(),
					gcStart,
					gcEnd,
					false,
					new Exception[] { e },
					null,
					mdFilesTmp.getIdSoftware().getIdIstituzione().getId(), 
					configuration.getSoftwareConfigMDNodi("nodo"), 
					configuration.getMDSoftware().getId()); 
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ nodi.genFilePremisDest(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (IOException e) {
			mdFileTmpBusiness.updateCopyPremis(
					mdFilesTmp.getId(),
					gcStart,
					gcEnd,
					false,
					new Exception[] { e },
					null,
					mdFilesTmp.getIdSoftware().getIdIstituzione().getId(), 
					configuration.getSoftwareConfigMDNodi("nodo"), 
					configuration.getMDSoftware().getId()); 
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ nodi.genFilePremisDest(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
//		} finally {
//			try {
//				if (fis != null) {
//					fis.close();
//				}
//			} catch (IOException e) {
//				mdFileTmpBusiness.updateCopyPremis(
//						mdFilesTmp.getId(),
//						gcStart,
//						gcEnd,
//						false,
//						new Exception[] { e },
//						null,
//						mdFilesTmp.getIdSoftware().getIdIstituzione().getId(), 
//						configuration.getSoftwareConfigMDNodi("nodo"), 
//						configuration.getMDSoftware().getId()); 
//				premisElab.addEvent(
//						"copyPremis",
//						gcStart,
//						gcEnd,
//						fInput.getAbsolutePath() + " => "
//								+ nodi.genFilePremisDest(), "KO",
//						new String[] { e.getMessage() }, null,
//						configuration.getMDSoftware(),
//						objectIdentifierMaster);
//				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
//			}
		}
		return result;
	}

}
