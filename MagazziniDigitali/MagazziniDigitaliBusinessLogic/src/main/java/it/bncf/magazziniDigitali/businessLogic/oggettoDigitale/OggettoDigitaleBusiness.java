package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.OggettoDigitalePublish;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ArchiveMD;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.utils.Record;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.naming.NamingException;

import mx.randalf.archive.info.DigestType;
import mx.randalf.archive.info.Xmltype;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class OggettoDigitaleBusiness {


	protected HibernateTemplate hibernateTemplate;
	
	public Logger log = Logger.getLogger(getClass());

//	private Vector<Record> records = null;

	public OggettoDigitaleBusiness(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * Metodo utilizzato per individuare lo stato di caricamento del materiale
	 * relativo ad un istituto specifico
	 * 
	 * @param idIstituto
	 *            Identificativo dell'Istituto
	 * @return Status dell'Istituto
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException
	 */
	public TreeMap<String, Integer> findStatus(String idIstituto)
			throws HibernateException, NamingException, ConfigurationException {
		MDFilesTmpBusiness mdFileTmp = null;
		TreeMap<String, Integer> ris = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			ris = new TreeMap<String, Integer>(
					mdFileTmp.findCountByIstituto(idIstituto));
		} catch (HibernateException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		}
		return ris;
	}

	public Vector<Record> findByNomeFile(String idIstituto, String nomeFile)
			throws SQLException {
		MDFilesTmpBusiness mdFileTmp = null;
		Vector<Record> records = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			records = mdFileTmp.findToRecord(null, idIstituto, nomeFile, null, null);
//			if (records!= null){
//				for (MDFilesTmp ai : records) {
//					addRecord(ai);
//				}
//			}
		} catch (ConfigurationException | HibernateException | NamingException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		}
		return records;
	}
