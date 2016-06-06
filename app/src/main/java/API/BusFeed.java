package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import model.Exceptions.NoDataReceivedException;

/**
 *
 * Communicates with the Translink API to retrieve a response from a request for Bus information
 * from a stop number
 */
public class BusFeed {
    private static final String APIkey = "";
    /**
     * Opens an http connection and retrieves JSON data for bus information
     * @param stopNumber the stop number from which bus information is requested
     * @return feedLineString the response from the request
     */
    public static String execute(int stopNumber) throws NoDataReceivedException {

        String feedLineString;
        try {
            URL url = new URL("http://api.translink.ca/rttiapi/v1/buses?apikey=" +
                    APIkey + "&stopNo=" + Integer.toString(stopNumber));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("accept", "application/json");
            feedLineString = retrieveFeed(urlConnection);
            urlConnection.disconnect();
        } catch (IOException e) {
            throw new NoDataReceivedException();
        }
        return feedLineString;
    }

    /**
     * A function to get the response from the url
     * @param url the url for the bus data request from a stop
     * @return the response as a string
     */

    private static String retrieveFeed(HttpURLConnection url) throws IOException{
        String bufferResponse;
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

