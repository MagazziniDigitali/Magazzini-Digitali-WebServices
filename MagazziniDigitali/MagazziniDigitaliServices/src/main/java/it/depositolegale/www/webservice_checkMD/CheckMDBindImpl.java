/**
 * CheckMDBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkMD;

import it.bncf.magazziniDigitali.services.implement.CheckMDImpl;

public class CheckMDBindImpl implements it.depositolegale.www.webservice_checkMD.CheckMDPortType{
    public it.depositolegale.www.readInfoOutput.ReadInfoOutput checkMDOperation(it.depositolegale.www.readInfoInput.ReadInfoInput checkMDInput) throws java.rmi.RemoteException {
        return CheckMDImpl.checkMDOperation(checkMDInput);
    }

}
