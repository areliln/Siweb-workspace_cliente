//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.12.21 at 05:27:11 PM GMT-06:00 
//


package bancoppel.cliente3.schema.SpConsultaVencidoBancoppel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bancoppel.cliente3.schema.SpConsultaVencidoBancoppel package. 
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

    private final static QName _SpConsultaVencidoBancoppelResponse_QNAME = new QName("http://www.bancoppel.com/Cliente3/SpConsultaVencidoBancoppel/", "SpConsultaVencidoBancoppelResponse");
    private final static QName _SpConsultaVencidoBancoppel_QNAME = new QName("http://www.bancoppel.com/Cliente3/SpConsultaVencidoBancoppel/", "SpConsultaVencidoBancoppel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bancoppel.cliente3.schema.SpConsultaVencidoBancoppel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SpConsultaVencidoBancoppelType }
     * 
     */
    public SpConsultaVencidoBancoppelType createSpConsultaVencidoBancoppelType() {
        return new SpConsultaVencidoBancoppelType();
    }

    /**
     * Create an instance of {@link SpConsultaVencidoBancoppelResponseType }
     * 
     */
    public SpConsultaVencidoBancoppelResponseType createSpConsultaVencidoBancoppelResponseType() {
        return new SpConsultaVencidoBancoppelResponseType();
    }

    /**
     * Create an instance of {@link SpConsultaVencidoBancoppelRegistroType }
     * 
     */
    public SpConsultaVencidoBancoppelRegistroType createSpConsultaVencidoBancoppelRegistroType() {
        return new SpConsultaVencidoBancoppelRegistroType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpConsultaVencidoBancoppelResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bancoppel.com/Cliente3/SpConsultaVencidoBancoppel/", name = "SpConsultaVencidoBancoppelResponse")
    public JAXBElement<SpConsultaVencidoBancoppelResponseType> createSpConsultaVencidoBancoppelResponse(SpConsultaVencidoBancoppelResponseType value) {
        return new JAXBElement<SpConsultaVencidoBancoppelResponseType>(_SpConsultaVencidoBancoppelResponse_QNAME, SpConsultaVencidoBancoppelResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpConsultaVencidoBancoppelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bancoppel.com/Cliente3/SpConsultaVencidoBancoppel/", name = "SpConsultaVencidoBancoppel")
    public JAXBElement<SpConsultaVencidoBancoppelType> createSpConsultaVencidoBancoppel(SpConsultaVencidoBancoppelType value) {
        return new JAXBElement<SpConsultaVencidoBancoppelType>(_SpConsultaVencidoBancoppel_QNAME, SpConsultaVencidoBancoppelType.class, null, value);
    }

}
