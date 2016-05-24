package API.Test;

import org.junit.Test;

import java.net.URL;

import API.BusData;
import API.BusDataParser;
import API.BusFeed;
import API.StopEstimatesDataParser;
import API.StopEstimatesFeed;
import API.StopsDataParser;
import API.StopsFeed;

/**
 * Created by Trevor on 5/14/2016.
 */
public class FeedTest {

    @Test
    public void testSetUp() throws Exception {
        String response = StopEstimatesFeed.execute(51479);
        StopEstimatesDataParser.parse(response);
    }

    @Test
    public void testStopsDataRetrievalAndParsing() {
        String response = StopsFeed.execute("200",49.263520, -123.138608);
        StopsDataParser.parse(response);
    }

    @Test
    public void testBusDataRetrievalAndParsing() {
        String response = BusFeed.execute(58135);
        BusDataParser.parse(response);
    }
}