/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import info.lc.xmlns.premis_v2.SignificantPropertiesComplexType;
import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ArchiveMD;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.utils.GenFileDest;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.concurrent.Callable;

import mx.randalf.archive.info.DigestType;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.tools.Utils;
import mx.randalf.tools.exception.UtilException;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;

/**
 * @author massi
 *
 */
public class OggettoDigitalePublish implements Callable<Boolean> {

	private Logger log = Logger.getLogger(getClass());

	private MDFilesTmp record = null;

	private Logger logPublish = null;

	private MDFilesTmpBusiness mdFileTmp = null;

	private String name = null;

	private String application = null;

	/**
	 * 
	 */
	public OggettoDigitalePublish(MDFilesTmp record, Logger logPublish, MDFilesTmpBusiness mdFileTmp,
			String name, String application) {
		this.record = record;
		this.logPublish = logPublish;
		this.mdFileTmp = mdFileTmp;
		this.name = name;
		this.application = application;
	}

	@SuppressWarnings("unused")
	@Override
	public Boolean call() throws Exception {
		Boolean ris = false;
		File filePremis = null;
		File filePremisMaster = null;
		GregorianCalendar start = null;
		String fileObj = null;
		File fObj = null;
		PremisXsd premisInput = null;
		String objectIdentifierContainer = null;
		int pos = 0;
		String ext = null;
		File fObjNew = null;
		PremisXsd premisElab = null;
		ValidateFile validate = null;
		ArchiveMD archive = null;
		String objectIdentifierPremis = null;
		File premisDest = null;
		GregorianCalendar stop = null;

		filePremis = new File(Configuration.getValue("path.premis")
				+ File.separator + UUID.randomUUID().toString()
				+ ".premis");
		try {

			validate = new ValidateFile();
			filePremisMaster = new File(
					Configuration.getValue("path.premis")
							+ File.separator
							+ record.getPremisFile());
			if (record.getStato().getId()
					.equals(MDStatoDAO.FINEVALID)) {
				logPublish.info(name+" Inizio la pubblicazione del file ["
						+ filePremisMaster.getAbsolutePath() + "]");
				start = mdFileTmp
						.updateStartPublish(record.getId());
			} else {
				logPublish.info(name+" Continuo la pubblicazione del file ["
						+ filePremisMaster.getAbsolutePath() + "]");
				start = new GregorianCalendar();
				start.setTimeInMillis(record.getPublishDataStart().getTime());
			}

			if (filePremisMaster.exists()) {
				// calcolo il file da validare
				fileObj = record.getIdIstituto().getPathTmp();
				fileObj += File.separator;
				fileObj += record.getNomeFile();
				fObj = new File(fileObj);
				logPublish.info(name+" fileObj: " + fObj.getAbsolutePath());
				if (!fObj.exists()) {
					fObj = new File(fObj.getParentFile()
							.getAbsolutePath()
							+ File.separator
							+ fObj.getName()
									.replace(".tar.gz", ".tar")
									.replace(".tgz", ".tar"));
				}

				premisInput = new PremisXsd(filePremisMaster);

				objectIdentifierContainer = findObjectIdentifierContainer(premisInput);
				
				pos = fObj.getName().indexOf(".");
				ext = fObj.getName().substring(pos);
				fObjNew = GenFileDest.genFileDest(Configuration.getValue("demoni.Publish.pathStorage")
						,objectIdentifierContainer
						+ ext);
				if (isFileExist(fObj, record, fObjNew)) {

					premisElab = new PremisXsd();

					validate.check(filePremisMaster);

					if (validate.getArchive().checkMimetype(
							"application/x-tar")) {
						archive = validate.getArchive();
					} else {
						archive = validate.getArchive();
						if (archive.getArchive() != null
								&& archive.getArchive().size() > 0) {
							archive = (ArchiveMD) archive
									.getArchive().get(0);
						}
					}
					objectIdentifierPremis = archive.getID();
					premisElab
							.addObjectFileContainer(
									objectIdentifierPremis,
									(archive.getXmltype() == null ? null
											: archive.getXmltype()
													.value()),
									archive.getType().getExt(),
									new BigInteger("0"),
									archive.getDigest(DigestType.SHA_1),
									archive.getType().getSize(),
									archive.getMimetype(), archive
											.getNome(), null,
									archive.getType().getFormat()
											.getVersion(), archive
											.getType().getPUID());

					premisDest= GenFileDest.genFileDest(Configuration.getValue("demoni.Publish.pathStorage")
							,filePremisMaster.getName());
					logPublish.info(name+" Copio il file "+
								filePremisMaster.getAbsolutePath()+
								" in "+
								premisDest.getAbsolutePath());
					// Copio il file premis nella sua posizione
					// definitiva
					if (copyFile(
							filePremisMaster,
							premisDest,
							record, mdFileTmp, premisElab,
							application, objectIdentifierPremis, 
								record.getIdIstituto())) {
						
						logPublish.info(name+" Sposto il file  "+
								fObj.getAbsolutePath()+
								" in "+
								fObjNew.getAbsolutePath());
						if (moveFile(fObj, fObjNew, record,
								mdFileTmp, premisElab, application,
								objectIdentifierContainer)) {
							logPublish.info(name+" Spostamento Terminato correttamente");
						} else {
							logPublish.error(name+" Riscontrato un problema nello spostamento");
							mdFileTmp
							.updateStopPublish(
									record.getId(),
									false,
									new String[] { "Riscontrato un problema nello spostamento del file da Archiviare" });
						}
					} else {
						logPublish.error(name+" Riscontrato un problema nella copia");
						mdFileTmp
						.updateStopPublish(
								record.getId(),
								false,
								new String[] { "Riscontrato un problema nella copia del file Premis" });
					}
				} else {
					logPublish.error(name+" Il file ["
											+ fObj.getAbsolutePath()
											+ "] non è presente sul Server");
					mdFileTmp
							.updateStopPublish(
									record.getId(),
									false,
									new String[] { "Il file ["
											+ fObj.getAbsolutePath()
											+ "] non è presente sul Server" });
				}
			} else {
				mdFileTmp.updateStopPublish(
						record.getId(),
						false,
						new String[] { "Il file ["
								+ filePremisMaster
										.getAbsolutePath()
								+ "] non è presente sul Server" });
			}
		} catch (ConfigurationException e) {
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						Configuration.getValue("demoni."
								+ application + ".UUID"), null);
			}
			mdFileTmp.updateStopPublish(record.getId(), false,
					new String[] { e.getMessage() });
			log.error(e.getMessage(), e);
		} catch (SQLException e) {
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						Configuration.getValue("demoni."
								+ application + ".UUID"), null);
			}
			mdFileTmp.updateStopPublish(record.getId(), false,
					new String[] { e.getMessage() });
			log.error(e.getMessage(), e);
		} catch (XsdException e) {
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						Configuration.getValue("demoni."
								+ application + ".UUID"), null);
			}
			mdFileTmp.updateStopPublish(record.getId(), false,
					new String[] { e.getMessage() });
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						Configuration.getValue("demoni."
								+ application + ".UUID"), null);
			}
			log.error(e.getMessage(), e);
			mdFileTmp.updateStopPublish(record.getId(), false,
					new String[] { e.getMessage() });
		} finally {
			try {
				if (premisElab != null){
					premisElab.write(filePremis, false);
				}
			} catch (PremisXsdException e) {
				log.error(e.getMessage(), e);
				mdFileTmp.updateStopPublish(record.getId(),
						false, new String[] { e.getMessage() });
			} catch (XsdException e) {
				log.error(e.getMessage(), e);
				mdFileTmp.updateStopPublish(record.getId(),
						false, new String[] { e.getMessage() });
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				mdFileTmp.updateStopPublish(record.getId(),
						false, new String[] { e.getMessage() });
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				mdFileTmp.updateStopPublish(record.getId(),
						false, new String[] { e.getMessage() });
			}
		}
		
		return ris;
	}

	/**
	 * Metodo utilizzato per ricamare l'identificativo dell'Oggetto dal tracciato Premis
	 * 
	 * @param premis Tracciato Premis da analizzare
	 * @return Valore individuato
	 */
	private String findObjectIdentifierContainer(PremisXsd premis) {
		String objectIdentifierContainer = null;
		info.lc.xmlns.premis_v2.File file = null;
		SignificantPropertiesComplexType significantProprties = null;
		String key = null;

		if (premis.getObject() != null) {
			for (int x = 0; x < premis.getObject().size(); x++) {
				if (premis.getObject().get(x) instanceof info.lc.xmlns.premis_v2.File) {
					file = (info.lc.xmlns.premis_v2.File) premis.getObject()
							.get(x);
					if (file.getSignificantProperties() != null) {
						for (int y = 0; y < file.getSignificantProperties()
								.size(); y++) {
							significantProprties = file
									.getSignificantProperties().get(y);
							if (significantProprties.getContent() != null) {
								for (int z = 0; z < significantProprties
										.getContent().size(); z++) {
									key = (String) significantProprties
											.getContent().get(z).getValue();
									if (key.equals("ActualFileName")) {
										if (file.getObjectIdentifier() != null) {
											for (int a = 0; a < file
													.getObjectIdentifier()
													.size(); a++) {
												if (file.getObjectIdentifier()
														.get(a)
														.getObjectIdentifierType()
														.equals("UUID-MD-OBJ")) {
													objectIdentifierContainer = file
															.getObjectIdentifier()
															.get(a)
															.getObjectIdentifierValue();
													break;
												}
											}
											if (objectIdentifierContainer != null) {
												break;
											}
										}
									}
								}
								if (objectIdentifierContainer != null) {
									break;
								}
							}
						}
						if (objectIdentifierContainer != null) {
							break;
						}
					}
				}
			}
		}
		return objectIdentifierContainer;
	}

	/**
	 * Metodo utilizzato per verificare la presenza del file di destinazione nelle diverse condizioni di elaborazione
	 * 
	 * @param fObj
	 * @param record
	 * @param fObjNew
	 * @return
	 */
	private boolean isFileExist(File fObj, MDFilesTmp record, File fObjNew){
		boolean ris = false;
		
		if (fObj.exists()) {
			ris=true;
		} else {
			if (record.getMoveFileDataStart() != null
					&& record.getMoveFileDataEnd() != null) {
				if (fObjNew.exists()){
					ris =  true;
				}
			}
		}
		
		return ris;
	}

	private boolean copyFile(File fInput, File fOutput, MDFilesTmp record,
			MDFilesTmpBusiness mdFileTmp, PremisXsd premisElab,
			String application, String objectIdentifierMaster, MDIstituzione idIstituto)
			throws SQLException, ConfigurationException {
		boolean result = false;
		GregorianCalendar gcStart = null;
		GregorianCalendar gcEnd = null;

		try {
			if (record.getCopyPremisDataStart() == null
					&& record.getCopyPremisDataEnd() == null) {
				gcStart = new GregorianCalendar();
				result = (Utils.copyFileValidate(fInput.getAbsolutePath(),
						fOutput.getAbsolutePath()));
				gcEnd = new GregorianCalendar();
				mdFileTmp.updateCopyPremis(
						record.getId(),
						gcStart,
						gcEnd,
						true,
						null,
						idIstituto.getUuid(),
						idIstituto.getMachineUuid(),
						idIstituto.getSoftwareUuid());
				premisElab
						.addEvent(
								"copyPremis",
								gcStart,
								gcEnd,
								fInput.getAbsolutePath() + " => "
										+ fOutput.getAbsolutePath(),
								(result ? "OK" : "KO"),
								(result ? null
										: new String[] { "Riscontrato un problema durante la copia del file ["+
								fInput.getAbsolutePath()+"] in ["+fOutput.getAbsolutePath()+"]"}),
								null, Configuration.getValue("demoni."
										+ application + ".UUID"),
								objectIdentifierMaster);
			} else {
				result = record.getCopyPremisEsito();
				premisElab.addEvent("copyPremis", record.getCopyPremisDataStart(), 
						record.getCopyPremisDataEnd(), fInput.getAbsolutePath()
						+ " => " + fOutput.getAbsolutePath(), (result ? "OK"
						: "KO"), null, null, Configuration.getValue("demoni."
						+ application + ".UUID"), objectIdentifierMaster);
			}
		} catch (UtilException e) {
			mdFileTmp.updateCopyPremis(
					record.getId(),
					gcStart,
					gcEnd,
					false,
					new String[] { e.getMessage() },
					idIstituto.getUuid(),
					idIstituto.getMachineUuid(),
					idIstituto.getSoftwareUuid());
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ fOutput.getAbsolutePath(), "KO",
					new String[] { e.getMessage() }, null,
					Configuration.getValue("demoni." + application + ".UUID"),
					objectIdentifierMaster);
			log.error(e.getMessage(), e);
		} catch (ConfigurationException e) {
			mdFileTmp.updateCopyPremis(
					record.getId(),
					gcStart,
					gcEnd,
					false,
					new String[] { e.getMessage() },
					idIstituto.getUuid(),
					idIstituto.getMachineUuid(),
					idIstituto.getSoftwareUuid());
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ fOutput.getAbsolutePath(), "KO",
					new String[] { e.getMessage() }, null,
					Configuration.getValue("demoni." + application + ".UUID"),
					objectIdentifierMaster);
			log.error(e.getMessage(), e);
		}
		return result;
	}

	private boolean moveFile(File fInput, File fOutput, MDFilesTmp record,
			MDFilesTmpBusiness mdFileTmp, PremisXsd premisElab,
			String application, String objectIdentifierMaster)
			throws SQLException, ConfigurationException {
		boolean result = false;
		GregorianCalendar gcStart = null;
		GregorianCalendar gcEnd = null;

		try {
			if (record.getMoveFileDataEnd() == null) {

				gcStart = new GregorianCalendar();
				if ( record.getMoveFileDataStart() !=null){
					gcStart.setTimeInMillis(record.getMoveFileDataStart().getTime());
				}
				
				result = (Utils.copyFileValidate(fInput.getAbsolutePath(),
						fOutput.getAbsolutePath()));
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
									+ fOutput.getAbsolutePath() + "]");
				}

				gcEnd = new GregorianCalendar();
				mdFileTmp.updateMoveFile(record.getId(), gcStart, gcEnd, true,
						null);
				premisElab.addEvent(
						"moveFile",
						gcStart,
						gcEnd,
						fInput.getAbsolutePath() + " => "
								+ fOutput.getAbsolutePath(),
						"OK",
						null,
						null,
						Configuration.getValue("demoni." + application
								+ ".UUID"), objectIdentifierMaster);
				result = true;
			} else {
				result = record.getCopyPremisEsito();
				
				gcStart = new GregorianCalendar();
				if ( record.getMoveFileDataStart() !=null){
					gcStart.setTimeInMillis(record.getMoveFileDataStart().getTime());
				}
				
				gcEnd = new GregorianCalendar();
				if ( record.getMoveFileDataEnd() !=null){
					gcEnd.setTimeInMillis(record.getMoveFileDataEnd().getTime());
				}
				
				mdFileTmp.updateMoveFile(record.getId(), gcStart, gcEnd, result,
						null);
				
				premisElab.addEvent("moveFile", record.getMoveFileDataStart(), 
						record.getMoveFileDataEnd(), fInput.getAbsolutePath()
						+ " => " + fOutput.getAbsolutePath(), (result ? "OK"
						: "KO"), null, null, Configuration.getValue("demoni."
						+ application + ".UUID"), objectIdentifierMaster);
			}
		} catch (UtilException e) {
			mdFileTmp.updateMoveFile(record.getId(), gcStart, gcEnd, false,
					new String[] { e.getMessage() });
			premisElab.addEvent(
					"moveFile",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ fOutput.getAbsolutePath(), "KO",
					new String[] { e.getMessage() }, null,
					Configuration.getValue("demoni." + application + ".UUID"),
					objectIdentifierMaster);
			log.error(e.getMessage(), e);
		} catch (ConfigurationException e) {
			mdFileTmp.updateMoveFile(record.getId(), gcStart, gcEnd, false,
					new String[] { e.getMessage() });
			premisElab.addEvent(
					"moveFile",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ fOutput.getAbsolutePath(), "KO",
					new String[] { e.getMessage() }, null,
					Configuration.getValue("demoni." + application + ".UUID"),
					objectIdentifierMaster);
			log.error(e.getMessage(), e);
		}
		return result;
	}
}
