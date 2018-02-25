/**
 * 
 */
package it.bncf.magazziniDigitali.nodi.exception;

/**
 * @author massi
 *
 */
public class NodiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6881053300269690493L;

	/**
	 * @param message
	 */
	public NodiException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NodiException(String message, Throwable cause) {
		super(message, cause);
	}

}
