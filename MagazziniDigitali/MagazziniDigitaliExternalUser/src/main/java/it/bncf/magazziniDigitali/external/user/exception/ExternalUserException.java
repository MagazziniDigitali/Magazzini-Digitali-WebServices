/**
 * 
 */
package it.bncf.magazziniDigitali.external.user.exception;

/**
 * @author massi
 *
 */
public class ExternalUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1198456368779999806L;

	/**
	 * @param message
	 */
	public ExternalUserException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ExternalUserException(String message, Throwable cause) {
		super(message, cause);
	}
}
