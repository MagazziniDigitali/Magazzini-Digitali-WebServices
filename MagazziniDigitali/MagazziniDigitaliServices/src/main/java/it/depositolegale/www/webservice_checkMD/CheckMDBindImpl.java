/**
 * CheckMDBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkMD;

import java.rmi.RemoteException;

import it.bncf.magazziniDigitali.services.implement.CheckMDImpl;
import it.depositolegale.www.readInfoOutput.ReadInfoOutput;

public class CheckMDBindImpl implements it.depositolegale.www.webservice_checkMD.CheckMDPortType{
    public it.depositolegale.www.readInfoOutput.ReadInfoOutput checkMDOperation(it.depositolegale.www.readInfoInput.ReadInfoInput checkMDInput) throws java.rmi.RemoteException {
    	ReadInfoOutput read = null;

    	try{
    		read = CheckMDImpl.checkMDOperation(checkMDInput);
    	} catch(RemoteException e){
    		e.printStackTrace();
    		throw e;
    	}
        return read;
    }

}
