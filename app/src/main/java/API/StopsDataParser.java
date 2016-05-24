package API;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by Tak on 24/05/2016.
 */
public class StopsDataParser {

    public static StopsData[] parse (String response){
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonArray = new JsonArray();
        jsonArray = jsonParser.parse(response);
        Gson gson = new Gson();
        StopsData[] stopsData = gson.fromJson(jsonArray, StopsData[].class);
        return stopsData;
    }

}

