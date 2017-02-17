/**
 * NumberViewService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice.numberView;

public interface NumberViewService extends javax.xml.rpc.Service {
    public java.lang.String getNumberViewPortAddress();

    public it.depositolegale.www.webservice.numberView.NumberViewPortType getNumberViewPort() throws javax.xml.rpc.ServiceException;

    public it.depositolegale.www.webservice.numberView.NumberViewPortType getNumberViewPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
