package org.learn.ws.client.jaxrs;

import com.sun.jersey.core.provider.CompletableReader;
import com.sun.jersey.spi.MessageBodyWorkers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by laurenra on 2/5/16.
 */
public class EntityMappingTester {

    private static final Logger LOG = LoggerFactory.getLogger(EntityMappingTester.class);

    /**
     * This is copied from the getEntity() method of the ClientResponse class
     * in package com.sun.jersey.api.client and modified to allow using arbitrary
     * input streams, either from the ClientResponse or from, for example,
     * a file. This makes it much easier to test Jersey's mapping of XML or JSON
     * to Java objects to verify that the API and the object classes work right.
     * It maps the stream using the same MessageBodyReader that would be used by
     * the getEntity() method of ClientResponse.
     *
     * Example:
     *
     * import com.sun.jersey.api.client.Client;
     * import com.sun.jersey.api.client.config.ClientConfig;
     * import com.sun.jersey.api.client.config.DefaultClientConfig;
     * import com.sun.jersey.api.json.JSONConfiguration;
     * import com.sun.jersey.core.header.InBoundHeaders;
     *
     * import javax.ws.rs.core.MediaType;
     * import java.io.FileInputStream;
     * import java.io.FileNotFoundException;
     * import java.io.InputStream;
     *
     * ClientConfig clientConfig = new DefaultClientConfig();
     * clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
     * Client client = Client.create(clientConfig);
     *
     * try {
     *     InputStream inputStream = new FileInputStream(sampleFile);
     *     InBoundHeaders headers = new InBoundHeaders();
     *     headers.add("Content-Type", MediaType.APPLICATION_JSON_TYPE.toString());
     *
     *     GeonamesType mappedObject = ws.mapStreamToObjects(inputStream,
     *             MediaType.APPLICATION_JSON_TYPE,
     *             GeonamesType.class,
     *             client.getMessageBodyWorkers(),
     *             headers);
     *
     * }
     * catch (FileNotFoundException e) {
     *     e.printStackTrace();
     * }
     *
     *
     * @param inputStream
     * @param mediaType
     * @param mapToClass
     * @param workers
     * @param headers
     * @param <T>
     * @return
     */
    public <T> T mapStreamToObjects(InputStream inputStream,
                                    MediaType mediaType,
                                    Class<T> mapToClass,
                                    MessageBodyWorkers workers,
                                    MultivaluedMap<String, String> headers) {

        Annotation[] EMPTY_ANNOTATION = new Annotation[0];

        MessageBodyReader reader = workers.getMessageBodyReader(mapToClass,
                mapToClass,
                EMPTY_ANNOTATION,
                mediaType);

        if(reader == null) {
            LOG.error("ERROR: a message body reader for Java class " + mapToClass.getName() + " and MIME type " + mediaType.toString() + " was not found.");
            Map readers = workers.getReaders(mediaType);
            LOG.error("The registered message body readers for MIME type " + mediaType.toString() + " are:\n" + workers.readersToString(readers));
        }

        if(LOG.isDebugEnabled()) {
            LOG.debug("reader for media type " + mediaType.toString() + ": " + reader.toString());
        }

        try {
            Object mappedObject = reader.readFrom(mapToClass,
                    mapToClass,
                    EMPTY_ANNOTATION,
                    mediaType,
                    headers,
                    inputStream);

            if(reader instanceof CompletableReader) {
                mappedObject = ((CompletableReader)reader).complete(mappedObject);
            }

            return (T) mappedObject;
        }
        catch (IOException e) {
            LOG.error("Exception reported: " + e.getMessage());
            LOG.error("Trace: " + e.getStackTrace().toString());
//            e.printStackTrace();
        }
        return null;
    }

}
