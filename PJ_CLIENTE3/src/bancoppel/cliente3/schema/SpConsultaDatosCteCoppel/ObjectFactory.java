//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.10.04 at 12:53:29 PM CDT 
//


package bancoppel.cliente3.schema.SpConsultaDatosCteCoppel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bancoppel.cliente3.schema.SpConsultaDatosCteCoppel package. 
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

    private final static QName _SpConsultaDatosCteCoppelResponse_QNAME = new QName("http://www.bancoppel.com/Cliente3/SpConsultaDatosCteCoppel/", "SpConsultaDatosCteCoppelResponse");
    private final static QName _SpConsultaDatosCteCoppel_QNAME = new QName("http://www.bancoppel.com/Cliente3/SpConsultaDatosCteCoppel/", "SpConsultaDatosCteCoppel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bancoppel.cliente3.schema.SpConsultaDatosCteCoppel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SpConsultaDatosCteCoppelType }
     * 
     */
    public SpConsultaDatosCteCoppelType createSpConsultaDatosCteCoppelType() {
        return new SpConsultaDatosCteCoppelType();
    }

    /**
     * Create an instance of {@link SpConsultaDatosCteCoppelResponseType }
     * 
     */
    public SpConsultaDatosCteCoppelResponseType createSpConsultaDatosCteCoppelResponseType() {
        return new SpConsultaDatosCteCoppelResponseType();
    }

    /**
     * Create an instance of {@link SpConsultaDatosCteCoppelRegistroType }
     * 
     */
    public SpConsultaDatosCteCoppelRegistroType createSpConsultaDatosCteCoppelRegistroType() {
        return new SpConsultaDatosCteCoppelRegistroType();
    }

    /**
     * Create an instance of {@link CabeceraType }
     * 
     */
    public CabeceraType createCabeceraType() {
        return new CabeceraType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpConsultaDatosCteCoppelResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bancoppel.com/Cliente3/SpConsultaDatosCteCoppel/", name = "SpConsultaDatosCteCoppelResponse")
    public JAXBElement<SpConsultaDatosCteCoppelResponseType> createSpConsultaDatosCteCoppelResponse(SpConsultaDatosCteCoppelResponseType value) {
        return new JAXBElement<SpConsultaDatosCteCoppelResponseType>(_SpConsultaDatosCteCoppelResponse_QNAME, SpConsultaDatosCteCoppelResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpConsultaDatosCteCoppelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bancoppel.com/Cliente3/SpConsultaDatosCteCoppel/", name = "SpConsultaDatosCteCoppel")
    public JAXBElement<SpConsultaDatosCteCoppelType> createSpConsultaDatosCteCoppel(SpConsultaDatosCteCoppelType value) {
        return new JAXBElement<SpConsultaDatosCteCoppelType>(_SpConsultaDatosCteCoppel_QNAME, SpConsultaDatosCteCoppelType.class, null, value);
    }

}
