/**
 * CheckStorageMDBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkStorageMD;

public class CheckStorageMDBindSkeleton implements it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/storage", "documenti"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/storage", ">documenti"), it.depositolegale.www.storage.Documenti.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("checkStorageMDOperation", _params, new javax.xml.namespace.QName("http://www.depositolegale.it/storage", "storage"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.depositolegale.it/storage", ">storage"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "CheckStorageMDOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("checkStorageMDOperation") == null) {
            _myOperations.put("checkStorageMDOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("checkStorageMDOperation")).add(_oper);
    }

    public CheckStorageMDBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDBindImpl();
    }

    public CheckStorageMDBindSkeleton(it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortType impl) {
        this.impl = impl;
    }
    public it.depositolegale.www.storage.Storage checkStorageMDOperation(it.depositolegale.www.storage.Documenti checkStorageMDInput) throws java.rmi.RemoteException
    {
        it.depositolegale.www.storage.Storage ret = impl.checkStorageMDOperation(checkStorageMDInput);
        return ret;
    }

}
