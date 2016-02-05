import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.header.InBoundHeaders;
import org.junit.Ignore;
import org.junit.Test;
import org.learn.ws.client.jaxrs.EntityMappingTester;
import org.learn.ws.client.jaxrs.xml.XmlGeonamesPostalCodeCountryInfo;
import org.learn.ws.model.jaxb.api.geonames.org.postal.countryinfo.types.GeonamesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by laurenra on 2/3/16.
 */
public class XmlGeonamesPostalCodeCountryInfoTest {

    private static final Logger LOG = LoggerFactory.getLogger(XmlGeonamesPostalCodeCountryInfo.class);

    @Ignore
    @Test
    public void shouldReturnStringSimple() {
        XmlGeonamesPostalCodeCountryInfo ws = new XmlGeonamesPostalCodeCountryInfo();
        String result = ws.simpleGetAsString();
        LOG.info("shouldReturnStringSimple result = \n" + result);
    }

    @Ignore
    @Test
    public void shouldReturnGeonamesTypeSimple() {
        XmlGeonamesPostalCodeCountryInfo ws = new XmlGeonamesPostalCodeCountryInfo();
        GeonamesType result = ws.simpleGetAndMapToObject();
        LOG.info("shouldReturnGeonamesTypeSimple result = \n" + result.toString());
    }

    @Ignore
    @Test
    public void shouldMapFileToGeonamesType() {

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

            GeonamesType mappedObject = ws.mapStreamToObjects(inputStream,
                    MediaType.APPLICATION_XML_TYPE,
                    GeonamesType.class,
                    client.getMessageBodyWorkers(),
                    headers);

            LOG.info("shouldMapFileToGeonamesType result = \n" + mappedObject.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
