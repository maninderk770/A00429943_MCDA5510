/**
 * Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mcds5510.request;

public interface Request extends java.rmi.Remote {
    public java.lang.String update(java.lang.String id, java.lang.String nameOnCard, java.lang.String cardNumber, java.lang.String unitPrice, java.lang.String quantity, java.lang.String expDate) throws java.rmi.RemoteException;
    public java.lang.String delete(java.lang.String id) throws java.rmi.RemoteException;
    public java.lang.String create(java.lang.String id, java.lang.String nameOnCard, java.lang.String cardNumber, java.lang.String unitPrice, java.lang.String quantity, java.lang.String expDate) throws java.rmi.RemoteException;
    public java.lang.String view(java.lang.String id) throws java.rmi.RemoteException;
}
