/**
 * 
 */
package it.bncf.magazziniDigitali.solr.exception;

/**
 * @author massi
 *
 */
public class SolrWarning extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3824883682299297622L;

	/**
	 * @param arg0
	 */
	public SolrWarning(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SolrWarning(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
