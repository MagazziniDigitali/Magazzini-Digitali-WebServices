/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ArchiveMD;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
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
import java.util.concurrent.Callable;

import mx.randalf.archive.info.DigestType;
import mx.randalf.archive.info.Xmltype;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;

/**
 * @author massi
 *
 */
public class OggettoDigitaleValidate implements Callable<Boolean> {

	private Logger log = Logger.getLogger(getClass());

	private MDFilesTmp record = null;

	private Logger logValidate = null;

	private MDFilesTmpBusiness mdFileTmp = null;

	private String name = null;

	private String application = null;

	/**
	 * 
	 */
	public OggettoDigitaleValidate(MDFilesTmp record, Logger logValidate, MDFilesTmpBusiness mdFileTmp,
			String name, String application) {
		this.record = record;
		this.logValidate = logValidate;
		this.mdFileTmp = mdFileTmp;
		this.name = name;
		this.application = application;
	}

	/**
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Boolean call() throws Exception {
		String fPremis = null;
		String fileObj = null;
		File fObj = null;
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

		try {
			df = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
			validate = new ValidateFile();
			fPremis = null;
			// calcolo il file da validare
			fileObj = record.getIdIstituto().getPathTmp();
			fileObj += File.separator;
			fileObj += record.getNomeFile();
			fObj = new File(fileObj);

			fileTar = fObj.getName().replace(".tar.gz", ".tar")
					.replace(".tgz", ".tar");

			logValidate.info(name
					+ " File da validare: "
					+ fObj.getAbsolutePath());
			eventDetailDecomp = fObj.getName() + " => " + fileTar;
			if (!fObj.exists()) {
				fObj = new File(fObj.getParentFile()
						.getAbsolutePath()
						+ File.separator
						+ fileTar);
			}
			if (fObj.exists()) {
				// il file Esiste
				if (record.getStato().getId()
						.equals(MDStatoDAO.FINETRASF)) {
					logValidate
							.info(name+ " Inizio la validazione del file ["
									+ fObj.getAbsolutePath() + "]");
					start = mdFileTmp.updateStartValidate(record
							.getId());
				} else {
					logValidate
							.info(name+" Continuo la validazione del file ["
									+ fObj.getAbsolutePath() + "]");
					start = new GregorianCalendar();
					start.setTimeInMillis(record
							.getValidDataStart().getTime());
				}
				validate.check(fObj);
				premis = new PremisXsd();
				if (validate.getGcUnzipStart() != null
						|| validate.getGcUnzipStop() != null) {
					mdFileTmp.updateDecompress(record.getId(),
							validate.getGcUnzipStart(), validate
									.getGcUnzipStop(), (validate
									.getUnzipError() == null ? true
									: false), validate
									.getUnzipError());
					startDecomp = validate.getGcUnzipStart();
					stopDecomp = validate.getGcUnzipStop();
				} else {
					if (record.getDecompDataStart() != null) {
						startDecomp = new GregorianCalendar();
						startDecomp.setTimeInMillis(record
								.getDecompDataStart().getTime());
					}
					if (record.getDecompDataEnd() != null) {
						stopDecomp = new GregorianCalendar();
						stopDecomp.setTimeInMillis(record
								.getDecompDataEnd().getTime());
					}
				}
				if (startDecomp != null || stopDecomp != null) {
					logValidate.info(name+" Tempo per Unzip del file da "
							+ (startDecomp == null ? "none"
									: df.format(startDecomp.getTime()))
							+ " a "
							+ (stopDecomp == null ? "none"
									: df.format(stopDecomp.getTime())));
				}
				if (validate.getArchive() != null) {
					logValidate.info(name+" Analizzo gli archivi");
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
							record.getIdIstituto().getRightUuid()
									, archive
									.getType().getFormat()
									.getVersion(), archive
									.getType().getPUID());

					if (archive.getArchive() != null
							&& archive.getArchive().size() > 0) {
						logValidate
								.info(name+" Inizio analisi degli Archivi "
										+ archive.getArchive()
												.size());
						for (int y = 0; y < archive.getArchive()
								.size(); y++) {
							if ((y % 100) == 0) {
								logValidate.info(name+" "+y
										+ " Archivi analizzati su "
										+ archive.getArchive()
												.size());
							}
							addArchive(premis, (ArchiveMD) archive
									.getArchive().get(y),
									objectIdentifierMaster);
						}
						logValidate
								.info(name+" Fine analisi degli Archivi "
										+ archive.getArchive()
												.size());
					}
				}

				fPremis = premis.getActualFileName() + ".premis";
				filePremis = new File(
						Configuration.getValue("path.premis")
								+ File.separator + fPremis);
				premis.addEvent("send", record
						.getTrasfDataStart(), record.getTrasfDataEnd(), null, "OK",
						null, record.getIdIstituto().getUuid()
								, record.getIdIstituto().getSoftwareUuid(),
						objectIdentifierMaster);
				if (validate.getUnzipError() != null) {
					logValidate
							.error(name+" Riscontrato un problema nella procedura di Unzip");
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
								.info(name+" Tempo di decompressione da "
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
								.error(name+" Riscontrato un errore nella validazione");
						stop = mdFileTmp.updateStopValidate(
								record.getId(), null, false,
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
									.error(name+" Impossibile individuare il formato del tracciato XML presente nel file");
							stop = mdFileTmp
									.updateStopValidate(
											record.getId(),
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
							stop = mdFileTmp.updateStopValidate(record.getId(), validate
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
									.info(name+" Tempo di validazione da "
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
				logValidate.info(name+" Il file ["
						+ fObj.getAbsolutePath()
						+ "] non è presente sul Server");
				mdFileTmp.updateStopValidate(record.getId(),
						null, false, new String[] { "Il file ["
								+ fObj.getAbsolutePath()
								+ "] non è presente sul Server" },
						fPremis);
			}
		} catch (ConfigurationException e) {
			mdFileTmp
					.updateStopValidate(record.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(e.getMessage(), e);
		} catch (SQLException e) {
			mdFileTmp
					.updateStopValidate(record.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(e.getMessage(), e);
		} catch (PremisXsdException e) {
			mdFileTmp
					.updateStopValidate(record.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(e.getMessage(), e);
		} catch (XsdException e) {
			mdFileTmp
					.updateStopValidate(record.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			mdFileTmp
					.updateStopValidate(record.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			mdFileTmp
					.updateStopValidate(record.getId(), null,
							false, new String[] { e.getMessage() },
							fPremis);
			log.error(e.getMessage(), e);
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
