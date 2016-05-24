package API;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by Tak on 24/05/2016.
 */
public class StopEstimatesDataParser {

    public static StopEstimatesData[] parse (String response){
    JsonParser jsonParser = new JsonParser();
    JsonElement jsonArray = new JsonArray();
    jsonArray = jsonParser.parse(response);
    Gson gson = new Gson();
    StopEstimatesData[] stopEstimatesData = gson.fromJson(jsonArray, StopEstimatesData[].class);
    return stopEstimatesData;
    }

}
