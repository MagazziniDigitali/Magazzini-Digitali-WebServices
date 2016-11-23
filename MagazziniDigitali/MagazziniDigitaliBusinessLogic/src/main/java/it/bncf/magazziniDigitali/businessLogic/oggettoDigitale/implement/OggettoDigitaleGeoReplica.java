/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.archive.MDArchiveBusiness;
import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.exception.ClientMDException;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
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
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.digest.SHA1;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.rsync.RSync;
import mx.randalf.tools.rsync.exception.RSyncException;
import mx.randalf.xsd.exception.XsdException;


/**
 * @author massi
 *
 */
public class OggettoDigitaleGeoReplica extends OggettoDigitale {

	private Logger log = Logger.getLogger(OggettoDigitaleGeoReplica.class);

	private Logger logPublish = null;
	
	private String name = null;

	private boolean trasferito = false;

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica( 
			Logger logPublish, String name) {
		this.logPublish = logPublish;
		this.name = name;
	}

	public boolean esegui(String objectIdentifierPremis, 
			String application, 
			IMDConfiguration<?> configuration) throws HibernateUtilException {
		MDFilesTmp mdFilesTmp = null;
		boolean esito = true;
		MDFilesTmpBusiness mdFileTmpBusiness = null;

		logPublish.info(name+" ["+objectIdentifierPremis+"] Inizio la geo replica");
		trasferito = false;
		try {
			mdFileTmpBusiness = new MDFilesTmpBusiness();
			mdFilesTmp = mdFileTmpBusiness.findPremis(genPathPremis(objectIdentifierPremis));
		} catch (HibernateException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
			throw e;
		}

		try {
			if (mdFilesTmp != null){
				esito = checkStato(mdFilesTmp, objectIdentifierPremis, configuration, mdFileTmpBusiness, application);
			} else {
				logPublish.error(name+" ["+objectIdentifierPremis+"]"+" Il file premis ["+objectIdentifierPremis+"] non è presente in archivio");
				esito = false;
			}
		} catch (HibernateUtilException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
			esito = false;
			throw e;
		} finally{
			logPublish.info(name+" ["+objectIdentifierPremis+"] Fine della geo replica");
		}
		return esito;
	}

