package model.Test;

import org.junit.Test;

import java.net.URL;

import model.Route;

import static org.junit.Assert.*;

/**
 * Created by Tak on 31/05/2016.
 */
public class RouteTest {

    @Test public void testRouteConstructor(){
        try {
            Route testroute = new Route("TestRoute", 99, "North", new URL("https://www.google.ca"));
            Assert.assertTrue(testroute.getListOfStops().isEmpty());
            Assert.assertEquals(testroute.getDirection(), "North");
            Assert.assertEquals(testroute.getRouteName(), "TestRoute");
            Assert.assertEquals(testroute.getRouteNumber(), 99);
            Assert.assertTrue(testroute.getSchedulesMap().isEmpty());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}