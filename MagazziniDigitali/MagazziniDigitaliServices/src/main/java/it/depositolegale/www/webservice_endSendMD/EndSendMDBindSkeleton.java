/**
 * EndSendMDBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_endSendMD;

public class EndSendMDBindSkeleton implements it.depositolegale.www.webservice_endSendMD.EndSendMDPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_endSendMD.EndSendMDPortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/endSend", "endSend"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/endSend", ">endSend"), it.depositolegale.www.endSend.EndSend.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("endSendMDOperation", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("", "EndSendMDOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("endSendMDOperation") == null) {
            _myOperations.put("endSendMDOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("endSendMDOperation")).add(_oper);
    }

    public EndSendMDBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_endSendMD.EndSendMDBindImpl();
    }

    public EndSendMDBindSkeleton(it.depositolegale.www.webservice_endSendMD.EndSendMDPortType impl) {
        this.impl = impl;
    }
    public void endSendMDOperation(it.depositolegale.www.endSend.EndSend endSendMDInput) throws java.rmi.RemoteException
    {
        impl.endSendMDOperation(endSendMDInput);
    }

}
