/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import info.lc.xmlns.premis_v2.CHECKSUMTYPEDefinition;
import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.ValidateFile;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.implement.ArchiveMD;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.nodi.Nodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.magazziniDigitali.xsd.premis.PremisDigest;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.archive.info.DigestType;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class OggettoDigitalePublish extends OggettoDigitalePublishPremis{

	private Logger log = Logger.getLogger(getClass());

	private PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab = null;
	
	/**
	 * 
	 */
	public OggettoDigitalePublish(
			Logger logPublish, String name) {
		super(logPublish, name);
	}

	public boolean esegui(String objectIdentifierPremis
			, String application
			, IMDConfiguration<?> configuration
			) 
			throws MDConfigurationException, SQLException 
	{
		Boolean ris = false;
		File filePremis = null;
//		String objectIdentifierPremis = null;
//		GregorianCalendar stop = null;
		MDFilesTmpBusiness mdFileTmpBusiness = null;
		MDFilesTmpDAO mdFileTmpDao= null;
		MDFilesTmp mdFilesTmp = null;

		logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Inizio la pubblicazione");
		try {
			mdFileTmpBusiness = new MDFilesTmpBusiness();
			mdFileTmpDao = new MDFilesTmpDAO();
			mdFilesTmp = mdFileTmpDao.findById(objectIdentifierPremis);
			
			if (mdFilesTmp.getPublishPremis() == null || 
					mdFilesTmp.getPublishPremis().trim().equals("")) {
				filePremis = new File(
						genFilePremis(
								configuration.getSoftwareConfigString("path.premis"), 
								"Publish",
								UUID.randomUUID().toString()));
			} else {
				filePremis = new File(
						genFilePremis(configuration.getSoftwareConfigString("path.premis"),
								mdFilesTmp.getPublishPremis()));
			}
		} catch (HibernateException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}

		try {

			if (mdFilesTmp != null){
				
				validate(configuration, mdFileTmpBusiness, mdFilesTmp, 
						objectIdentifierPremis
						//, application
						, filePremis);

			} else {
				logPublish.error("\n"+name+" ["+objectIdentifierPremis+"] Il file premis ["+objectIdentifierPremis+"] non è presente in archivio");
				ris = false;
			}
		} catch (MDConfigurationException e) {
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware(),
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), 
						null);
			}
			mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware(),
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), 
						null);
			}
			mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (PremisXsdException e) {
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware(),
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), 
						null);
			}
			mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
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
						configuration.getMDSoftware(), 
