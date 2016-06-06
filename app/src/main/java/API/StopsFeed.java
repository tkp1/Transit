package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import model.Exceptions.NoDataReceivedException;

/**
 *
 * Communicates with the Translink API to retrieve a response from a request for stop information
 * from a location
 */
public class StopsFeed {
    private static final String APIKey = "";

    /**
     * Opens and http connection and retrieves JSON data for nearby stops
     * @param radius the radius searched in meters
     * @param latitude the latitude of the current location
     * @param longitude the longitude of the current location
     * @return a string of the response
     * @throws NoDataReceivedException
     */
    public static String execute(int radius, double latitude, double longitude)
            throws NoDataReceivedException {

        String feedLineString;
        try {
            DecimalFormat numberFormat = new DecimalFormat("#.000000");
            URL url = new URL("http://api.translink.ca/rttiapi/v1/stops?apikey=" + APIKey
                    + "&lat=" + numberFormat.format(latitude) + "&long=" +
                    numberFormat.format(longitude) + "&radius=" + Integer.toString(radius));
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
     * @param url the url for the stop data request from a location
     * @return a string of the response
     * @throws IOException
     */

    private static String retrieveFeed(HttpURLConnection url) throws IOException {
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
