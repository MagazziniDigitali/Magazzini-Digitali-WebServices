/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.ObjectComplexType;
import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrEvent;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrObjectFile;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.solr.IndexDocumentMD;
import it.bncf.magazziniDigitali.solr.exception.SolrWarning;
import it.bncf.magazziniDigitali.utils.GenFileDest;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import mx.randalf.archive.Tar;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class OggettoDigitaleSolrIndex {

	private Logger log = Logger.getLogger(OggettoDigitaleSolrIndex.class);

	private Logger logSolr = null;

	private String name = null;

	private HibernateTemplate hibernateTemplate= null;

	/**
	 * 
	 */
	public OggettoDigitaleSolrIndex(HibernateTemplate hibernateTemplate, 
			Logger logSolr, String name) {
		this.logSolr = logSolr;
		this.name = name;
		this.hibernateTemplate = hibernateTemplate;
	}

	public boolean esegui(String objectIdentifierPremis, String application) {
		File filePremis = null;
		GregorianCalendar start = null;
		PremisXsd premisInput = null;
		String objectIdentifierContainer = null;
		File fObjNew = null;
		File fObjNewPremis = null;
		PremisXsd premisElab = null;
		GregorianCalendar stop = null;
		boolean esito = false;
		MDFilesTmpBusiness mdFileTmpBusiness= null;
		MDFilesTmpDAO mdFileTmpDao= null;
		MDFilesTmp mdFilesTmp = null;

		
		try {
			mdFileTmpBusiness = new MDFilesTmpBusiness(hibernateTemplate);
			mdFileTmpDao = new MDFilesTmpDAO(hibernateTemplate);
			mdFilesTmp = mdFileTmpDao.findById(objectIdentifierPremis);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		}

		try {
			if (mdFilesTmp != null) {
				FactoryDAO.initialize(mdFilesTmp.getStato());
				if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEARCHIVE) ||
						mdFilesTmp.getStato().getId().equals(MDStatoDAO.INITINDEX)){
					try {
						filePremis = new File(Configuration.getValue("path.premisSolr")
								+ File.separator + UUID.randomUUID().toString()
								+ ".premis");

						fObjNewPremis = GenFileDest.genFileDest(Configuration.getValue("demoni.Publish.pathStorage")
								,mdFilesTmp.getPremisFile());
						premisInput = new PremisXsd(fObjNewPremis);
						fObjNew = new File(fObjNewPremis.getParentFile().getAbsolutePath()+
								File.separator+fObjNewPremis.getName().replace(".premis", ""));
						if (mdFilesTmp.getStato().getId()
								.equals(MDStatoDAO.FINEARCHIVE)) {
							logSolr.info(name+" Inizio l'indicizzazione del file ["
									+ fObjNew.getAbsolutePath() + "]");
							start = mdFileTmpBusiness
									.updateStartIndex(mdFilesTmp.getId());
						} else {
							logSolr.info(name+" Continuo l'indicizzazione del file ["
									+ fObjNew.getAbsolutePath() + "]");
							start = new GregorianCalendar();
							start.setTimeInMillis(mdFilesTmp.getIndexDataStart().getTime());
						}
						premisElab = new PremisXsd();
						premisElab
								.addObjectFileContainer(
										objectIdentifierPremis,
										null, null,
										new BigInteger("0"),
										null, null, null, null, null, null, null);
						logSolr.info(name+" Preparo l'indicizzazione del materiale in Solr");
						if (preIndexSolr(premisInput, fObjNew, logSolr)) {
							stop = mdFileTmpBusiness.updateCheckIndex(
									mdFilesTmp.getId(),
									filePremis.getName());
							premisElab
								.addEvent(
										"preIndex",
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
							logSolr.info(name+" Materiale pubblicato");
							esito = true;
						} else {
							logSolr.error(name+" Riscontrato un problema nella pubblicazione");
							mdFileTmpBusiness
								.updateStopIndex(
										mdFilesTmp.getId(),
										false,
										new String[] { "Riscontrato un problema nella preIndicizazione" });
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
						mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(), false,
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
						mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(), false,
								new String[] { e.getMessage() });
						log.error(e.getMessage(), e);
					} catch (SolrWarning e) {
						if (premisElab != null) {
							premisElab.addEvent(
									"Warning",
									null,
									null,
									null,
									"KO",
									new String[] { e.getMessage() },
									null,
									Configuration.getValue("demoni."
											+ application + ".UUID"), null);
						}
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
						mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(), false,
								new String[] { e.getMessage() });
					} finally {
						try {
							if (premisElab != null && esito){
								premisElab.write(filePremis, false);
							}
						} catch (PremisXsdException e) {
							log.error(e.getMessage(), e);
							mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
									false, new String[] { e.getMessage() });
						} catch (XsdException e) {
							log.error(e.getMessage(), e);
							mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
									false, new String[] { e.getMessage() });
						} catch (IOException e) {
							log.error(e.getMessage(), e);
							mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
									false, new String[] { e.getMessage() });
						} catch (Exception e) {
							log.error(e.getMessage(), e);
							mdFileTmpBusiness.updateStopIndex(mdFilesTmp.getId(),
									false, new String[] { e.getMessage() });
						}
					}
				} else if (!mdFilesTmp.getStato().getId().equals(MDStatoDAO.CHECKINDEX)){
					esito = false;
				}
			}
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
			esito = false;
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			esito = false;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			esito = false;
		}
		return esito;
	}

	private boolean preIndexSolr(PremisXsd premis, File fObj, Logger logPublish)
			throws SolrException, SolrWarning {
		boolean ris = false;
		List<ObjectComplexType> objects = null;
		List<EventComplexType> events = null;
		ObjectComplexType object = null;
		EventComplexType event = null;
		IndexDocumentMD admd = null;
		SolrEvent se = null;
		SolrObjectFile sof = null;
		File pathTar = null;

		try {
			pathTar = new File(Configuration.getValue("demoni.SolrIndex.tmpPath")+
					File.separator+fObj.getName());
			if (!pathTar.exists()){
				if (!pathTar.mkdirs()){
					throw new SolrException("Riscontrato un problema nella creazione della cartella ["+
							pathTar.getAbsolutePath()+"]");
				}
			}
			Tar.decompress(fObj, pathTar);
			admd = new IndexDocumentMD(fObj.getName());
			objects = premis.getObject();
			if (objects != null && objects.size() > 0) {
				logPublish.info(name+" Oggetto da preIndicizzare "+objects.size());
				sof = new SolrObjectFile();
				for (int x = 0; x < objects.size(); x++) {
					if ((x%100)==0){
						logPublish.info(name+" Oggetto "+x+"/"+objects.size());
						System.gc();
					}
					object = objects.get(x);
					if (object instanceof info.lc.xmlns.premis_v2.File) {
						sof.publishSolr((info.lc.xmlns.premis_v2.File) object,
								admd, pathTar);
					}
				}
				logPublish.info(name+" Fine preIndicizzare oggetti");
			}
			System.gc();
			events = premis.getEvent();
			if (events != null && events.size() > 0) {
				logPublish.info(name+" Eventi da preIndicizzare "+events.size());
				se = new SolrEvent();
				for (int x = 0; x < events.size(); x++) {
					if ((x%100)==0){
						logPublish.info(name+" Eventi "+x+"/"+events.size());
						System.gc();
					}
					event = events.get(x);
					se.publishSolr(event, admd);
				}
				logPublish.info(name+" Fine preIndicizzare eventi");
			}
			logPublish.info(name+" Inizio pubblicazione in Solr");
			admd.send();
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
		} catch (SolrWarning e) {
			log.warn(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} finally {
			try {
				if (pathTar.exists()){
					
					if (!deleteFolder(pathTar)){
						throw new SolrException("Problemi nella cancellazione della cartella ["+pathTar.getAbsolutePath()+"]");
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			}
		}

		return ris;
	}

	private boolean deleteFolder(File path){
		boolean ris = true;
		File[] fl = null;
		fl = path.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().equals(".") ||
						pathname.getName().equals("..")){
					return false;
				} else {
					return true;
				}
			}
		});
		for (int x=0; x<fl.length; x++){
			if (fl[x].isDirectory()){
				if (!deleteFolder(fl[x])){
					ris = false;
				}
			} else {
				if (!fl[x].delete()){
					ris=false;
				}
			}
		}
		if (ris){
			if (!path.delete()){
				ris = false;
			}
		}
		return ris;
	}
}
