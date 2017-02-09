/**
 * CheckTicketBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkTicket;

public class CheckTicketBindSkeleton implements it.depositolegale.www.webservice_checkTicket.CheckTicketPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_checkTicket.CheckTicketPortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicket", "checkTicket"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicket", ">checkTicket"), it.depositolegale.www.checkTicket.CheckTicket.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("checkTicketOperation", _params, new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicketOutput", "checkTicketOutput"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicketOutput", ">checkTicketOutput"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "CheckTicketOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("checkTicketOperation") == null) {
            _myOperations.put("checkTicketOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("checkTicketOperation")).add(_oper);
    }

    public CheckTicketBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_checkTicket.CheckTicketBindImpl();
    }

    public CheckTicketBindSkeleton(it.depositolegale.www.webservice_checkTicket.CheckTicketPortType impl) {
        this.impl = impl;
    }
    public it.depositolegale.www.checkTicketOutput.CheckTicketOutput checkTicketOperation(it.depositolegale.www.checkTicket.CheckTicket checkTicketInput) throws java.rmi.RemoteException
    {
        it.depositolegale.www.checkTicketOutput.CheckTicketOutput ret = impl.checkTicketOperation(checkTicketInput);
        return ret;
    }

}