//						Configuration.getValue("demoni."
//								+ application + ".UUID"), 
						null);
			}
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
			mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
		} finally {
			try {
				if (premisElab != null){
					premisElab.write(filePremis, false);
				}
			} catch (PremisXsdException e) {
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (XsdException e) {
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (IOException e) {
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (Exception e) {
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopPublish(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} finally {
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Fine della pubblicazione");
			}
		}
		
		return ris;
	}

	private void validate(IMDConfiguration<?> configuration
			, MDFilesTmpBusiness mdFileTmpBusiness
			, MDFilesTmp mdFilesTmp
			, String objectIdentifierPremis
//			, String application
			, File filePremis
			) 
					throws HibernateException, MDConfigurationException, HibernateUtilException,
						SQLException, PremisXsdException{
		File filePremisMaster = null;
		GregorianCalendar start = null;
//		MDNodi mdNodi = null;
		String fileObj = null;
		File fObj = null;
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisInput = null;
		String objectIdentifierContainer = null;
		int pos = 0;
		String ext = null;
//		File fObjNew = null;
//		File fNodo = null;
		Nodi nodi = null;

		try {
			filePremisMaster = new File(genFilePremis(
							configuration.getSoftwareConfigString("path.premis"),
							mdFilesTmp.getPremisFile()));
			
			FactoryDAO.initialize(mdFilesTmp.getStato());
			if (mdFilesTmp.getStato().getId()
					.equals(MDStatoDAO.FINEVALID)) {
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Inizio la pubblicazione del file ["
						+ filePremisMaster.getAbsolutePath() + "]");
				start = mdFileTmpBusiness
						.updateStartPublish(mdFilesTmp.getId());
			} else {
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Continuo la pubblicazione del file ["
						+ filePremisMaster.getAbsolutePath() + "]");
				start = new GregorianCalendar();
				start.setTimeInMillis(mdFilesTmp.getPublishDataStart().getTime());
			}

			if (filePremisMaster.exists()) {
				// calcolo il file da validare
				FactoryDAO.initialize(mdFilesTmp.getIdIstituto());
				if (mdFilesTmp.getTarTmpFile() ==null){
					// TODO: da AggiornaremdFilesTmp.getIdSoftware().getPathTar();
					fileObj = configuration.getSoftwareConfigString("path.tar");
					fileObj += File.separator;
					fileObj += mdFilesTmp.getIdIstituto().getpIva();
					fileObj += File.separator;
					fileObj += mdFilesTmp.getNomeFile();
				} else {
					fileObj = mdFilesTmp.getTarTmpFile();
				}
				fObj = new File(fileObj);
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] fileObj: " + fObj.getAbsolutePath());
				if (!fObj.exists()) {
					fObj = new File(fObj.getParentFile()
							.getAbsolutePath()
							+ File.separator
							+ fObj.getName()
									.replace(".tar.gz", ".tar")
									.replace(".tgz", ".tar")
									.replace(".warc.gz", ".warc"));
				}

				premisInput = PremisXsd.open(filePremisMaster);

				objectIdentifierContainer = findObjectIdentifierContainer(premisInput);
				
				pos = fObj.getName().lastIndexOf(".");
				ext = fObj.getName().substring(pos);

				nodi = new Nodi(configuration.getSoftwareConfigMDNodi("nodo"),
						objectIdentifierContainer + ext,
						filePremisMaster.getName());
				
				
				if (nodi.isStorageActive()){
					verifyExistsFile(fObj
							, mdFileTmpBusiness
							, mdFilesTmp
							, filePremisMaster
							, configuration
							, objectIdentifierPremis
							, objectIdentifierContainer
							, filePremisMaster
							, nodi);
//					verifyExistsFile(fObj, mdFileTmpBusiness, mdFilesTmp, 
//							filePremisMaster, 
//							configuration, 
//							objectIdentifierPremis, application, 
//							objectIdentifierContainer, filePremis, nodi);
				} else {
					logPublish.error("\n"+name+" ["+objectIdentifierPremis+"] Non risulta presente l'archivio di Storage ["
							+ nodi.pathStorageActive() + "]");
					mdFileTmpBusiness.updateStopPublish(
							mdFilesTmp.getId(),
							false,
							null, 
							new String[] { "Non risulta presente l'archivio di Storage ["
									+ nodi.pathStorageActive() +"]" },
							writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
				}
			} else {
				logPublish.error("\n"+name+" ["+objectIdentifierPremis+"] Il file ["
								+ filePremisMaster
										.getAbsolutePath()
								+ "] non è presente sul Server");
				mdFileTmpBusiness.updateStopPublish(
						mdFilesTmp.getId(),
						false,
						null, 
						new String[] { "Il file ["
								+ filePremisMaster
										.getAbsolutePath()
								+ "] non è presente sul Server" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (HibernateException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (PremisXsdException e) {
			throw e;
		} catch (NodiException e) {
			throw new MDConfigurationException(e.getMessage(), e);
		}
	}

	private void verifyExistsFile(File fObj
			, MDFilesTmpBusiness mdFileTmpBusiness
			, MDFilesTmp mdFilesTmp
//			, File fObjNew
			, File filePremisMaster
			,  IMDConfiguration<?> configuration
			, String objectIdentifierPremis
//			, String application
			, String objectIdentifierContainer
			, File filePremis
			, Nodi nodi
			) 
					throws MDConfigurationException, SQLException{
		ValidateFile validate = null;
		ArchiveMD archive = null;
//		File premisDest = null;
//		File fNodo = null;
		List<PremisDigest> digests = null;
		String digest = null;

		try {
			validate = new ValidateFile();
			if (isFileExist(fObj, mdFilesTmp, nodi)) {

				premisElab = PremisXsd.initialize();

				validate.check(filePremisMaster, null, configuration, null, false);

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

				premisElab
						.addObjectFileContainer(
								objectIdentifierPremis,
								(archive.getXmltype() == null ? null
										: archive.getXmltype()
												.value()),
								archive.getType().getExt(),
								new BigInteger("0"),
								digests,
								archive.getType().getSize(),
								archive.getMimetype(), archive
										.getNome(), null,
								archive.getType().getFormat()
										.getVersion(), archive
										.getType().getPUID());
//				premisDest= GenFileDest.genFileDest(configuration.getSoftwareConfigMDNodi("nodo")
//						//Configuration.getValue("demoni.Publish.pathStorage")
//						,filePremisMaster.getName());
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Copio il file "+
							filePremisMaster.getAbsolutePath()+
							" in "+
							nodi.genFilePremisDest());
//							premisDest.getAbsolutePath());
				executeCopy(filePremisMaster
//						, premisDest
						, mdFileTmpBusiness
						, mdFilesTmp
//						, application
						, objectIdentifierPremis
						, configuration
						, fObj
//						, fObjNew
						, objectIdentifierContainer
						, filePremis
						, nodi);
			} else {
				logPublish.error("\n"+name+" ["+objectIdentifierPremis+"] Il file ["
										+ fObj.getAbsolutePath()
										+ "] non è presente sul Server");
				mdFileTmpBusiness
						.updateStopPublish(
								mdFilesTmp.getId(),
								false,
								null, 
								new String[] { "Il file ["
										+ fObj.getAbsolutePath()
										+ "] non è presente sul Server" },
								writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (MDConfigurationException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (NodiException e) {
			throw new MDConfigurationException(e.getMessage(), e);
		}
	}

	private void executeCopy(
			File filePremisMaster
//			, File premisDest
			, MDFilesTmpBusiness mdFileTmpBusiness
			, MDFilesTmp mdFilesTmp
//			, String application
			, String objectIdentifierPremis
			, IMDConfiguration<?> configuration
			, File fObj
//			, File fObjNew
			, String objectIdentifierContainer
			, File filePremis
			, Nodi nodi
			) 
					throws SQLException, MDConfigurationException{
		try {
			// Copio il file premis nella sua posizione
			// definitiva
			if (sendFilePremis(filePremisMaster
					, mdFilesTmp
					, mdFileTmpBusiness
					, premisElab
					, objectIdentifierPremis
					, objectIdentifierPremis
					, configuration
					, nodi)){
//					copyFile(
//					,
//					premisDest,
//					mdFilesTmp, mdFileTmpBusiness, premisElab,
//					application, objectIdentifierPremis, 
//					mdFilesTmp.getIdSoftware(), objectIdentifierPremis, configuration)) {
				
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Sposto il file  "+
						fObj.getAbsolutePath()+
						" in "+
						nodi.genFileTarDest()
						);
				sendFileTar(fObj
						// fObjNew, 
						, mdFileTmpBusiness
						, mdFilesTmp
						, objectIdentifierContainer
						, objectIdentifierPremis
						, configuration
//						, application
						, filePremis
						, nodi
						, premisElab);
			} else {
				logPublish.error("\n"+name+" ["+objectIdentifierPremis+"] Riscontrato un problema nella copia");
				mdFileTmpBusiness
				.updateStopPublish(
						mdFilesTmp.getId(),
						false,
						null, 
						new String[] { "Riscontrato un problema nella copia del file Premis" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (SQLException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		}

	}

	/**
	 * Metodo utilizzato per verificare la presenza del file di destinazione nelle diverse condizioni di elaborazione
	 * 
	 * @param fObj
	 * @param record
	 * @param fObjNew
	 * @return
	 * @throws NodiException 
	 */
	private boolean isFileExist(File fObj, MDFilesTmp record, 
			Nodi nodi) throws NodiException{
		boolean ris = false;
		
		if (fObj.exists()) {
			ris=true;
		} else {
			if (record.getMoveFileDataStart() != null
					&& record.getMoveFileDataEnd() != null) {
				if (nodi.isFileExists()){
					ris =  true;
				}
			}
		}
		
		return ris;
	}
}
