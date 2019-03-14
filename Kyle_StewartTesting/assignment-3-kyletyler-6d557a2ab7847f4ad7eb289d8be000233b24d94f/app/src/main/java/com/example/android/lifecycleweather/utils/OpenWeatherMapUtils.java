package com.example.android.lifecycleweather.utils;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class OpenWeatherMapUtils {

    private static final String TAG = "TAG";

    public static final String EXTRA_FORECAST_ITEM = "com.example.android.lifecycleweather.utils.ForecastItem";

    private final static String OWM_FORECAST_BASE_URL = " http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002";
    private final static String OWM_FORECAST_APPID_PARAM = "key";
    private final static String OWM_FORECAST_STEAMID_PARAM = "steamids";

    /*
     * Set your own APPID here.
     */

    private final static String OWM_FORECAST_APPID = "36CA30F19E38A1E627CED21BA4CD3342";
    private final static String OWM_FORECAST_USERID = "76561197960435530";

    /*
     * This class is used as a final representation of a single forecast item.  It condenses the
     * classes below that are used for parsing the OWN JSON response with Gson.
     */
    public static class ForecastItem implements Serializable {
        public String steamid;
        public String personaname;
        public String profileurl;
        public String avatar;
        public int personastate;
    }

    static class OWMForecastResults {
        public ForecastItem[] players;
    }

    public static String buildForecastURL(String forecastLocation, String temperatureUnits) {
        return Uri.parse(OWM_FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(OWM_FORECAST_APPID_PARAM, OWM_FORECAST_APPID)
                .appendQueryParameter(OWM_FORECAST_STEAMID_PARAM, OWM_FORECAST_USERID)
                .build()
                .toString();
    }

    /*
     TODO: For some reason results.players is null
     We aren't sure what is wrong with our parsing, but something definitely is
      */

    public static ForecastItem[] parseForecastJSON(String forecastJSON) {
        Gson gson = new Gson();
        OWMForecastResults results = gson.fromJson(forecastJSON, OWMForecastResults.class);

        //Log.d(TAG, "parseForecastJSON: results" + results);

        if (results != null && results.players != null) {
            Log.d(TAG, "parseForecastJSON: results.players not null");
            return results.players;
        }
        else {
            Log.d(TAG, "parseForecastJSON: results.players null");
            return null;
        }
    }

}
