/**
 * 
 */
package it.bncf.magazziniDigitali.solr;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.solr.exception.SolrWarning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

//import mx.randalf.configuration.Configuration;
//import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.solr.Item;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.tools.rsync.RSync;
import mx.randalf.tools.rsync.exception.RSyncException;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest.ACTION;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author massi
 *
 */
public class IndexDocumentMD {

//	private Logger log = Logger.getLogger(IndexDocumentMD.class);

	protected UpdateRequest request;

	private String fileName = null;
	
	/**
	 * 
	 */
	public IndexDocumentMD() {
	}

	public IndexDocumentMD(String fileName) {
		this.fileName = fileName;
	}


	/**
	 * Metodo per aggiungere/modificare un oggetto sul database Solr
	 * 
	 * @param params
	 * @throws SolrException 
	 */
	public void add(Hashtable<String, List<Object>> params, 
			Item items) throws SolrException{
		String id = null;

		id = (String) params.get(Item.ID).get(0);
		add(initItem(id, params, items));
	}
	public void add(SolrInputDocument item) throws SolrException{
		if (request == null){
			request = new UpdateRequest();
			request.setAction(ACTION.OPTIMIZE, false, false);
		}
		if (item.get("_version_") != null){
			item.remove("_version_");
		}
		if (item.get("indexed") != null){
			item.remove("indexed");
		}
		request.add(item);
	}

	public void clear(){
		if (request != null){
			request.clear();
		}
	}
	
	public static SolrInputDocument initItem(Hashtable<String, List<Object>> params,
			Item items) {
		SolrInputDocument item = null;
//		Vector<Object> objs = null;
//		Object obj = null;
		Enumeration<String> keys =null;
		String key = null;

		item = new SolrInputDocument();
	
		keys = params.keys();

		while(keys.hasMoreElements()){
			key = keys.nextElement();
			items.add(item, key, (Vector<Object>) params.get(key));
		}
		return item;
	}
	
	private SolrInputDocument initItem(String id,
			Hashtable<String, List<Object>> params,
			Item items) {
		SolrInputDocument item = null;
//		Vector<Object> objs = null;
//		Object obj = null;
		Enumeration<String> keys =null;
		String key = null;

		item = new SolrInputDocument();
	
		keys = params.keys();

		while(keys.hasMoreElements()){
			key = keys.nextElement();
			items.add(item, key, (Vector<Object>) params.get(key));
		}
		return item;
	}

	public void write(File file) throws IOException{
//		FileWriter writer = null;
		FileWriterWithEncoding writer = null;
		try {
//			writer = new FileWriter(file);
			if (request != null){
				if (!file.getParentFile().exists()){
					if (!file.getParentFile().mkdirs()){
						throw new FileNotFoundException("Riscontrato un problema nella creazione della cartella ["+file.getParentFile().getAbsolutePath()+"]");
					}
				}
				writer = new FileWriterWithEncoding(file, "UTF-8");
				request.writeXML(writer);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (writer != null){
					writer.flush();
					writer.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		
	}

	public void send(IMDConfiguration<?> configuration) 
			throws IOException, SolrWarning
	{
		File file = null;
		
		try {
			try{
				file = new File(
						configuration.getSoftwareConfigString("solrIndex.tmpPath")+
						//Configuration.getValue("demoni.SolrIndex.tmpPath")+
						File.separator+
						fileName+".xml");
				write(file);
			} catch (IOException e) {
				throw e;
			} catch (MDConfigurationException e) {
				throw new SolrWarning(e.getMessage(), e);
			}
			RSync.send(configuration.getSoftwareConfigString("rSync.path"),
//						Configuration.getValue("md.sendRsync.path"), 
					configuration.getSoftwareConfigString("solrIndex.rSyncPassword"),
//					Configuration.getValue("demone.SolrIndex.rSyncPassword"), 
					RSync.checkPath(file), 
					configuration.getSoftwareConfigString("solrIndex.rSync"));
//					Configuration.getValue("demoni.SolrIndex.rSync"));
		} catch (RSyncException e) {
			throw new SolrWarning(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			throw new SolrWarning(e.getMessage(), e);
		} finally {
			if (file != null){
				file.delete();
			}
		}
	}
}
