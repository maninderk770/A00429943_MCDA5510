/**
 * RequestServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcds5510.request;

public class RequestServiceLocator extends org.apache.axis.client.Service implements com.mcds5510.request.RequestService {

    public RequestServiceLocator() {
    }


    public RequestServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RequestServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Request
    private java.lang.String Request_address = "http://localhost:8080/Assignment3_A00429943/services/Request";

    public java.lang.String getRequestAddress() {
        return Request_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RequestWSDDServiceName = "Request";

    public java.lang.String getRequestWSDDServiceName() {
        return RequestWSDDServiceName;
    }

    public void setRequestWSDDServiceName(java.lang.String name) {
        RequestWSDDServiceName = name;
    }

    public com.mcds5510.request.Request getRequest() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Request_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRequest(endpoint);
    }

    public com.mcds5510.request.Request getRequest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.mcds5510.request.RequestSoapBindingStub _stub = new com.mcds5510.request.RequestSoapBindingStub(portAddress, this);
            _stub.setPortName(getRequestWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRequestEndpointAddress(java.lang.String address) {
        Request_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.mcds5510.request.Request.class.isAssignableFrom(serviceEndpointInterface)) {
                com.mcds5510.request.RequestSoapBindingStub _stub = new com.mcds5510.request.RequestSoapBindingStub(new java.net.URL(Request_address), this);
                _stub.setPortName(getRequestWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Request".equals(inputPortName)) {
            return getRequest();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://request.mcds5510.com", "RequestService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://request.mcds5510.com", "Request"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Request".equals(portName)) {
            setRequestEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
