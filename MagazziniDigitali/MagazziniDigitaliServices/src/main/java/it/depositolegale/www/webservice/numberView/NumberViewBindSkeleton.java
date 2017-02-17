/**
 * NumberViewBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice.numberView;

public class NumberViewBindSkeleton implements it.depositolegale.www.webservice.numberView.NumberViewPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice.numberView.NumberViewPortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/numberView", "numberView"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/numberView", ">numberView"), it.depositolegale.www.numberView.NumberView.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("numberViewOperation", _params, new javax.xml.namespace.QName("", "NumberViewOutput"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "NumberViewOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("numberViewOperation") == null) {
            _myOperations.put("numberViewOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("numberViewOperation")).add(_oper);
    }

    public NumberViewBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice.numberView.NumberViewBindImpl();
    }

    public NumberViewBindSkeleton(it.depositolegale.www.webservice.numberView.NumberViewPortType impl) {
        this.impl = impl;
    }
    public java.math.BigInteger numberViewOperation(it.depositolegale.www.numberView.NumberView numberViewInput) throws java.rmi.RemoteException
    {
        java.math.BigInteger ret = impl.numberViewOperation(numberViewInput);
        return ret;
    }

}
