package org.learn.ws.client.jaxrs.xml;

import org.learn.ws.client.jaxrs.JaxRSWebService;
import org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types.GeonamesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by laurenra on 2/3/16.
 */
public class XmlGeonamesPostalCodeCountryInfo extends JaxRSWebService {

    private static final Logger LOG = LoggerFactory.getLogger(XmlGeonamesPostalCodeCountryInfo.class);

    private static final String url = "http://api.geonames.org/postalCodeCountryInfo?&username=laurenseven";


    public String simpleGetAsString() {
        return httpGetXml(url);
    }


    public GeonamesType simpleGetAndMapToObject() {
        return httpGetXmlAndMapToObject(url, GeonamesType.class);
    }


}
