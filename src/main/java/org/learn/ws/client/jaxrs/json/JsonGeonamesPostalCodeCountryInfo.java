package org.learn.ws.client.jaxrs.json;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import org.learn.ws.client.jaxrs.JaxRSWebService;
import org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types.GeonamesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;

/**
 * Created by laurenra on 2/3/16.
 */
public class JsonGeonamesPostalCodeCountryInfo extends JaxRSWebService {

    private static final Logger LOG = LoggerFactory.getLogger(JsonGeonamesPostalCodeCountryInfo.class);

    private static final String url = "http://api.geonames.org/postalCodeCountryInfoJSON?formatted=true&username=laurenseven&style=full";


    public String simpleGetAsString() {
        return httpGetJson(url);
    }


    public GeonamesType simpleGetAndMapToObject() {
        return httpGetJsonAndMapToObject(url, GeonamesType.class);
    }


    public String getAsString() {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        /* To log Jersey request and response, you must bridge java.util.logging
         * with SLF4JBridgeHandler in resources/logging.properties and include
         * the jul-to-slf4j dependency.
         */
        if (LOG.isDebugEnabled()) {
            client.addFilter(new LoggingFilter(java.util.logging.Logger.getGlobal()));
        }

        WebResource resource = client.resource(url);

        try {
            ClientResponse response = resource
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .get(ClientResponse.class);

            return response.getEntity(String.class);
        }
        catch (Exception e) {
            LOG.error("Exception reported: " + e.getMessage());
            LOG.error("Trace: " + e.getStackTrace().toString());
            return null;
        }

    }


    public GeonamesType getAsObject() {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        /* To log Jersey request and response, you must bridge java.util.logging
         * with SLF4JBridgeHandler in resources/logging.properties and include
         * the jul-to-slf4j dependency.
         */
        if (LOG.isDebugEnabled()) {
            client.addFilter(new LoggingFilter(java.util.logging.Logger.getGlobal()));
        }

        WebResource resource = client.resource(url);

        try {
            ClientResponse response = resource
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .get(ClientResponse.class);

            return response.getEntity(GeonamesType.class);
        }
        catch (Exception e) {
            LOG.error("Exception reported: " + e.getMessage());
            LOG.error("Trace: " + e.getStackTrace().toString());
            return null;
        }

    }


    public GeonamesType getAsObjectSimpler() {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        /* To log Jersey request and response, you must bridge java.util.logging
         * with SLF4JBridgeHandler in resources/logging.properties and include
         * the jul-to-slf4j dependency.
         */
        if (LOG.isDebugEnabled()) {
            client.addFilter(new LoggingFilter(java.util.logging.Logger.getGlobal()));
        }

        WebResource resource = client.resource(url);

        try {
            return (GeonamesType) resource
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .get(GeonamesType.class);
        }
        catch (Exception e) {
            LOG.error("Exception reported: " + e.getMessage());
            LOG.error("Trace: " + e.getStackTrace().toString());
            return null;
        }

    }

}
