/**
 * CheckStorageMDBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkStorageMD;

import java.rmi.RemoteException;

import it.bncf.magazziniDigitali.services.implement.CheckStorageMDImpl;
import it.depositolegale.www.storage.Storage;

public class CheckStorageMDBindImpl implements it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortType{
    public it.depositolegale.www.storage.Storage checkStorageMDOperation(it.depositolegale.www.storage.Documenti checkStorageMDInput) throws java.rmi.RemoteException {
    	Storage output = null;

    	try{
	    	output = CheckStorageMDImpl.checkStorageMDOperation(checkStorageMDInput);
    	} catch(RemoteException e){
    		e.printStackTrace();
    		throw e;
    	}
        return output;
    }

}
