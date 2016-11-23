/**
 * AuthenticationSoftwareBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationSoftware;

import it.bncf.magazziniDigitali.services.implement.software.AuthenticationSoftware;

public class AuthenticationSoftwareBindImpl implements it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwarePortType{
    public it.depositolegale.www.software.Software authenticationSoftwareOperation(it.depositolegale.www.login.Authentication authenticationSoftwareInput) throws java.rmi.RemoteException {
		return AuthenticationSoftware.AuthenticationSoftwareOperation(authenticationSoftwareInput);
    }

}
