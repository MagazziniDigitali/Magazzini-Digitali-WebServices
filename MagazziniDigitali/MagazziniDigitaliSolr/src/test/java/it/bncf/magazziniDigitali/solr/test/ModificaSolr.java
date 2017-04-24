/**
 * 
 */
package it.bncf.magazziniDigitali.solr.test;

import java.io.IOException;
import java.util.Vector;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import mx.randalf.solr.Item;
import mx.randalf.solr.Params;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class ModificaSolr {

	String url = "http://md-solr01.test.bncf.lan:8983/solr/mdTest";
	boolean cloud = false;
	String collection = "mdTest";
	int connectionTimeout = 60000;
	int clientTimeout = 100000;
	/**
	 * 
	 */
	public ModificaSolr() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ModificaSolr modificaSolr = null;
		
		if (args.length==2){
			modificaSolr = new ModificaSolr();
			modificaSolr.modificaRights(args[0], args[1]);
		} else {
			System.out.println("E' necessario indicare i seguenti parametri");
			System.out.println("1) Identificativo oggetto");
			System.out.println("2) identificativo nuovo rigths");
		}
	}

	public void modificaRights(String id, String newRigths){
		AddDocumentMD addDocumentMD = null;
		QueryResponse queryResponse = null;
		SolrDocumentList solrDocumentList = null;
		Params params = null;

		try {
			addDocumentMD = new AddDocumentMD(url, cloud, collection, connectionTimeout, clientTimeout);
			
			queryResponse = addDocumentMD.find("id:\""+id+"\"");
	
			if (queryResponse.getResponse() != null &&
					queryResponse.getResponse().get("response")!=null){
				solrDocumentList = (SolrDocumentList) queryResponse.getResponse().get("response");
			
			if (solrDocumentList.getNumFound()>0){
				for (int x=0; x<solrDocumentList.getNumFound(); x++){
					if (solrDocumentList.get(x).get("rights_show")!= null){
						params = Item.convert(solrDocumentList.get(x));
						System.out.println("rights: "+((Vector<Object>)params.getParams().get("rights")).get(0));
						((Vector<Object>)params.getParams().get("rights")).set(0, newRigths);
						System.out.println("rights: "+((Vector<Object>)params.getParams().get("rights")).get(0));
						addDocumentMD.add(params.getParams(), new ItemMD());
					}
				}
			}
			
			addDocumentMD.commit();
			}
		} catch (SolrException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (addDocumentMD != null){
					addDocumentMD.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
