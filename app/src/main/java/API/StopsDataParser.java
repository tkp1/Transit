package API;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import model.Stop;

public class StopsDataParser {
    /**
     * A parser for stop data, to get nearby stops
     * @param response the response as a string from the Http request
     * @return a list of nearby stops
     */


    public static List<Stop> parse (String response){
        int stopNo;
        String name;
        String bayNo;
        String city;
        String onStreet;
        String atStreet;
        double latitude;
        double longitude;
        boolean wheelchairAccess;
        int distance;
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
        List<Stop> listOfStops = new ArrayList<>();
        for (JsonElement jsonElement: jsonArray ){
            stopNo = 0;
            name = "";
            bayNo = "";
            city = "";
            onStreet = "";
            atStreet = "";
            latitude = 0;
            longitude = 0;
            wheelchairAccess = false;
            distance = 0;
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            stopNo = jsonObject.get("StopNo").getAsInt();
            name = jsonObject.get("Name").getAsString();
            bayNo = jsonObject.get("BayNo").getAsString();
            city = jsonObject.get("City").getAsString();
            onStreet = jsonObject.get("OnStreet").getAsString();
            atStreet = jsonObject.get("AtStreet").getAsString();
            latitude = jsonObject.get("Latitude").getAsDouble();
            longitude = jsonObject.get("Longitude").getAsDouble();
            if (jsonObject.get("WheelchairAccess").getAsInt() == 1){
                wheelchairAccess = true;}
            distance = jsonObject.get("Distance").getAsInt();
            if ( !(stopNo == 0) && !name.equals("") && !city.equals("") && !(latitude==0)
                    && !(longitude==0)) {
                Stop stop = new Stop(stopNo, name, bayNo, city, onStreet, atStreet, latitude,
                        longitude,wheelchairAccess, distance);
                listOfStops.add(stop);
            }
        }
        return listOfStops;

    }

}

