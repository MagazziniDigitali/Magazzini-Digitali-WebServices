/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.userLibrary;

/**
 * @author massi
 *
 */
public class AuthenticationUserLibraryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4495297142315576154L;

	/**
	 * @param arg0
	 */
	public AuthenticationUserLibraryException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public AuthenticationUserLibraryException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
