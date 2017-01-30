/**
 * WriteEventNBNBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_writeEventNBN;

public class WriteEventNBNBindSkeleton implements it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNPortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBN", "writeEventNBN"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBN", ">writeEventNBN"), it.depositolegale.www.writeEventNBN.WriteEventNBN.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("writeEventNBNOperation", _params, new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBN", "writeEventNBN"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBN", ">writeEventNBN"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "WriteEventNBNOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("writeEventNBNOperation") == null) {
            _myOperations.put("writeEventNBNOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("writeEventNBNOperation")).add(_oper);
    }

    public WriteEventNBNBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNBindImpl();
    }

    public WriteEventNBNBindSkeleton(it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNPortType impl) {
        this.impl = impl;
    }
    public it.depositolegale.www.writeEventNBN.WriteEventNBN writeEventNBNOperation(it.depositolegale.www.writeEventNBN.WriteEventNBN writeEventNBNInput) throws java.rmi.RemoteException
    {
        it.depositolegale.www.writeEventNBN.WriteEventNBN ret = impl.writeEventNBNOperation(writeEventNBNInput);
        return ret;
    }

}
