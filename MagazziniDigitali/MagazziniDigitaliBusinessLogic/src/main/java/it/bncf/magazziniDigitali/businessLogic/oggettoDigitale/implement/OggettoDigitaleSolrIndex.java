/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.IndexPremis;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.IndexPremis2_2;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.IndexPremis3_0;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.solr.exception.SolrWarning;
import it.bncf.magazziniDigitali.utils.GenFileDest;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
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
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisInput = null;
		GregorianCalendar start = null;
		GregorianCalendar stop = null;
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab = null;
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
			premisInput = PremisXsd.open(fInputPremis);
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
			premisElab = PremisXsd.initialize();
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
		} catch (PremisXsdException e) {
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

	
	private boolean preIndexSolr(PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisInput, File fInput,
			Logger logSolr2, String objectIdentifierPremis, IMDConfiguration<?> configuration) throws SolrException, SolrWarning {
		IndexPremis<?, ?, ?> indexPremis = null;

		if (premisInput.getVersion().equals("2.2")){
			indexPremis = new IndexPremis2_2(name);
		} else {
			indexPremis = new IndexPremis3_0(name);
		}
		return indexPremis.preIndexSolr(premisInput, fInput, logSolr2, objectIdentifierPremis, configuration);
	}
}
