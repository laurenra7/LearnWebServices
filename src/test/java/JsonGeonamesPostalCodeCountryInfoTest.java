import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.header.InBoundHeaders;
import org.junit.Ignore;
import org.junit.Test;
import org.learn.ws.client.jaxrs.EntityMappingTester;
import org.learn.ws.client.jaxrs.json.JsonGeonamesPostalCodeCountryInfo;
import org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types.CountryType;
import org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types.GeonamesType;
import org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types.PartialCountryType;
import org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types.PartialGeonamesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by laurenra on 2/3/16.
 */
public class JsonGeonamesPostalCodeCountryInfoTest {

    private static final Logger LOG = LoggerFactory.getLogger(JsonGeonamesPostalCodeCountryInfo.class);

    @Ignore
    @Test
    public void shouldReturnStringSimple() {
        JsonGeonamesPostalCodeCountryInfo ws = new JsonGeonamesPostalCodeCountryInfo();
        String result = ws.simpleGetAsString();
        LOG.info("shouldReturnStringSimple result = \n" + result);
    }

    @Ignore
    @Test
    public void shouldReturnGeonamesTypeSimple() {
        JsonGeonamesPostalCodeCountryInfo ws = new JsonGeonamesPostalCodeCountryInfo();
        GeonamesType result = ws.simpleGetAndMapToObject();
        LOG.info("shouldReturnGeonamesTypeSimple result = \n" + result.toString());
    }

    @Ignore
    @Test
    public void shouldReturnString() {
        JsonGeonamesPostalCodeCountryInfo ws = new JsonGeonamesPostalCodeCountryInfo();
        String result = ws.getAsString();
        LOG.info("shouldReturnString result = \n" + result);
    }

    @Ignore
    @Test
    public void shouldReturnGeonamesType() {
        JsonGeonamesPostalCodeCountryInfo ws = new JsonGeonamesPostalCodeCountryInfo();
        GeonamesType result = ws.getAsObject();
        LOG.info("shouldReturnGeonamesType result = \n" + result.toString());
    }

    @Ignore
    @Test
    public void shouldAlsoReturnGeonamesType() {
        JsonGeonamesPostalCodeCountryInfo ws = new JsonGeonamesPostalCodeCountryInfo();
        GeonamesType result = ws.getAsObjectSimpler();
        LOG.info("shouldReturnGeonamesType result = \n" + result.toString());
    }

    @Ignore
    @Test
    public void shouldMapFileToGeonamesType() {

        String sampleFile = "src/main/java/org/learn/ws/model/jaxb/api/geonames/org/postal/countryinfo/small-sample.json";

        EntityMappingTester ws = new EntityMappingTester();

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

        try {
            InputStream inputStream = new FileInputStream(sampleFile);
            InBoundHeaders headers = new InBoundHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_JSON_TYPE.toString());

            GeonamesType mappedObject = ws.mapStreamToObjects(inputStream,
                    MediaType.APPLICATION_JSON_TYPE,
                    GeonamesType.class,
                    client.getMessageBodyWorkers(),
                    headers);

            LOG.info("shouldMapFileToGeonamesType result = \n" + mappedObject.toString());

            for (CountryType country : (List<CountryType>) mappedObject.getCountry()) {
//                LOG.info("  countryCode: " + country.getCountryCode());
                LOG.info("  countryName: " + country.getCountryName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * This uses the "broken" or partial version of CountryType that
     * purposely includes only some of the properties.  This demonstrates
     * how to use @JsonIgnoreProperties(ignoreUnknown = true) to solve
     * the Jackson UnrecognizedPropertyException (Unrecognized field "blah"
     * , not marked as ignorable).  JAXB will automatically ignore fields
     * that don't have like-named properties in the class, but Jackson
     * requires the @JsonIgnoreProperties annotation at the class level
     * to force it to ignore fields that don't correspond to properties
     * in the class definition.
     */

    @Test
    public void shouldMapFileToPartialGeonamesType() {

        String sampleFile = "src/main/java/org/learn/ws/model/jaxb/api/geonames/org/postal/countryinfo/small-sample.xml";

        EntityMappingTester ws = new EntityMappingTester();

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

        try {
            InputStream inputStream = new FileInputStream(sampleFile);
            InBoundHeaders headers = new InBoundHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_XML_TYPE.toString());

            PartialGeonamesType mappedObject = ws.mapStreamToObjects(inputStream,
                    MediaType.APPLICATION_XML_TYPE,
                    PartialGeonamesType.class,
                    client.getMessageBodyWorkers(),
                    headers);

            LOG.info("shouldMapFileToPartialGeonamesType result = \n" + mappedObject.toString());

            for (PartialCountryType country : (List<PartialCountryType>) mappedObject.getCountry()) {
//                LOG.info("  countryCode: " + country.getCountryCode());
                LOG.info("  countryName: " + country.getCountryName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
