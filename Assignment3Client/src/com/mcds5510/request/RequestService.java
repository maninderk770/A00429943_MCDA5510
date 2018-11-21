/**
 * RequestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcds5510.request;

public interface RequestService extends javax.xml.rpc.Service {
    public java.lang.String getRequestAddress();

    public com.mcds5510.request.Request getRequest() throws javax.xml.rpc.ServiceException;

    public com.mcds5510.request.Request getRequest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
