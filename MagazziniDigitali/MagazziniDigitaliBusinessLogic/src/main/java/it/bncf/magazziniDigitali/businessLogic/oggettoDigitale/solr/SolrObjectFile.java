package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.util.List;
import java.util.Vector;

import org.purl.dc.elements._1.SimpleLiteral;

public class SolrObjectFile {
	protected String filename = null;
	protected Params params;
	protected String objectIdentifier = null;

	public SolrObjectFile() {
	}
	
	protected void read(String key, List<SimpleLiteral> values){
		List<String> sValues = null;
		if (values != null){
			for(int x=0; x<values.size(); x++){
				sValues = ((SimpleLiteral)values.get(x)).getContent();
				for(int y=0; y<sValues.size(); y++){
					params.add(key, sValues.get(y));
				}
			}
		}
	}

	protected void read(String key, String value) {
		if (value != null){
			params.add(key, value);
		}
	}

	protected void read(String key, Vector<String> values){
		if (values != null){
			for(int x=0; x<values.size(); x++){
				params.add(key, (String)values.get(x));
			}
		}
	}

}
