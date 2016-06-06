package API.Test;

import org.junit.Test;

import java.util.List;

import API.BusDataParser;
import API.BusFeed;
import API.StopEstimatesDataParser;
import API.StopEstimatesFeed;
import API.StopsDataParser;
import API.StopsFeed;
import model.Bus;
import model.Route;
import model.Stop;

/**
 * Created by Trevor
 */
public class FeedTest {

    @Test
    public void testSetUp(){
        List<Route> stopEstimatesData;
        try {
            String response = StopEstimatesFeed.execute(50217);
            Stop testStop = new Stop(50539,"SomeName", "1", "Vancouver", "SomeStreet", "AnotherStreet", 0.1213, 0.4321,false, 1.01);
            stopEstimatesData = StopEstimatesDataParser.parse(response, testStop);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testStopsDataRetrievalAndParsing() {
        List<Stop> stopsData;
        try {
            String response = StopsFeed.execute(200,49.263520, -123.138608);
            stopsData = StopsDataParser.parse(response);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testBusDataRetrievalAndParsing() {
        try {
            String response = BusFeed.execute(50216);
            List<Bus> buses = BusDataParser.parse(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}