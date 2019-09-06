/**
 * InterestServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mahendra.services;

public interface InterestServiceService extends javax.xml.rpc.Service {
    public java.lang.String getInterestServiceAddress();

    public com.mahendra.services.InterestService getInterestService() throws javax.xml.rpc.ServiceException;

    public com.mahendra.services.InterestService getInterestService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
