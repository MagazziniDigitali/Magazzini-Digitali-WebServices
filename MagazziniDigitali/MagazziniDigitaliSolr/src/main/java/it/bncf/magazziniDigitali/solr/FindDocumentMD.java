/**
 * 
 */
package it.bncf.magazziniDigitali.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import mx.randalf.solr.FindDocument;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class FindDocumentMD extends FindDocument {

	/**
	 * @param url
	 * @param cloud
	 * @param collection
	 * @throws SolrException
	 */
	public FindDocumentMD(String url, boolean cloud, String collection) throws SolrException {
		super(url, cloud, collection);
	}

	/**
	 * @param url
	 * @param cloud
	 * @param collection
	 * @param connectionTimeout
	 * @param clientTimeout
	 * @throws SolrException
	 */
	public FindDocumentMD(String url, boolean cloud, String collection, int connectionTimeout, int clientTimeout)
			throws SolrException {
		super(url, cloud, collection, connectionTimeout, clientTimeout);
	}

	public SolrDocumentList find(String key, String value) throws SolrServerException{
		QueryResponse qr = null;
		String query = "";
		SolrDocumentList response = null;

		try {
			query = key+":\""+value+"\"";
			
			qr = this.find(query);
			if (qr.getResponse() != null &&
					qr.getResponse().get("response")!=null){
				response = (SolrDocumentList) qr.getResponse().get("response");
			}
		} catch (SolrServerException e) {
			throw e;
		}
		return response;
	}

	public SolrDocumentList findEvent(String id, String eventType) throws SolrServerException{
		QueryResponse qr = null;
		String query = "";
		SolrDocumentList response = null;

		try {
			query = "_root_:\""+id+"\"  AND tipoOggetto:evento AND eventType:\""+eventType+"\"";
			
			qr = this.find(query);
			if (qr.getResponse() != null &&
					qr.getResponse().get("response")!=null){
				response = (SolrDocumentList) qr.getResponse().get("response");
			}
		} catch (SolrServerException e) {
			throw e;
		}
		return response;
	}
}
