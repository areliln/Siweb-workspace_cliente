//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.10.04 at 12:53:29 PM CDT 
//


package bancoppel.cliente3.schema.TarjCop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CabeceraType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CabeceraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTrxGlobal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sistemaOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CabeceraType", namespace = "http://www.bancoppel.com/Logger", propOrder = {
    "idTrxGlobal",
    "sistemaOrigen"
})
public class CabeceraType {

    @XmlElement(required = true)
    protected String idTrxGlobal;
    @XmlElement(required = true)
    protected String sistemaOrigen;

    /**
     * Gets the value of the idTrxGlobal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTrxGlobal() {
        return idTrxGlobal;
    }

    /**
     * Sets the value of the idTrxGlobal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTrxGlobal(String value) {
        this.idTrxGlobal = value;
    }

    /**
     * Gets the value of the sistemaOrigen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemaOrigen() {
        return sistemaOrigen;
    }

    /**
     * Sets the value of the sistemaOrigen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemaOrigen(String value) {
        this.sistemaOrigen = value;
    }

}
