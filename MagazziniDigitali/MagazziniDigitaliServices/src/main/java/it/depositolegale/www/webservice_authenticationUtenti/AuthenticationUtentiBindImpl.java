/**
 * AuthenticationUtentiBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationUtenti;

import it.bncf.magazziniDigitali.services.implement.utenti.AuthenticationUtenti;

public class AuthenticationUtentiBindImpl
		implements it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType {
	public it.depositolegale.www.utenti.Utenti authenticationUtentiOperation(
			it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationUtentiInput)
			throws java.rmi.RemoteException {
		return AuthenticationUtenti.AuthenticationUtentiOperation(authenticationUtentiInput);
	}

}
