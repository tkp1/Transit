package API;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import model.Route;
import model.Schedule;
import model.Stop;

/**
 * Created by Tak on 24/05/2016.
 */
public class StopEstimatesDataParser {


    public static List<Route> parse (String response, Stop stop) {
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
        String routeNumber;
        String routeName;
        String direction;
        String routeMap;
        List<Route> listOfRoutes = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            routeNumber = "";
            routeName = "";
            direction = "";
            routeMap = "";
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            routeNumber = jsonObject.get("RouteNo").getAsString().trim();
            if (routeNumber.startsWith("0")) {
                int length = routeNumber.length();
                routeNumber = routeNumber.substring(1, length);
            }
            routeName = jsonObject.get("RouteName").getAsString().trim();
            direction = jsonObject.get("Direction").getAsString().trim();
            routeMap = jsonObject.get("RouteMap").getAsJsonObject().get("Href").getAsString().trim();
            if (!(routeNumber.equals("")) && !(routeName.equals("")) && !(direction.equals(""))) {
                Route route = new Route(routeNumber, routeName, direction, routeMap);
                listOfRoutes.add(route);
                JsonArray arrayOfSchedules = jsonObject.get("Schedules").getAsJsonArray();
                for (JsonElement scheduleElement: arrayOfSchedules) {
                    JsonObject scheduleJsonObject = scheduleElement.getAsJsonObject();
                    Schedule schedule = parseSchedule(scheduleJsonObject);
                    route.addSchedule(schedule, stop);
                }
            }

        }
        return listOfRoutes;
    }

    private static Schedule parseSchedule(JsonObject jsonObject){
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
        pattern = jsonObject.get("Pattern").getAsString().trim();
        destination = jsonObject.get("Destination").getAsString().trim();
        expectedLeaveTime = jsonObject.get("ExpectedLeaveTime").getAsString().trim();
        expectedCountdown = jsonObject.get("ExpectedCountdown").getAsInt();
        scheduleStatus = jsonObject.get("ScheduleStatus").getAsString().trim();
        cancelledTrip = jsonObject.get("CancelledTrip").getAsBoolean();
        cancelledStop = jsonObject.get("CancelledStop").getAsBoolean();
        addedTrip = jsonObject.get("AddedTrip").getAsBoolean();
        addedStop = jsonObject.get("AddedStop").getAsBoolean();
        lastUpdate = jsonObject.get("LastUpdate").getAsString().trim();
        if (!destination.equals("") && !expectedLeaveTime.equals("") &&
                !(expectedCountdown.toString().isEmpty())) {
            Schedule schedule = new Schedule(pattern,destination,expectedLeaveTime,
                    expectedCountdown,scheduleStatus,cancelledTrip,cancelledStop,addedTrip,addedStop,
                    lastUpdate);
            return schedule;
        }
        return null;
    }

}



