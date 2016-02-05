package org.learn.ws.client.jaxrs;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.net.URI;

/**
 * Created by laurenra on 1/29/16.
 */
public abstract class JaxRSWebService {

    private static final Logger LOG = LoggerFactory.getLogger(JaxRSWebService.class);

    public String httpGetJson(URI uri) {
        return httpGet(uri, MediaType.APPLICATION_JSON_TYPE);
    }

    public String httpGetJson(String url) {
        return httpGet(url, MediaType.APPLICATION_JSON_TYPE);
    }

    public <T> T httpGetJsonAndMapToObject(String url, Class<T> mapToClass) {
        return httpGetAndMapToObject(url, MediaType.APPLICATION_JSON_TYPE, mapToClass);
    }

    public String httpGetXml(URI uri) {
        return httpGet(uri, MediaType.APPLICATION_XML_TYPE);
    }

    public String httpGetXml(String url) {
        return httpGet(url, MediaType.APPLICATION_XML_TYPE);
    }

    public <T> T httpGetXmlAndMapToObject(String url, Class<T> mapToClass) {
        return httpGetAndMapToObject(url, MediaType.APPLICATION_XML_TYPE, mapToClass);
    }

    public String httpGet(URI uri, MediaType mediaType) {
        return httpGet(uri.toString(), mediaType);
    }


    public String httpGet(String url, MediaType mediaType) {

        Client client = Client.create();

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
                    .accept(mediaType)
                    .type(mediaType)
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                logError(response, LOG);
                return null;
            }
            else {
                return response.getEntity(String.class);
            }
        }
        catch (Exception e) {
            LOG.error("Exception reported: " + e.getMessage());
            LOG.error("Trace: " + e.getStackTrace().toString());
            return null;
        }
    }


    public <T> T httpGetAndMapToObject(String url, MediaType mediaType, Class<T> mapToClass) {

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
                    .accept(mediaType)
                    .type(mediaType)
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                logError(response, LOG);
                return null;
            }
            else {
                return (T) response.getEntity(mapToClass);
            }
        }
        catch (Exception e) {
            LOG.error("Exception reported: " + e.getMessage());
            LOG.error("Trace: " + e.getStackTrace().toString());
            return null;
        }
    }


    public void logError(ClientResponse response, Logger log) {
        log.error("Failed with HTTP error " + response.getStatus());
        log.error("  header: " + response.getHeaders());
        log.error("   (raw): " + response.getEntity(String.class));
    }
}
