/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.oggettoDigitale;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class OggettoDigitaleBusinessStatus extends OggettoDigitaleBusiness {

	/**
	 * 
	 */
	public OggettoDigitaleBusinessStatus() {
	}

	/**
	 * Metodo utilizzato per verificare lo stato dell'OggettoDigitale
	 * 
	 * @param sha1
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws SolrException
	 * @throws SolrServerException
	 * @throws MDConfigurationException 
	 */
	public Hashtable<String, String> checkStatus(String md5, String md564base, String sha1, String sha164base, 
			String sha256, String sha25664base, IMDConfiguration<?> configuration)
			throws SolrException, SolrServerException, HibernateException, HibernateUtilException, MDConfigurationException {
		MDFilesTmpBusiness mdFileTmp = null;
		List<MDFilesTmp> records = null;
		Hashtable<String, String> result = null;
		AddDocumentMD admd = null;
		QueryResponse qr = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness();
			records = mdFileTmp.find(null, null, null, null, md5, md564base, sha1, sha164base, sha256, sha25664base, 0, 0);

			if (records != null) {
				for (int x = 0; x < records.size(); x++) {
					if (records.get(x).getStato() != null) {
						if (result == null) {
							result = new Hashtable<String, String>();
						}
						result.put("id", records.get(x).getId());
						result.put("stato", records.get(x).getStato().getId());

						break;
					}
				}
			} else {
				admd = new AddDocumentMD(configuration.getSoftwareConfigString("solr.URL"),
//						Configuration.getValue("demoni.Publish.solr.URL"),
						configuration.getSoftwareConfigBoolean("solr.Cloud"),
//						Boolean.parseBoolean(Configuration
//								.getValue("demoni.Publish.solr.Cloud")),
						configuration.getSoftwareConfigString("solr.collection"),
//						Configuration
//								.getValue("demoni.Publish.solr.collection"),
						configuration.getSoftwareConfigInteger("solr.connectionTimeOut"),
//						Integer.parseInt(Configuration
//								.getValue("demoni.Publish.solr.connectionTimeOut")),
						configuration.getSoftwareConfigInteger("solr.clientTimeOut")
						, configuration.getSoftwareConfigString("solr.optional"));
//						Integer.parseInt(Configuration
//								.getValue("demoni.Publish.solr.clientTimeOut")));
				qr = admd.find("sha1:" + sha1);
				if (qr != null && qr.getResults() != null
						&& qr.getResults().getNumFound() > 0) {
					if (result == null) {
						result = new Hashtable<String, String>();
					}
					result.put("id", ((String) qr.getResults().get(0)
							.getFieldValue("id")).substring(0, 36));
					result.put("stato", MDStatoDAO.FINEPUBLISH);
				}
			}
		} catch (SolrException e) {
			throw e;
		} catch (SolrServerException e) {
			throw e;
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		}
		return result;
	}

}
