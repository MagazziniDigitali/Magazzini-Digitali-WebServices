/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.ObjectComplexType;
import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrEvent;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrObjectFileAnalyze;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.exception.SolrWarning;
import it.bncf.magazziniDigitali.utils.GenFileDest;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.archive.Tar;
//import mx.randalf.configuration.Configuration;
//import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class OggettoDigitaleSolrIndex extends OggettoDigitale {

	private Logger log = Logger.getLogger(OggettoDigitaleSolrIndex.class);

	private Logger logSolr = null;

	private String name = null;

	/**
	 * 
	 */
	public OggettoDigitaleSolrIndex(Logger logSolr, String name) {
		this.logSolr = logSolr;
		this.name = name;
	}

	public boolean esegui(String objectIdentifierPremis, String application, IMDConfiguration<?> configuration) {
		boolean esito = false;
		MDFilesTmpBusiness mdFileTmpBusiness = null;
		MDFilesTmpDAO mdFileTmpDao = null;
		MDFilesTmp mdFilesTmp = null;

		logSolr.info(name + " [" + objectIdentifierPremis + "] Inizio l'indicizzazione");

		try {
			mdFileTmpBusiness = new MDFilesTmpBusiness();
			mdFileTmpDao = new MDFilesTmpDAO();
			mdFilesTmp = mdFileTmpDao.findById(objectIdentifierPremis);
		} catch (HibernateException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		}

		try {
			if (mdFilesTmp != null) {
				esito = checkStato(configuration, mdFileTmpBusiness, mdFilesTmp, objectIdentifierPremis);
			}
		} catch (MDConfigurationException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			esito = false;
		} catch (HibernateException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			esito = false;
		} catch (HibernateUtilException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			esito = false;
		} catch (SQLException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			esito = false;
		} finally {
			logSolr.info(name + " [" + objectIdentifierPremis + "] Fine dell'indicizzazione");
		}
		return esito;
	}

	private boolean checkStato(IMDConfiguration<?> configuration, MDFilesTmpBusiness mdFileTmpBusiness,
			MDFilesTmp mdFilesTmp, String objectIdentifierPremis) 
					throws HibernateException, HibernateUtilException, MDConfigurationException, SQLException {
		boolean esito = false;
		try {
			FactoryDAO.initialize(mdFilesTmp.getStato());
			if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEARCHIVE)
					|| mdFilesTmp.getStato().getId().equals(MDStatoDAO.INITINDEX)) {
				esito = indexFiles(configuration, mdFileTmpBusiness, mdFilesTmp, objectIdentifierPremis);
			} else if (!mdFilesTmp.getStato().getId().equals(MDStatoDAO.CHECKINDEX)) {
				esito = false;
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
		return esito;
	}

	@SuppressWarnings("unused")
	private boolean indexFiles(IMDConfiguration<?> configuration, MDFilesTmpBusiness mdFileTmpBusiness,
			MDFilesTmp mdFilesTmp, String objectIdentifierPremis) throws MDConfigurationException, SQLException {
		File filePremis = null;
		File fInputPremis = null;
		File fInput = null;
		PremisXsd premisInput = null;
		GregorianCalendar start = null;
		GregorianCalendar stop = null;
		PremisXsd premisElab = null;
		String pFile = null;
		int pos = 0;
		boolean esito = false;

		try {
			filePremis = new File(genFilePremis(configuration.getSoftwareConfigString("path.premis"), "SolrIndex",
					UUID.randomUUID().toString(), ".premis"));

			pFile = mdFilesTmp.getPremisFile();
			pos = pFile.lastIndexOf("/");
			if (pos > -1) {
				pFile = pFile.substring(pos + 1);
			}
			fInputPremis = GenFileDest.genFileDest(configuration.getSoftwareConfigMDNodi("nodo"), pFile);
			premisInput = new PremisXsd(fInputPremis);
			fInput = new File(fInputPremis.getParentFile().getAbsolutePath() + File.separator
					+ fInputPremis.getName().replace(".premis", ""));
			if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEARCHIVE)) {
				logSolr.info(name + " [" + objectIdentifierPremis + "]" + " Inizio l'indicizzazione del file ["
						+ fInput.getAbsolutePath() + "]");
				start = mdFileTmpBusiness.updateStartIndex(mdFilesTmp.getId());
			} else {
				logSolr.info(name + " [" + objectIdentifierPremis + "]" + " Continuo l'indicizzazione del file ["
						+ fInput.getAbsolutePath() + "]");
				start = new GregorianCalendar();
				start.setTimeInMillis(mdFilesTmp.getIndexDataStart().getTime());
			}
			premisElab = new PremisXsd();
			premisElab.addObjectFileContainer(objectIdentifierPremis, null, null, new BigInteger("0"), null, null, null,
					null, null, null, null);
			logSolr.info(
					name + " [" + objectIdentifierPremis + "]" + " Preparo l'indicizzazione del materiale in Solr");
			if (preIndexSolr(premisInput, fInput, logSolr, objectIdentifierPremis, configuration)) {
				// stop = mdFileTmpBusiness.updateCheckIndex(
				// mdFilesTmp.getId(),
				// filePremis.getName());
				stop = mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), true,
						null,null);
				premisElab.addEvent("index", start, stop, null, "OK", null, null, configuration.getMDSoftware(),
						findObjectIdentifierContainer(premisInput));
				logSolr.info(name + " [" + objectIdentifierPremis + "]" + " Materiale pubblicato");
				esito = true;
			} else {
				logSolr.error(
						name + " [" + objectIdentifierPremis + "]" + " Riscontrato un problema nella pubblicazione");
				mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
						null, new String[] { "Riscontrato un problema nella preIndicizazione" });
			}
		} catch (SQLException e) {
			if (premisElab != null) {
				premisElab.addEvent("Error", null, null, null, "KO", new String[] { e.getMessage() }, null,
						configuration.getMDSoftware(),
						// Configuration.getValue("demoni."
						// + application + ".UUID"),
						null);
			}
			mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
					new Exception[] { e },
					null);
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
		} catch (SolrWarning e) {
			if (premisElab != null) {
				premisElab.addEvent("Warning", null, null, null, "KO", new String[] { e.getMessage() }, null,
						configuration.getMDSoftware(),
						// Configuration.getValue("demoni."
						// + application + ".UUID"),
						null);
			}
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
					new Exception[] { e },
					null);
		} catch (MDConfigurationException e) {
			if (premisElab != null) {
				premisElab.addEvent("Warning", null, null, null, "KO", new String[] { e.getMessage() }, null,
						configuration.getMDSoftware(),
						// Configuration.getValue("demoni."
						// + application + ".UUID"),
						null);
			}
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
					new Exception[] { e },
					null);
		} catch (XsdException e) {
			if (premisElab != null) {
				premisElab.addEvent("Warning", null, null, null, "KO", new String[] { e.getMessage() }, null,
						configuration.getMDSoftware(),
						// Configuration.getValue("demoni."
						// + application + ".UUID"),
						null);
			}
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
					new Exception[] { e },
					null);
		} catch (SolrException e) {
			if (premisElab != null) {
				premisElab.addEvent("Warning", null, null, null, "KO", new String[] { e.getMessage() }, null,
						configuration.getMDSoftware(),
						// Configuration.getValue("demoni."
						// + application + ".UUID"),
						null);
			}
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
					new Exception[] { e },
					null);
		} catch (Exception e) {
			if (premisElab != null) {
				premisElab.addEvent("Error", null, null, null, "KO", new String[] { e.getMessage() }, null,
						configuration.getMDSoftware(),
						// Configuration.getValue("demoni."
						// + application + ".UUID"),
						null);
			}
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
					new Exception[] { e },
					null);
		} finally {
			try {
				if (premisElab != null && esito) {
					premisElab.write(filePremis, false);
				}
			} catch (PremisXsdException e) {
				log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
				mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
						new Exception[] { e },
						null);
			} catch (XsdException e) {
				log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
				mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
						new Exception[] { e },
						null);
			} catch (IOException e) {
				log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
				mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
						new Exception[] { e },
						null);
			} catch (Exception e) {
				log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
				mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")), false,
						new Exception[] { e },
						null);
			}
		}
		return esito;
	}

	private boolean preIndexSolr(PremisXsd premis, File fObj, Logger logPublish, String objectIdentifierPremis,
			IMDConfiguration<?> configuration) throws SolrException, SolrWarning {
		boolean ris = false;
		List<ObjectComplexType> objects = null;
		List<EventComplexType> events = null;
		ObjectComplexType object = null;
		EventComplexType event = null;
		AddDocumentMD admd = null;
		SolrEvent se = null;
		SolrObjectFileAnalyze sof = null;
		File pathTar = null;

		try {
			pathTar = new File(configuration.getSoftwareConfigString("solrIndex.tmpPath") +
			// Configuration.getValue("demoni.SolrIndex.tmpPath")+
					File.separator + fObj.getName());
			if (!pathTar.exists()) {
				if (!pathTar.mkdirs()) {
					throw new SolrException("Riscontrato un problema nella creazione della cartella ["
							+ pathTar.getAbsolutePath() + "]");
				}
			}
			Tar.decompress(fObj, pathTar);
			admd = new AddDocumentMD(configuration.getSoftwareConfigString("solr.URL"),
					configuration.getSoftwareConfigBoolean("solr.Cloud"),
					configuration.getSoftwareConfigString("solr.collection"),
					configuration.getSoftwareConfigInteger("solr.connectionTimeOut"),
					configuration.getSoftwareConfigInteger("solr.clientTimeOut"));
			// admd = new IndexDocumentMD(fObj.getName());
			objects = premis.getObject();
			if (objects != null && objects.size() > 0) {
				logPublish.info(
						name + " [" + objectIdentifierPremis + "]" + " Oggetto da preIndicizzare " + objects.size());
				sof = new SolrObjectFileAnalyze();
				for (int x = 0; x < objects.size(); x++) {
					if ((x % 100) == 0) {
						logPublish.info(
								name + " [" + objectIdentifierPremis + "]" + " Oggetto " + x + "/" + objects.size());
						System.gc();
					}
					object = objects.get(x);
					if (object instanceof info.lc.xmlns.premis_v2.File) {
						sof.publishSolr((info.lc.xmlns.premis_v2.File) object, admd, pathTar, configuration);
					}
				}
				logPublish.info(name + " [" + objectIdentifierPremis + "]" + " Fine preIndicizzare oggetti");
			}
			System.gc();
			events = premis.getEvent();
			if (events != null && events.size() > 0) {
				logPublish.info(
						name + " [" + objectIdentifierPremis + "]" + " Eventi da preIndicizzare " + events.size());
				se = new SolrEvent();
				for (int x = 0; x < events.size(); x++) {
					if ((x % 100) == 0) {
						logPublish.info(
								name + " [" + objectIdentifierPremis + "]" + " Eventi " + x + "/" + events.size());
						System.gc();
					}
					event = events.get(x);
					se.publishSolr(event, admd);
				}
				logPublish.info(name + " [" + objectIdentifierPremis + "]" + " Fine preIndicizzare eventi");
			}
			logPublish.info(name + " [" + objectIdentifierPremis + "]" + " Inizio pubblicazione in Solr");
			admd.commit();
			// admd.send(configuration);
			ris = true;
		} catch (NumberFormatException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (SolrException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			throw e;
			// } catch (ConfigurationException e) {
			// log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(),
			// e);
			// throw new SolrException(e.getMessage(), e);
			// } catch (SolrWarning e) {
			// log.warn(name+" ["+objectIdentifierPremis+"] "+e.getMessage(),
			// e);
			// throw e;
		} catch (Exception e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} finally {
			try {
				if (pathTar.exists()) {

					if (!deleteFolder(pathTar)) {
						throw new SolrException(
								"Problemi nella cancellazione della cartella [" + pathTar.getAbsolutePath() + "]");
					}
				}
			} catch (Exception e) {
				log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			}
		}

		return ris;
	}

	private boolean deleteFolder(File path) {
		boolean ris = true;
		File[] fl = null;
		fl = path.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().equals(".") || pathname.getName().equals("..")) {
					return false;
				} else {
					return true;
				}
			}
		});
		for (int x = 0; x < fl.length; x++) {
			if (fl[x].isDirectory()) {
				if (!deleteFolder(fl[x])) {
					ris = false;
				}
			} else {
				if (!fl[x].delete()) {
					ris = false;
				}
			}
		}
		if (ris) {
			if (!path.delete()) {
				ris = false;
			}
		}
		return ris;
	}
}
