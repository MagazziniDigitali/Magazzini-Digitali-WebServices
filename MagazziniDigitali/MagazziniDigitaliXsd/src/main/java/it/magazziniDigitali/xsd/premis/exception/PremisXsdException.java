/**
 * 
 */
package it.magazziniDigitali.xsd.premis.exception;

/**
 * @author massi
 *
 */
public class PremisXsdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2400854152217659962L;

	/**
	 * @param message
	 */
	public PremisXsdException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PremisXsdException(String message, Throwable cause) {
		super(message, cause);
	}

}
