/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.io.FileInputStream;
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
import mx.randalf.tools.exception.UtilException;

/**
 * @author massi
 *
 */
public class OggettoDigitalePublishTar extends OggettoDigitale{

	private Logger log = LogManager.getLogger(OggettoDigitalePublishTar.class);

	protected Logger logPublish = null;

	protected String name = null;

	/**
	 * 
	 */
	public OggettoDigitalePublishTar(Logger logPublish, String name) {
		this.logPublish = logPublish;
		this.name = name;
	}

	protected void sendFileTar(
			File fObj
//			, File fObjNew
			, MDFilesTmpBusiness mdFileTmpBusiness
			, MDFilesTmp mdFilesTmp
			, String objectIdentifierContainer
			, String objectIdentifierPremis
			, IMDConfiguration<?> configuration
//			, String application
			, File filePremis
			, Nodi nodi
			, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
			) 
					throws SQLException, MDConfigurationException{
		try {
			if (moveFile(
					fObj
//					, fObjNew
					, mdFilesTmp
					, mdFileTmpBusiness
					, premisElab
//					, application
					, objectIdentifierContainer
					, objectIdentifierPremis
					, configuration
					, filePremis
					, nodi
					)) {
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Spostamento Terminato correttamente");
			} else {
				logPublish.error("\n"+name+" ["+objectIdentifierPremis+"] Riscontrato un problema nello spostamento");
				mdFileTmpBusiness
				.updateStopPublish(
						mdFilesTmp.getId(),
						false,
						null, 
						new String[] { "Riscontrato un problema nello spostamento del file da Archiviare" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (SQLException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		}
	}

	private boolean moveFile(File fInput
//			, File fOutput
			, MDFilesTmp mdFilesTmp
			, MDFilesTmpBusiness mdFileTmpBusiness
			, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
//			, String application
			, String objectIdentifierMaster
			, String objectIdentifierPremis
			, IMDConfiguration<?> configuration
			, File filePremis
			, Nodi nodi
			)
			throws SQLException, MDConfigurationException 
	{
		boolean result = false;
		GregorianCalendar gcStart = null;
		GregorianCalendar gcEnd = null;
//		FileInputStream fis = null;

		try {
			if (mdFilesTmp.getMoveFileDataEnd() == null) {

				gcStart = new GregorianCalendar();
				if ( mdFilesTmp.getMoveFileDataStart() !=null){
					gcStart.setTimeInMillis(mdFilesTmp.getMoveFileDataStart().getTime());
				}

//				fis = new FileInputStream(fInput);
				result = nodi.copyFile(fInput, 
						fInput.length(), 
						MD5Tools.readMD5File(fInput.getAbsolutePath()),
						ENodi.TAR);

//				result = (Utils.copyFileValidate(fInput.getAbsolutePath(),
//						fOutput.getAbsolutePath()));
				if (result){
					if (!fInput.delete()){
						throw new UtilException(
								"Riscontrato un problema nella cancellazione del file ["
										+ fInput.getAbsolutePath() + "]");
					}
				} else {
					throw new UtilException(
							"Riscontrato un problema nella copia del file ["
									+ fInput.getAbsolutePath() + "] in ["
									+ nodi.genFileTarDest() + "]");
				}

				gcEnd = new GregorianCalendar();
				mdFileTmpBusiness.updateMoveFile(mdFilesTmp.getId(), gcStart, gcEnd, true,
						null, null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
				premisElab.addEvent(
						"moveFile",
						gcStart,
						gcEnd,
						fInput.getAbsolutePath() + " => "
								+ nodi.genFileTarDest(),
						"OK",
						null,
						null,
						configuration.getMDSoftware(),
						objectIdentifierMaster);
				result = true;
			} else {
				result = mdFilesTmp.getCopyPremisEsito();
				
				gcStart = new GregorianCalendar();
				if ( mdFilesTmp.getMoveFileDataStart() !=null){
					gcStart.setTimeInMillis(mdFilesTmp.getMoveFileDataStart().getTime());
				}
				
				gcEnd = new GregorianCalendar();
				if ( mdFilesTmp.getMoveFileDataEnd() !=null){
					gcEnd.setTimeInMillis(mdFilesTmp.getMoveFileDataEnd().getTime());
				}
				
				mdFileTmpBusiness.updateMoveFile(mdFilesTmp.getId(), gcStart, gcEnd, result,
						null, null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
				
				premisElab.addEvent("moveFile", mdFilesTmp.getMoveFileDataStart(), 
						mdFilesTmp.getMoveFileDataEnd(), fInput.getAbsolutePath()
						+ " => " + nodi.genFileTarDest(), (result ? "OK"
						: "KO"), null, null, 
						configuration.getMDSoftware(),
						objectIdentifierMaster);
			}
		} catch (UtilException |NoSuchAlgorithmException | NodiException | IOException e) {
			mdFileTmpBusiness.updateMoveFile(mdFilesTmp.getId(), gcStart, gcEnd, false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			premisElab.addEvent(
					"moveFile",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ nodi.genFileTarDest(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}
		return result;
	}

}
