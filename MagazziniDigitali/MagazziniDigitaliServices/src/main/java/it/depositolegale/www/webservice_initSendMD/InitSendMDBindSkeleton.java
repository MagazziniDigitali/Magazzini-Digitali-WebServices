/**
 * InitSendMDBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_initSendMD;

public class InitSendMDBindSkeleton implements it.depositolegale.www.webservice_initSendMD.InitSendMDPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_initSendMD.InitSendMDPortType impl;
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
        _oper = new org.apache.axis.description.OperationDesc("initSendMDOperation", _params, new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "readInfoOutput"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", ">readInfoOutput"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "InitSendMDOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("initSendMDOperation") == null) {
            _myOperations.put("initSendMDOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("initSendMDOperation")).add(_oper);
    }

    public InitSendMDBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_initSendMD.InitSendMDBindImpl();
    }

    public InitSendMDBindSkeleton(it.depositolegale.www.webservice_initSendMD.InitSendMDPortType impl) {
        this.impl = impl;
    }
    public it.depositolegale.www.readInfoOutput.ReadInfoOutput initSendMDOperation(it.depositolegale.www.readInfoInput.ReadInfoInput initSendMDInput) throws java.rmi.RemoteException
    {
        it.depositolegale.www.readInfoOutput.ReadInfoOutput ret = impl.initSendMDOperation(initSendMDInput);
        return ret;
    }

}
