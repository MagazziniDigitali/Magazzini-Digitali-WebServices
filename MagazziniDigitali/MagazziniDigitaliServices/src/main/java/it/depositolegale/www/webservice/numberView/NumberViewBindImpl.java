/**
 * NumberViewBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice.numberView;

import it.bncf.magazziniDigitali.services.implement.numberView.NumberViewImpl;

public class NumberViewBindImpl implements it.depositolegale.www.webservice.numberView.NumberViewPortType{
    public java.math.BigInteger numberViewOperation(it.depositolegale.www.numberView.NumberView numberViewInput) throws java.rmi.RemoteException {
        return NumberViewImpl.numberView(numberViewInput);
    }

}
