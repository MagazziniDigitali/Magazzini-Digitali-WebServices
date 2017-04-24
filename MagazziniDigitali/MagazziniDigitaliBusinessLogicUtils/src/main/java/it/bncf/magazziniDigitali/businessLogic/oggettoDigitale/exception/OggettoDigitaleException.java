/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.exception;

/**
 * @author massi
 *
 */
public class OggettoDigitaleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3436413040424701284L;

	/**
	 * @param message
	 */
	public OggettoDigitaleException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OggettoDigitaleException(String message, Throwable cause) {
		super(message, cause);
	}

}
