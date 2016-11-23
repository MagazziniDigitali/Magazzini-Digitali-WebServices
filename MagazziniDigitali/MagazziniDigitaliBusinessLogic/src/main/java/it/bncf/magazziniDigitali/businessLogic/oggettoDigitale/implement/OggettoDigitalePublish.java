/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ArchiveMD;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.utils.GenFileDest;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.archive.info.DigestType;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.Utils;
import mx.randalf.tools.exception.UtilException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class OggettoDigitalePublish extends OggettoDigitale{

	private Logger log = Logger.getLogger(getClass());

	private Logger logPublish = null;

	private String name = null;

	private PremisXsd premisElab = null;
	
	/**
	 * 
	 */
	public OggettoDigitalePublish(
			Logger logPublish, String name) {
		this.logPublish = logPublish;
		this.name = name;
	}

	public boolean esegui(String objectIdentifierPremis, String application, IMDConfiguration<?> configuration) 
			throws MDConfigurationException, SQLException 
	{
		Boolean ris = false;
		File filePremis = null;
//		String objectIdentifierPremis = null;
//		GregorianCalendar stop = null;
		MDFilesTmpBusiness mdFileTmpBusiness = null;
		MDFilesTmpDAO mdFileTmpDao= null;
		MDFilesTmp mdFilesTmp = null;

		logPublish.info(name+" ["+objectIdentifierPremis+"] Inizio la pubblicazione");
		try {
			mdFileTmpBusiness = new MDFilesTmpBusiness();
			mdFileTmpDao = new MDFilesTmpDAO();
			mdFilesTmp = mdFileTmpDao.findById(objectIdentifierPremis);
			
			if (mdFilesTmp.getPublishPremis() == null || 
					!mdFilesTmp.getPublishPremis().trim().equals("")) {
				filePremis = new File(
						genFilePremis(
								configuration.getSoftwareConfigString("path.premis"), 
								"Publish",
								UUID.randomUUID().toString()));
			} else {
				filePremis = new File(
						genFilePremis(configuration.getSoftwareConfigString("path.premis"),
								mdFilesTmp.getPublishPremis()));
			}
		} catch (HibernateException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}

		try {

			if (mdFilesTmp != null){
				
				validate(configuration, mdFileTmpBusiness, mdFilesTmp, 
						objectIdentifierPremis, application, filePremis);

			} else {
				logPublish.error(name+" ["+objectIdentifierPremis+"] Il file premis ["+objectIdentifierPremis+"] non è presente in archivio");
				ris = false;
			}
		} catch (MDConfigurationException e) {
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware(),
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), 
						null);
			}
			mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware(),
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), 
						null);
			}
			mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware(),
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), 
						null);
			}
			mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware(), 
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), 
						null);
			}
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
			mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
		} finally {
			try {
				if (premisElab != null){
					premisElab.write(filePremis, false);
				}
			} catch (PremisXsdException e) {
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (XsdException e) {
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (IOException e) {
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (Exception e) {
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} finally {
				logPublish.info(name+" ["+objectIdentifierPremis+"] Fine della pubblicazione");
			}
		}
		
		return ris;
	}

	private void validate(IMDConfiguration<?> configuration, MDFilesTmpBusiness mdFileTmpBusiness,
			MDFilesTmp mdFilesTmp, String objectIdentifierPremis, String application, File filePremis) 
					throws HibernateException, MDConfigurationException, HibernateUtilException,
						SQLException, XsdException{
		File filePremisMaster = null;
		GregorianCalendar start = null;
		String fileObj = null;
		File fObj = null;
		PremisXsd premisInput = null;
		String objectIdentifierContainer = null;
		int pos = 0;
		String ext = null;
		File fObjNew = null;
		File fNodo = null;

		try {
			filePremisMaster = new File(genFilePremis(
							configuration.getSoftwareConfigString("path.premis"),
							mdFilesTmp.getPremisFile()));
			
			FactoryDAO.initialize(mdFilesTmp.getStato());
			if (mdFilesTmp.getStato().getId()
					.equals(MDStatoDAO.FINEVALID)) {
				logPublish.info(name+" ["+objectIdentifierPremis+"] Inizio la pubblicazione del file ["
						+ filePremisMaster.getAbsolutePath() + "]");
				start = mdFileTmpBusiness
						.updateStartPublish(mdFilesTmp.getId());
			} else {
				logPublish.info(name+" ["+objectIdentifierPremis+"] Continuo la pubblicazione del file ["
						+ filePremisMaster.getAbsolutePath() + "]");
				start = new GregorianCalendar();
				start.setTimeInMillis(mdFilesTmp.getPublishDataStart().getTime());
			}

			if (filePremisMaster.exists()) {
				// calcolo il file da validare
				FactoryDAO.initialize(mdFilesTmp.getIdIstituto());
				if (mdFilesTmp.getTarTmpFile() ==null){
					fileObj = configuration.getSoftwareConfigString("path.tar");// TODO: da AggiornaremdFilesTmp.getIdSoftware().getPathTar();
					fileObj += File.separator;
					fileObj += mdFilesTmp.getIdIstituto().getId();
					fileObj += File.separator;
					fileObj += mdFilesTmp.getNomeFile();
				} else {
					fileObj = mdFilesTmp.getTarTmpFile();
				}
				fObj = new File(fileObj);
				logPublish.info(name+" ["+objectIdentifierPremis+"] fileObj: " + fObj.getAbsolutePath());
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

				fNodo = new File(configuration.getSoftwareConfigMDNodi("nodo").getPathStorage()+File.separator+"storage.id");
				if (fNodo.exists()){
					fObjNew = GenFileDest.genFileDest(
							configuration.getSoftwareConfigMDNodi("nodo")
							//Configuration.getValue("demoni.Publish.pathStorage")
							,objectIdentifierContainer
							+ ext);
					verifyExistsFile(fObj, mdFileTmpBusiness, mdFilesTmp, 
							fObjNew, filePremisMaster, configuration, 
							objectIdentifierPremis, application, 
							objectIdentifierContainer, filePremis);
				} else {
					logPublish.error(name+" ["+objectIdentifierPremis+"] Non risulta presente l'archivio di Storage ["
							+ fNodo.getParentFile()
									.getAbsolutePath()
							+ "] dalla verifica del file ["+fNodo.getName()+"]");
					mdFileTmpBusiness.updateStopPublish(
							mdFilesTmp.getId(),
							false,
							null, 
							new String[] { "Non risulta presente l'archivio di Storage ["
											+ fNodo.getParentFile()
													.getAbsolutePath()
											+ "] dalla verifica del file ["+fNodo.getName()+"]" },
							writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
				}
			} else {
				logPublish.error(name+" ["+objectIdentifierPremis+"] Il file ["
								+ filePremisMaster
										.getAbsolutePath()
								+ "] non è presente sul Server");
				mdFileTmpBusiness.updateStopPublish(
						mdFilesTmp.getId(),
						false,
						null, 
						new String[] { "Il file ["
								+ filePremisMaster
										.getAbsolutePath()
								+ "] non è presente sul Server" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (HibernateException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		}
	}

	private void verifyExistsFile(File fObj, MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp, File fObjNew, 
			File filePremisMaster, IMDConfiguration<?> configuration,
			String objectIdentifierPremis, String application, 
			String objectIdentifierContainer, File filePremis) 
					throws MDConfigurationException, SQLException{
		ValidateFile validate = null;
		ArchiveMD archive = null;
		File premisDest = null;
		File fNodo = null;

		try {
			validate = new ValidateFile();
			if (isFileExist(fObj, mdFilesTmp, fObjNew)) {

				premisElab = new PremisXsd();

				validate.check(filePremisMaster, null, configuration, null, false);

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

				fNodo = new File(configuration.getSoftwareConfigMDNodi("nodo").getPathStorage()+File.separator+"storage.id");
				if (fNodo.exists()){
					premisDest= GenFileDest.genFileDest(configuration.getSoftwareConfigMDNodi("nodo")
							//Configuration.getValue("demoni.Publish.pathStorage")
							,filePremisMaster.getName());
					logPublish.info(name+" ["+objectIdentifierPremis+"] Copio il file "+
								filePremisMaster.getAbsolutePath()+
								" in "+
								premisDest.getAbsolutePath());
					executeCopy(filePremisMaster, premisDest, mdFileTmpBusiness, mdFilesTmp,
							application, objectIdentifierPremis, configuration, fObj, 
							fObjNew, objectIdentifierContainer, filePremis);
				} else {
					logPublish.error(name+" ["+objectIdentifierPremis+"] Non risulta presente l'archivio di Storage ["
							+ fNodo.getParentFile()
									.getAbsolutePath()
							+ "] dalla verifica del file ["+fNodo.getName()+"]");
					mdFileTmpBusiness.updateStopPublish(
							mdFilesTmp.getId(),
							false,
							null,
							new String[] { "Non risulta presente l'archivio di Storage ["
											+ fNodo.getParentFile()
													.getAbsolutePath()
											+ "] dalla verifica del file ["+fNodo.getName()+"]" },
							writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
				}

			} else {
				logPublish.error(name+" ["+objectIdentifierPremis+"] Il file ["
										+ fObj.getAbsolutePath()
										+ "] non è presente sul Server");
				mdFileTmpBusiness
						.updateStopPublish(
								mdFilesTmp.getId(),
								false,
								null, 
								new String[] { "Il file ["
										+ fObj.getAbsolutePath()
										+ "] non è presente sul Server" },
								writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (MDConfigurationException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
	}

	private void executeCopy(File filePremisMaster, File premisDest,
			MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp,
			String application, String objectIdentifierPremis,
			IMDConfiguration<?> configuration, File fObj, File fObjNew, 
			String objectIdentifierContainer, File filePremis) 
					throws SQLException, MDConfigurationException{
		try {
			// Copio il file premis nella sua posizione
			// definitiva
			if (copyFile(
					filePremisMaster,
					premisDest,
					mdFilesTmp, mdFileTmpBusiness, premisElab,
					application, objectIdentifierPremis, 
					mdFilesTmp.getIdSoftware(), objectIdentifierPremis, configuration)) {
				
				logPublish.info(name+" ["+objectIdentifierPremis+"] Sposto il file  "+
						fObj.getAbsolutePath()+
						" in "+
						fObjNew.getAbsolutePath());
				executeMove(fObj, fObjNew, mdFileTmpBusiness, mdFilesTmp, 
						objectIdentifierContainer, objectIdentifierPremis,
						configuration, application, filePremis);
			} else {
				logPublish.error(name+" ["+objectIdentifierPremis+"] Riscontrato un problema nella copia");
				mdFileTmpBusiness
				.updateStopPublish(
						mdFilesTmp.getId(),
						false,
						null, 
						new String[] { "Riscontrato un problema nella copia del file Premis" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (SQLException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		}

	}

	private void executeMove(File fObj, File fObjNew, MDFilesTmpBusiness mdFileTmpBusiness, 
			MDFilesTmp mdFilesTmp, String objectIdentifierContainer, String objectIdentifierPremis,
			IMDConfiguration<?> configuration, String application, File filePremis) 
					throws SQLException, MDConfigurationException{
		try {
			if (moveFile(fObj, fObjNew, mdFilesTmp,
					mdFileTmpBusiness, premisElab, application,
					objectIdentifierContainer, objectIdentifierPremis, configuration, filePremis)) {
				logPublish.info(name+" ["+objectIdentifierPremis+"] Spostamento Terminato correttamente");
			} else {
				logPublish.error(name+" ["+objectIdentifierPremis+"] Riscontrato un problema nello spostamento");
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
			MDFilesTmpBusiness mdFileTmpBusiness, PremisXsd premisElab,
			String application, String objectIdentifierMaster, MDSoftware idIstituto,
			String objectIdentifierPremis, IMDConfiguration<?> configuration)
					throws SQLException, MDConfigurationException 
	{
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
				mdFileTmpBusiness.updateCopyPremis(
						record.getId(),
						gcStart,
						gcEnd,
						true,
						null,
						null,
						record.getIdSoftware().getIdIstituzione().getId(), // TODO: da aggiustare idIstituto.getUuid(), //ID Istituto depositante
						configuration.getSoftwareConfigMDNodi("nodo"), // TODO: da aggiustare idIstituto.getMachineUuid(), //ID del Nodo 
						configuration.getMDSoftware().getId()); // TODO: da aggiustare idIstituto.getSoftwareUuid()); // ID Software ATT...
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
								null, 
								configuration.getMDSoftware(),
//								Configuration.getValue("demoni."
//										+ application + ".UUID"),
								objectIdentifierMaster);
			} else {
				result = record.getCopyPremisEsito();
				premisElab.addEvent("copyPremis", record.getCopyPremisDataStart(), 
						record.getCopyPremisDataEnd(), fInput.getAbsolutePath()
						+ " => " + fOutput.getAbsolutePath(), (result ? "OK"
						: "KO"), null, null, configuration.getMDSoftware(),
//						Configuration.getValue("demoni."
//						+ application + ".UUID"), 
						objectIdentifierMaster);
			}
		} catch (UtilException e) {
			mdFileTmpBusiness.updateCopyPremis(
					record.getId(),
					gcStart,
					gcEnd,
					false,
					new Exception[] { e },
					null,
					record.getIdSoftware().getIdIstituzione().getId(), // TODO: da aggiustare idIstituto.getUuid(), //ID Istituto depositante
					configuration.getSoftwareConfigMDNodi("nodo"), // TODO: da aggiustare idIstituto.getMachineUuid(), //ID del Nodo 
					configuration.getMDSoftware().getId()); // TODO: da aggiustare idIstituto.getSoftwareUuid()); // ID Software ATT...
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ fOutput.getAbsolutePath(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
//					Configuration.getValue("demoni." + application + ".UUID"),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (MDConfigurationException e) {
			mdFileTmpBusiness.updateCopyPremis(
					record.getId(),
					gcStart,
					gcEnd,
					false,
					new Exception[] { e },
					null,
					record.getIdSoftware().getIdIstituzione().getId(), // TODO: da aggiustare idIstituto.getUuid(), //ID Istituto depositante
					configuration.getSoftwareConfigMDNodi("nodo"), // TODO: da aggiustare idIstituto.getMachineUuid(), //ID del Nodo 
					configuration.getMDSoftware().getId()); // TODO: da aggiustare idIstituto.getSoftwareUuid()); // ID Software ATT...
			premisElab.addEvent(
					"copyPremis",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ fOutput.getAbsolutePath(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
//					Configuration.getValue("demoni." + application + ".UUID"),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}
		return result;
	}

	private boolean moveFile(File fInput, File fOutput, MDFilesTmp record,
			MDFilesTmpBusiness mdFileTmp, PremisXsd premisElab,
			String application, String objectIdentifierMaster, String objectIdentifierPremis,
			IMDConfiguration<?> configuration, File filePremis)
			throws SQLException, MDConfigurationException 
	{
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
						null, null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
				premisElab.addEvent(
						"moveFile",
						gcStart,
						gcEnd,
						fInput.getAbsolutePath() + " => "
								+ fOutput.getAbsolutePath(),
						"OK",
						null,
						null,
						configuration.getMDSoftware(),
//						Configuration.getValue("demoni." + application
//								+ ".UUID"), 
						objectIdentifierMaster);
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
						null, null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
				
				premisElab.addEvent("moveFile", record.getMoveFileDataStart(), 
						record.getMoveFileDataEnd(), fInput.getAbsolutePath()
						+ " => " + fOutput.getAbsolutePath(), (result ? "OK"
						: "KO"), null, null, 
						configuration.getMDSoftware(),
//						Configuration.getValue("demoni."
//						+ application + ".UUID"), 
						objectIdentifierMaster);
			}
		} catch (UtilException e) {
			mdFileTmp.updateMoveFile(record.getId(), gcStart, gcEnd, false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			premisElab.addEvent(
					"moveFile",
					gcStart,
					gcEnd,
					fInput.getAbsolutePath() + " => "
							+ fOutput.getAbsolutePath(), "KO",
					new String[] { e.getMessage() }, null,
					configuration.getMDSoftware(),
//					Configuration.getValue("demoni." + application + ".UUID"),
					objectIdentifierMaster);
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}
		return result;
	}
}
