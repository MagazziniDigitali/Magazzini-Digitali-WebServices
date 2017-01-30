/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.exception;

/**
 * @author massi
 *
 */
public class ReaderTtlException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7772428079827888107L;

	/**
	 * @param message
	 */
	public ReaderTtlException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ReaderTtlException(String message, Throwable cause) {
		super(message, cause);
	}

}
