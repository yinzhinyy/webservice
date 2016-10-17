
package com.egova.client.service.yhdistrict;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.egova.client.service.yhdistrict package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SspProcessResponse_QNAME = new QName("http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", "sspProcessResponse");
    private final static QName _SspProcess_QNAME = new QName("http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", "sspProcess");
    private final static QName _Process_QNAME = new QName("http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", "process");
    private final static QName _ProcessResponse_QNAME = new QName("http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", "processResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.egova.client.service.yhdistrict
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Process }
     * 
     */
    public Process createProcess() {
        return new Process();
    }

    /**
     * Create an instance of {@link ProcessResponse }
     * 
     */
    public ProcessResponse createProcessResponse() {
        return new ProcessResponse();
    }

    /**
     * Create an instance of {@link SspProcessResponse }
     * 
     */
    public SspProcessResponse createSspProcessResponse() {
        return new SspProcessResponse();
    }

    /**
     * Create an instance of {@link SspProcess }
     * 
     */
    public SspProcess createSspProcess() {
        return new SspProcess();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SspProcessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", name = "sspProcessResponse")
    public JAXBElement<SspProcessResponse> createSspProcessResponse(SspProcessResponse value) {
        return new JAXBElement<SspProcessResponse>(_SspProcessResponse_QNAME, SspProcessResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SspProcess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", name = "sspProcess")
    public JAXBElement<SspProcess> createSspProcess(SspProcess value) {
        return new JAXBElement<SspProcess>(_SspProcess_QNAME, SspProcess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Process }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", name = "process")
    public JAXBElement<Process> createProcess(Process value) {
        return new JAXBElement<Process>(_Process_QNAME, Process.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sjzt.iexchange.proxy.syn.digitalcity.kingdom.com/", name = "processResponse")
    public JAXBElement<ProcessResponse> createProcessResponse(ProcessResponse value) {
        return new JAXBElement<ProcessResponse>(_ProcessResponse_QNAME, ProcessResponse.class, null, value);
    }

}
