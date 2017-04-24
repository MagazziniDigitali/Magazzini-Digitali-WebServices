/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl;

import java.util.Vector;

/**
 * @author massi
 *
 */
public class ReaderTtlPerson extends ReaderTtlAnalyze{

	private String familyName = null;

	private String givenName = null;

	private String name = null;

	/**
	 * 
	 */
	public ReaderTtlPerson() {
	}
	
	public void analyze (String id , Vector<String> lines) {
		this.id = id;
		for (int x=0; x<lines.size(); x++){
			if (lines.get(x).startsWith("foaf:familyName")){
				familyName = analyze(lines.get(x));
			} else if (lines.get(x).startsWith("foaf:givenName")){
				givenName = analyze(lines.get(x));
			} else if (lines.get(x).startsWith("foaf:name")){
				name = analyze(lines.get(x));
			}
		}
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
