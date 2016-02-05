
package org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types package. 
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

    private final static QName _Geonames_QNAME = new QName("", "geonames");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GeonamesType }
     * 
     */
    public GeonamesType createGeonamesType() {
        return new GeonamesType();
    }

    /**
     * Create an instance of {@link CountryType }
     * 
     */
    public CountryType createCountryType() {
        return new CountryType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeonamesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "geonames")
    public JAXBElement<GeonamesType> createGeonames(GeonamesType value) {
        return new JAXBElement<GeonamesType>(_Geonames_QNAME, GeonamesType.class, null, value);
    }

}
