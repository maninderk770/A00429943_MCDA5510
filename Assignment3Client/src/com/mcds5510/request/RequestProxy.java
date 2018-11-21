package com.mcds5510.request;

public class RequestProxy implements com.mcds5510.request.Request {
  private String _endpoint = null;
  private com.mcds5510.request.Request request = null;
  
  public RequestProxy() {
    _initRequestProxy();
  }
  
  public RequestProxy(String endpoint) {
    _endpoint = endpoint;
    _initRequestProxy();
  }
  
  private void _initRequestProxy() {
    try {
      request = (new com.mcds5510.request.RequestServiceLocator()).getRequest();
      if (request != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)request)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)request)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (request != null)
      ((javax.xml.rpc.Stub)request)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.mcds5510.request.Request getRequest() {
    if (request == null)
      _initRequestProxy();
    return request;
  }
  
  public java.lang.String update(java.lang.String id, java.lang.String nameOnCard, java.lang.String cardNumber, java.lang.String unitPrice, java.lang.String quantity, java.lang.String expDate) throws java.rmi.RemoteException{
    if (request == null)
      _initRequestProxy();
    return request.update(id, nameOnCard, cardNumber, unitPrice, quantity, expDate);
  }
  
  public java.lang.String delete(java.lang.String id) throws java.rmi.RemoteException{
    if (request == null)
      _initRequestProxy();
    return request.delete(id);
  }
  
  public java.lang.String create(java.lang.String id, java.lang.String nameOnCard, java.lang.String cardNumber, java.lang.String unitPrice, java.lang.String quantity, java.lang.String expDate) throws java.rmi.RemoteException{
    if (request == null)
      _initRequestProxy();
    return request.create(id, nameOnCard, cardNumber, unitPrice, quantity, expDate);
  }
  
  public java.lang.String view(java.lang.String id) throws java.rmi.RemoteException{
    if (request == null)
      _initRequestProxy();
    return request.view(id);
  }
  
  
}