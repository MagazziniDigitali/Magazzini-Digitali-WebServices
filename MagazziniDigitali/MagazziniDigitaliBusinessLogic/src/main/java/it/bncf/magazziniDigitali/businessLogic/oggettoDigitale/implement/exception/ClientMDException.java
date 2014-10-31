package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.exception;

/**
 * Classe utilizzata per la gestione degli eventuali errori relativi all'invio del materiale a MD
 * 
 * @author massi
 *
 */
public class ClientMDException extends Exception {

	/**
	 * Numero relativo al Serial Version UID
	 */
	private static final long serialVersionUID = 4811582135913992838L;

	/**
	 * Costruttore
	 * @param arg0 Messaggio di Errore
	 */
	public ClientMDException(String arg0) {
		super(arg0);
	}

	/**
	 * Costruttore
	 * 
	 * @param arg0 Messaggio di Errore
	 * @param arg1 Messaggio Padre
	 */
	public ClientMDException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
