
package org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for geonamesType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="geonamesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="country" type="{}countryType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "geonamesType", propOrder = {
        "country"
})
public class PartialGeonamesType {

    @JsonProperty(value = "geonames")
    protected List<PartialCountryType> country;

    /**
     * Gets the value of the country property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the country property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountry().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountryType }
     *
     *
     */
    public List<PartialCountryType> getCountry() {
        if (country == null) {
            country = new ArrayList<PartialCountryType>();
        }
        return this.country;
    }

    @Override
    public String toString() {

//        return "GeonamesType{" +
//                "country=" + country +
//                '}';

        String str = "GeonamesType{";

        for(PartialCountryType item : country) {
            str = str + "\n    " + item.toString();
        }
        str = str + "\n}";
        return str;
    }
}
