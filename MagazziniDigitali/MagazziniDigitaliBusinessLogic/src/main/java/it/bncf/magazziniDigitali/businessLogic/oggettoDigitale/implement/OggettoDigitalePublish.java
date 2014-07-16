/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.ObjectComplexType;
import info.lc.xmlns.premis_v2.SignificantPropertiesComplexType;
import it.bncf.magazzimiDigitali.database.entity.MDFilesTmp;
import it.bncf.magazzimiDigitali.databaseSchema.sqlite.MDFilesTmpSqlite;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrEvent;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrObjectFile;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ArchiveMD;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;

import mx.randalf.archive.Tar;
import mx.randalf.archive.info.DigestType;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.tools.Utils;
import mx.randalf.tools.exception.UtilException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class OggettoDigitalePublish implements Callable<Boolean> {

	private Logger log = Logger.getLogger(getClass());

	private MDFilesTmp record = null;

	private Logger logPublish = null;

	private MDFilesTmpSqlite mdFileTmp = null;

	private String name = null;

	private String application = null;

	/**
	 * 
	 */
	public OggettoDigitalePublish(MDFilesTmp record, Logger logPublish, MDFilesTmpSqlite mdFileTmp,
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
			if (record.getStato()
					.equals(MDFilesTmpSqlite.FINEVALID)) {
				logPublish.info(name+" Inizio la pubblicazione del file ["
						+ filePremisMaster.getAbsolutePath() + "]");
				start = mdFileTmp
						.updateStartPublish(record.getId());
			} else {
				logPublish.info(name+" Continuo la pubblicazione del file ["
						+ filePremisMaster.getAbsolutePath() + "]");
				start = OggettoDigitaleBusiness.convertGregorianCalendar(record.getPublishDataStart());
			}

			if (filePremisMaster.exists()) {
				// calcolo il file da validare
				fileObj = Configuration.getValue("istituto."
						+ record.getIdIstituto() + ".pathTmp");
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
				fObjNew = genFileDest(objectIdentifierContainer
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

					premisDest= genFileDest(filePremisMaster.getName());
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
							logPublish.info(name+" Pubblico il materiale in Solr");
							if (publishSolr(premisInput, fObjNew, logPublish)) {
								stop = mdFileTmp.updateStopPublish(
										record.getId(), true, null);
								premisElab
										.addEvent(
												"publish",
												start,
												stop,
												null,
												"OK",
												null,
												null,
												Configuration
														.getValue("demoni."
																+ application
																+ ".UUID"),
												objectIdentifierContainer);
								logPublish.info(name+" Materiale pubblicato");
							} else {
								logPublish.error(name+" Riscontrato un problema nella pubblicazione");
							}
						} else {
							logPublish.error(name+" Riscontrato un problema nello spostamento");
						}
					} else {
						logPublish.error(name+" Riscontrato un problema nella copia");
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
		} catch (SolrException e) {
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
			mdFileTmp.updateStopPublish(record.getId(), false,
					new String[] { e.getMessage() });
			log.error(e.getMessage(), e);
		} finally {
			try {
				premisElab.write(filePremis, false);
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
	 * Metodo utilizzato per la generazione del percorso di Storicizzazione del materiale
	 * 
	 * @param nomeFile Nome del file da Storizzare
	 * @return PErsorso assoluto della risorsa
	 * @throws ConfigurationException
	 */
	private File genFileDest(String nomeFile) throws ConfigurationException {
		String path = null;
		String key = null;

		try {
			path = Configuration.getValue("demoni.Publish.pathStorage")
					+ File.separator;

			key = nomeFile.replace("-", "");

			for (int x = 0; x < 8; x++) {
				path += key.substring(0, 2) + File.separator;
				key = key.substring(2);
			}
			path += nomeFile;
		} catch (ConfigurationException e) {
			throw e;
		}
		return new File(path);
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
			MDFilesTmpSqlite mdFileTmp, PremisXsd premisElab,
			String application, String objectIdentifierMaster, String idIstituto)
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
						Configuration.getValue("istituto." + idIstituto
								+ ".UUID"),
						Configuration.getValue("istituto." + idIstituto
								+ ".machine.UUID"),
						Configuration.getValue("istituto." + idIstituto
								+ ".software.UUID"));
				premisElab
						.addEvent(
								"copyPremis",
								gcStart,
								gcEnd,
								fInput.getAbsolutePath() + " => "
										+ fOutput.getAbsolutePath(),
								(result ? "OK" : "KO"),
								(result ? null
										: new String[] { "Riscontrato un problema durante la copia del file" }),
								null, Configuration.getValue("demoni."
										+ application + ".UUID"),
								objectIdentifierMaster);
			} else {
				result = record.isCopyPremisEsito();
				premisElab.addEvent("copyPremis", OggettoDigitaleBusiness.convertGregorianCalendar(record
						.getCopyPremisDataStart()), OggettoDigitaleBusiness.convertGregorianCalendar(record
						.getCopyPremisDataEnd()), fInput.getAbsolutePath()
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
					Configuration.getValue("istituto." + idIstituto + ".UUID"),
					Configuration.getValue("istituto." + idIstituto
							+ ".machine.UUID"),
					Configuration.getValue("istituto." + idIstituto
							+ ".software.UUID"));
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
					Configuration.getValue("istituto." + idIstituto + ".UUID"),
					Configuration.getValue("istituto." + idIstituto
							+ ".machine.UUID"),
					Configuration.getValue("istituto." + idIstituto
							+ ".software.UUID"));
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
			MDFilesTmpSqlite mdFileTmp, PremisXsd premisElab,
			String application, String objectIdentifierMaster)
			throws SQLException, ConfigurationException {
		boolean result = false;
		GregorianCalendar gcStart = null;
		GregorianCalendar gcEnd = null;

		try {
			if (record.getMoveFileDataStart() == null
					&& record.getMoveFileDataEnd() == null) {
				gcStart = new GregorianCalendar();
				if (!fOutput.getParentFile().exists()) {
					if (!fOutput.getParentFile().mkdirs()) {
						throw new UtilException(
								"Riscontrato un problema nella creazione della cartella ["
										+ fOutput.getParentFile()
												.getAbsolutePath() + "]");
					}
				}
				if (!fInput.renameTo(fOutput)) {
					throw new UtilException(
							"Riscontrato un problema nelli spostamento del file ["
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
				result = record.isCopyPremisEsito();
				premisElab.addEvent("moveFile", OggettoDigitaleBusiness.convertGregorianCalendar(record
						.getMoveFileDataStart()), OggettoDigitaleBusiness.convertGregorianCalendar(record
						.getMoveFileDataEnd()), fInput.getAbsolutePath()
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

	private boolean publishSolr(PremisXsd premis, File fObj, Logger logPublish)
			throws SolrException {
		boolean ris = false;
		List<ObjectComplexType> objects = null;
		List<EventComplexType> events = null;
		ObjectComplexType object = null;
		EventComplexType event = null;
		AddDocumentMD admd = null;
		SolrEvent se = null;
		SolrObjectFile sof = null;
		File pathTar = null;

		try {
			pathTar = new File(Configuration.getValue("demoni.Publish.tmpPath")+
					File.separator+fObj.getName());
			if (!pathTar.exists()){
				if (pathTar.mkdirs()){
					
				}
			}
			Tar.decompress(fObj, pathTar);
			admd = new AddDocumentMD(
					Configuration.getValue("demoni.Publish.solr.URL"),
					Boolean.parseBoolean(Configuration
							.getValue("demoni.Publish.solr.Cloud")),
					Configuration.getValue("demoni.Publish.solr.collection"),
					Integer.parseInt(Configuration
							.getValue("demoni.Publish.solr.connectionTimeOut")),
					Integer.parseInt(Configuration
							.getValue("demoni.Publish.solr.clientTimeOut")));
			objects = premis.getObject();
			if (objects != null && objects.size() > 0) {
				logPublish.info(name+" Oggetto da pubblicare "+objects.size());
				sof = new SolrObjectFile();
				for (int x = 0; x < objects.size(); x++) {
					if ((x%100)==0){
						logPublish.info(name+" Oggetto "+x+"/"+objects.size());
						System.gc();
					}
					object = objects.get(x);
					if (object instanceof info.lc.xmlns.premis_v2.File) {
						sof.publishSolr((info.lc.xmlns.premis_v2.File) object,
								admd, pathTar);
					}
				}
				logPublish.info(name+" Fine pubblicazione oggetti");
			}
			System.gc();
			events = premis.getEvent();
			if (events != null && events.size() > 0) {
				logPublish.info(name+" Eventi da pubblicare "+events.size());
				se = new SolrEvent();
				for (int x = 0; x < events.size(); x++) {
					if ((x%100)==0){
						logPublish.info(name+" Eventi "+x+"/"+events.size());
						System.gc();
					}
					event = events.get(x);
					se.publishSolr(event, admd);
				}
				logPublish.info(name+" Fine pubblicazione eventi");
			}
			logPublish.info(name+" Inizio pubblicazione in Solr");
			System.gc();
			admd.commint();
			logPublish.info(name+" Fine pubblicazione in Solr");
			Thread.sleep(5000);
			ris = true;
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (SolrException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} finally {
			try {
				if (admd != null){
					logPublish.info(name+" Inizio ottimizzazione in Solr");
					admd.optimize();
					logPublish.info(name+" Fine ottimizzazione in Solr");
				} else {
					throw new SolrException("Riscontrato un problema nella connessione al Database");
				}
				if (pathTar.exists()){
					
					if (!deleteFolder(pathTar)){
						throw new SolrException("Problemi nella cancellazione della cartella ["+pathTar.getAbsolutePath()+"]");
					}
				}
			} catch (SolrServerException e) {
				log.error(e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			} finally{
				if (admd != null){
					admd.close();
				}
			}
		}

		return ris;
	}

	private boolean deleteFolder(File path){
		boolean ris = true;
		File[] fl = null;
		fl = path.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().equals(".") ||
						pathname.getName().equals("..")){
					return false;
				} else {
					return true;
				}
			}
		});
		for (int x=0; x<fl.length; x++){
			if (fl[x].isDirectory()){
				if (!deleteFolder(fl[x])){
					ris = false;
				}
			} else {
				if (!fl[x].delete()){
					ris=false;
				}
			}
		}
		if (ris){
			if (!path.delete()){
				ris = false;
			}
		}
		return ris;
	}
}
