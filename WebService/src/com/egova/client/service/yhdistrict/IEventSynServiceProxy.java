package com.egova.client.service.yhdistrict;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.3
 * 2016-09-13T14:34:59.674+08:00
 * Generated source version: 2.4.3
 * 
 */
@WebService(targetNamespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", name = "IEventSynServiceProxy")
@XmlSeeAlso({ObjectFactory.class})
public interface IEventSynServiceProxy {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sspProcess", targetNamespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", className = "com.egova.client.service.yhdistrict.SspProcess")
    @WebMethod
    @ResponseWrapper(localName = "sspProcessResponse", targetNamespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", className = "com.egova.client.service.yhdistrict.SspProcessResponse")
    public java.lang.String sspProcess(
        @WebParam(name = "requestXML", targetNamespace = "")
        java.lang.String requestXML
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "process", targetNamespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", className = "com.egova.client.service.yhdistrict.Process")
    @WebMethod
    @ResponseWrapper(localName = "processResponse", targetNamespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", className = "com.egova.client.service.yhdistrict.ProcessResponse")
    public java.lang.String process(
        @WebParam(name = "requestXML", targetNamespace = "")
        java.lang.String requestXML
    );
}
