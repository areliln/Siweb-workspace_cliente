//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.08 at 12:13:00 PM GMT-06:00 
//


package bancoppel.cliente3.schema.TarjCop;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TarjCopRegistroType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TarjCopRegistroType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vCodRet1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vTarjCop" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mSolicitado" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TarjCopRegistroType", propOrder = {
    "vCodRet1",
    "vTarjCop",
    "cNombre",
    "mSolicitado"
})
public class TarjCopRegistroType {

    protected String vCodRet1;
    protected String vTarjCop;
    protected String cNombre;
    protected BigDecimal mSolicitado;

    /**
     * Gets the value of the vCodRet1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCodRet1() {
        return vCodRet1;
    }

    /**
     * Sets the value of the vCodRet1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCodRet1(String value) {
        this.vCodRet1 = value;
    }

    /**
     * Gets the value of the vTarjCop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVTarjCop() {
        return vTarjCop;
    }

    /**
     * Sets the value of the vTarjCop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVTarjCop(String value) {
        this.vTarjCop = value;
    }

    /**
     * Gets the value of the cNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNombre() {
        return cNombre;
    }

    /**
     * Sets the value of the cNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNombre(String value) {
        this.cNombre = value;
    }

    /**
     * Gets the value of the mSolicitado property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMSolicitado() {
        return mSolicitado;
    }

    /**
     * Sets the value of the mSolicitado property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMSolicitado(BigDecimal value) {
        this.mSolicitado = value;
    }

}
