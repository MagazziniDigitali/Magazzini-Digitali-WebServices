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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import mx.randalf.tools.rsync.RSync;
import mx.randalf.tools.rsync.exception.RSyncException;
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
		Documenti documenti = null;

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
						filePremis = new File(Configuration.getValue("path.premisGeoReplica")
								+ File.separator + UUID.randomUUID().toString()
								+ ".geoReplica.premis");

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
									documenti = genDocumenti(files);
									for (MDNodi mdNodi : mdNodis){
										if (!mdNodi.getId().equals(Configuration.getValue("nodo"))){
											msgs =geoReplica(mdNodi, files, mdFilesTmp, premisElab, application, 
													objectIdentifierPremis, documenti);
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
			String application, String objectIdentifierMaster, Documenti documenti) throws HibernateException, NamingException, 
			ConfigurationException, SQLException, NoSuchAlgorithmException, IOException{
		Vector<String> ris = null;
		Storage storage = null;
		MDArchive mdArchive = null;
		MDArchiveBusiness mdArchiveBusiness = null;
		GregorianCalendar dataStart = null;
		GregorianCalendar dataEnd = null;
		GregorianCalendar dStart = null;
		
		try {
			dataStart = new GregorianCalendar();
			dStart = new GregorianCalendar();
			mdArchiveBusiness = new MDArchiveBusiness(hibernateTemplate);
			mdArchive = mdArchiveBusiness.find(mdFilesTmp, mdNodi);
			if (mdArchive == null || !mdArchive.getEsito().booleanValue()){
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
		}
		return (ris==null?null:ris.toArray(new String[ris.size()]));
	}

	private Vector<String> analizeRisp(Storage storage, MDNodi mdNodi, PremisXsd premisElab,
			GregorianCalendar dStart, File[] files, String application, String objectIdentifierMaster,
			Vector<String> ris, boolean testRes) throws ConfigurationException{
		String msg = null;
		GregorianCalendar dEnd = null;

		if (storage!=null &&
				storage.getDocumenti() != null &&
				storage.getDocumenti().getDocumento() != null){
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
		} else {
			if (ris ==null){
				ris = new Vector<String>();
			}
			msg = "Nel modo: ["+
					mdNodi.getNome()+
					" - "+
					mdNodi.getDescrizione()+
					"] riscontrato un errore nella risposta del Servizio ckeckGeoReplica";
			ris.add(msg);
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
		
		try {
			RSync.send(Configuration.getValue("md.sendRsync.path"), 
					mdNodi.getRsyncPassword(), 
					RSync.checkPath(fSend), 
					mdNodi.getRsync()+
					fSend.getAbsolutePath().replace(Configuration.getValue("demoni.Publish.pathStorage"), ""));
		} catch (RSyncException e) {
			throw new ClientMDException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new ClientMDException(e.getMessage(), e);
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

	/**
	 * @return the trasterito
	 */
	public boolean isTrasterito() {
		return trasterito;
	}

}
