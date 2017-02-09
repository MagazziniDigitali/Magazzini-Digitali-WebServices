/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.exception.SolrWarning;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.archive.Tar;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public abstract class IndexPremis<PX extends PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, OCT, ECT> {

	private Logger log = Logger.getLogger(IndexPremis.class);

	protected String name = null;

	/**
	 * 
	 */
	public IndexPremis(String name) {
		this.name=name;
	}

	@SuppressWarnings("unchecked")
	public boolean preIndexSolr(PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisInput, File fObj, Logger logPublish, String objectIdentifierPremis,
			IMDConfiguration<?> configuration) throws SolrException, SolrWarning {
		boolean ris = false;
		AddDocumentMD admd = null;
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
			if (fObj.getName().toLowerCase().endsWith(".tar")){
				Tar.decompress(fObj, pathTar);
			}
			admd = new AddDocumentMD(configuration.getSoftwareConfigString("solr.URL"),
					configuration.getSoftwareConfigBoolean("solr.Cloud"),
					configuration.getSoftwareConfigString("solr.collection"),
					configuration.getSoftwareConfigInteger("solr.connectionTimeOut"),
					configuration.getSoftwareConfigInteger("solr.clientTimeOut"));
			// admd = new IndexDocumentMD(fObj.getName());
			checkObject((List<OCT>) premisInput.getObject(), logPublish, objectIdentifierPremis,admd, pathTar, configuration, false);
			checkEvent((List<ECT>) premisInput.getEvent(), logPublish, objectIdentifierPremis, admd);
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

	@SuppressWarnings("unchecked")
	public void preIndexSolr(PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisInput, AddDocumentMD admd,
			IMDConfiguration<?> configuration, Logger logPublish, String objectIdentifierPremis, File pathTar)
			throws SolrException {
		try {
			checkObject((List<OCT>) premisInput.getObject(), logPublish, objectIdentifierPremis,admd, pathTar, configuration, true);
			checkEvent((List<ECT>) premisInput.getEvent(), logPublish, objectIdentifierPremis, admd);
		} catch (SolrException e) {
			log.error(name + " [" + objectIdentifierPremis + "] " + e.getMessage(), e);
			throw e;
		}
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
					System.out.println("Problemi nella cancellazione della cartella ["+fl[x].getAbsolutePath()+"]");
					ris = false;
				}
			} else {
				if (!fl[x].delete()) {
					if (fl[x].exists()){
						System.out.println("Problemi nella cancellazione del file ["+fl[x].getAbsolutePath()+"]");
						ris = false;
					}
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

	protected abstract void checkObject(List<OCT> objects, Logger logPublish, String objectIdentifierPremis, AddDocumentMD admd,
			File pathTar, IMDConfiguration<?> configuration, boolean elabTarPremis) throws SolrException;

	protected abstract void checkEvent(List<ECT> events, Logger logPublish, String objectIdentifierPremis, AddDocumentMD admd)
			throws SolrException;

}
