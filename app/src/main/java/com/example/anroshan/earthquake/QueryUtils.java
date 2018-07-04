package com.example.anroshan.earthquake;

import android.util.Log;

import com.example.anroshan.earthquake.ListEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.jar.JarException;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private QueryUtils() {
    }

    /**
     * Return a list of {@link ListEntry} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<ListEntry> extractEarthquakes(String SAMPLE_JSON_RESPONSE) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<ListEntry> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // build up a list of Earthquake objects with the corresponding data.
            JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray featuresArray = root.optJSONArray("features");
            for(int i = 0; i < featuresArray.length(); i++)
            {
                JSONObject objects = featuresArray.optJSONObject(i);
                JSONObject properties = objects.optJSONObject("properties");


                Double magnitude = properties.optDouble("mag");


                String placeUnseperated = properties.optString("place").toString();
                int index = placeUnseperated.indexOf("of");
                String direction = placeUnseperated.substring(0,index+2);
                String place = placeUnseperated.substring(index+3);
                if(index == -1)
                {
                    direction = "";
                    place = placeUnseperated;
                }


                long time = properties.optLong("time");
                Date dateObject = new Date(time);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
                String date = dateFormatter.format(dateObject);

                SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
                String time2 = timeFormat.format(dateObject);

                String url = properties.optString("url");
                earthquakes.add(new ListEntry(magnitude, direction, place, date, time2, url));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}