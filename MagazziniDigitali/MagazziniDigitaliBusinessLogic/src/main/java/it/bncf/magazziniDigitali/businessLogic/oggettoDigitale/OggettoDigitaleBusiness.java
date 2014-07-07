package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.ObjectComplexType;
import info.lc.xmlns.premis_v2.SignificantPropertiesComplexType;
import it.bncf.magazzimiDigitali.database.entity.MDFilesTmp;
import it.bncf.magazzimiDigitali.databaseSchema.sqlite.MDFilesTmpSqlite;
import it.bncf.magazzimiDigitali.databaseSchema.sqlite.SqliteCore;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrEvent;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrObjectFile;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ArchiveMD;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
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
import java.util.UUID;

import mx.randalf.archive.info.DigestType;
import mx.randalf.archive.info.Xmltype;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.tools.Utils;
import mx.randalf.tools.exception.UtilException;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

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
		AddDocumentMD admd = null;
		QueryResponse qr = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();
			records = mdFileTmp.findBySha1(sha1);

			if (records != null) {
				for (int x = 0; x < records.size(); x++) {
					if (records.get(x).getStato() != null) {
						if (result == null) {
							result = new Hashtable<String, String>();
						}
						result.put("id", records.get(x).getId());
						result.put("stato", records.get(x).getStato());

						// if
						// (records.get(x).getStato().startsWith(MDFilesTmpSqlite.ERROR)){
						// result.put("stato", "ERROR");
						// }else if (records.get(x).getPublishDataEnd() != null)
						// {
						// result.put("stato", "ARCHIVIATO");
						// } else if (records.get(x).getValidDataEnd() != null)
						// {
						// result.put("stato", "CHECKARCHIVIAZIONE");
						// } else if (records.get(x).getValidDataStart() !=
						// null) {
						// result.put("stato", "CHECKARCHIVIAZIONE");
						// } else if (records.get(x).getTrasfDataEnd() != null)
						// {
						// result.put("stato", "FINETRASF");
						// } else if (records.get(x).getTrasfDataStart() !=
						// null) {
						// result.put("stato", "INITTRASF");
						// }
						break;
					}
				}
			} else {
				admd = new AddDocumentMD(
						Configuration.getValue("demoni.Publish.solr.URL"),
						Boolean.parseBoolean(Configuration
								.getValue("demoni.Publish.solr.Cloud")),
						Configuration
								.getValue("demoni.Publish.solr.collection"),
						Integer.parseInt(Configuration
								.getValue("demoni.Publish.solr.connectionTimeOut")),
						Integer.parseInt(Configuration
								.getValue("demoni.Publish.solr.clientTimeOut")));
				qr = admd.find("sha1:" + sha1);
				if (qr != null && qr.getResults() != null
						&& qr.getResults().getNumFound() > 0) {
					if (result == null) {
						result = new Hashtable<String, String>();
					}
					result.put("id", ((String) qr.getResults().get(0)
							.getFieldValue("id")).substring(0, 36));
					result.put("stato", MDFilesTmpSqlite.FINEPUBLISH);
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SolrException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} finally {
			if (mdFileTmp != null) {
				mdFileTmp.disconnect();
			}
			if (admd != null){
				admd.close();
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
	public void confirmDel(String id, boolean esito, String msgError)
			throws FileNotFoundException, ClassNotFoundException, SQLException,
			ConfigurationException {
		MDFilesTmpSqlite mdFileTmp = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();

			mdFileTmp.confirmDel(id, esito, (msgError == null ? null
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

	public void indexer() {
		MDFilesTmpSqlite mdFileTmp = null;
		List<MDFilesTmp> rs = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();

			rs = mdFileTmp.findByStatus(new String[] {
					MDFilesTmpSqlite.FINEVALID, MDFilesTmpSqlite.INITPUBLISH });
			if (rs != null && rs.size() > 0) {
				for (int x = 0; x < rs.size(); x++) {

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

	public void validate(String application, boolean testMode, Logger logValidate) {
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
			logValidate.info("Ricerco oggetti da validare");
			mdFileTmp = new MDFilesTmpSqlite();

			// Eseguo la ricerca di tutti i files che hanno finito il
			// trasferimento oppure che risultano in fase di validazione
			rs = mdFileTmp.findByStatus(new String[] {
					MDFilesTmpSqlite.FINETRASF, MDFilesTmpSqlite.INITVALID });
			if (rs != null && rs.size() > 0) {
				logValidate.info("Numero oggetti da validare ["+rs.size()+"]");
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

						fileTar = fObj.getName().replace(".tar.gz", ".tar")
								.replace(".tgz", ".tar");

						logValidate.info(x+"/"+rs.size()+" File da validare: " + fObj.getAbsolutePath());
						eventDetailDecomp = fObj.getName() + " => " + fileTar;
						if (!fObj.exists()) {
							fObj = new File(fObj.getParentFile()
									.getAbsolutePath()
									+ File.separator
									+ fileTar);
						}
						if (fObj.exists()) {
							// il file Esiste
							if (rs.get(x).getStato()
									.equals(MDFilesTmpSqlite.FINETRASF)) {
								logValidate.info("Inizio la validazione del file ["
										+ fObj.getAbsolutePath() + "]");
								start = mdFileTmp.updateStartValidate(rs.get(x)
										.getId());
							} else {
								logValidate.info("Continuo la validazione del file ["
										+ fObj.getAbsolutePath() + "]");
								start = convertDate(rs.get(x)
										.getValidDataStart());
							}
							validate.check(fObj);
							premis = new PremisXsd();
							if (validate.getGcUnzipStart() != null
									|| validate.getGcUnzipStop() != null) {
								mdFileTmp.updateDecompress(rs.get(x).getId(),
										validate.getGcUnzipStart(), validate
												.getGcUnzipStop(), (validate
												.getUnzipError() == null ? true
												: false), validate
												.getUnzipError());
								startDecomp = validate.getGcUnzipStart();
								stopDecomp = validate.getGcUnzipStop();
							} else {
								if (rs.get(x).getDecompDataStart() != null) {
									startDecomp = convertDate(rs.get(x)
											.getDecompDataStart());
								}
								if (rs.get(x).getDecompDataEnd() != null) {
									stopDecomp = convertDate(rs.get(x)
											.getDecompDataEnd());
								}
							}
							if (startDecomp != null ||
									stopDecomp != null){
								logValidate.info("Tempo per Unzip del file da "+
										(startDecomp == null?"none":SqliteCore.convert(startDecomp)) +
										" a " +
										(stopDecomp == null?"none":SqliteCore.convert(stopDecomp))
										);
							}
							if (validate.getArchive() != null) {
								logValidate.info("Analizzo gli archivi");
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
										Configuration.getValue("istituto."
												+ rs.get(x).getIdIstituto()
												+ ".right.UUID"), archive
												.getType().getFormat()
												.getVersion(), archive
												.getType().getPUID());

								if (archive.getArchive() != null
										&& archive.getArchive().size() > 0) {
									logValidate.info("Inizio analisi degli Archivi "+archive.getArchive().size());
									for (int y = 0; y < archive.getArchive()
											.size(); y++) {
										if ((y%100)==0){
											logValidate.info(y+" Archivi analizzati su "+archive.getArchive().size());
										}
										addArchive(premis, (ArchiveMD) archive
												.getArchive().get(y),
												objectIdentifierMaster);
									}
									logValidate.info("Fine analisi degli Archivi "+archive.getArchive().size());
								}
							}

							fPremis = premis.getActualFileName() + ".premis";
							filePremis = new File(
									Configuration.getValue("path.premis")
											+ File.separator + fPremis);
							premis.addEvent("send", convertDate(rs.get(x)
									.getTrasfDataStart()), convertDate(rs
									.get(x).getTrasfDataEnd()), null, "OK",
									null, Configuration.getValue("istituto."
											+ rs.get(x).getIdIstituto()
											+ ".UUID"), Configuration
											.getValue("istituto."
													+ rs.get(x).getIdIstituto()
													+ ".software.UUID"),
									objectIdentifierMaster);
							if (validate.getUnzipError() != null) {
								logValidate.error("Riscontrato un problema nella procedura di Unzip");
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
									logValidate.info("Tempo di decompressione da "+
												(startDecomp==null?"none":SqliteCore.convert(startDecomp))+
												" a "+
												(stopDecomp==null?"none":SqliteCore.convert(stopDecomp))
												);
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
									logValidate.error("Riscontrato un errore nella validazione");
									stop = mdFileTmp.updateStopValidate(
											rs.get(x).getId(), null, false,
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
										logValidate.error("Impossibile individuare il formato del tracciato XML presente nel file");
										stop = mdFileTmp
												.updateStopValidate(
														rs.get(x).getId(),
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
										stop = mdFileTmp.updateStopValidate(rs
												.get(x).getId(), validate
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
										logValidate.info("Tempo di validazione da "+
												(start==null?"none":SqliteCore.convert(start))+
												" a "+
												(stop==null?"none":SqliteCore.convert(stop))
												);
									}
								}
							}
						} else {
							logValidate.info("Il file ["
									+ fObj.getAbsolutePath()
									+ "] non è presente sul Server");
							mdFileTmp.updateStopValidate(rs.get(x).getId(),
									null, false, new String[] { "Il file ["
											+ fObj.getAbsolutePath()
											+ "] non è presente sul Server" },
									fPremis);
						}
					} catch (ConfigurationException e) {
						mdFileTmp
								.updateStopValidate(rs.get(x).getId(), null,
										false, new String[] { e.getMessage() },
										fPremis);
						log.error(e.getMessage(), e);
					} catch (SQLException e) {
						mdFileTmp
								.updateStopValidate(rs.get(x).getId(), null,
										false, new String[] { e.getMessage() },
										fPremis);
						log.error(e.getMessage(), e);
					} catch (PremisXsdException e) {
						mdFileTmp
								.updateStopValidate(rs.get(x).getId(), null,
										false, new String[] { e.getMessage() },
										fPremis);
						log.error(e.getMessage(), e);
					} catch (XsdException e) {
						mdFileTmp
								.updateStopValidate(rs.get(x).getId(), null,
										false, new String[] { e.getMessage() },
										fPremis);
						log.error(e.getMessage(), e);
					} catch (IOException e) {
						mdFileTmp
								.updateStopValidate(rs.get(x).getId(), null,
										false, new String[] { e.getMessage() },
										fPremis);
						log.error(e.getMessage(), e);
					} catch (Exception e) {
						mdFileTmp
								.updateStopValidate(rs.get(x).getId(), null,
										false, new String[] { e.getMessage() },
										fPremis);
						log.error(e.getMessage(), e);
					} finally {
						if (testMode) {
							break;
						}
					}
				}
			} else {
				logValidate.info("Nessun oggetto da validare ");
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
			} finally {
				logValidate.info("Fine ciclo di validazione");
			}
		}
	}

	private GregorianCalendar convertDate(String date) {
		GregorianCalendar gc = null;
		int year;
		int month;
		int dayOfMonth;
		int hourOfDay;
		int minute;
		int second;
		int millisecond = 0;
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
		if (pos == -1) {
			second = Integer.parseInt(st2[2]);
		} else {
			second = Integer.parseInt(st2[2].substring(0, pos));
			millisecond = Integer.parseInt(st2[2].substring(pos + 1));
		}

		gc = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute,
				second);
		if (millisecond > 0) {
			gc.add(Calendar.MILLISECOND, millisecond);
		}
		return gc;
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

	/**
	 * Questo metodo viene utilizzato per gestire la pubblicazione del materiale
	 * 
	 * @param application
	 */
	public void publish(String application, boolean testMode, Logger logPublish) {
		MDFilesTmpSqlite mdFileTmp = null;
		List<MDFilesTmp> rs = null;
		MDFilesTmp record = null;
		File filePremis = null;
		File filePremisMaster = null;
		PremisXsd premisInput = null;
		PremisXsd premisElab = null;
		String fileObj = null;
		String objectIdentifierPremis = null;
		String objectIdentifierContainer = null;
		File fObj = null;
		File fObjNew = null;
		File premisDest = null;
		ValidateFile validate = null;
		ArchiveMD archive = null;
		GregorianCalendar start = null;
		GregorianCalendar stop = null;
		int pos = 0;
		String ext = null;

		try {
			logPublish.info("Ricerco oggetti da Pubblicare");
			mdFileTmp = new MDFilesTmpSqlite();
			rs = mdFileTmp.findByStatus(new String[] {
					MDFilesTmpSqlite.FINEVALID, MDFilesTmpSqlite.INITPUBLISH });

			validate = new ValidateFile();
			if (rs != null) {
				logPublish.info("Ci sono "+rs.size()+" oggetti da Pubblicare");
				for (int x = 0; x < rs.size(); x++) {
					filePremis = new File(Configuration.getValue("path.premis")
							+ File.separator + UUID.randomUUID().toString()
							+ ".premis");
					try {
						record = rs.get(x);

						filePremisMaster = new File(
								Configuration.getValue("path.premis")
										+ File.separator
										+ record.getPremisFile());
						if (record.getStato()
								.equals(MDFilesTmpSqlite.FINEVALID)) {
							logPublish.info(x+"/"+rs.size()+" Inizio la pubblicazione del file ["
									+ filePremisMaster.getAbsolutePath() + "]");
							start = mdFileTmp
									.updateStartPublish(record.getId());
						} else {
							logPublish.info(x+"/"+rs.size()+" Continuo la pubblicazione del file ["
									+ filePremisMaster.getAbsolutePath() + "]");
							start = convertDate(record.getPublishDataStart());
						}

						if (filePremisMaster.exists()) {
							// calcolo il file da validare
							fileObj = Configuration.getValue("istituto."
									+ record.getIdIstituto() + ".pathTmp");
							fileObj += File.separator;
							fileObj += record.getNomeFile();
							fObj = new File(fileObj);
							logPublish.info("fileObj: " + fObj.getAbsolutePath());
							if (!fObj.exists()) {
								fObj = new File(fObj.getParentFile()
										.getAbsolutePath()
										+ File.separator
										+ fObj.getName()
												.replace(".tar.gz", ".tar")
												.replace(".tgz", ".tar"));
							}
							if (fObj.exists()) {
								premisInput = new PremisXsd(filePremisMaster);

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

								objectIdentifierContainer = findObjectIdentifierContainer(premisInput);

								premisDest= genFileDest(filePremisMaster.getName());
								logPublish.info("Copio il file "+
											filePremisMaster.getAbsolutePath()+
											" in "+
											premisDest.getAbsolutePath());
								// Copio il file premis nella sua posizione
								// definitiva
								if (copyFile(
										filePremisMaster,
										premisDest,
										record, mdFileTmp, premisElab,
										application, objectIdentifierPremis, rs
												.get(x).getIdIstituto())) {
									pos = fObj.getName().indexOf(".");
									ext = fObj.getName().substring(pos);
									fObjNew = genFileDest(objectIdentifierContainer
											+ ext);
									
									logPublish.info("Sposto il file  "+
											fObj.getAbsolutePath()+
											" in "+
											fObjNew.getAbsolutePath());
									if (moveFile(fObj, fObjNew, record,
											mdFileTmp, premisElab, application,
											objectIdentifierContainer)) {
										logPublish.info("Pubblico il materiale in Solr");
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
											logPublish.info("Materiale pubblicato");
										} else {
											logPublish.error("Riscontrato un problema nella pubblicazione");
										}
									} else {
										logPublish.error("Riscontrato un problema nello spostamento");
									}
								} else {
									logPublish.error("Riscontrato un problema nella copia");
								}
							} else {
								logPublish.error("Il file ["
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
						mdFileTmp.updateStopPublish(rs.get(x).getId(), false,
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
						mdFileTmp.updateStopPublish(rs.get(x).getId(), false,
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
						mdFileTmp.updateStopPublish(rs.get(x).getId(), false,
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
						mdFileTmp.updateStopPublish(rs.get(x).getId(), false,
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
						mdFileTmp.updateStopPublish(rs.get(x).getId(), false,
								new String[] { e.getMessage() });
						log.error(e.getMessage(), e);
					} finally {
						try {
							premisElab.write(filePremis, false);
						} catch (PremisXsdException e) {
							log.error(e.getMessage(), e);
							mdFileTmp.updateStopPublish(rs.get(x).getId(),
									false, new String[] { e.getMessage() });
						} catch (XsdException e) {
							log.error(e.getMessage(), e);
							mdFileTmp.updateStopPublish(rs.get(x).getId(),
									false, new String[] { e.getMessage() });
						} catch (IOException e) {
							log.error(e.getMessage(), e);
							mdFileTmp.updateStopPublish(rs.get(x).getId(),
									false, new String[] { e.getMessage() });
						} catch (Exception e) {
							log.error(e.getMessage(), e);
							mdFileTmp.updateStopPublish(rs.get(x).getId(),
									false, new String[] { e.getMessage() });
						} finally{
							if (testMode) {
								break;
							}
						}
					}
				}
			} else {
				logPublish.info("Nessun oggetti da Pubblicare");
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

		try {
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
				logPublish.info("Oggetto da pubblicare "+objects.size());
				sof = new SolrObjectFile();
				for (int x = 0; x < objects.size(); x++) {
					if ((x%100)==0){
						logPublish.info("Oggetto "+x+"/"+objects.size());
						System.gc();
					}
					object = objects.get(x);
					if (object instanceof info.lc.xmlns.premis_v2.File) {
						sof.publishSolr((info.lc.xmlns.premis_v2.File) object,
								admd, fObj);
					}
				}
				logPublish.info("Fine pubblicazione oggetti");
			}
			System.gc();
			events = premis.getEvent();
			if (events != null && events.size() > 0) {
				logPublish.info("Eventi da pubblicare "+events.size());
				se = new SolrEvent();
				for (int x = 0; x < events.size(); x++) {
					if ((x%100)==0){
						logPublish.info("Eventi "+x+"/"+events.size());
						System.gc();
					}
					event = events.get(x);
					se.publishSolr(event, admd);
				}
				logPublish.info("Fine pubblicazione eventi");
			}
			logPublish.info("Inizio pubblicazione in Solr");
			System.gc();
			admd.commint();
			logPublish.info("Fine pubblicazione in Solr");
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
					logPublish.info("Inizio ottimizzazione in Solr");
					admd.optimize();
					logPublish.info("Fine ottimizzazione in Solr");
				} else {
					throw new SolrException("Riscontrato un problema nella connessione al Database");
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
				premisElab.addEvent("copyPremis", convertDate(record
						.getCopyPremisDataStart()), convertDate(record
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
				premisElab.addEvent("moveFile", convertDate(record
						.getMoveFileDataStart()), convertDate(record
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
}
