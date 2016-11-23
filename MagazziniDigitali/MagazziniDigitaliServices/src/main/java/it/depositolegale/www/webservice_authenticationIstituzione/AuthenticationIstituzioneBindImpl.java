/**
 * AuthenticationIstituzioneBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationIstituzione;

import it.bncf.magazziniDigitali.services.implement.istituzioni.AuthenticationIstituzione;

public class AuthenticationIstituzioneBindImpl implements it.depositolegale.www.webservice_authenticationIstituzione.AuthenticationIstituzionePortType{
    public it.depositolegale.www.istituzione.MdIstituzione authenticationIstituzioneOperation(it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationIstituzioneInput) throws java.rmi.RemoteException {
        return AuthenticationIstituzione.AuthenticationSoftwareOperation(authenticationIstituzioneInput);
    }

}
