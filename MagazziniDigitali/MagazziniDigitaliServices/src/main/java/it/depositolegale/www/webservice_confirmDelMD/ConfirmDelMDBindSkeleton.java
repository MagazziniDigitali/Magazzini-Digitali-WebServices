/**
 * ConfirmDelMDBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_confirmDelMD;

public class ConfirmDelMDBindSkeleton implements it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDPortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoInput", "readInfoInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoInput", ">readInfoInput"), it.depositolegale.www.readInfoInput.ReadInfoInput.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("confirmDelMDOperation", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("", "ConfirmDelMDOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("confirmDelMDOperation") == null) {
            _myOperations.put("confirmDelMDOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("confirmDelMDOperation")).add(_oper);
    }

    public ConfirmDelMDBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDBindImpl();
    }

    public ConfirmDelMDBindSkeleton(it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDPortType impl) {
        this.impl = impl;
    }
    public void confirmDelMDOperation(it.depositolegale.www.readInfoInput.ReadInfoInput confirmDelMDInput) throws java.rmi.RemoteException
    {
        impl.confirmDelMDOperation(confirmDelMDInput);
    }

}
