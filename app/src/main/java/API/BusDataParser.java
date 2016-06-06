package API;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.LinkedList;
import java.util.List;

import model.Bus;

/**
 *
 * A parser to create bus objects from an response string
 */

public class BusDataParser {
    /**
     * Parses and returns a list of bus objects from a request for bus information from the
     * TransLink API
     *
     * @param response the response as a string from the Http request
    */
    public static List<Bus> parse (String response){
        String vehicleNo = "";
        int tripId = 0;
        String routeNo= "";
        String direction = "";
        String destination = "";
        String pattern = "";
        double latitude = 0;
        double longitude = 0;
        String recordedTime = "";
        String routeMapHref = "";
        List<Bus> listOfBuses = new LinkedList<>();

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
        for (JsonElement object : jsonArray) {
            JsonObject jsonObject = object.getAsJsonObject();
            vehicleNo = jsonObject.get("VehicleNo").getAsString();
            tripId = jsonObject.get("TripId").getAsInt();
            routeNo = jsonObject.get("RouteNo").getAsString();
            if (routeNo.startsWith("0")) {
                routeNo = routeNo.substring(1, routeNo.length());
            }
            direction = jsonObject.get("Direction").getAsString();
            destination = jsonObject.get("Destination").getAsString();
            pattern = jsonObject.get("Pattern").getAsString();
            longitude = jsonObject.get("Longitude").getAsDouble();
            latitude = jsonObject.get("Latitude").getAsDouble();
            recordedTime = jsonObject.get("RecordedTime").getAsString();
            routeMapHref = jsonObject.get("RouteMap").getAsJsonObject().get("Href").getAsString();
            if(!(tripId == 0) && !(latitude == 0) && !(longitude == 0) && !(routeNo.equals(""))&&
                    !direction.equals("") && !destination.equals("") &&!pattern.equals("") &&
                    !recordedTime.equals("") && !routeMapHref.equals("")) {
                Bus newBus = new Bus(vehicleNo, tripId, routeNo, direction, pattern,
                        longitude, latitude, destination, recordedTime, routeMapHref);
                listOfBuses.add(newBus);
            }
            vehicleNo = "";
            tripId = 0;
            routeNo= "";
            direction = "";
            destination = "";
            pattern = "";
            latitude = 0;
            longitude = 0;
            recordedTime = "";
            routeMapHref = "";
        }
        return listOfBuses;
    }

}
