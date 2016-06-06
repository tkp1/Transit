package API;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.LinkedList;
import java.util.List;

import model.Route;
import model.Schedule;
import model.Stop;

/**
 * A parser for stop estimates data, into route objects
 */
public class StopEstimatesDataParser {
    /**
     * Parses and returns a list of route objects from a request for stop estimates information from
     * the TransLink API
     *
     * @param response the response as a string from the Http request
     * @return a list of routes from a given stop
     */

    public static List<Route> parse (String response, Stop stop){
        String routeNo = "";
        String routeName = "";
        String direction = "";
        String routeMapHref = "";
        JsonArray schedules = null;
        List<Route> listOfRoutes = new LinkedList<>();

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
        for(JsonElement object: jsonArray){
            JsonObject jsonObject = object.getAsJsonObject();
            routeNo = jsonObject.get("RouteNo").getAsString();
            if (routeNo.startsWith("0")) {
                routeNo = routeNo.substring(1, routeNo.length());
            }
            routeName = jsonObject.get("RouteName").getAsString();
            direction = jsonObject.get("Direction").getAsString();
            routeMapHref = jsonObject.get("RouteMap").getAsJsonObject().get("Href").getAsString();
            schedules = jsonObject.get("Schedules").getAsJsonArray();
            if (!(routeNo.equals("")) && !(routeName.equals("")) && !(direction.equals("")) &&
                    !(routeMapHref.equals(""))) {
                Route newRoute = new Route(routeName,routeNo,direction,routeMapHref);

                for(JsonElement jsonElement: schedules){
                    JsonObject scheduleJson = jsonElement.getAsJsonObject();
                    Schedule schedule = parseSchedule(scheduleJson);
                    if(schedule !=null) {
                        newRoute.addScheduleEstimate(schedule, stop);
                    }
                }
                listOfRoutes.add(newRoute);
            }
            routeNo = "";
            routeName = "";
            direction = "";
            routeMapHref = "";
            schedules = null;
        }
        return listOfRoutes;
//    Gson gson = new Gson();
//    StopEstimatesData[] stopEstimatesData = gson.fromJson(jsonArray, StopEstimatesData[].class);
//    return stopEstimatesData;
    }
    /**
    *
     */
    private static Schedule parseSchedule(JsonObject scheduleJson){
        String pattern = "";
        String destination = "";
        String expectedLeaveTime = "";
        Integer expectedCountdown = null;
        String scheduleStatus = "";
        boolean cancelledTrip = false;
        boolean cancelledStop = false;
        boolean addedTrip = false;
        boolean addedStop = false;
        String lastUpdate = "";
        pattern = scheduleJson.get("Pattern").getAsString();
        destination = scheduleJson.get("Destination").getAsString();
        expectedLeaveTime = scheduleJson.get("ExpectedLeaveTime").getAsString();
        expectedCountdown = scheduleJson.get("ExpectedCountdown").getAsInt();
        scheduleStatus = scheduleJson.get("ScheduleStatus").getAsString();
        cancelledTrip = scheduleJson.get("CancelledTrip").getAsBoolean();
        cancelledStop = scheduleJson.get("CancelledStop").getAsBoolean();
        addedTrip = scheduleJson.get("AddedTrip").getAsBoolean();
        addedStop = scheduleJson.get("AddedStop").getAsBoolean();
        lastUpdate = scheduleJson.get("LastUpdate").getAsString();
        if( !pattern.equals("") && !destination.equals("") && !expectedLeaveTime.equals("") &&
                (expectedCountdown.toString()!= null)){
            Schedule newSchedule = new Schedule(pattern,destination,expectedLeaveTime,
                    expectedCountdown,scheduleStatus,cancelledTrip,cancelledStop,addedTrip,
                    addedStop,lastUpdate);
            return newSchedule;
        }
        else{
            return null;
        }
    }

}
