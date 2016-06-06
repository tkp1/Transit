package API;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import model.Stop;

/**
 * Created by Tak on 24/05/2016.
 */
public class /**/StopsDataParser {


    public static List<Stop> parse (String response){
        int stopNumber;
        String name;
        String bayNo;
        String city;
        String onStreet;
        String atStreet;
        double latitude;
        double longitude;
        boolean wheelchairAccess;
        Integer distance;

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(response).getAsJsonArray();
        List<Stop> listOfStops = new ArrayList<>();
        for (JsonElement element: jsonArray){
            stopNumber = 0;
            name = "";
            bayNo = "";
            city = "";
            onStreet = "";
            atStreet = "";
            latitude = 0;
            longitude = 0;
            wheelchairAccess = false;
            distance = null;
            JsonObject jsonObject = element.getAsJsonObject();
            stopNumber =jsonObject.get("StopNo").getAsInt();
            name = jsonObject.get("Name").getAsString().trim();
            bayNo = jsonObject.get("BayNo").getAsString().trim();
            city = jsonObject.get("City").getAsString().trim();
            onStreet = jsonObject.get("OnStreet").getAsString().trim();
            atStreet = jsonObject.get("AtStreet").getAsString().trim();
            latitude = jsonObject.get("Latitude").getAsDouble();
            longitude = jsonObject.get("Longitude").getAsDouble();
            int access =jsonObject.get("WheelchairAccess").getAsInt();
            if(access == 1){
                wheelchairAccess = true;
            }
            distance = jsonObject.get("Distance").getAsInt();
            if (!(stopNumber==0) && !(name.equals("")) && !(bayNo.equals("")) && !(city.equals(""))
                    && !(onStreet.equals("")) && !(atStreet.equals("")) && !(latitude==0) &&
                    !(longitude==0) && (distance != null)){
                Stop stop = new Stop(stopNumber,name,bayNo,city,onStreet,atStreet,latitude,longitude,
                        wheelchairAccess,distance);
                listOfStops.add(stop);
            }
        }
        return listOfStops;
    }


}