/*
	protected void addRecord(MDFilesTmp dati) throws ConfigurationException {

		try {
			if (this.records == null) {
				this.records = new Vector<Record>();
			}

			this.records.add(setRecord(dati, true));
		} catch (ConfigurationException e) {
			log.error(e);
			throw e;
		}

	}

	public static Record setRecord(MDFilesTmp dati, boolean isInteger)
			throws ConfigurationException {
		Record record = null;
		// Vector<Record> clubs = null;
		// Iterator<SociClub> iClubs = null;

		record = new Record();
		record.set("idMDFilesTmp", dati.getId());
		record.set("idIstituto", dati.getIdIstituto());
		record.set("nomeFile", dati.getNomeFile());
		record.set("sha1", dati.getSha1());
		record.set("nomeFileMod", convertTimestamp(dati.getNomeFileMod()).getTime()+"");
		record.set("stato", dati.getStato());

		if (dati.getTrasfDataStart()!= null){
			record.set("trasfDataStart", convertTimestamp(dati.getTrasfDataStart()).getTime()+"");
		}
		if (dati.getTrasfDataEnd()!= null){
			record.set("trasfDataEnd", convertTimestamp(dati.getTrasfDataEnd()).getTime()+"");
		}
		record.set("trasfEsito", dati.isTrasfEsito());

		if (dati.getValidDataStart()!= null){
		record.set("validDataStart", convertTimestamp(dati.getValidDataStart()).getTime()+"");
		}
		if (dati.getValidDataEnd()!= null){
		record.set("validDataEnd", convertTimestamp(dati.getValidDataEnd()).getTime()+"");
		}
		record.set("validEsito", dati.isValidEsito());

		record.set("xmlMimeType", dati.getXmlMimeType());

		if (dati.getDecompDataStart()!= null){
		record.set("decompDataStart", convertTimestamp(dati.getDecompDataStart()).getTime()+"");
		}
		if (dati.getDecompDataEnd()!= null){
		record.set("decompDataEnd", convertTimestamp(dati.getDecompDataEnd()).getTime()+"");
		}
		record.set("decompEsito", dati.isDecompEsito());

		if (dati.getPublishDataStart()!= null){
		record.set("publishDataStart", convertTimestamp(dati.getPublishDataStart()).getTime()+"");
		}
		if (dati.getPublishDataEnd()!= null){
		record.set("publishDataEnd", convertTimestamp(dati.getPublishDataEnd()).getTime()+"");
		}
		record.set("publishEsito", dati.isPublishEsito());

		if (dati.getCopyPremisDataStart()!= null){
		record.set("copyPremisDataStart", convertTimestamp(dati.getCopyPremisDataStart()).getTime()+"");
		}
		if (dati.getCopyPremisDataEnd()!= null){
		record.set("copyPremisDataEnd", convertTimestamp(dati.getCopyPremisDataEnd()).getTime()+"");
		}
		record.set("copyPremisEsito", dati.isCopyPremisEsito());

		if (dati.getMoveFileDataStart()!= null){
		record.set("moveFileDataStart", convertTimestamp(dati.getMoveFileDataStart()).getTime()+"");
		}
		if (dati.getMoveFileDataEnd()!= null){
		record.set("moveFileDataEnd", convertTimestamp(dati.getMoveFileDataEnd()).getTime()+"");
		}
		record.set("moveFileEsito", dati.isMoveFileEsito());

		if (dati.getDeleteLocalData()!= null){
		record.set("deleteLocalDataEnd", convertTimestamp(dati.getDeleteLocalData()).getTime()+"");
		}
		record.set("deleteLocalEsito", dati.isDeleteLocalEsito());

		record.set("premisFile", dati.getPremisFile());

		return record;
	}
*/
	/**
	 * Metodo utilizzato per verificare lo stato dell'OggettoDigitale
	 * 
	 * @param sha1
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException
	 * @throws SolrException
	 * @throws SolrServerException
	 */
	public Hashtable<String, String> checkStatus(String sha1)
			throws FileNotFoundException, ClassNotFoundException, SQLException,
			ConfigurationException, SolrException, SolrServerException,
			HibernateException, NamingException {
		MDFilesTmpBusiness mdFileTmp = null;
		List<MDFilesTmp> records = null;
		Hashtable<String, String> result = null;
		AddDocumentMD admd = null;
		QueryResponse qr = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			records = mdFileTmp.find(null, null, null, null, sha1);

			if (records != null) {
				for (int x = 0; x < records.size(); x++) {
					if (records.get(x).getStato() != null) {
						if (result == null) {
							result = new Hashtable<String, String>();
						}
						result.put("id", records.get(x).getId());
						result.put("stato", records.get(x).getStato());

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
					result.put("stato", MDFilesTmpDAO.FINEPUBLISH);
				}
			}
		} catch (ConfigurationException e) {
			throw e;
		} catch (NumberFormatException e) {
			throw e;
		} catch (SolrException e) {
			throw e;
		} catch (SolrServerException e) {
			throw e;
		} catch (HibernateException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
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
	public String initSend(String idIstituto, String nomeFile, String sha1,
			Calendar nomeFileMod) throws SQLException {
		MDFilesTmpBusiness mdFileTmp = null;
		String id = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);

			id = mdFileTmp
					.insertNewRec(idIstituto, nomeFile, sha1, nomeFileMod);
		} catch (SQLException e) {
			throw e;
		}
		return id;
	}
	 */

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
	public void endSend(String id, boolean esito, String msgError)
			throws FileNotFoundException, ClassNotFoundException, SQLException,
			ConfigurationException {
		MDFilesTmpDAO mdFileTmp = null;

		try {
			mdFileTmp = new MDFilesTmpDAO();

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
	 */

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
	public void confirmDel(String id, boolean esito, String msgError)
			throws FileNotFoundException, ClassNotFoundException, SQLException,
			ConfigurationException {
		MDFilesTmpDAO mdFileTmp = null;

		try {
			mdFileTmp = new MDFilesTmpDAO();

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
	 */

	/*
	public void indexer() {
		MDFilesTmpDAO mdFileTmp = null;
		List<MDFilesTmp> rs = null;

		try {
			mdFileTmp = new MDFilesTmpDAO();

			rs = mdFileTmp.findByStatus(new String[] {
					MDFilesTmpDAO.FINEVALID, MDFilesTmpDAO.INITPUBLISH });
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
*/
	public void validate(String application, boolean testMode,
			Logger logValidate) {
		MDFilesTmpBusiness mdFileTmp = null;
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
		DateFormat df = null;

		try {
			df = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
			logValidate.debug("Ricerco oggetti da validare");
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);

			// Eseguo la ricerca di tutti i files che hanno finito il
			// trasferimento oppure che risultano in fase di validazione
			rs = mdFileTmp.find(null, null, null, new String[] {
					MDFilesTmpDAO.FINETRASF, MDFilesTmpDAO.INITVALID }, null);
			if (rs != null && rs.size() > 0) {
				logValidate.info("Numero oggetti da validare [" + rs.size()
						+ "]");
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

						logValidate.info(x + "/" + rs.size()
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
							if (rs.get(x).getStato()
									.equals(MDFilesTmpDAO.FINETRASF)) {
								logValidate
										.info("Inizio la validazione del file ["
												+ fObj.getAbsolutePath() + "]");
								start = mdFileTmp.updateStartValidate(rs.get(x)
										.getId());
							} else {
								logValidate
										.info("Continuo la validazione del file ["
												+ fObj.getAbsolutePath() + "]");
								start = new GregorianCalendar();
								start.setTimeInMillis(rs.get(x)
										.getValidDataStart().getTime());
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
									startDecomp = new GregorianCalendar();
									startDecomp.setTimeInMillis(rs.get(x)
											.getDecompDataStart().getTime());
								}
								if (rs.get(x).getDecompDataEnd() != null) {
									stopDecomp = new GregorianCalendar();
									stopDecomp.setTimeInMillis(rs.get(x)
											.getDecompDataEnd().getTime());
								}
							}
							if (startDecomp != null || stopDecomp != null) {
								logValidate.info("Tempo per Unzip del file da "
										+ (startDecomp == null ? "none"
												: df.format(startDecomp.getTime()))
										+ " a "
										+ (stopDecomp == null ? "none"
												: df.format(stopDecomp.getTime())));
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
									logValidate
											.info("Inizio analisi degli Archivi "
													+ archive.getArchive()
															.size());
									for (int y = 0; y < archive.getArchive()
											.size(); y++) {
										if ((y % 100) == 0) {
											logValidate.info(y
													+ " Archivi analizzati su "
													+ archive.getArchive()
															.size());
										}
										addArchive(premis, (ArchiveMD) archive
												.getArchive().get(y),
												objectIdentifierMaster);
									}
									logValidate
											.info("Fine analisi degli Archivi "
													+ archive.getArchive()
															.size());
								}
							}

							fPremis = premis.getActualFileName() + ".premis";
							filePremis = new File(
									Configuration.getValue("path.premis")
											+ File.separator + fPremis);
							premis.addEvent("send", rs.get(x)
									.getTrasfDataStart(), rs
									.get(x).getTrasfDataEnd(), null, "OK",
									null, Configuration.getValue("istituto."
											+ rs.get(x).getIdIstituto()
											+ ".UUID"), Configuration
											.getValue("istituto."
													+ rs.get(x).getIdIstituto()
													+ ".software.UUID"),
									objectIdentifierMaster);
							if (validate.getUnzipError() != null) {
								logValidate
										.error("Riscontrato un problema nella procedura di Unzip");
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
											.info("Tempo di decompressione da "
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
											.error("Riscontrato un errore nella validazione");
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
										logValidate
												.error("Impossibile individuare il formato del tracciato XML presente nel file");
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
										logValidate
												.info("Tempo di validazione da "
														+ (start == null ? "none"
																: df.format(start.getTime()))
														+ " a "
														+ (stop == null ? "none"
																: df.format(stop.getTime())));
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
				logValidate.debug("Nessun oggetto da validare ");
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static Date convertDate(String date){
		Date ris = null;
		GregorianCalendar gc = null;
		gc = convertGregorianCalendar(date);
		ris = new Date(gc.getTimeInMillis());
		return ris;
	}
	

	public static Timestamp convertTimestamp(String date){
		Timestamp ris = null;
		GregorianCalendar gc = null;
		gc = convertGregorianCalendar(date);
		ris = new Timestamp(gc.getTimeInMillis());
		return ris;
	}

	public static GregorianCalendar convertGregorianCalendar(String date) {
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
		MDFilesTmpBusiness mdFileTmp = null;
		List<MDFilesTmp> rs = null;

		List<Future<Boolean>> futuresList = null;
		int nrOfProcessors = 1;
		ExecutorService eservice = null;
		int numberThread = 10;

		try {
			logPublish.debug("Ricerco oggetti da Pubblicare");
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			rs = mdFileTmp.find(null, null, null, new String[] {
					MDFilesTmpDAO.FINEVALID, MDFilesTmpDAO.INITPUBLISH }, null);

			if (rs != null && rs.size() > 0) {
				logPublish.info("Ci sono " + rs.size()
						+ " oggetti da Pubblicare");
				futuresList = new ArrayList<Future<Boolean>>();
				nrOfProcessors = Runtime.getRuntime().availableProcessors();
				eservice = Executors.newFixedThreadPool(nrOfProcessors);
				if (Configuration.getValue("demoni.Publish.numberThread") != null) {
					numberThread = Integer.valueOf(Configuration
							.getValue("demoni.Publish.numberThread"));
				}
				if (testMode) {
					numberThread = 1;
				}
				for (int x = 0; x < rs.size(); x++) {

					if (futuresList.size() >= numberThread) {
						checkThread(futuresList, numberThread);
						if (testMode) {
							break;
						}
					}
					futuresList.add(eservice.submit(new OggettoDigitalePublish(
							rs.get(x), logPublish, mdFileTmp, "Publish " + x
									+ "/" + rs.size(), application)));
					Thread.sleep(10000);
				}
				checkThread(futuresList, -1);
				eservice.shutdown();
			} else {
				logPublish.debug("Nessun oggetti da Pubblicare");
			}
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void checkThread(List<Future<Boolean>> futuresList, int numberThread) {
		while (true) {
			try {
				for (Future<Boolean> future : futuresList) {
					if (future.isDone()) {
						future.get();
						if (!futuresList.remove(future)) {
							log.error("Riscontrato nella eliminazione di un Thread dalla Lista");
						}
						future = null;
						break;
					}
				}
				if (futuresList.size() == 0) {
					break;
				} else {
					if (numberThread > -1) {
						if (futuresList.size() < numberThread) {
							break;
						}
					}
				}
			} catch (Exception e) {
			}

		}
	}
}
