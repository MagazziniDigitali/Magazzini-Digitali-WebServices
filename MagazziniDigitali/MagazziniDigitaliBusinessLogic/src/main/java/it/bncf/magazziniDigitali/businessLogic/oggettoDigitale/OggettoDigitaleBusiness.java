package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import it.bncf.magazzimiDigitali.database.entity.MDFilesTmp;
import it.bncf.magazzimiDigitali.databaseSchema.sqlite.MDFilesTmpSqlite;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ArchiveMD;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import mx.randalf.archive.info.DigestType;
import mx.randalf.archive.info.Xmltype;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;

public class OggettoDigitaleBusiness {

	public Logger log = Logger.getLogger(getClass());

	public OggettoDigitaleBusiness() {
	}

	/**
	 * Metodo utilizzato per verificare lo stato dell'OggettoDigitale
	 * 
	 * @param sha1
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException
	 */
	public Hashtable<String, String> checkStatus(String sha1)
			throws FileNotFoundException, ClassNotFoundException, SQLException,
			ConfigurationException {
		MDFilesTmpSqlite mdFileTmp = null;
		List<MDFilesTmp> records = null;
		Hashtable<String, String> result = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();
			records = mdFileTmp.findBySha1(sha1);

			if (records != null) {
				for (int x = 0; x < records.size(); x++) {
					if (records.get(x).getStato() != null
							&& !records.get(x).getStato()
									.equalsIgnoreCase("ERROR")) {
						if (result == null) {
							result = new Hashtable<String, String>();
						}
						result.put("id", records.get(x).getId());
						if (records.get(x).getDataArchiviazione() != null) {
							result.put("stato", "ARCHIVIATO");
						} else if (records.get(x).getValidDataEnd() != null) {
							result.put("stato", "CHECKARCHIVIAZIONE");
						} else if (records.get(x).getValidDataStart() != null) {
							result.put("stato", "CHECKARCHIVIAZIONE");
						} else if (records.get(x).getTrasfDataEnd() != null) {
							result.put("stato", "FINETRASF");
						} else if (records.get(x).getTrasfDataStart() != null) {
							result.put("stato", "INITTRASF");
						}
						break;
					}
				}
			}

			// TODO: E' da implementare il colloquio con l'archivio solr di
			// Magazini Digitali
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} finally {
			if (mdFileTmp != null) {
				mdFileTmp.disconnect();
			}
		}
		return result;
	}

	/**
	 * Metodo utilizzato per la gestione dell'inserimento dell'informazioni
	 * dell'oggetto digitale nella tabella Tempornea
	 * 
	 * @param idIstituto
	 *            Identicativo dell'Istituto
	 * @param nomeFile
	 *            Nome del file originale
	 * @param sha1
	 *            Chiave Sha1 del file originale
	 * @param nomeFileMod
	 *            Data dell'ultima modifica del file originale
	 * @return Identificativo dell'oggetto digitale
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *             Eccezione SQL
	 * @throws ConfigurationException
	 */
	public String initSend(String idIstituto, String nomeFile, String sha1,
			Calendar nomeFileMod) throws FileNotFoundException,
			ClassNotFoundException, SQLException, ConfigurationException {
		MDFilesTmpSqlite mdFileTmp = null;
		String id = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();

			id = mdFileTmp
					.insertNewRec(idIstituto, nomeFile, sha1, nomeFileMod);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} finally {
			if (mdFileTmp != null) {
				mdFileTmp.disconnect();
			}
		}
		return id;
	}

	/**
	 * Metodo utilizzato per la gestione dell'inserimento dell'informazioni
	 * dell'oggetto digitale nella tabella Tempornea
	 * 
	 * @param idIstituto
	 *            Identicativo dell'Istituto
	 * @param nomeFile
	 *            Nome del file originale
	 * @param sha1
	 *            Chiave Sha1 del file originale
	 * @param nomeFileMod
	 *            Data dell'ultima modifica del file originale
	 * @return Identificativo dell'oggetto digitale
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *             Eccezione SQL
	 * @throws ConfigurationException
	 */
	public void endSend(String id, boolean esito, String msgError)
			throws FileNotFoundException, ClassNotFoundException, SQLException,
			ConfigurationException {
		MDFilesTmpSqlite mdFileTmp = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();

			mdFileTmp.updatEndSend(id, esito, (msgError == null ? null
					: new String[] { msgError }));
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} finally {
			if (mdFileTmp != null) {
				mdFileTmp.disconnect();
			}
		}
	}

	public void indexer(){
		MDFilesTmpSqlite mdFileTmp = null;
		List<MDFilesTmp> rs = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();
			
			rs = mdFileTmp.findByStatus(new String[] {
					MDFilesTmpSqlite.FINEVALID, MDFilesTmpSqlite.INITPUBLISH});
			if (rs != null && rs.size() > 0) {
				for (int x=0;x <rs.size(); x++){
					
				}
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (mdFileTmp != null) {
					mdFileTmp.disconnect();
				}
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	public void validate(String application){
		MDFilesTmpSqlite mdFileTmp = null;
		List<MDFilesTmp> rs = null;
		String fileObj = null;
		File fObj = null;
		ValidateFile validate = null;
		PremisXsd premis = null;
		GregorianCalendar start;
		GregorianCalendar stop;
		GregorianCalendar startDecomp = null;
		GregorianCalendar stopDecomp = null;
		ArchiveMD archive = null;
		File filePremis = null;
		String fileTar = null;
		String objectIdentifierMaster = null;
		String eventDetailDecomp = null;
		String fPremis = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();

			// Eseguo la ricerca di tutti i files che hanno finito il
			// trasferimento oppure che risultano in fase di validazione
			rs = mdFileTmp.findByStatus(new String[] {
					MDFilesTmpSqlite.FINETRASF, MDFilesTmpSqlite.INITVALID });
			if (rs != null && rs.size() > 0) {
				// Risulta almeno 1 record da elaborare
				validate = new ValidateFile();
				for (int x = 0; x < rs.size(); x++) {
					try {
						fPremis = null;
						// calcolo il file da validare
						fileObj = Configuration.getValue("istituto."
								+ rs.get(x).getIdIstituto() + ".pathTmp");
						fileObj += File.separator;
						fileObj += rs.get(x).getNomeFile();
						fObj = new File(fileObj);

						fileTar = fObj.getName().
								replace(".tar.gz", ".tar").
								replace(".tgz", ".tar");
						
						log.debug("fileObj: " + fObj.getAbsolutePath());
						eventDetailDecomp = fObj.getName()+" => "+fileTar;
						if (!fObj.exists()) {
							fObj = new File(fObj.getParentFile().getAbsolutePath()+
									File.separator+
									fileTar);
						}
						if (fObj.exists()) {
							// il file Esiste
							if (rs.get(x).getStato()
									.equals(MDFilesTmpSqlite.FINETRASF)) {
								log.debug("Inizio la validazione del file ["
										+ fObj.getAbsolutePath() + "]");
								start = mdFileTmp.updateStartValidate(rs.get(x).getId());
							} else {
								log.debug("Continuo la validazione del file ["
										+ fObj.getAbsolutePath() + "]");
								start = convertDate(rs.get(x).getValidDataStart());
							}
							validate.check(fObj);
							premis = new PremisXsd();
							if (validate.getGcUnzipStart() != null ||
									validate.getGcUnzipStop() != null){
								mdFileTmp.updateCompress(rs.get(x).getId(), 
										validate.getGcUnzipStart(), 
										validate.getGcUnzipStop(), 
										(validate.getUnzipError()==null?true:false), validate.getUnzipError());
								startDecomp = validate.getGcUnzipStart();
								stopDecomp = validate.getGcUnzipStop();
							} else {
								if (rs.get(x).getDecompDataStart() != null){
									startDecomp = convertDate(rs.get(x).getDecompDataStart());
								}
								if (rs.get(x).getDecompDataEnd() != null){
									stopDecomp = convertDate(rs.get(x).getDecompDataEnd());
								}
							}
							if (validate.getArchive()!= null){
								if (validate.getArchive().checkMimetype("application/x-tar")){
									archive = validate.getArchive();
								} else {
									archive = (ArchiveMD) validate.getArchive().getArchive().get(0);
								}
								objectIdentifierMaster = archive.getID();
								premis.addObjectFileContainer(objectIdentifierMaster, archive.getXmltype().value(), 
										archive.getType().getExt(), new BigInteger("0"), 
										archive.getDigest(DigestType.SHA_1), archive.getType().getSize(), 
										archive.getMimetype(), archive.getNome(), 
										Configuration.getValue("istituto."+rs.get(x).getIdIstituto()+".right.UUID"),
										archive.getType().getFormat().getVersion(), archive.getType().getPUID());

								if (archive.getArchive() != null && archive.getArchive().size() > 0) {
									for (int y = 0; y < archive.getArchive().size(); y++) {
										addArchive(premis, (ArchiveMD) archive.getArchive().get(y), objectIdentifierMaster);
									}
								}
							}

							fPremis = premis.getActualFileName()+".premis";
							filePremis = new File(Configuration.getValue("path.premis")+File.separator+
									fPremis);
							premis.addEvent("send", convertDate(rs.get(x).getTrasfDataStart()), 
									convertDate(rs.get(x).getTrasfDataEnd()), null, 
									"OK", null, Configuration.getValue("istituto."+rs.get(x).getIdIstituto()+".UUID"), 
									Configuration.getValue("istituto."+rs.get(x).getIdIstituto()+".software.UUID"), 
									objectIdentifierMaster);
							if (validate.getUnzipError() != null){
								premis.addEvent("decompress", startDecomp, 
										stopDecomp, eventDetailDecomp, 
										"KO", validate.getUnzipError(), null, 
										Configuration.getValue("demoni."+application+".UUID"), 
										objectIdentifierMaster);
								premis.write(filePremis, false);
							} else {
								if (startDecomp != null ||
										stopDecomp != null){
									premis.addEvent("decompress", startDecomp, 
											stopDecomp, eventDetailDecomp, 
											"OK", null, null, 
											Configuration.getValue("demoni."+application+".UUID"), 
											objectIdentifierMaster);
								}
								if (validate.isErrors()) {
									stop = mdFileTmp.updateStopValidate(rs.get(x).getId(),
												null, false, validate.getErrors(), fPremis);
									premis.addEvent("validation", start, 
											stop, null, 
											"KO", validate.getErrors(), null, 
											Configuration.getValue("demoni."+application+".UUID"), 
											objectIdentifierMaster);
									premis.write(filePremis, false);
								} else {
									if (validate.getXmlType()== null){
										stop = mdFileTmp.updateStopValidate(rs.get(x).getId(),
												null, false, new String[] {
														"Impossibile individuare il formato del tracciato XML presente nel file"
													}, fPremis);
										premis.addEvent("validation", start, 
												stop, null, 
												"KO", new String[] {
														"Impossibile individuare il formato del tracciato XML presente nel file"
													}, null, 
												Configuration.getValue("demoni."+application+".UUID"), 
												objectIdentifierMaster);
										premis.write(filePremis, false);
									} else {
										stop = mdFileTmp.updateStopValidate(rs.get(x).getId(),
												validate.getXmlType().value(), true,
												null, fPremis);
										premis.addEvent("validation", start, 
												stop, null, 
												"OK", null, null, 
												Configuration.getValue("demoni."+application+".UUID"), 
												objectIdentifierMaster);
										premis.write(filePremis, false);
									}
								}
							}
						} else {
							mdFileTmp.updateStopValidate(rs.get(x).getId(),
									null, false, new String[] { "Il file ["
											+ fObj.getAbsolutePath()
											+ "] non è presente sul Server" }, fPremis);
						}
					} catch (ConfigurationException e) {
						mdFileTmp.updateStopValidate(
								rs.get(x).getId(),
								null,
								false,
								new String[] { e.getMessage() }, fPremis);
						log.error(e.getMessage(), e);
					} catch (SQLException e) {
						mdFileTmp.updateStopValidate(
								rs.get(x).getId(),
								null,
								false,
								new String[] { e.getMessage() }, fPremis);
						log.error(e.getMessage(), e);
					} catch (PremisXsdException e) {
						mdFileTmp.updateStopValidate(
								rs.get(x).getId(),
								null,
								false,
								new String[] { e.getMessage() }, fPremis);
						log.error(e.getMessage(), e);
					} catch (XsdException e) {
						mdFileTmp.updateStopValidate(
								rs.get(x).getId(),
								null,
								false,
								new String[] { e.getMessage() }, fPremis);
						log.error(e.getMessage(), e);
					} catch (IOException e) {
						mdFileTmp.updateStopValidate(
								rs.get(x).getId(),
								null,
								false,
								new String[] { e.getMessage() }, fPremis);
						log.error(e.getMessage(), e);
					} catch (Exception e) {
						mdFileTmp.updateStopValidate(
								rs.get(x).getId(),
								null,
								false,
								new String[] { e.getMessage() }, fPremis);
						log.error(e.getMessage(), e);
					}
				}
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (mdFileTmp != null) {
					mdFileTmp.disconnect();
				}
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	private GregorianCalendar convertDate(String date){
		GregorianCalendar gc = null;
		int year;
		int month;
		int dayOfMonth;
		int hourOfDay;
		int minute;
		int second;
		int millisecond=0;
		String[] st = null;
		String[] st2 = null;
		int pos = 0;
		
		st = date.split(" ");
		st2 = st[0].split("/");
		year = Integer.parseInt(st2[0]);
		month = Integer.parseInt(st2[1]);
		dayOfMonth = Integer.parseInt(st2[2]);

		st2 = st[1].split(":");
		hourOfDay = Integer.parseInt(st2[0]);
		minute = Integer.parseInt(st2[1]);
		pos = st2[2].indexOf(".");
		if (pos == -1){
			second = Integer.parseInt(st2[2]);
		} else {
			second = Integer.parseInt(st2[2].substring(0,pos));
			millisecond = Integer.parseInt(st2[2].substring(pos+1));
		}
		
		gc = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
		if (millisecond>0){
			gc.add(Calendar.MILLISECOND, millisecond);
		}
		return gc;
	}

	private void addArchive(PremisXsd premis, ArchiveMD archive, String objectIdentifierMaster) throws ConfigurationException {
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
		if (archive.getMimetype() != null &&
				Configuration.getValue("demoni.Validate.compositionLevel", archive.getMimetype().split(",")[0])!= null){
			compositionLevel = new BigInteger(Configuration.getValue("demoni.Validate.compositionLevel", archive.getMimetype().split(",")[0]));
		}

		digest = archive.getDigest(DigestType.SHA_1);
		size = archive.getType().getSize();
		formatDesignationValue = archive.getMimetype();
		originalName = archive.getNome();
		contentLocationValue = archive.getType().getContentLocation();
		if (archive.getType().getFormat() != null){
			formatVersion = archive.getType().getFormat().getVersion();
		}
		if (archive.getType().getPUID() != null){
			puid = archive.getType().getPUID();
		}

		if (archive.getType().getExt().equals("xml") &&
				(archive.getXmltype() != null && 
					(archive.getXmltype().equals(Xmltype.MAG) ||
							archive.getXmltype().equals(Xmltype.METS)))
				){
			relationshipSubType="R";
		} else {
			relationshipSubType = "1";
		}
		premis.addObjectFile(objectIdentifierValue, compositionLevel, digest, size, 
				formatDesignationValue, originalName, contentLocationValue, formatVersion, puid, relationshipSubType, 
				objectIdentifierMaster);

		if (archive.getArchive() != null && archive.getArchive().size() > 0) {
			for (int x = 0; x < archive.getArchive().size(); x++) {
				addArchive(premis, (ArchiveMD) archive.getArchive().get(x), objectIdentifierMaster);
			}
		}
	}

	public void publish(){
//		MDFilesTmpSqlite mdFileTmp = null;
//		List<MDFilesTmp> rs = null;
//		File filePremis = null;
//		PremisXsd premis = null;
//		String fileObj = null;
//		File fObj = null;
//
//		mdFileTmp = new MDFilesTmpSqlite();
//		rs = mdFileTmp.findByStatus(new String[]{
//				MDFilesTmpSqlite.FINEVALID,
//				MDFilesTmpSqlite.INITPUBLISH
//			});
//
//		for (int x=0; x<rs.size(); x++){
//			filePremis = new File(Configuration.getValue("path.premis")+File.separator+
//					rs.get(x).getId()+".premis");
//			
//			// calcolo il file da validare
//			fileObj = Configuration.getValue("istituto."
//					+ rs.get(x).getIdIstituto() + ".pathTmp");
//			fileObj += File.separator;
//			fileObj += rs.get(x).getNomeFile();
//			fObj = new File(fileObj);
//			log.debug("fileObj: " + fObj.getAbsolutePath());
//			if (!fObj.exists()) {
//				fObj = new File(fObj.getParentFile().getAbsolutePath()+
//						File.separator+
//						fObj.getName().
//						replace(".tar.gz", ".tar").
//						replace(".tgz", ".tar"));
//			}
////			if (fObj.exists()) {
////				premis = new PremisXsd(filePremis);
////				if (premis.)
////			} else {
////				mdFileTmp.updateStopValidate(rs.get(x).getId(),
////						null, false, new String[] { "Il file ["
////								+ fObj.getAbsolutePath()
////								+ "] non è presente sul Server" });
////			}
//		}
	}
}
