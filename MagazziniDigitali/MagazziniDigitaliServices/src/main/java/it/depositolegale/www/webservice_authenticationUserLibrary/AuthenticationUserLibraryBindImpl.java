/**
 * AuthenticationUserLibraryBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationUserLibrary;

import it.bncf.magazziniDigitali.services.implement.userLibrary.AuthenticationUserLibrary;

public class AuthenticationUserLibraryBindImpl implements it.depositolegale.www.webservice_authenticationUserLibrary.AuthenticationUserLibraryPortType{
    public it.depositolegale.www.authenticationUserOutput.AuthenticationUserOutput authenticationUserLibraryOperation(it.depositolegale.www.authenticationUserInput.AuthenticationUserInput authenticationUserLibraryInput) throws java.rmi.RemoteException {
        return AuthenticationUserLibrary.AuthenticationUserLibraryOperation(authenticationUserLibraryInput);
    }

}
