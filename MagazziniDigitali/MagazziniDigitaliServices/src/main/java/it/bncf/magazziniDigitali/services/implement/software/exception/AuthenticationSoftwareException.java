/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.software.exception;

/**
 * @author massi
 *
 */
public class AuthenticationSoftwareException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5898553003632344616L;

	/**
	 * @param message
	 */
	public AuthenticationSoftwareException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AuthenticationSoftwareException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthenticationSoftwareException(String message, Throwable cause) {
		super(message, cause);
	}

}
