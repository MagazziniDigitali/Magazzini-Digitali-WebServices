/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl;

import java.util.Vector;

/**
 * @author massi
 *
 */
public abstract class ReaderTtlAnalyze {

	protected String id = null;

	/**
	 * 
	 */
	public ReaderTtlAnalyze() {
		// TODO Auto-generated constructor stub
	}

	public abstract void analyze (String id , Vector<String> lines);

	protected String analyze(String line){
		int pos = 0;
		
		pos = line.indexOf("\"");
		if (pos>-1){
			line = line.substring(pos+1);
			pos = line.lastIndexOf("\"");
			line = line.substring(0,pos);
		} else {
			pos = line.indexOf("<");
			line = line.substring(pos);
			pos = line.lastIndexOf(">");
			line = line.substring(0,pos+1);
		}
		return line;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
}
