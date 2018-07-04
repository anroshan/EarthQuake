package com.example.anroshan.earthquake;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;



public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<ListEntry>> {

    /** Tag for the log messages */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /** URL to query the USGS dataset for earthquake information */
    private String mUrl;
    // ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2017-01-01&endtime=2017-12-01";


    public EarthquakeLoader(Context context, String url) {
            super(context);
            mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override

    public ArrayList<ListEntry> loadInBackground() {

        URL url = createUrl(mUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            // TODO Handle the IOException
            Log.e(LOG_TAG,"Error making http request", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<ListEntry> earthquake = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return earthquake;

    }

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if(url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {
                Log.e(LOG_TAG,"Error respond code: "+urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            // TODO: Handle the exception
            Log.e(LOG_TAG,"Problem retrieving data from Earthquake json", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an  object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
    private ArrayList<ListEntry> extractFeatureFromJson(String earthquakeJSON) {

        if(TextUtils.isEmpty(earthquakeJSON)) return null;

        ArrayList<ListEntry> earthquakes = QueryUtils.extractEarthquakes(earthquakeJSON);
        return earthquakes;
        //return null;
    }

}

//
//        JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
//        JSONArray featureArray = baseJsonResponse.getJSONArray("features");
//
//        // If there are results in the features array
//        if (featureArray.length() > 0) {
//        // Extract out the first feature (which is an earthquake)
//        JSONObject firstFeature = featureArray.getJSONObject(0);
//        JSONObject properties = firstFeature.getJSONObject("properties");
//
//        // Extract out the title, time, and tsunami values
//        String title = properties.getString("title");
//        long time = properties.getLong("time");
//        int tsunamiAlert = properties.getInt("tsunami");
//
//        // Create a new {@link Event} object
//        return new Event(title, time, tsunamiAlert);