/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import info.lc.xmlns.premis_v2.CHECKSUMTYPEDefinition;
import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.exception.ClientMDException;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.implement.ArchiveMD;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.magazziniDigitali.xsd.premis.PremisDigest;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.archive.info.DigestType;
import mx.randalf.archive.info.Type;
import mx.randalf.archive.info.Xmltype;
//import mx.randalf.configuration.Configuration;
//import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class OggettoDigitaleValidate extends OggettoDigitale {

	private Logger log = LogManager.getLogger(getClass());

	private Logger logValidate = null;

	private String name = null;

	private File filePremis = null;

	/**
	 * 
	 */
	public OggettoDigitaleValidate(Logger logValidate, String name) {
		this.logValidate = logValidate;
		this.name = name;
	}

	/**
	 * @throws SQLException
	 * @throws MDConfigurationException 
	 * @see java.util.concurrent.Callable#call()
	 */
	public boolean esegui(String objectIdentifierPremis, String application, IMDConfiguration<?> configuration)
			throws SQLException, MDConfigurationException {
		// String fPremis = null;
		MDFilesTmpBusiness mdFileTmpBusiness = null;
		MDFilesTmpDAO mdFileTmpDao = null;
		MDFilesTmp mdFilesTmp = null;
		Boolean ris = false;

		logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Inizio la validazione");
		try {
			mdFileTmpBusiness = new MDFilesTmpBusiness();
			mdFileTmpDao = new MDFilesTmpDAO();
			mdFilesTmp = mdFileTmpDao.findById(objectIdentifierPremis);
		} catch (HibernateException e) {
			log.error(name + " [" + objectIdentifierPremis + "]" + " " + e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(name + " [" + objectIdentifierPremis + "]" + " " + e.getMessage(), e);
		}

		try {
			ris = checkFileArchive(mdFileTmpBusiness, mdFilesTmp, configuration, objectIdentifierPremis, ris);
		} catch (SQLException e) {
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (PremisXsdException e) {
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (XsdException e) {
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (IOException e) {
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (HibernateException e) {
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (HibernateUtilException e) {
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (MDConfigurationException e) {
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (ClientMDException e) {
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (Exception e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, new Exception[] { e },
					null, writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
		} finally {
			logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Fine della validazione");
		}
		return ris;
	}

	private Boolean checkFileArchive(MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp,
			IMDConfiguration<?> configuration, String objectIdentifierPremis, Boolean ris)
			throws HibernateException, HibernateUtilException, MDConfigurationException, ClientMDException,
			SQLException, PremisXsdException, XsdException, IOException {
		String fileObj = null;
		String fileTar = null;
		File fObj = null;
		File fTar = null;
		ValidateFile validate = null;
		String eventDetailDecomp = null;
		Boolean removeOrigin=true;

		try {
			if (mdFilesTmp != null) {
				validate = new ValidateFile();
				// fPremis = null;

				// calcolo il file da validare
				if (mdFilesTmp.getIdSoftware() != null){
					FactoryDAO.initialize(mdFilesTmp.getIdSoftware());
					FactoryDAO.initialize(mdFilesTmp.getIdSoftware().getIdIstituzione());
					if (mdFilesTmp.getIdSoftware().getIdIstituzione().getPathTmp() == null
							|| mdFilesTmp.getIdSoftware().getIdIstituzione().getPathTmp().equals("")) {
						throw new ClientMDException("Non è stata indicata la Path Temporanea per L'istituto");
					}
					fileObj = mdFilesTmp.getIdSoftware().getIdIstituzione().getPathTmp(); 
				} else {
					FactoryDAO.initialize(mdFilesTmp.getIdIstituto());
					if (mdFilesTmp.getIdIstituto().getPathTmp() == null
							|| mdFilesTmp.getIdIstituto().getPathTmp().equals("")) {
						throw new ClientMDException("Non è stata indicata la Path Temporanea per L'istituto");
					}
					fileObj = mdFilesTmp.getIdIstituto().getPathTmp();
				}
				fileObj += File.separator;
				fileObj += mdFilesTmp.getNomeFile();
				fObj = new File(fileObj);

				if (!fObj.getName().endsWith(".tgz") &&
						!fObj.getName().endsWith(".tar.gz") &&
						!fObj.getName().endsWith(".warc.gz")){
					validate.setDecompressRequired(false);
				}
				if (mdFilesTmp.getTarTmpFile() == null) {
					fileTar = configuration.getSoftwareConfigString("path.tar"); 
					fileTar += File.separator;
					fileTar += configuration.getMDSoftware().getIdIstituzione().getId();
					fileTar += File.separator;
					fileTar += mdFilesTmp.
							getNomeFile().
							replace(".tar.gz", ".tar").
							replace(".tgz", ".tar").
							replace(".warc.gz", ".warc");
				} else {
					fileTar = mdFilesTmp.getTarTmpFile();
				}
				fTar = new File(fileTar);

				logValidate.info(
						"\n"+name + " [" + objectIdentifierPremis + "]" + " File da validare: " + fObj.getAbsolutePath());
				eventDetailDecomp = fObj.getName() + " => " + fileTar;
				if (!fObj.exists()) {
					removeOrigin=false;
					fObj = fTar;
				}
				if (fObj.exists()) {
					ris = validate(mdFileTmpBusiness, mdFilesTmp, objectIdentifierPremis, fObj, fTar, configuration,
							validate, eventDetailDecomp, removeOrigin);
				} else {
					logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Il file [" + fObj.getAbsolutePath()
							+ "] non è presente sul Server");
					mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, null, 
							new String[] { "Il file [" + fObj.getAbsolutePath() + "] non è presente sul Server" },
							writeFilePremisDB(filePremis,
									configuration.getSoftwareConfigString("path.premis")));
				}
			} else {
				logValidate.error("\n"+name + " [" + objectIdentifierPremis + "]" + " L'identiifcativo ["
						+ objectIdentifierPremis + "] non è presente in archivio");
				ris = false;
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (ClientMDException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (PremisXsdException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return ris;
	}

	private Boolean validate(MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp, String objectIdentifierPremis,
			File fObj, File fTar, IMDConfiguration<?> configuration, ValidateFile validate, String eventDetailDecomp,
			Boolean removeOrigin)
			throws HibernateException, HibernateUtilException, SQLException, MDConfigurationException,
			PremisXsdException, XsdException, IOException {
		GregorianCalendar start = null;
		Boolean result = false;

		try {
			// il file Esiste
			FactoryDAO.initialize(mdFilesTmp.getStato());
			if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINETRASF) || mdFilesTmp.getValidDataStart()==null) {
				logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Inizio la validazione del file ["
						+ fObj.getAbsolutePath() + "]");
				start = mdFileTmpBusiness.updateStartValidate(mdFilesTmp.getId(), fTar);
			} else {
				logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Continuo la validazione del file ["
						+ fObj.getAbsolutePath() + "]");
				start = new GregorianCalendar();
				start.setTimeInMillis(mdFilesTmp.getValidDataStart().getTime());
			}
			validate.check(fObj, fTar, configuration, mdFilesTmp.getDecompEsito(), removeOrigin);
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				result = writePremis(mdFileTmpBusiness, mdFilesTmp, validate, objectIdentifierPremis, configuration,
						eventDetailDecomp, start);
			} catch (SQLException e) {
				throw e;
			} catch (MDConfigurationException e) {
				throw e;
			} catch (PremisXsdException e) {
				throw e;
			} catch (XsdException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}

		return result;
	}

	private Boolean writePremis(MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp, ValidateFile validate,
			String objectIdentifierPremis, IMDConfiguration<?> configuration, String eventDetailDecomp,
			GregorianCalendar start)
			throws SQLException, MDConfigurationException, PremisXsdException, XsdException, IOException {
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premis = null;
		String objectIdentifierMaster = null;
		Boolean result = false;

		try {
			premis = PremisXsd.initialize();
			objectIdentifierMaster = writePremisArchive(validate, objectIdentifierPremis, premis, configuration, mdFilesTmp);
			if (premis.getActualFileName() != null) {
				if (mdFilesTmp.getPremisFile() == null || mdFilesTmp.getPremisFile().trim().equals("")) {
					filePremis = new File(
							genFilePremis(
									configuration.getSoftwareConfigString("path.premis"), 
									"Validate",
									premis.getActualFileName()));
				} else {
					filePremis = new File(
							genFilePremis(configuration.getSoftwareConfigString("path.premis"),
									mdFilesTmp.getPremisFile()));
				}
			}
			writePremisSend(premis, mdFileTmpBusiness, mdFilesTmp, configuration, objectIdentifierMaster, validate.getIdDepositante());
			if (!validate.isDecompressRequired() ||
					writePremisDeCompress(mdFileTmpBusiness, mdFilesTmp, validate, objectIdentifierPremis, premis,
					configuration, eventDetailDecomp, objectIdentifierMaster)) {
				result = writePremisValidate(validate, objectIdentifierPremis, mdFileTmpBusiness, mdFilesTmp,
						configuration, premis, objectIdentifierMaster, start);
			}
		} catch (SQLException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (PremisXsdException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return result;
	}

	private Boolean writePremisDeCompress(MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp,
			ValidateFile validate, String objectIdentifierPremis, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premis, IMDConfiguration<?> configuration,
			String eventDetailDecomp, String objectIdentifierMaster)
			throws SQLException, MDConfigurationException, PremisXsdException, XsdException, IOException {
		GregorianCalendar startDecomp = null;
		GregorianCalendar stopDecomp = null;
		DateFormat df = null;
		Boolean result = false;

		try {
			df = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
			if (validate.getGcUnzipStart() != null || validate.getGcUnzipStop() != null) {
				mdFileTmpBusiness.updateDecompress(mdFilesTmp.getId(), validate.getGcUnzipStart(),
						validate.getGcUnzipStop(), (validate.getUnzipError() == null ? true : false),
						null, validate.getUnzipError());
				startDecomp = validate.getGcUnzipStart();
				stopDecomp = validate.getGcUnzipStop();
			} else {
				if (mdFilesTmp.getDecompDataStart() != null) {
					startDecomp = new GregorianCalendar();
					startDecomp.setTimeInMillis(mdFilesTmp.getDecompDataStart().getTime());
				}
				if (mdFilesTmp.getDecompDataEnd() != null) {
					stopDecomp = new GregorianCalendar();
					stopDecomp.setTimeInMillis(mdFilesTmp.getDecompDataEnd().getTime());
				}
			}
			if (startDecomp != null || stopDecomp != null) {
				logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Tempo per Unzip del file da "
						+ (startDecomp == null ? "none" : df.format(startDecomp.getTime())) + " a "
						+ (stopDecomp == null ? "none" : df.format(stopDecomp.getTime())));
			}
			if (validate.getUnzipError() != null) {
				logValidate.error("\n"+name + " [" + objectIdentifierPremis + "]"
						+ " Riscontrato un problema nella procedura di Unzip");
				premis.addEvent("decompress", startDecomp, stopDecomp, eventDetailDecomp, "KO",
						validate.getUnzipError(), null, configuration.getMDSoftware(),
						// Configuration.getValue("demoni."
						// + application + ".UUID"),
						objectIdentifierMaster);
				premis.write(filePremis, false);
			} else {
				if (startDecomp != null || stopDecomp != null) {
					logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Tempo di decompressione da "
							+ (startDecomp == null ? "none" : df.format(startDecomp.getTime())) + " a "
							+ (stopDecomp == null ? "none" : df.format(stopDecomp.getTime())));
					premis.addEvent("decompress", startDecomp, stopDecomp, eventDetailDecomp, "OK", null, null,
							configuration.getMDSoftware(),
							// Configuration.getValue("demoni."
							// + application + ".UUID"),
							objectIdentifierMaster);
					result = true;
				} else {
					if (mdFilesTmp.getDecompDataStart() != null){
						startDecomp = new GregorianCalendar();
						startDecomp.setTimeInMillis(mdFilesTmp.getDecompDataStart().getTime());
					}
					if (mdFilesTmp.getDecompDataEnd() != null){
						stopDecomp = new GregorianCalendar();
						stopDecomp.setTimeInMillis(mdFilesTmp.getDecompDataEnd().getTime());
					}
					if (mdFilesTmp.getDecompEsito()!= null) {
						if (mdFilesTmp.getDecompEsito()){
							logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Tempo di decompressione da "
									+ (startDecomp == null ? "none" : df.format(startDecomp.getTime())) + " a "
									+ (stopDecomp == null ? "none" : df.format(stopDecomp.getTime())));
							premis.addEvent("decompress", startDecomp, stopDecomp, eventDetailDecomp, "OK", null, null,
									configuration.getMDSoftware(),
									// Configuration.getValue("demoni."
									// + application + ".UUID"),
									objectIdentifierMaster);
							result = true;
						} else {
							logValidate.error("\n"+name + " [" + objectIdentifierPremis + "]"
									+ " Riscontrato un problema nella procedura di Unzip");
							premis.addEvent("decompress", startDecomp, stopDecomp, eventDetailDecomp, "KO",
									validate.getUnzipError(), null, configuration.getMDSoftware(),
									// Configuration.getValue("demoni."
									// + application + ".UUID"),
									objectIdentifierMaster);
							premis.write(filePremis, false);
						}
					} else {
						logValidate.error("\n"+name + " [" + objectIdentifierPremis + "]"
								+ " Riscontrato un problema nella definizione dello stato di decompressione");
						premis.addEvent("decompress", startDecomp, stopDecomp, eventDetailDecomp, "KO",
								validate.getUnzipError(), null, configuration.getMDSoftware(),
								// Configuration.getValue("demoni."
								// + application + ".UUID"),
								objectIdentifierMaster);
						premis.write(filePremis, false);
					}
				}
			}

		} catch (SQLException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (PremisXsdException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return result;
	}

	private String writePremisArchive(ValidateFile validate, String objectIdentifierPremis, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premis,
			IMDConfiguration<?> configuration, MDFilesTmp mdFilesTmp) throws MDConfigurationException {
		ArchiveMD archive = null;
		String objectIdentifierMaster = null;
		int pos = 0;
		boolean isWarc = false;
		List<PremisDigest> digests = null;
		String digest = null;
		Type type = null;

		try {
			if (validate.getArchive() != null) {
				logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Analizzo gli archivi");
				if (validate.getArchive().getType() != null &&
						validate.getArchive().getType().getExt() != null &&
						(validate.getArchive().getType().getExt().equalsIgnoreCase("warc") ||
						validate.getArchive().getType().getExt().equalsIgnoreCase("mrc"))){
					isWarc = true;
				}
				if (validate.getArchive().checkMimetype("application/x-tar") || isWarc) {
					archive = validate.getArchive();
				} else {
					archive = (ArchiveMD) validate.getArchive().getArchive().get(0);
				}
				
				digest = archive.getDigest(DigestType.SHA_1);
				if (digest != null && !digest.trim().equals("")) {
					digests = new Vector<PremisDigest>();
					digests.add(new PremisDigest(CHECKSUMTYPEDefinition.SHA_1, digest));
				}
				
				digest = archive.getDigest(DigestType.MD_5);
				if (digest != null && !digest.trim().equals("")) {
					if (digests==null) {
						digests = new Vector<PremisDigest>();
					}
					digests.add(new PremisDigest(CHECKSUMTYPEDefinition.MD_5, digest));
				}
				
				digest = archive.getDigest(DigestType.SHA_256);
				if (digest != null && !digest.trim().equals("")) {
					if (digests==null) {
						digests = new Vector<PremisDigest>();
					}
					digests.add(new PremisDigest(CHECKSUMTYPEDefinition.SHA_256, digest));
				}

				if (mdFilesTmp.getPremisFile() == null || mdFilesTmp.getPremisFile().trim().equals("")) {
					objectIdentifierMaster = archive.getID();
				} else {
					objectIdentifierMaster = mdFilesTmp.getPremisFile();
					pos = objectIdentifierMaster.lastIndexOf("/");
					objectIdentifierMaster = objectIdentifierMaster.substring(pos).replace("/", "");
					pos = objectIdentifierMaster.indexOf(".");
					objectIdentifierMaster = objectIdentifierMaster.substring(0,pos).replace(".", "");
				}
				type = archive.getType();
				premis.addObjectFileContainer(objectIdentifierMaster, archive.getXmltype().value(),
						type.getExt(), new BigInteger("0"), digests,
						type.getSize(), archive.getMimetype(), archive.getNome(),
						mdFilesTmp.getIdSoftware().getIdRigths() 
						, (type.getFormat()==null?null:type.getFormat().getVersion()), type.getPUID());

				if (archive.getArchive() != null && archive.getArchive().size() > 0) {
					logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Inizio analisi degli Archivi "
							+ archive.getArchive().size());
					for (int y = 0; y < archive.getArchive().size(); y++) {
						if ((y % 100) == 0) {
							logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " " + y
									+ " Archivi analizzati su " + archive.getArchive().size());
						}
						addArchive(premis, (ArchiveMD) archive.getArchive().get(y), objectIdentifierMaster,
								configuration, validate, "", isWarc);
					}
					logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Fine analisi degli Archivi "
							+ archive.getArchive().size());
				}
			}
		} catch (MDConfigurationException e) {
			throw e;
		}
		return objectIdentifierMaster;
	}

	private void writePremisSend(PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premis, MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp, IMDConfiguration<?> configuration,
			String objectIdentifierMaster, MDIstituzione idDepositante) throws SQLException {

		try {
			if (idDepositante != null){
				mdFileTmpBusiness.updateIdDepositante(mdFilesTmp.getId(), idDepositante);
			}
			premis.addEvent("send", mdFilesTmp.getTrasfDataStart(), mdFilesTmp.getTrasfDataEnd(), null, "OK", null,
					(idDepositante != null?idDepositante.getId():mdFilesTmp.getIdSoftware().getIdIstituzione().getId()), 
					mdFilesTmp.getIdSoftware(), 
					objectIdentifierMaster);
		} catch (SQLException e) {
			throw e;
		}
	}

	private Boolean writePremisValidate(ValidateFile validate, String objectIdentifierPremis,
			MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp, IMDConfiguration<?> configuration,
			PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premis, String objectIdentifierMaster, GregorianCalendar start)
			throws SQLException, MDConfigurationException, PremisXsdException, XsdException, IOException {
		GregorianCalendar stop = null;
		DateFormat df = null;
		Boolean result = false;

		try {
			df = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
			if (validate.isErrors()) {
				logValidate
						.error(name + " [" + objectIdentifierPremis + "]" + " Riscontrato un errore nella validazione");
				stop = mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, validate.getExceptionErrors(), validate.getErrors(),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
				premis.addEvent("validation", start, stop, null, "KO", validate.getErrors(), null,
						configuration.getMDSoftware(),
						// Configuration.getValue("demoni."
						// + application + ".UUID"),
						objectIdentifierMaster);
				premis.write(filePremis, false);
			} else {
				if (validate.getXmlType() == null) {
					logValidate.error("\n"+name + " [" + objectIdentifierPremis + "]"
							+ " Impossibile individuare il formato del tracciato XML presente nel file");
					stop = mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), null, false, null, 
							new String[] { "Impossibile individuare il formato del tracciato XML presente nel file" },
							writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
					premis.addEvent("validation", start, stop, null, "KO",
							new String[] { "Impossibile individuare il formato del tracciato XML presente nel file" },
							null, configuration.getMDSoftware(),
							// Configuration
							// .getValue("demoni."
							// + application
							// + ".UUID"),
							objectIdentifierMaster);
					premis.write(filePremis, false);
				} else {
					stop = mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(), validate.getXmlType().value(), true,
							null, null, 
							writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
					premis.addEvent("validation", start, stop, null, "OK", null, null, configuration.getMDSoftware(),
							// Configuration
							// .getValue("demoni."
							// + application
							// + ".UUID"),
							objectIdentifierMaster);
					premis.write(filePremis, false);
					logValidate.info("\n"+name + " [" + objectIdentifierPremis + "]" + " Tempo di validazione da "
							+ (start == null ? "none" : df.format(start.getTime())) + " a "
							+ (stop == null ? "none" : df.format(stop.getTime())));
					result = true;
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (PremisXsdException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return result;
	}

	private void addArchive(PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premis, ArchiveMD archive, String objectIdentifierMaster,
			IMDConfiguration<?> configuration, ValidateFile validate, String folder, boolean isWarc) throws MDConfigurationException {
		String objectIdentifierValue = null;
		BigInteger compositionLevel = null;
		List<PremisDigest> digests = null;
		String digest = null;
		Long size = null;
		String formatDesignationValue = null;
		String originalName = null;
		String contentLocationValue = null;
		String formatVersion = null;
		String puid = null;
		String relationshipSubType = null;

		try {
			if (checkValidateArchive(archive, validate, isWarc)){
				objectIdentifierValue = archive.getID();
	
				compositionLevel = new BigInteger("0");
				if (archive.getMimetype() != null && configuration.getCompositionLevel(archive.getMimetype().split(",")[0])
				// Configuration.getValue("demoni.Validate.compositionLevel",
				// archive.getMimetype().split(",")[0])
				!= null) {
					compositionLevel = configuration.getCompositionLevel(archive.getMimetype().split(",")[0]);
					// new BigInteger(Configuration.getValue(
					// "demoni.Validate.compositionLevel", archive.getMimetype()
					// .split(",")[0]));
				}
	
				digest = archive.getDigest(DigestType.SHA_1);
				if (digest != null && !digest.trim().equals("")) {
					digests = new Vector<PremisDigest>();
					digests.add(new PremisDigest(CHECKSUMTYPEDefinition.SHA_1, digest));
				}
				
				digest = archive.getDigest(DigestType.MD_5);
				if (digest != null && !digest.trim().equals("")) {
					if (digests==null) {
						digests = new Vector<PremisDigest>();
					}
					digests.add(new PremisDigest(CHECKSUMTYPEDefinition.MD_5, digest));
				}
				
				digest = archive.getDigest(DigestType.SHA_256);
				if (digest != null && !digest.trim().equals("")) {
					if (digests==null) {
						digests = new Vector<PremisDigest>();
					}
					digests.add(new PremisDigest(CHECKSUMTYPEDefinition.SHA_256, digest));
				}
				size = archive.getType().getSize();
				formatDesignationValue = archive.getMimetype();
				originalName = (archive.getProtocol() != null?archive.getProtocol():"")+
						((folder != null && !folder.trim().equals(""))?folder+File.separator:"") + archive.getNome();
				contentLocationValue = archive.getType().getContentLocation();
				if (archive.getType().getFormat() != null) {
					formatVersion = archive.getType().getFormat().getVersion();
				}
				if (archive.getType().getPUID() != null) {
					puid = archive.getType().getPUID();
				}
	
				if ((archive.getType().getExt() != null && 
						(archive.getType().getExt().equals("xml") ||
								archive.getType().getExt().equals("premis"))) && 
						(archive.getXmltype() != null && 
							(archive.getXmltype().equals(Xmltype.MAG) || 
									archive.getXmltype().equals(Xmltype.METS) || 
									archive.getXmltype().equals(Xmltype.PREMIS) || 
									archive.getXmltype().equals(Xmltype.AGENT) || 
									archive.getXmltype().equals(Xmltype.BAGIT) || 
									archive.getXmltype().equals(Xmltype.RIGHTS) || 
									archive.getXmltype().equals(Xmltype.EVENT) || 
									archive.getXmltype().equals(Xmltype.WARC)))) {
					relationshipSubType = "R";
				} else {
					relationshipSubType = "1";
				}
				premis.addObjectFile(objectIdentifierValue, compositionLevel, digests, size, formatDesignationValue,
						originalName, contentLocationValue, formatVersion, puid, relationshipSubType,
						objectIdentifierMaster);
			}
			if (archive.getArchive() != null && archive.getArchive().size() > 0) {
				for (int x = 0; x < archive.getArchive().size(); x++) {
					addArchive(premis, (ArchiveMD) archive.getArchive().get(x), objectIdentifierMaster, configuration, validate,
							((folder != null && !folder.trim().equals(""))?folder+File.separator:"") + archive.getNome(), isWarc);
				}
			}
		} catch (MDConfigurationException e) {
			throw e;
		}
	}

	private boolean checkValidateArchive(ArchiveMD archive, ValidateFile validate, boolean isWarc){
		boolean result =true;
		String nome = "";
		int pos = 0;

		if (archive.getNome().startsWith(".")){
			result= false;
		} else if (archive.getTypeObject().equalsIgnoreCase("Folder")){
			result = false;
		} else if (validate.getXmlType() != null &&
				validate.getXmlType().equals(Xmltype.BAGIT)){
			nome = archive.getNome().trim().toLowerCase();
			pos = nome.indexOf(".");
			nome = nome.substring(pos+1);
			pos = nome.indexOf(".");
			if (pos >-1){
				nome = nome.substring(pos+1);
				if (nome.equals("metadata.json") ||
						nome.equals("siegfried.json") ||
						nome.equals("tika.json") ||
						nome.equals("txt")){
					result = false;
				}
			}
		} else if (isWarc){
			if (archive.getMimetype() == null || 
					!archive.getMimetype().equalsIgnoreCase("application/pdf")){
				result = false;
			}
		}
				
		return result;
	}
}
