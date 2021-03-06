package com.egova.client.service.yhdistrict;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.3
 * 2016-09-13T14:34:59.681+08:00
 * Generated source version: 2.4.3
 * 
 */
@WebServiceClient(name = "IEventSynServiceProxyService", 
                  wsdlLocation = "file:/D:/MyWorks/0901-长沙/WebService/src/IEventSynServiceProxy.WSDL",
                  targetNamespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/") 
public class IEventSynServiceProxyService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", "IEventSynServiceProxyService");
    public final static QName IEventSynServiceProxyPort = new QName("http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", "IEventSynServiceProxyPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/MyWorks/0901-长沙/WebService/src/IEventSynServiceProxy.WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(IEventSynServiceProxyService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/MyWorks/0901-长沙/WebService/src/IEventSynServiceProxy.WSDL");
        }
        WSDL_LOCATION = url;
    }

    public IEventSynServiceProxyService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IEventSynServiceProxyService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IEventSynServiceProxyService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns IEventSynServiceProxy
     */
    @WebEndpoint(name = "IEventSynServiceProxyPort")
    public IEventSynServiceProxy getIEventSynServiceProxyPort() {
        return super.getPort(IEventSynServiceProxyPort, IEventSynServiceProxy.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IEventSynServiceProxy
     */
    @WebEndpoint(name = "IEventSynServiceProxyPort")
    public IEventSynServiceProxy getIEventSynServiceProxyPort(WebServiceFeature... features) {
        return super.getPort(IEventSynServiceProxyPort, IEventSynServiceProxy.class, features);
    }

}
