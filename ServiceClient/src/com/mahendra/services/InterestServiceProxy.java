package com.mahendra.services;

public class InterestServiceProxy implements com.mahendra.services.InterestService {
  private String _endpoint = null;
  private com.mahendra.services.InterestService interestService = null;
  
  public InterestServiceProxy() {
    _initInterestServiceProxy();
  }
  
  public InterestServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initInterestServiceProxy();
  }
  
  private void _initInterestServiceProxy() {
    try {
      interestService = (new com.mahendra.services.InterestServiceServiceLocator()).getInterestService();
      if (interestService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)interestService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)interestService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (interestService != null)
      ((javax.xml.rpc.Stub)interestService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.mahendra.services.InterestService getInterestService() {
    if (interestService == null)
      _initInterestServiceProxy();
    return interestService;
  }
  
  public double calc(double principal, double rate, int duration) throws java.rmi.RemoteException{
    if (interestService == null)
      _initInterestServiceProxy();
    return interestService.calc(principal, rate, duration);
  }
  
  
}