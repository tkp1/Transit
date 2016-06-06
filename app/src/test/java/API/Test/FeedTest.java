package API.Test;

import android.location.Location;

import org.junit.Test;

import java.net.URL;
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
 * Created by Trevor on 5/14/2016.
 */
public class FeedTest {

    @Test
    public void testSetUp() throws Exception {
        List<Route> routes;
        Stop stop = new Stop (0, "1", "2", "3", "4", "5", 6.0, 7.0, false, 8);
        String response = StopEstimatesFeed.execute(58135);
        routes = StopEstimatesDataParser.parse(response, stop);
    }

    @Test
    public void testStopsDataRetrievalAndParsing() {
        List<Stop> listOfStops;
        String string = "";
        string.trim();
        String response = StopsFeed.execute(200,49.263520, -123.138608);
        listOfStops = StopsDataParser.parse(response);
    }

    @Test
    public void testBusDataRetrievalAndParsing() {
        List<Bus> buses;
        String response = BusFeed.execute(58135);
        buses = BusDataParser.parse(response);
    }
}