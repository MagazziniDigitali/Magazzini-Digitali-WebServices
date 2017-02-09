/**
 * CheckTicketBindImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkTicket;

import it.bncf.magazziniDigitali.services.implement.checkTicket.CheckTicketImpl;

public class CheckTicketBindImpl implements it.depositolegale.www.webservice_checkTicket.CheckTicketPortType{
    public it.depositolegale.www.checkTicketOutput.CheckTicketOutput checkTicketOperation(it.depositolegale.www.checkTicket.CheckTicket checkTicketInput) throws java.rmi.RemoteException {
        return CheckTicketImpl.checkTicket(checkTicketInput);
    }

}
