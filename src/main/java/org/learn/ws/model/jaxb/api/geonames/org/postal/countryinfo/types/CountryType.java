
package org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for countryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="countryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numPostalCodes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="minPostalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maxPostalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "countryType", propOrder = {
    "countryCode",
    "countryName",
    "numPostalCodes",
    "minPostalCode",
    "maxPostalCode"
})
public class CountryType {

    @XmlElement(required = true)
    protected String countryCode;
    @XmlElement(required = true)
    protected String countryName;
    @XmlElement(required = true)
    protected String numPostalCodes;
    @XmlElement(required = true)
    protected String minPostalCode;
    @XmlElement(required = true)
    protected String maxPostalCode;

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
    }

    /**
     * Gets the value of the numPostalCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumPostalCodes() {
        return numPostalCodes;
    }

    /**
     * Sets the value of the numPostalCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumPostalCodes(String value) {
        this.numPostalCodes = value;
    }

    /**
     * Gets the value of the minPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinPostalCode() {
        return minPostalCode;
    }

    /**
     * Sets the value of the minPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinPostalCode(String value) {
        this.minPostalCode = value;
    }

    /**
     * Gets the value of the maxPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxPostalCode() {
        return maxPostalCode;
    }

    /**
     * Sets the value of the maxPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxPostalCode(String value) {
        this.maxPostalCode = value;
    }

    @Override
    public String toString() {
        return "CountryType{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", numPostalCodes='" + numPostalCodes + '\'' +
                ", minPostalCode='" + minPostalCode + '\'' +
                ", maxPostalCode='" + maxPostalCode + '\'' +
                '}';
    }
}
