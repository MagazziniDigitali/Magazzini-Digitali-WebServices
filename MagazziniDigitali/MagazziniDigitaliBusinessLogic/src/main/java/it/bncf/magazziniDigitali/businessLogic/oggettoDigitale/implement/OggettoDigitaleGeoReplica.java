/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import it.bncf.magazziniDigitali.businessLogic.archive.MDArchiveBusiness;
import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.exception.ClientMDException;
import it.bncf.magazziniDigitali.database.dao.MDNodiDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDArchive;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.utils.GenFileDest;
import it.depositolegale.www.storage.Documenti;
import it.depositolegale.www.storage.DocumentiDocumento;
import it.depositolegale.www.storage.Storage;
import it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortTypeProxy;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.naming.NamingException;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.digest.SHA1;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;


/**
 * @author massi
 *
 */
public class OggettoDigitaleGeoReplica {

	private Logger log = Logger.getLogger(OggettoDigitaleGeoReplica.class);

	private Logger logPublish = null;
	
	private String name = null;
	
	private HibernateTemplate hibernateTemplate= null;
	private boolean trasterito = false;

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica(HibernateTemplate hibernateTemplate, Logger logPublish, String name) {
		this.logPublish = logPublish;
		this.name = name;
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unused")
	public boolean esegui(String objectIdentifierPremis, MDFilesTmpBusiness mdFileTmpBusiness, String application) {
		File filePremis = null;
		PremisXsd premisElab = null;
		GregorianCalendar gcStart = null;
		GregorianCalendar gcEnd = null;
		MDFilesTmp mdFilesTmp = null;
		File fileElab = null;
		File fileElabPremis = null;
		MDNodiDAO mdNodiDAO = null;
		List<MDNodi> mdNodis = null;
		boolean elabNodi = true;
		File[] files = null;
		String[] msgs = null;
		Vector<String> msgErr = null;
		boolean esito = true;

		trasterito = false;
		try {
			mdFilesTmp = mdFileTmpBusiness.findPremis(objectIdentifierPremis);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		}

		try {
			if (mdFilesTmp != null){
				FactoryDAO.initialize(mdFilesTmp.getStato());
				if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEPUBLISH) ||
						mdFilesTmp.getStato().getId().equals(MDStatoDAO.INITARCHIVE)){
					trasterito = true;
					try {
						filePremis = new File(Configuration.getValue("path.premis")
								+ File.separator + UUID.randomUUID().toString()
								+ ".premis");

						premisElab = new PremisXsd();
						try {

							fileElabPremis = GenFileDest.genFileDest(Configuration.getValue("demoni.Publish.pathStorage")
									,mdFilesTmp.getPremisFile());
							fileElab = new File(fileElabPremis.getAbsolutePath().replace(".premis", ""));
							log.info(name+" Inizio l'elaborazione del file ["+fileElab.getAbsolutePath()+"]");
							if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEPUBLISH)){
								logPublish.info(name+" Inizio l'archiviazione del file ["
										+ fileElab.getAbsolutePath() + "]");
								gcStart = mdFileTmpBusiness
										.updateStartArchive(mdFilesTmp.getId());
							} else {
								logPublish.info(name+" Continuo l'archivizione del file ["
										+ fileElab.getAbsolutePath() + "]");
								gcStart = new GregorianCalendar();
								gcStart.setTimeInMillis(mdFilesTmp.getArchiveDataStart().getTime());
							}
							premisElab
							.addObjectFileContainer(
									objectIdentifierPremis,
									null,
									null,
									new BigInteger("0"),
									null,
									null,
									null, null, null,
									null, null);
							if (fileElab.exists()){
								if (fileElabPremis.exists()){
									mdNodiDAO = new MDNodiDAO(hibernateTemplate);
									mdNodis = mdNodiDAO.findAll();
									elabNodi = true;
									files = new File[2];
									files[0] = fileElab;
									files[1] = fileElabPremis;
									for (MDNodi mdNodi : mdNodis){
										if (!mdNodi.getId().equals(Configuration.getValue("nodo"))){
											msgs =geoReplica(mdNodi, files, mdFilesTmp, premisElab, application, objectIdentifierPremis);
											if (msgs!=null){
												if (msgErr==null){
													msgErr = new Vector<String>();
												}
												for (int x=0; x<msgs.length; x++){
													msgErr.add(msgs[x]);
												}
												elabNodi = false;
											}
										}
									}
									if (elabNodi){
										gcEnd = mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), true, null);
									} else {
										esito = false;
										gcEnd = mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false, 
												msgErr.toArray(new String[msgErr.size()]));
									}
								} else {
									esito = false;
									logPublish.error(name+" Il file ["+fileElabPremis.getAbsolutePath()+"] non è presente");
									premisElab.addEvent(
											"Error",
											null,
											null,
											null,
											"KO",
											new String[] { "Il file ["+fileElabPremis.getAbsolutePath()+"] non è presente" },
											null,
											Configuration.getValue("demoni."
													+ application + ".UUID"), null);
									mdFileTmpBusiness
									.updateStopArchive(
											mdFilesTmp.getId(),
											false,
											new String[] { "Il file ["+fileElabPremis.getAbsolutePath()+"] non è presente" });
								}
							} else {
								esito = false;
								logPublish.error(name+" Il file ["+fileElab.getAbsolutePath()+"] non è presente");
								premisElab.addEvent(
										"Error",
										null,
										null,
										null,
										"KO",
										new String[] { "Il file ["+fileElab.getAbsolutePath()+"] non è presente" },
										null,
										Configuration.getValue("demoni."
												+ application + ".UUID"), null);
								mdFileTmpBusiness
								.updateStopArchive(
										mdFilesTmp.getId(),
										false,
										new String[] { "Il file ["+fileElab.getAbsolutePath()+"] non è presente" });
							}
						} catch (ConfigurationException e) {
							esito = false;
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
							mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
									new String[] { e.getMessage() });
							log.error(e.getMessage(), e);
						} catch (SQLException e) {
							esito = false;
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
							mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
									new String[] { e.getMessage() });
							log.error(e.getMessage(), e);
						} catch (HibernateException e) {
							esito = false;
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
							mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
									new String[] { e.getMessage() });
							log.error(e.getMessage(), e);
						} catch (NoSuchAlgorithmException e) {
							esito = false;
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
							mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
									new String[] { e.getMessage() });
							log.error(e.getMessage(), e);
						} catch (IOException e) {
							esito = false;
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
							mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
									new String[] { e.getMessage() });
							log.error(e.getMessage(), e);
						}finally{
							logPublish.info(name+" Fine l'elaborazione del file ["+objectIdentifierPremis+"]");
							try {
								if (premisElab != null){
									premisElab.write(filePremis, false);
								}
							} catch (PremisXsdException e) {
								esito = false;
								log.error(e.getMessage(), e);
								mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
										false, new String[] { e.getMessage() });
							} catch (XsdException e) {
								esito = false;
								log.error(e.getMessage(), e);
								mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
										false, new String[] { e.getMessage() });
							} catch (IOException e) {
								esito = false;
								log.error(e.getMessage(), e);
								mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
										false, new String[] { e.getMessage() });
							} catch (Exception e) {
								esito = false;
								log.error(e.getMessage(), e);
								mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
										false, new String[] { e.getMessage() });
							}
						}
					} catch (SQLException e) {
						esito = false;
						log.error(e.getMessage(), e);
					}
				} else if (!mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEARCHIVE)){
					esito = false;
				}
			} else {
				logPublish.error(name+" Il file premis ["+objectIdentifierPremis+"] non è presente in archivio");
				esito = false;
			}
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
			esito = false;
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			esito = false;
		}
		return esito;
	}

	private String[] geoReplica(MDNodi mdNodi, File[] files, MDFilesTmp mdFilesTmp, PremisXsd premisElab, 
			String application, String objectIdentifierMaster) throws HibernateException, NamingException, 
			ConfigurationException, SQLException, NoSuchAlgorithmException, IOException{
		Vector<String> ris = null;
		Storage storage = null;
//		MDArchiveDAO mdArchivioDAO = null;
		MDArchive mdArchive = null;
		MDArchiveBusiness mdArchiveBusiness = null;
		GregorianCalendar dataStart = null;
		GregorianCalendar dataEnd = null;
		GregorianCalendar dStart = null;
		Documenti documenti = null;
		
		try {
			dataStart = new GregorianCalendar();
			dStart = new GregorianCalendar();
			mdArchiveBusiness = new MDArchiveBusiness(hibernateTemplate);
			mdArchive = mdArchiveBusiness.find(mdFilesTmp, mdNodi);
			if (mdArchive == null || !mdArchive.getEsito().booleanValue()){
				documenti = genDocumenti(files);
				storage = checkStorage(mdNodi, documenti);
				if (storage !=null && (storage.getEsito().equals("OK")
						||storage.getEsito().equals("DOCNOTFOUND"))){
					for (int x=0; x<files.length; x++){
						ris = sendFile(mdNodi,files[x],premisElab, application, objectIdentifierMaster, ris);
					}
					if (ris==null){
						storage = checkStorage(mdNodi, documenti);
						ris = analizeRisp(storage, mdNodi, premisElab, dStart, files, application, objectIdentifierMaster, ris, true);
					}
				} else {
					ris = analizeRisp(storage, mdNodi, premisElab, dStart, files, application, objectIdentifierMaster, ris, false);
				}
				dataEnd = new GregorianCalendar();
				mdArchiveBusiness.insert((mdArchive==null?null:mdArchive.getId()), 
						mdFilesTmp, mdNodi, dataStart, dataEnd, (ris==null));
			} else {
				for (int x=0; x<files.length; x++){
					addGeoReplica(premisElab, mdArchive.getDataStart(), mdArchive.getDataEnd(), 
							mdNodi, files[x].getAbsolutePath(), 
							null, application, objectIdentifierMaster);
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return (ris==null?null:ris.toArray(new String[ris.size()]));
	}

	private Vector<String> analizeRisp(Storage storage, MDNodi mdNodi, PremisXsd premisElab,
			GregorianCalendar dStart, File[] files, String application, String objectIdentifierMaster,
			Vector<String> ris, boolean testRes) throws ConfigurationException{
		String msg = null;
		GregorianCalendar dEnd = null;

		dEnd = new GregorianCalendar();
		for (int x=0; x<storage.getDocumenti().getDocumento().length; x++){
			if (storage.getDocumenti().getDocumento()[x].getEsito().equals("ERRORDIGEST")){
				if (ris ==null){
					ris = new Vector<String>();
				}
				msg = "Nel modo: ["+
						mdNodi.getNome()+
						" - "+
						mdNodi.getDescrizione()+
						"] lo sha1 del file ["+
						storage.getDocumenti().getDocumento()[x].getNomeFile()+
						"] non coincide";
				ris.add(msg);
				addGeoReplica(premisElab, dStart, dEnd, mdNodi, files[0].getAbsolutePath(), 
						msg, application, objectIdentifierMaster);
			} else if (storage.getDocumenti().getDocumento()[x].getEsito().equals("ERRORCREATEFOLDER")){
				if (ris ==null){
					ris = new Vector<String>();
				}
				msg = "Nel modo: ["+
						mdNodi.getNome()+
						" - "+
						mdNodi.getDescrizione()+
						"] riscontrato un problema nella creazione della cartella per il file ["+
						storage.getDocumenti().getDocumento()[x].getNomeFile()+
						"]";
				ris.add(msg);
				addGeoReplica(premisElab, dStart, dEnd, mdNodi, files[0].getAbsolutePath(), 
						msg, application, objectIdentifierMaster);
			} else if (storage.getDocumenti().getDocumento()[x].getEsito().equals("ERROR")){
				if (ris ==null){
					ris = new Vector<String>();
				}
				msg = "Nel modo: ["+
						mdNodi.getNome()+
						" - "+
						mdNodi.getDescrizione()+
						"] riscontrato un errore generio per il file ["+
						storage.getDocumenti().getDocumento()[x].getNomeFile()+
						"]";
				ris.add(msg);
				addGeoReplica(premisElab, dStart, dEnd, mdNodi, files[0].getAbsolutePath(), 
						msg, application, objectIdentifierMaster);
			} else if (!testRes && 
					storage.getDocumenti().getDocumento()[x].getEsito().equals("FOUND")){
				addGeoReplica(premisElab, dStart, dEnd, mdNodi, files[0].getAbsolutePath(), 
						null, application, objectIdentifierMaster);
			} else if (storage.getDocumenti().getDocumento()[x].getEsito().equals("NOTFOUND")){
				if (testRes){
					if (ris ==null){
						ris = new Vector<String>();
					}
					msg = "Nel modo: ["+
							mdNodi.getNome()+
							" - "+
							mdNodi.getDescrizione()+
							"] riscontrato un errore nella verifica per il file ["+
							storage.getDocumenti().getDocumento()[x].getNomeFile()+
							"]";
					ris.add(msg);
					addGeoReplica(premisElab, dStart, dEnd, mdNodi, files[0].getAbsolutePath(), 
							msg, application, objectIdentifierMaster);
				} else {
					ris = sendFile(mdNodi,files[x],premisElab, application, objectIdentifierMaster, ris);
				}
			}
		}
		return ris;
	}
	
	private Vector<String> sendFile(MDNodi mdNodi, File file, PremisXsd premisElab, 
			String application, String objectIdentifierMaster, Vector<String> ris) throws ConfigurationException{
		GregorianCalendar dStart = null;
		GregorianCalendar dEnd = null;
		try {
			dStart = new GregorianCalendar();
			rsyncFile(mdNodi, file);
			dEnd = new GregorianCalendar();
			addGeoReplica(premisElab, dStart, dEnd, mdNodi, file.getAbsolutePath(), 
					null, application, objectIdentifierMaster);
		} catch (ClientMDException e) {
			if (ris ==null){
				ris = new Vector<String>();
			}
			ris.add(e.getMessage());
			dEnd = new GregorianCalendar();
			addGeoReplica(premisElab, dStart, dEnd, mdNodi, file.getAbsolutePath(), 
					e.getMessage(), application, objectIdentifierMaster);
		}
		return ris;
	}

	private void addGeoReplica(PremisXsd premisElab, Timestamp dataStart, Timestamp dataEnd,
			MDNodi mdNodi, String file, String msgError, String application, String objectIdentifierMaster) throws ConfigurationException{
		GregorianCalendar dStart;
		GregorianCalendar dEnd;

		dStart = new GregorianCalendar();
		dStart.setTimeInMillis(dataStart.getTime());

		dEnd = new GregorianCalendar();
		dEnd.setTimeInMillis(dataEnd.getTime());

		addGeoReplica(premisElab, dStart, dEnd, mdNodi, file, msgError, application, objectIdentifierMaster);
	}

	private void addGeoReplica(PremisXsd premisElab, GregorianCalendar dataStart, GregorianCalendar dataEnd,
			MDNodi mdNodi, String file, String msgError, String application, String objectIdentifierMaster) throws ConfigurationException{
		premisElab.addEvent("geoReplica", dataStart, dataEnd, 
				"Nodo ID: "+mdNodi.getNome()+" File: "+file, 
				(msgError==null?"OK":"KO"), 
				(msgError==null?null:new String[]{msgError}), 
				null, Configuration.getValue("demoni."
						+ application + ".UUID"),
				objectIdentifierMaster);
	}

	private void rsyncFile(MDNodi mdNodi, File fSend) throws ClientMDException{
		Runtime rt = null;
		String fileInput = null;
		String[] cmd = null;
		Process proc = null;
		InputStream stderr = null;
		InputStreamReader isrErr = null;
		BufferedReader brErr = null;
		InputStream stdout = null;
		InputStreamReader isrStd = null;
		BufferedReader brStd = null;
		String val = null;
		int exitVal = -1;
		
		try {
			rt = Runtime.getRuntime();
			if (File.separator.equals("\\")){
				fileInput = "/cygdrive/"+fSend.getAbsolutePath().replace(":", "").replace("\\", "/");
			} else {
				fileInput = fSend.getAbsolutePath();
			}
			cmd = new String[] { Configuration.getValue("md.sendRsync.path"), 
					"-av", 
					"--progress",
					fileInput,
					mdNodi.getRsync()+
					fSend.getAbsolutePath().replace(Configuration.getValue("demoni.Publish.pathStorage"), "")};

			proc = rt.exec(cmd, new String[]{"RSYNC_PASSWORD="+mdNodi.getRsyncPassword()});

			stderr = proc.getErrorStream();
			isrErr = new InputStreamReader(stderr);
			brErr = new BufferedReader(isrErr);

			stdout = proc.getInputStream();
			isrStd = new InputStreamReader(stdout);
			brStd = new BufferedReader(isrStd);

			while ((val = brStd.readLine()) != null) {
				log.debug(val);
			}

			while ((val = brErr.readLine()) != null) {
				log.error(val);
			}

			exitVal = proc.waitFor();

			switch (exitVal) {
			case 0:
				break;
			case 1:
				throw new ClientMDException("Errore di sintassi o l'utilizzo");
			case 2:
				throw new ClientMDException("Protocollo di incompatibilità");
			case 3:
				throw new ClientMDException(
						"Errori di selezione dei file di input / output, dirs");
			case 4:
				throw new ClientMDException(
						"L'azione richiesta non è supportata: un tentativo è stato fatto per manipolare file a 64 bit su una piattaforma che non li può sostenere, o un'opzione stato precisato che è supportato dal client e non dal server.");
			case 5:
				throw new ClientMDException(
						"Errore durante l'avvio del protocollo client-server");
			case 6:
				throw new ClientMDException(
						"Daemon in grado di aggiungere al log-file");
			case 10:
				throw new ClientMDException("Errore in socket I/O");
			case 11:
				throw new ClientMDException("Errore in file I/O");
			case 12:
				throw new ClientMDException(
						"Errore nei rsync flusso di dati del protocollo");
			case 13:
				throw new ClientMDException(
						"Errori con diagnostica del programma");
			case 14:
				throw new ClientMDException("Errore nel codice IPC");
			case 20:
				throw new ClientMDException("Ricevuto SIGUSR1 o SIGINT");
			case 21:
				throw new ClientMDException(
						"Qualche errore restituito da waitpid()");
			case 22:
				throw new ClientMDException(
						"Buffer di memoria centrale che ripartisce errore");
			case 23:
				throw new ClientMDException(
						"Trasferimento parziale a causa di un errore");
			case 24:
				throw new ClientMDException(
						"Trasferimento parziale a causa di file di origine scomparsi");
			case 25:
				throw new ClientMDException(
						"Il limite --max-delete a fermato eliminazioni");
			case 30:
				throw new ClientMDException(
						"Timeout nei dati di invio / ricezione");
			case 255:
				throw new ClientMDException(
						"Autorizzazione negata, riprova.");
			default:
				throw new ClientMDException(
						"Errore generico ["+exitVal+"]");
			}
		} catch (ConfigurationException e) {
			throw new ClientMDException(e.getMessage(), e);
		} catch (IOException e) {
			throw new ClientMDException(e.getMessage(), e);
		} catch (InterruptedException e) {
			throw new ClientMDException(e.getMessage(), e);
		} catch (ClientMDException e) {
			throw e;
		} finally {
			try {
				if (brStd!= null){
					brStd.close();
				}
				if (isrStd != null){
					isrStd.close();
				}
				if (stdout != null){
					stdout.close();
				}
				if (brErr != null){
					brErr.close();
				}
				if (isrErr != null){
					isrErr.close();
				}
				if (stderr != null){
					stderr.close();
				}
				log.info("File: "+fSend.getAbsolutePath()+" inviato");
			} catch (IOException e) {
				throw new ClientMDException(e.getMessage(), e);
			}
		}
	}
	
	private Documenti genDocumenti(File[] files) throws ConfigurationException, NoSuchAlgorithmException, FileNotFoundException, IOException{
		Documenti documenti = null;
		DocumentiDocumento[] documento =  null;
		SHA1 sha1 = null;
		Calendar calendar = null;
		
		documenti = new Documenti();
		documenti.setNumDoc(new BigInteger(String.valueOf(files.length)));

		documento = new DocumentiDocumento[files.length];
		sha1 = new SHA1();
		for (int x=0; x<files.length; x++){
			documento[x] = new DocumentiDocumento();
			documento[x].setNomeFile(files[x].getAbsolutePath().replace(Configuration.getValue("demoni.Publish.pathStorage"), 
					""));
			documento[x].setDigest(sha1.getDigest(files[x]));
			calendar = new GregorianCalendar();
			calendar.setTimeInMillis(files[x].lastModified());
			documento[x].setDataMod(calendar);
			
			documento[x].setEsito("CHECK");
		}
		documenti.setDocumento(documento);

		return documenti;
	}
	
	private Storage checkStorage(MDNodi mdNodi, Documenti documenti){
		CheckStorageMDPortTypeProxy proxy = null;
		Storage storage = null;
		
		try {

			proxy = new CheckStorageMDPortTypeProxy(mdNodi.getUrlCheckStorage());
			storage = proxy.checkStorageMDOperation(documenti);
		} catch (RemoteException e) {
			log.error(e.getMessage(),e);
		}
		
		return storage;
	}
	/*
	private void test(){
		Boolean ris = false;
		File filePremisMaster = null;
		GregorianCalendar start = null;
		String fileObj = null;
		File fObj = null;
		PremisXsd premisInput = null;
		String objectIdentifierContainer = null;
		int pos = 0;
		String ext = null;
		File fObjNew = null;
		ValidateFile validate = null;
		ArchiveMD archive = null;
		String objectIdentifierPremis = null;
		File premisDest = null;
		GregorianCalendar stop = null;

		try {

			validate = new ValidateFile();
			filePremisMaster = new File(
					Configuration.getValue("path.premis")
							+ File.separator
							+ record.getPremisFile());
			if (record.getStato()
					.equals(MDFilesTmpDAO.FINEVALID)) {
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
				fObjNew = GenFileDest.genFileDest(Configuration.getValue("demoni.Publish.pathStorage")
						,objectIdentifierContainer
						+ ext);
				if (isFileExist(fObj, record, fObjNew)) {


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
//							logPublish.info(name+" Pubblico il materiale in Solr");
//							if (publishSolr(premisInput, fObjNew, logPublish)) {
//								stop = mdFileTmp.updateStopPublish(
//										record.getId(), true, null);
//								premisElab
//										.addEvent(
//												"publish",
//												start,
//												stop,
//												null,
//												"OK",
//												null,
//												null,
//												Configuration
//														.getValue("demoni."
//																+ application
//																+ ".UUID"),
//												objectIdentifierContainer);
//								logPublish.info(name+" Materiale pubblicato");
//							} else {
//								logPublish.error(name+" Riscontrato un problema nella pubblicazione");
//								mdFileTmp
//								.updateStopPublish(
//										record.getId(),
//										false,
//										new String[] { "Riscontrato un problema nella pubblicazione" });
//							}
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
//		} catch (SolrException e) {
//			if (premisElab != null) {
//				premisElab.addEvent(
//						"Error",
//						null,
//						null,
//						null,
//						"KO",
//						new String[] { e.getMessage() },
//						null,
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), null);
//			}
//			mdFileTmp.updateStopPublish(record.getId(), false,
//					new String[] { e.getMessage() });
//			log.error(e.getMessage(), e);
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
*/
	/**
	 * Metodo utilizzato per ricamare l'identificativo dell'Oggetto dal tracciato Premis
	 * 
	 * @param premis Tracciato Premis da analizzare
	 * @return Valore individuato
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
	 */

	/**
	 * @return the trasterito
	 */
	public boolean isTrasterito() {
		return trasterito;
	}

	/**
	 * Metodo utilizzato per verificare la presenza del file di destinazione nelle diverse condizioni di elaborazione
	 * 
	 * @param fObj
	 * @param record
	 * @param fObjNew
	 * @return
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
	 */

//	private boolean publishSolr(PremisXsd premis, File fObj, Logger logPublish)
//			throws SolrException {
//		boolean ris = false;
//		List<ObjectComplexType> objects = null;
//		List<EventComplexType> events = null;
//		ObjectComplexType object = null;
//		EventComplexType event = null;
//		AddDocumentMD admd = null;
//		SolrEvent se = null;
//		SolrObjectFile sof = null;
//		File pathTar = null;
//
//		try {
//			pathTar = new File(Configuration.getValue("demoni.Publish.tmpPath")+
//					File.separator+fObj.getName());
//			if (!pathTar.exists()){
//				if (pathTar.mkdirs()){
//					
//				}
//			}
//			Tar.decompress(fObj, pathTar);
//			admd = new AddDocumentMD(
//					Configuration.getValue("demoni.Publish.solr.URL"),
//					Boolean.parseBoolean(Configuration
//							.getValue("demoni.Publish.solr.Cloud")),
//					Configuration.getValue("demoni.Publish.solr.collection"),
//					Integer.parseInt(Configuration
//							.getValue("demoni.Publish.solr.connectionTimeOut")),
//					Integer.parseInt(Configuration
//							.getValue("demoni.Publish.solr.clientTimeOut")));
//			objects = premis.getObject();
//			if (objects != null && objects.size() > 0) {
//				logPublish.info(name+" Oggetto da pubblicare "+objects.size());
//				sof = new SolrObjectFile();
//				for (int x = 0; x < objects.size(); x++) {
//					if ((x%100)==0){
//						logPublish.info(name+" Oggetto "+x+"/"+objects.size());
//						System.gc();
//					}
//					object = objects.get(x);
//					if (object instanceof info.lc.xmlns.premis_v2.File) {
//						sof.publishSolr((info.lc.xmlns.premis_v2.File) object,
//								admd, pathTar);
//					}
//				}
//				logPublish.info(name+" Fine pubblicazione oggetti");
//			}
//			System.gc();
//			events = premis.getEvent();
//			if (events != null && events.size() > 0) {
//				logPublish.info(name+" Eventi da pubblicare "+events.size());
//				se = new SolrEvent();
//				for (int x = 0; x < events.size(); x++) {
//					if ((x%100)==0){
//						logPublish.info(name+" Eventi "+x+"/"+events.size());
//						System.gc();
//					}
//					event = events.get(x);
//					se.publishSolr(event, admd);
//				}
//				logPublish.info(name+" Fine pubblicazione eventi");
//			}
//			logPublish.info(name+" Inizio pubblicazione in Solr GC");
//			System.gc();
//			logPublish.info(name+" Inizio pubblicazione in Solr");
//			admd.commit();
//			logPublish.info(name+" Fine pubblicazione in Solr");
//			Thread.sleep(5000);
//			ris = true;
//		} catch (NumberFormatException e) {
//			log.error(e.getMessage(), e);
//			throw new SolrException(e.getMessage(), e);
//		} catch (SolrException e) {
//			log.error(e.getMessage(), e);
//			throw e;
//		} catch (ConfigurationException e) {
//			log.error(e.getMessage(), e);
//			throw new SolrException(e.getMessage(), e);
//		} catch (InterruptedException e) {
//			log.error(e.getMessage(), e);
//			throw new SolrException(e.getMessage(), e);
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new SolrException(e.getMessage(), e);
//		} finally {
//			try {
//				if (admd != null){
//					logPublish.info(name+" Inizio ottimizzazione in Solr");
//					admd.optimize();
//					logPublish.info(name+" Fine ottimizzazione in Solr");
//				} else {
//					throw new SolrException("Riscontrato un problema nella connessione al Database");
//				}
//				if (pathTar.exists()){
//					
//					if (!deleteFolder(pathTar)){
//						throw new SolrException("Problemi nella cancellazione della cartella ["+pathTar.getAbsolutePath()+"]");
//					}
//				}
//			} catch (SolrServerException e) {
//				log.error(e.getMessage(), e);
//				throw new SolrException(e.getMessage(), e);
//			} catch (IOException e) {
//				log.error(e.getMessage(), e);
//				throw new SolrException(e.getMessage(), e);
//			} catch (Exception e) {
//				log.error(e.getMessage(), e);
//				throw new SolrException(e.getMessage(), e);
//			} finally{
//				if (admd != null){
//					admd.close();
//				}
//			}
//		}
//
//		return ris;
//	}

//	private boolean deleteFolder(File path){
//		boolean ris = true;
//		File[] fl = null;
//		fl = path.listFiles(new FileFilter() {
//			
//			@Override
//			public boolean accept(File pathname) {
//				if (pathname.getName().equals(".") ||
//						pathname.getName().equals("..")){
//					return false;
//				} else {
//					return true;
//				}
//			}
//		});
//		for (int x=0; x<fl.length; x++){
//			if (fl[x].isDirectory()){
//				if (!deleteFolder(fl[x])){
//					ris = false;
//				}
//			} else {
//				if (!fl[x].delete()){
//					ris=false;
//				}
//			}
//		}
//		if (ris){
//			if (!path.delete()){
//				ris = false;
//			}
//		}
//		return ris;
//	}
}
