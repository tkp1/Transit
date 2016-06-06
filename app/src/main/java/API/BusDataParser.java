package API;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import model.Bus;

/**
 * Created by Tak on 24/05/2016.
 */
public class BusDataParser {

    public static List<Bus> parse (String response){
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
        String vehicleNumber;
        String tripID;
        String routeNumber;
        String direction;
        String destination;
        String pattern;
        double longitude;
        double latitude;
        String recordedTime;
        String href;
        List<Bus> listOfBuses = new ArrayList<>();
        for (JsonElement jsonElement: jsonArray){
            vehicleNumber = "";
            tripID = "";
            routeNumber = "";
            direction = "";
            destination = "";
            pattern = "";
            longitude = 0;
            latitude = 0;
            recordedTime = "";
            href = "";
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            vehicleNumber = jsonObject.get("VehicleNo").getAsString().trim();
            tripID = jsonObject.get("TripId").getAsString().trim();
            routeNumber = jsonObject.get("RouteNo").getAsString().trim();
            direction = jsonObject.get("Direction").getAsString().trim();
            destination = jsonObject.get("Destination").getAsString().trim();
            pattern = jsonObject.get("Pattern").getAsString().trim();
            latitude = jsonObject.get("Latitude").getAsDouble();
            longitude = jsonObject.get("Longitude").getAsDouble();
            recordedTime = jsonObject.get("RecordedTime").getAsString().trim();
            href = jsonObject.get("RouteMap").getAsJsonObject().get("Href").getAsString();
            if (!vehicleNumber.equals("") && !tripID.equals("") && !routeNumber.equals("")
                    && !direction.equals("") && !destination.equals("") && !(longitude==0) &&
                    !pattern.equals("") && !(latitude == 0) && !recordedTime.equals("") &&
                    !href.equals("")){
                Bus bus = new Bus(vehicleNumber,tripID,routeNumber,direction,destination,pattern,
                        longitude,latitude,recordedTime,href);
                listOfBuses.add(bus);
            }

        }
        return listOfBuses;
    }

}
