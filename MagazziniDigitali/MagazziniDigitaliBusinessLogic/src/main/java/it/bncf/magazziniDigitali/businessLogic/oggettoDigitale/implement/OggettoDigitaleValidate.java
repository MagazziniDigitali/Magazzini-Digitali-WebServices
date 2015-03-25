/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ArchiveMD;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.naming.NamingException;

import mx.randalf.archive.info.DigestType;
import mx.randalf.archive.info.Xmltype;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class OggettoDigitaleValidate {

	private Logger log = Logger.getLogger(getClass());

	private Logger logValidate = null;

	private String name = null;

	private HibernateTemplate hibernateTemplate= null;

	/**
	 * 
	 */
	public OggettoDigitaleValidate(HibernateTemplate hibernateTemplate, 
			Logger logValidate, String name) {
		this.hibernateTemplate = hibernateTemplate;
		this.logValidate = logValidate;
		this.name = name;
	}

	/**
	 * @throws SQLException 
	 * @see java.util.concurrent.Callable#call()
	 */
	public boolean esegui(String objectIdentifierPremis, String application) throws SQLException {
		String fPremis = null;
		String fileObj = null;
		File fObj = null;
		File fTar = null;
		String fileTar = null;
		String eventDetailDecomp = null;
		GregorianCalendar start;
		ValidateFile validate = null;
		PremisXsd premis = null;
		GregorianCalendar startDecomp = null;
		GregorianCalendar stopDecomp = null;
		DateFormat df = null;
		ArchiveMD archive = null;
		String objectIdentifierMaster = null;
		File filePremis = null;
		GregorianCalendar stop;
		Boolean ris = false;
		MDFilesTmpBusiness mdFileTmpBusiness = null;
		MDFilesTmpDAO mdFileTmpDao= null;
		MDFilesTmp mdFilesTmp = null;

		logValidate.info(name 
				+" ["+objectIdentifierPremis+"]"
				+ " Inizio la validazione");
		try {
			mdFileTmpBusiness = new MDFilesTmpBusiness(hibernateTemplate);
			mdFileTmpDao = new MDFilesTmpDAO(hibernateTemplate);
			mdFilesTmp = mdFileTmpDao.findById(objectIdentifierPremis);
		} catch (HibernateException e) {
			log.error(name 
					+" ["+objectIdentifierPremis+"]"
					+ " "+e.getMessage(), e);
		} catch (NamingException e) {
			log.error(name 
					+" ["+objectIdentifierPremis+"]"
					+ " "+e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(name 
					+" ["+objectIdentifierPremis+"]"
					+ " "+e.getMessage(), e);
		}

		try {
			if (mdFilesTmp != null){
				df = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
				validate = new ValidateFile();
				fPremis = null;
	
				// calcolo il file da validare
				FactoryDAO.initialize(mdFilesTmp.getIdIstituto());
				fileObj = mdFilesTmp.getIdIstituto().getPathTmp();
				fileObj += File.separator;
				fileObj += mdFilesTmp.getNomeFile();
				fObj = new File(fileObj);
	
				fileTar = mdFilesTmp.getIdIstituto().getPathTar();
				fileTar += File.separator;
				fileTar += mdFilesTmp.getNomeFile().replace(".tar.gz", ".tar")
						.replace(".tgz", ".tar");
				fTar = new File(fileTar);

				logValidate.info(name 
						+" ["+objectIdentifierPremis+"]"
						+ " File da validare: "
						+ fObj.getAbsolutePath());
				eventDetailDecomp = fObj.getName() + " => " + fileTar;
				if (!fObj.exists()) {
//					fObj = new File(fObj.getParentFile()
//							.getAbsolutePath()
//							+ File.separator
//							+ fileTar);
					fObj = fTar;
				}
				if (fObj.exists()) {
					// il file Esiste
					FactoryDAO.initialize(mdFilesTmp.getStato());
					if (mdFilesTmp.getStato().getId()
							.equals(MDStatoDAO.FINETRASF)) {
						logValidate
								.info(name
										+" ["+objectIdentifierPremis+"]"
										+ " Inizio la validazione del file ["
										+ fObj.getAbsolutePath() + "]");
						start = mdFileTmpBusiness.updateStartValidate(mdFilesTmp
								.getId());
					} else {
						logValidate
								.info(name	
										+" ["+objectIdentifierPremis+"]"
										+" Continuo la validazione del file ["
										+ fObj.getAbsolutePath() + "]");
						start = new GregorianCalendar();
						start.setTimeInMillis(mdFilesTmp
								.getValidDataStart().getTime());
					}
					validate.check(fObj, fTar);
					premis = new PremisXsd();
					if (validate.getGcUnzipStart() != null
							|| validate.getGcUnzipStop() != null) {
						mdFileTmpBusiness.updateDecompress(mdFilesTmp.getId(),
								validate.getGcUnzipStart(), validate
										.getGcUnzipStop(), (validate
										.getUnzipError() == null ? true
										: false), validate
										.getUnzipError());
						startDecomp = validate.getGcUnzipStart();
						stopDecomp = validate.getGcUnzipStop();
					} else {
						if (mdFilesTmp.getDecompDataStart() != null) {
							startDecomp = new GregorianCalendar();
							startDecomp.setTimeInMillis(mdFilesTmp
									.getDecompDataStart().getTime());
						}
						if (mdFilesTmp.getDecompDataEnd() != null) {
							stopDecomp = new GregorianCalendar();
							stopDecomp.setTimeInMillis(mdFilesTmp
									.getDecompDataEnd().getTime());
						}
					}
					if (startDecomp != null || stopDecomp != null) {
						logValidate.info(name
								+" ["+objectIdentifierPremis+"]"
								+" Tempo per Unzip del file da "
								+ (startDecomp == null ? "none"
										: df.format(startDecomp.getTime()))
								+ " a "
								+ (stopDecomp == null ? "none"
										: df.format(stopDecomp.getTime())));
					}
					if (validate.getArchive() != null) {
						logValidate.info(name
								+" ["+objectIdentifierPremis+"]"
								+" Analizzo gli archivi");
						if (validate.getArchive().checkMimetype(
								"application/x-tar")) {
							archive = validate.getArchive();
						} else {
							archive = (ArchiveMD) validate.getArchive()
									.getArchive().get(0);
						}
						objectIdentifierMaster = archive.getID();
						premis.addObjectFileContainer(
								objectIdentifierMaster, archive
										.getXmltype().value(), archive
										.getType().getExt(),
								new BigInteger("0"), archive
										.getDigest(DigestType.SHA_1),
								archive.getType().getSize(), archive
										.getMimetype(), archive
										.getNome(),
								mdFilesTmp.getIdIstituto().getRightUuid()
										, archive
										.getType().getFormat()
										.getVersion(), archive
										.getType().getPUID());
	
						if (archive.getArchive() != null
								&& archive.getArchive().size() > 0) {
							logValidate
									.info(name
											+" ["+objectIdentifierPremis+"]"
											+" Inizio analisi degli Archivi "
											+ archive.getArchive()
													.size());
							for (int y = 0; y < archive.getArchive()
									.size(); y++) {
								if ((y % 100) == 0) {
									logValidate.info(name
											+" ["+objectIdentifierPremis+"]"
											+" "+y
											+ " Archivi analizzati su "
											+ archive.getArchive()
													.size());
								}
								addArchive(premis, (ArchiveMD) archive
										.getArchive().get(y),
										objectIdentifierMaster);
							}
							logValidate
									.info(name
											+ " ["+objectIdentifierPremis+"]"
											+" Fine analisi degli Archivi "
											+ archive.getArchive()
													.size());
						}
					}
	
					fPremis = premis.getActualFileName() + ".premis";
					filePremis = new File(
							Configuration.getValue("path.premis")
									+ File.separator + fPremis);
					premis.addEvent("send", mdFilesTmp
							.getTrasfDataStart(), mdFilesTmp.getTrasfDataEnd(), null, "OK",
							null, mdFilesTmp.getIdIstituto().getUuid()
									, mdFilesTmp.getIdIstituto().getSoftwareUuid(),
							objectIdentifierMaster);
					if (validate.getUnzipError() != null) {
						logValidate
								.error(name
										+" ["+objectIdentifierPremis+"]"
										+" Riscontrato un problema nella procedura di Unzip");
						premis.addEvent(
								"decompress",
								startDecomp,
								stopDecomp,
								eventDetailDecomp,
								"KO",
								validate.getUnzipError(),
								null,
								Configuration.getValue("demoni."
										+ application + ".UUID"),
								objectIdentifierMaster);
						premis.write(filePremis, false);
					} else {
						if (startDecomp != null || stopDecomp != null) {
							logValidate
									.info(name
											+" ["+objectIdentifierPremis+"]"
											+" Tempo di decompressione da "
											+ (startDecomp == null ? "none"
													: df.format(startDecomp.getTime()))
											+ " a "
											+ (stopDecomp == null ? "none"
													: df.format(stopDecomp.getTime())));
							premis.addEvent(
									"decompress",
									startDecomp,
									stopDecomp,
									eventDetailDecomp,
									"OK",
									null,
									null,
									Configuration.getValue("demoni."
											+ application + ".UUID"),
									objectIdentifierMaster);
						}
						if (validate.isErrors()) {
							logValidate
									.error(name
											+" ["+objectIdentifierPremis+"]"
											+" Riscontrato un errore nella validazione");
							stop = mdFileTmpBusiness.updateStopValidate(
									mdFilesTmp.getId(), null, false,
									validate.getErrors(), fPremis);
							premis.addEvent(
									"validation",
									start,
									stop,
									null,
									"KO",
									validate.getErrors(),
									null,
									Configuration.getValue("demoni."
											+ application + ".UUID"),
									objectIdentifierMaster);
							premis.write(filePremis, false);
						} else {
							if (validate.getXmlType() == null) {
								logValidate
										.error(name
												+" ["+objectIdentifierPremis+"]"
												+" Impossibile individuare il formato del tracciato XML presente nel file");
								stop = mdFileTmpBusiness
										.updateStopValidate(
												mdFilesTmp.getId(),
												null,
												false,
												new String[] { "Impossibile individuare il formato del tracciato XML presente nel file" },
												fPremis);
								premis.addEvent(
										"validation",
										start,
										stop,
										null,
										"KO",
										new String[] { "Impossibile individuare il formato del tracciato XML presente nel file" },
										null, Configuration
												.getValue("demoni."
														+ application
														+ ".UUID"),
										objectIdentifierMaster);
								premis.write(filePremis, false);
							} else {
								stop = mdFileTmpBusiness.updateStopValidate(
										mdFilesTmp.getId(), validate
										.getXmlType().value(), true,
										null, fPremis);
								premis.addEvent("validation", start,
										stop, null, "OK", null, null,
										Configuration
												.getValue("demoni."
														+ application
														+ ".UUID"),
										objectIdentifierMaster);
								premis.write(filePremis, false);
								logValidate
										.info(name
												+" ["+objectIdentifierPremis+"]"
												+" Tempo di validazione da "
												+ (start == null ? "none"
														: df.format(start.getTime()))
												+ " a "
												+ (stop == null ? "none"
														: df.format(stop.getTime())));
								ris = true;
							}
						}
					}
				} else {
					logValidate.info(name
							+" ["+objectIdentifierPremis+"]"
							+" Il file ["
							+ fObj.getAbsolutePath()
							+ "] non è presente sul Server");
					mdFileTmpBusiness.updateStopValidate(mdFilesTmp.getId(),
							null, false, new String[] { "Il file ["
									+ fObj.getAbsolutePath()
									+ "] non è presente sul Server" },
							fPremis);
				}
			} else {
				logValidate.error(name
						+" ["+objectIdentifierPremis+"]"
						+" L'identiifcativo ["+
						objectIdentifierPremis+
						"] non è presente in archivio");
				ris = false;
			}
		} catch (ConfigurationException e) {
			mdFileTmpBusiness
					.updateStopValidate(mdFilesTmp.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(name
					+" ["+objectIdentifierPremis+"] "
					+e.getMessage(), e);
		} catch (SQLException e) {
			mdFileTmpBusiness
					.updateStopValidate(mdFilesTmp.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(name
					+" ["+objectIdentifierPremis+"] "
					+e.getMessage(), e);
		} catch (PremisXsdException e) {
			mdFileTmpBusiness
					.updateStopValidate(mdFilesTmp.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(name
					+" ["+objectIdentifierPremis+"] "
					+e.getMessage(), e);
		} catch (XsdException e) {
			mdFileTmpBusiness
					.updateStopValidate(mdFilesTmp.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(name
					+" ["+objectIdentifierPremis+"] "
					+e.getMessage(), e);
		} catch (IOException e) {
			mdFileTmpBusiness
					.updateStopValidate(mdFilesTmp.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(name
					+" ["+objectIdentifierPremis+"] "
					+e.getMessage(), e);
		} catch (Exception e) {
			mdFileTmpBusiness
					.updateStopValidate(mdFilesTmp.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(name
					+" ["+objectIdentifierPremis+"] "
					+e.getMessage(), e);
		} finally {
			logValidate.info(name 
					+" ["+objectIdentifierPremis+"]"
					+ " Fine della validazione");
		}
		return ris;
	}

	private void addArchive(PremisXsd premis, ArchiveMD archive,
			String objectIdentifierMaster) throws ConfigurationException {
		String objectIdentifierValue = null;
		BigInteger compositionLevel = null;
		String digest = null;
		Long size = null;
		String formatDesignationValue = null;
		String originalName = null;
		String contentLocationValue = null;
		String formatVersion = null;
		String puid = null;
		String relationshipSubType = null;

		objectIdentifierValue = archive.getID();

		compositionLevel = new BigInteger("0");
		if (archive.getMimetype() != null
				&& Configuration.getValue("demoni.Validate.compositionLevel",
						archive.getMimetype().split(",")[0]) != null) {
			compositionLevel = new BigInteger(Configuration.getValue(
					"demoni.Validate.compositionLevel", archive.getMimetype()
							.split(",")[0]));
		}

		digest = archive.getDigest(DigestType.SHA_1);
		size = archive.getType().getSize();
		formatDesignationValue = archive.getMimetype();
		originalName = archive.getNome();
		contentLocationValue = archive.getType().getContentLocation();
		if (archive.getType().getFormat() != null) {
			formatVersion = archive.getType().getFormat().getVersion();
		}
		if (archive.getType().getPUID() != null) {
			puid = archive.getType().getPUID();
		}

		if (archive.getType().getExt().equals("xml")
				&& (archive.getXmltype() != null && (archive.getXmltype()
						.equals(Xmltype.MAG) || archive.getXmltype().equals(
						Xmltype.METS)))) {
			relationshipSubType = "R";
		} else {
			relationshipSubType = "1";
		}
		premis.addObjectFile(objectIdentifierValue, compositionLevel, digest,
				size, formatDesignationValue, originalName,
				contentLocationValue, formatVersion, puid, relationshipSubType,
				objectIdentifierMaster);

		if (archive.getArchive() != null && archive.getArchive().size() > 0) {
			for (int x = 0; x < archive.getArchive().size(); x++) {
				addArchive(premis, (ArchiveMD) archive.getArchive().get(x),
						objectIdentifierMaster);
			}
		}
	}

}
