package model.Test;

import org.junit.Before;

import model.Route;
import model.Schedule;
import model.Stop;

/**
 * Created by Tak on 31/05/2016.
 */
public class RouteTest {
    String testURL;
    String testURL2;
    Route testRoute;
    Route testRoute2;
    Stop testStop;
    Stop testStop2;
    Schedule testSchedule;
    Schedule testSchedule2;
    Schedule testSchedule3;

    @Before
    public void setUp() throws Exception {
        testURL = "https://www.google.ca";
        testURL2 = "https://www.yahoo.com";
        testRoute = new Route("TestRoute", "99", "North", testURL);
        testRoute2 = new Route("TestRoute2", "9", "South", testURL2);
        testStop = new Stop(51479,"SomeName", "1", "Vancouver", "SomeStreet", "AnotherStreet", 0.1213, 0.4321,false, 1.01);
        testStop2 = new Stop(51479,"SomeName2", "2", "Richmond", "SomeStreet2", "AnotherStreet2", 0.1213, 0.4321,false, 1.01);
        testSchedule = new Schedule("A","B","1:00PM",1,"Fine",false,false,false,false,"1:00PM");
        testSchedule2 = new Schedule("A","B","1:00PM",2,"Fine",false,false,false,false,"1:00PM");
        testSchedule3 = new Schedule("A","B","1:00PM",1,"Fine",false,false,false,false,"1:01PM");
    }

//
//    @Test
//    public void testRouteConstructor(){
//
//            assertEquals(testRoute.getRouteMap(), testURL.toString());
//            Assert.assertTrue(testRoute.getListOfStops().isEmpty());
//            Assert.assertEquals(testRoute.getDirection(), "North");
//            Assert.assertEquals(testRoute.getRouteName(), "TestRoute");
//            Assert.assertEquals(testRoute.getRouteNumber(), "99");
//            Assert.assertTrue(testRoute.getListOfEstimates().isEmpty());
//
//    }
//    @Test
//    public void testRouteAndStopBiDirectionalAssociation(){
//        testRoute.addStop(testStop);
//        assertEquals(testRoute.getListOfStops().size(),1);
//        assertEquals(testStop.getRoutes().size(),1);
//        assertEquals(testRoute.getListOfStops().get(0),testStop);
//        assertEquals(testStop.getRoutes().get(0),testRoute);
//
//    }
//    @Test
//    public void testTreeStructureWithSameDepartureTimes(){
//        testRoute.addStop(testStop);
//        testRoute.addScheduleEstimate(testSchedule,testStop);
//        testRoute.addScheduleEstimate(testSchedule2,testStop);
//        testRoute.addScheduleEstimate(testSchedule3,testStop);
//        testRoute.sortEstimates();
//        assertEquals(testRoute.getListOfEstimates().size(), 3);
//
//    }


}