	private boolean checkStato(MDFilesTmp mdFilesTmp, String objectIdentifierPremis,IMDConfiguration<?> configuration, 
			MDFilesTmpBusiness mdFileTmpBusiness, String application) 
			throws HibernateException, HibernateUtilException {
		boolean esito = true;
		try {
			FactoryDAO.initialize(mdFilesTmp.getStato());
			if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEPUBLISH) ||
					mdFilesTmp.getStato().getId().equals(MDStatoDAO.INITARCHIVE)){
				
				esito = initGeoReplica(objectIdentifierPremis, configuration, mdFilesTmp, mdFileTmpBusiness, application);
			} else if (!mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEARCHIVE)){
				esito = false;
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}
		return esito;
	}

	private boolean initGeoReplica(String objectIdentifierPremis,IMDConfiguration<?> configuration, MDFilesTmp mdFilesTmp, 
			MDFilesTmpBusiness mdFileTmpBusiness, String application) {
		File filePremis = null;
		PremisXsd premisElab = null;
		boolean esito = true;

		trasferito = true;
		try {
			filePremis = new File(
					genFilePremis(configuration.getSoftwareConfigString("path.premis"), 
								"GeoReplica",UUID.randomUUID().toString(),".geoReplica.premis"));

			premisElab = new PremisXsd();
			esito = elabGeoReplica(configuration, mdFilesTmp, objectIdentifierPremis, premisElab, mdFileTmpBusiness, filePremis, 
					application);
		} catch (MDConfigurationException e) {
			esito = false;
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (SQLException e) {
			esito = false;
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}
		return esito;
	}

	private boolean elabGeoReplica(IMDConfiguration<?> configuration, MDFilesTmp mdFilesTmp, String objectIdentifierPremis, 
			PremisXsd premisElab, MDFilesTmpBusiness mdFileTmpBusiness, File filePremis, String application) throws SQLException, MDConfigurationException {
		File fileElabPremis = null;
		File fileElab = null;
		GregorianCalendar gcStart = null;
		boolean esito = true;
		String pFile = "";
		int pos = 0;

		try {
			pFile = mdFilesTmp.getPremisFile();
			pos = pFile.lastIndexOf("/");
			pFile = pFile.substring(pos+1);

			fileElabPremis = GenFileDest.genFileDest(
					configuration.getSoftwareConfigMDNodi("nodo")
//					Configuration.getValue("demoni.Publish.pathStorage")
					,pFile);
			fileElab = new File(fileElabPremis.getAbsolutePath().replace(".premis", ""));
			log.info(name+" ["+objectIdentifierPremis+"]"+" Inizio l'elaborazione del file ["+fileElab.getAbsolutePath()+"]");
			if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEPUBLISH)){
				logPublish.info(name+" ["+objectIdentifierPremis+"]"+" Inizio l'archiviazione del file ["
						+ fileElab.getAbsolutePath() + "]");
				gcStart = mdFileTmpBusiness
						.updateStartArchive(mdFilesTmp.getId());
			} else {
				logPublish.info(name+" ["+objectIdentifierPremis+"]"+" Continuo l'archivizione del file ["
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
				esito = checkFileElabPremis(fileElabPremis, objectIdentifierPremis, premisElab, configuration, mdFileTmpBusiness, 
						mdFilesTmp, fileElab, application, filePremis);
			} else {
				esito = false;
				logPublish.error(name+" ["+objectIdentifierPremis+"]"+" Il file ["+fileElab.getAbsolutePath()+"] non è presente");
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { "Il file ["+fileElab.getAbsolutePath()+"] non è presente" },
						null,
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
				mdFileTmpBusiness
				.updateStopArchive(
						mdFilesTmp.getId(),
						false, null, 
						new String[] { "Il file ["+fileElab.getAbsolutePath()+"] non è presente" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (MDConfigurationException e) {
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
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (HibernateUtilException e) {
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
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}finally{
			logPublish.info(name+" Fine l'elaborazione del file ["+objectIdentifierPremis+"]");
			try {
				if (premisElab != null){
					premisElab.write(filePremis, false);
				}
			} catch (PremisXsdException e) {
				esito = false;
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (XsdException e) {
				esito = false;
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (IOException e) {
				esito = false;
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (Exception e) {
				esito = false;
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		}
		return esito;
	}

	private boolean checkFileElabPremis(File fileElabPremis, String objectIdentifierPremis, PremisXsd premisElab, 
			IMDConfiguration<?> configuration, MDFilesTmpBusiness mdFileTmpBusiness, MDFilesTmp mdFilesTmp, File fileElab,
			String application, File filePremis) 
					throws MDConfigurationException, SQLException, NoSuchAlgorithmException, FileNotFoundException,
							HibernateException, IOException, HibernateUtilException	{
		boolean esito = true;

		try {
			if (fileElabPremis.exists()){
				esito = sendNodes(fileElab, fileElabPremis, configuration, mdFilesTmp, premisElab, application, objectIdentifierPremis, 
						mdFileTmpBusiness, filePremis);
			} else {
				esito = false;
				logPublish.error(name+" ["+objectIdentifierPremis+"]"+" Il file ["+fileElabPremis.getAbsolutePath()+"] non è presente");
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { "Il file ["+fileElabPremis.getAbsolutePath()+"] non è presente" },
						null,
						configuration.getMDSoftware()
//					Configuration.getValue("demoni."
//							+ application + ".UUID")
						, null);
				mdFileTmpBusiness
				.updateStopArchive(
						mdFilesTmp.getId(),
						false,
						null,
						new String[] { "Il file ["+fileElabPremis.getAbsolutePath()+"] non è presente" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (MDConfigurationException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (HibernateException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}
		return esito;
	}

	@SuppressWarnings("unused")
	private boolean sendNodes(File fileElab, File fileElabPremis, IMDConfiguration<?> configuration, MDFilesTmp mdFilesTmp, 
			PremisXsd premisElab, String application, String objectIdentifierPremis, MDFilesTmpBusiness mdFileTmpBusiness,
			File filePremis) 
					throws NoSuchAlgorithmException, FileNotFoundException, HibernateException, IOException,
						MDConfigurationException, HibernateUtilException, SQLException{
		MDNodiDAO mdNodiDAO = null;
		List<MDNodi> mdNodis = null;
		boolean elabNodi = true;
		File[] files = null;
		Documenti documenti = null;
		String[] msgs = null;
		Vector<String> msgErr = null;
		GregorianCalendar gcEnd = null;
		boolean esito = true;

		try {
			mdNodiDAO = new MDNodiDAO();
			mdNodis = mdNodiDAO.findActive();
			elabNodi = true;
			files = new File[2];
			files[0] = fileElab;
			files[1] = fileElabPremis;
			documenti = genDocumenti(files, configuration);
			for (MDNodi mdNodi : mdNodis){
				if (!mdNodi.getId().equals(
						configuration.getSoftwareConfigMDNodi("nodo").getId()
//					Configuration.getValue("nodo")
						)){
					msgs =geoReplica(mdNodi, files, mdFilesTmp, premisElab, application, 
							objectIdentifierPremis, documenti, objectIdentifierPremis,
							configuration);
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
				gcEnd = mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), true, null, null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} else {
				esito = false;
				gcEnd = mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false, null,
						msgErr.toArray(new String[msgErr.size()]),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (HibernateException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
		return esito;
	}

	private String[] geoReplica(MDNodi mdNodi, File[] files, MDFilesTmp mdFilesTmp, PremisXsd premisElab, 
			String application, String objectIdentifierMaster, Documenti documenti, String objectIdentifierPremis,
			IMDConfiguration<?> configuration) throws HibernateException, HibernateUtilException, SQLException,
			MDConfigurationException
	{
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
			mdArchiveBusiness = new MDArchiveBusiness();
			mdArchive = mdArchiveBusiness.find(mdFilesTmp, mdNodi);
			if (mdArchive == null || !mdArchive.getEsito().booleanValue()){
				storage = checkStorage(mdNodi, documenti, objectIdentifierPremis);
				if (storage !=null && (storage.getEsito().equals("OK")
						||storage.getEsito().equals("DOCNOTFOUND"))){
					for (int x=0; x<files.length; x++){
						ris = sendFile(mdNodi,files[x],premisElab, application, objectIdentifierMaster, ris, configuration);
					}
					if (ris==null){
						storage = checkStorage(mdNodi, documenti, objectIdentifierPremis);
						ris = analizeRisp(storage, mdNodi, premisElab, dStart, files, application, objectIdentifierMaster, ris, true, configuration);
					}
				} else {
					ris = analizeRisp(storage, mdNodi, premisElab, dStart, files, application, objectIdentifierMaster, ris, false, configuration);
				}
				dataEnd = new GregorianCalendar();
				mdArchiveBusiness.insert((mdArchive==null?null:mdArchive.getId()), 
						mdFilesTmp, mdNodi, dataStart, dataEnd, (ris==null));
			} else {
				for (int x=0; x<files.length; x++){
					addGeoReplica(premisElab, mdArchive.getDataStart(), mdArchive.getDataEnd(), 
							mdNodi, files[x].getAbsolutePath(), 
							null, application, objectIdentifierMaster, configuration);
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		}
		return (ris==null?null:ris.toArray(new String[ris.size()]));
	}

	private Vector<String> analizeRisp(Storage storage, MDNodi mdNodi, PremisXsd premisElab,
			GregorianCalendar dStart, File[] files, String application, String objectIdentifierMaster,
			Vector<String> ris, boolean testRes, IMDConfiguration<?> configuration) throws MDConfigurationException {
		String msg = null;
		GregorianCalendar dEnd = null;

		try {
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
							msg, application, objectIdentifierMaster, configuration);
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
							msg, application, objectIdentifierMaster, configuration);
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
							msg, application, objectIdentifierMaster, configuration);
				} else if (!testRes && 
						storage.getDocumenti().getDocumento()[x].getEsito().equals("FOUND")){
					addGeoReplica(premisElab, dStart, dEnd, mdNodi, files[0].getAbsolutePath(), 
							null, application, objectIdentifierMaster, configuration);
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
								msg, application, objectIdentifierMaster, configuration);
					} else {
						ris = sendFile(mdNodi,files[x],premisElab, application, objectIdentifierMaster, ris, configuration);
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
		} catch (MDConfigurationException e) {
			throw e;
		}
		return ris;
	}
	
	private Vector<String> sendFile(MDNodi mdNodi, File file, PremisXsd premisElab, 
			String application, String objectIdentifierMaster, Vector<String> ris, 
			IMDConfiguration<?> configuration) throws MDConfigurationException {
		GregorianCalendar dStart = null;
		GregorianCalendar dEnd = null;

		try {
			try {
				dStart = new GregorianCalendar();
				rsyncFile(mdNodi, file, configuration);
				dEnd = new GregorianCalendar();
				addGeoReplica(premisElab, dStart, dEnd, mdNodi, file.getAbsolutePath(), 
						null, application, objectIdentifierMaster, configuration);
			} catch (ClientMDException e) {
				if (ris ==null){
					ris = new Vector<String>();
				}
				ris.add(e.getMessage());
				dEnd = new GregorianCalendar();
				addGeoReplica(premisElab, dStart, dEnd, mdNodi, file.getAbsolutePath(), 
						e.getMessage(), application, objectIdentifierMaster, configuration);
			} catch (MDConfigurationException e) {
				if (ris ==null){
					ris = new Vector<String>();
				}
				ris.add(e.getMessage());
				dEnd = new GregorianCalendar();
				addGeoReplica(premisElab, dStart, dEnd, mdNodi, file.getAbsolutePath(), 
						e.getMessage(), application, objectIdentifierMaster, configuration);
			}
		} catch (MDConfigurationException e) {
			throw e;
		}
		return ris;
	}

	private void addGeoReplica(PremisXsd premisElab, Date dataStart, Date dataEnd,
			MDNodi mdNodi, String file, String msgError, String application, String objectIdentifierMaster, 
			IMDConfiguration<?> configuration) throws MDConfigurationException {
		GregorianCalendar dStart;
		GregorianCalendar dEnd;

		try {
			dStart = new GregorianCalendar();
			dStart.setTimeInMillis(dataStart.getTime());

			dEnd = new GregorianCalendar();
			dEnd.setTimeInMillis(dataEnd.getTime());

			addGeoReplica(premisElab, dStart, dEnd, mdNodi, file, msgError, application, objectIdentifierMaster, configuration);
		} catch (MDConfigurationException e) {
			throw e;
		}
	}

	private void addGeoReplica(PremisXsd premisElab, GregorianCalendar dataStart, GregorianCalendar dataEnd,
			MDNodi mdNodi, String file, String msgError, String application, String objectIdentifierMaster, 
			IMDConfiguration<?> configuration) throws MDConfigurationException
	{
		try {
			premisElab.addEvent("geoReplica", dataStart, dataEnd, 
					"Nodo ID: "+mdNodi.getNome()+" File: "+file, 
					(msgError==null?"OK":"KO"), 
					(msgError==null?null:new String[]{msgError}), 
					null, 
					configuration.getMDSoftware(),
//				Configuration.getValue("demoni."
//						+ application + ".UUID"),
					objectIdentifierMaster);
		} catch (MDConfigurationException e) {
			throw e;
		}
	}

	private void rsyncFile(MDNodi mdNodi, File fSend, IMDConfiguration<?> configuration) throws ClientMDException{
		
		try {
			RSync.send(//configuration.getSoftwareConfigString("rSync.path"),
					Configuration.getValue("md.sendRsync.path"), 
					mdNodi.getRsyncPassword(), 
					RSync.checkPath(fSend), 
					mdNodi.getRsync()+
					fSend.getAbsolutePath().replace(
							configuration.getSoftwareConfigMDNodi("nodo").getPathStorage()
//							Configuration.getValue("demoni.Publish.pathStorage")
							, ""));
		} catch (RSyncException e) {
			throw new ClientMDException(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new ClientMDException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new ClientMDException(e.getMessage(), e);
		}
	}
	
	private Documenti genDocumenti(File[] files, IMDConfiguration<?> configuration) 
			throws NoSuchAlgorithmException, FileNotFoundException, IOException, MDConfigurationException
			//throws ConfigurationException, NoSuchAlgorithmException, FileNotFoundException, IOException
	{
		Documenti documenti = null;
		DocumentiDocumento[] documento =  null;
		SHA1 sha1 = null;
		Calendar calendar = null;
		
		try {
			documenti = new Documenti();
			documenti.setNumDoc(new BigInteger(String.valueOf(files.length)));

			documento = new DocumentiDocumento[files.length];
			sha1 = new SHA1();
			for (int x=0; x<files.length; x++){
				documento[x] = new DocumentiDocumento();
				documento[x].setNomeFile(files[x].getAbsolutePath().replace(
						configuration.getSoftwareConfigMDNodi("nodo").getPathStorage(),
//					Configuration.getValue("demoni.Publish.pathStorage"), 
						""));
				documento[x].setDigest(sha1.getDigest(files[x]));
				calendar = new GregorianCalendar();
				calendar.setTimeInMillis(files[x].lastModified());
				documento[x].setDataMod(calendar);
				
				documento[x].setEsito("CHECK");
			}
			documenti.setDocumento(documento);
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		}

		return documenti;
	}
	
	private Storage checkStorage(MDNodi mdNodi, Documenti documenti, String objectIdentifierPremis){
		CheckStorageMDPortTypeProxy proxy = null;
		Storage storage = null;
		
		try {

			proxy = new CheckStorageMDPortTypeProxy(mdNodi.getUrlCheckStorage());
			storage = proxy.checkStorageMDOperation(documenti);
		} catch (RemoteException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(),e);
		}
		
		return storage;
	}

	/**
	 * @return the trasterito
	 */
	public boolean isTrasferito() {
		return trasferito;
	}

}
