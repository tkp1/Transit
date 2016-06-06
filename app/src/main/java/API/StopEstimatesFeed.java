package API;

import android.location.Location;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Trevor on 5/14/2016.
 */
public class StopEstimatesFeed {
    private static String API_KEY = "3u5DrRTEp1P6bMUlApvA";

    public static String execute(int stopNumber) {
        String feedLineString = "";
        try {
            URL url = new URL("http://api.translink.ca/rttiapi/v1/stops/" + Integer.toString(stopNumber) + "/estimates?apikey=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("accept", "application/json");
            feedLineString = retrieveFeed(urlConnection);
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feedLineString;
    }



    private static String retrieveFeed(HttpURLConnection url) throws IOException{
        String bufferResponse = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        bufferResponse = stringBuilder.toString();
        return bufferResponse;
    }



}