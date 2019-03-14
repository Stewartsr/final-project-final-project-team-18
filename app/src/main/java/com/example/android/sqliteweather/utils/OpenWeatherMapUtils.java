package com.example.android.sqliteweather.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.ForecastItem;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class OpenWeatherMapUtils {

    public static final String EXTRA_FORECAST_ITEM = "com.example.android.lifecycleweather.utils.ForecastItem";

    private final static String OWM_FORECAST_BASE_URL = " http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002";
//  private final static String OWM_FORECAST_BASE_URL = "http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key=36CA30F19E38A1E627CED21BA4CD3342&steamid=76561197960435530&relationship=friend";
    private final static String OWM_ICON_URL_FORMAT_STR = "https://openweathermap.org/img/w/%s.png";
    private final static String OWM_FORECAST_APPID_PARAM = "key";
    private final static String OWM_FORECAST_STEAMID_PARAM = "steamids";

    /*
     * Set your own APPID here.
     */
    private final static String OWM_FORECAST_APPID = "36CA30F19E38A1E627CED21BA4CD3342";
    private final static String OWM_FORECAST_USERID = "76561197960435530";


    /*
     * The below several classes are used only for JSON parsing with Gson.  The main class that's
     * used to represent a single forecast item throughout the rest of the app is the ForecastItem
     * class in the data package.
     */
    static class OWMForecastResults {
        OWMForecastListItem[] list;
    }

    static class OWMForecastListItem {
        String dt_txt;
        OWMForecastItemMain main;
        OWMForecastItemWeather[] weather;
        OWMForecastItemWind wind;
    }

    static class OWMForecastItemMain {
        float temp;
        float temp_min;
        float temp_max;
        float humidity;
    }

    static class OWMForecastItemWeather {
        String description;
        String icon;
    }

    static class OWMForecastItemWind {
        float speed;
        float deg;
    }

    public static String buildForecastURL(String forecastLocation, String temperatureUnits) {
        return Uri.parse(OWM_FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(OWM_FORECAST_APPID_PARAM, OWM_FORECAST_APPID)
                .appendQueryParameter(OWM_FORECAST_STEAMID_PARAM, OWM_FORECAST_USERID)
                .build()
                .toString();
    }

    public static String buildIconURL(String icon) {
        return String.format(OWM_ICON_URL_FORMAT_STR, icon);
    }

    public static ArrayList<ForecastItem> parseForecastJSON(String forecastJSON) {
        Gson gson = new Gson();
        OWMForecastResults results = gson.fromJson(forecastJSON, OWMForecastResults.class);
        if (results != null && results.list != null) {
            ArrayList<ForecastItem> forecastItems = new ArrayList<>();


            /*
             * Loop through all results parsed from JSON and condense each one into one
             * single-level ForecastItem object.
             */
            for (OWMForecastListItem listItem : results.list) {
                ForecastItem forecastItem = new ForecastItem();


                forecastItem.description = listItem.weather[0].description;
                forecastItem.icon = listItem.weather[0].icon;

                forecastItem.temperature = Math.round(listItem.main.temp);
                forecastItem.temperatureHigh = Math.round(listItem.main.temp_max);
                forecastItem.temperatureLow = Math.round(listItem.main.temp_min);
                forecastItem.humidity = Math.round(listItem.main.humidity);

                forecastItem.windSpeed = Math.round(listItem.wind.speed);
                forecastItem.windDirection = windAngleToDirection(listItem.wind.deg);

                forecastItems.add(forecastItem);
            }

            return forecastItems;
        } else {
            return null;
        }
    }

    public static String windAngleToDirection(double angleDegrees) {
        if (angleDegrees >= 0 && angleDegrees < 11.25) {
            return "N";
        } else if (angleDegrees >= 11.25 && angleDegrees < 33.75) {
            return "NNE";
        } else if (angleDegrees >= 33.75 && angleDegrees < 56.25) {
            return "NE";
        } else if (angleDegrees >= 56.25 && angleDegrees < 78.75) {
            return "ENE";
        } else if (angleDegrees >= 78.75 && angleDegrees < 101.25) {
            return "E";
        } else if (angleDegrees >= 101.25 && angleDegrees < 123.75) {
            return "ESE";
        } else if (angleDegrees >= 123.75 && angleDegrees < 146.25) {
            return "SE";
        } else if (angleDegrees >= 146.25 && angleDegrees < 168.75) {
            return "SSE";
        } else if (angleDegrees >= 168.75 && angleDegrees < 191.25) {
            return "S";
        } else if (angleDegrees >= 191.25 && angleDegrees < 213.75) {
            return "SSW";
        } else if (angleDegrees >= 213.75 && angleDegrees < 236.25) {
            return "SW";
        } else if (angleDegrees >= 236.25 && angleDegrees < 258.75) {
            return "WSW";
        } else if (angleDegrees >= 258.75 && angleDegrees < 281.25) {
            return "W";
        } else if (angleDegrees >= 281.25 && angleDegrees < 303.75) {
            return "WNW";
        } else if (angleDegrees >= 303.75 && angleDegrees < 326.25) {
            return "WNW";
        } else if (angleDegrees >= 326.25 && angleDegrees < 348.75) {
            return "NNW";
        } else {
            return "N";
        }
    }

    public static String getTemperatureUnitsAbbr(Context context, String temperatureUnitsValue) {
        if (temperatureUnitsValue.equals(context.getString(R.string.pref_units_kelvin_value))) {
            return context.getString(R.string.units_kelvin);
        } else if (temperatureUnitsValue.equals(context.getString(R.string.pref_units_metric_value))) {
            return context.getString(R.string.units_metric);
        } else {
            return context.getString(R.string.units_imperial);
        }
    }
}
