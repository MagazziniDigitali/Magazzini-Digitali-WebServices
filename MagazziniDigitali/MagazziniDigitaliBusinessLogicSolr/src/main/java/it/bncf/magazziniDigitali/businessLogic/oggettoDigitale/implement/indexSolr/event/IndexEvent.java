/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.event;

import java.io.File;
import java.io.FileFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.implement.TarMD;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.exception.SolrWarning;
import it.magazziniDigitali.xsd.event.EventXsd;
import mx.randalf.archive.Tar;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public abstract class IndexEvent<PX extends EventXsd<?, ?, ?, ?, ?>> {

	private Logger log = LogManager.getLogger(IndexEvent.class);

	protected String name = null;

	/**
	 * 
	 */
	public IndexEvent(String name) {
		this.name=name;
	}

	@SuppressWarnings("unchecked")
	public boolean preIndexSolr(EventXsd<?, ?, ?, ?, ?> eventInput, File fObj, IMDConfiguration<?> configuration) throws SolrException, SolrWarning {
		boolean ris = false;
		AddDocumentMD admd = null;
		File pathTar = null;
		Tar tar = null;

		try {
			pathTar = new File(configuration.getSoftwareConfigString("solrIndex.tmpPath") +
					File.separator + fObj.getName());
			if (!pathTar.exists()) {
				if (!pathTar.mkdirs()) {
					throw new SolrException("Riscontrato un problema nella creazione della cartella ["
							+ pathTar.getAbsolutePath() + "]");
				}
			}
			tar = new TarMD();
			tar.decompress(fObj, pathTar);
			admd = new AddDocumentMD(configuration.getSoftwareConfigString("solr.URL"),
					configuration.getSoftwareConfigBoolean("solr.Cloud"),
					configuration.getSoftwareConfigString("solr.collection"),
					configuration.getSoftwareConfigInteger("solr.connectionTimeOut"),
					configuration.getSoftwareConfigInteger("solr.clientTimeOut"),
					configuration.getSoftwareConfigString("solr.optional"));

			checkEvent((PX) eventInput, admd);
			log.info("\n"+name + " Inizio pubblicazione in Solr");
			admd.commit();
			ris = true;
		} catch (NumberFormatException e) {
			log.error(name + e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} catch (SolrException e) {
			log.error(name + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(name + e.getMessage(), e);
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
				log.error(name + e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			}
		}

		return ris;
	}

	@SuppressWarnings("unchecked")
	public void preIndexSolr(EventXsd<?, ?, ?, ?, ?> premisInput, AddDocumentMD admd)
			throws SolrException {
		try {
			checkEvent((PX) premisInput, admd);
		} catch (SolrException e) {
			log.error(name + " " + e.getMessage(), e);
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
					ris = false;
				}
			} else {
				if (!fl[x].delete()) {
					if (fl[x].exists()){
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

	protected abstract void checkEvent(PX events, AddDocumentMD admd)
			throws SolrException;

}
