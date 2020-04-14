package it.bncf.magazziniDigitali.solr.test;

import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import mx.randalf.solr.exception.SolrException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

public class AddDocumentMDTest {

	public AddDocumentMDTest() {
	}

	@SuppressWarnings("unused")
	public static void main(String[] args){
		AddDocumentMD admd = null;
		String url = null;
		Boolean cloud = null;
		String collection = null;
		int connectionTimeout = -1;
		int clientTimeout = -1;
		QueryResponse qr = null;
		
		if (args.length==5){
			try {
				url= args[0];
				cloud = Boolean.parseBoolean(args[1]);
				collection = args[2];
				connectionTimeout = Integer.parseInt(args[3]);
				clientTimeout = Integer.parseInt(args[4]);
				System.out.println("url: "+url);
				System.out.println("cloud: "+cloud);
				System.out.println("collection: "+collection);
				System.out.println("connectionTimeout: "+connectionTimeout);
				System.out.println("clientTimeout: "+clientTimeout);
				System.out.println("Apro la connessione con Solr");
				admd = new AddDocumentMD(url, cloud, collection, connectionTimeout, clientTimeout,null);
				
				if (admd != null){
					System.out.println("Eseguo la ricerca nel database");
					qr = admd.findAll();
					if (qr!= null){
						System.out.println("Risultato: "+qr.getResponse().toString());
					} else {
						System.out.println("Riscontrato un problema nella ricerca");
					}
				} else {
					System.out.println("Riscontrato un problema di connessione");
				}
			} catch (SolrException e) {
				e.printStackTrace();
			} catch (SolrServerException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("E' necessario indicare i sguenti parametri");
			System.out.println("1) url (md-solr01.bncf.lan:2181,md-solr02.bncf.lan:2181,md-solr03.bncf.lan:2181)");
			System.out.println("2) cloud (true)");
			System.out.println("3) collection (mdProd)");
			System.out.println("4) connectionTimeout (60000)");
			System.out.println("5) clientTimeout (100000)");
		}
	}
}
