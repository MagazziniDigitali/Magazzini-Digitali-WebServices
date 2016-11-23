/**
 * 
 */
package it.bncf.magazziniDigitali.configuration.exception;

/**
 * @author massi
 *
 */
public class MDConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1110150576663558713L;

	/**
	 * @param message
	 */
	public MDConfigurationException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MDConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
