package com.example.android.connectedweather;

import android.net.Uri;

import com.google.gson.Gson;

public class ForecastUtils {
    private final static String GITHUB_SEARCH_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast?q=London,uk&APPID=1cb3804e39db10933b1364b59826488f";
    //private final static String GITHUB_SEARCH_QUERY_PARAM = "q";
    //private final static String GITHUB_SEARCH_SORT_PARAM = "sort";
    //private final static String GITHUB_SEARCH_SORT_VALUE = "stars";

    public static class forecastRes {
        public String dt_txt;
        public String temperature;
        public String description;

    }

    public static class forecastSearchResults {
        public forecastRes[] items;
    }

    public static String buildForecastSearchURL() {
        return Uri.parse(GITHUB_SEARCH_BASE_URL).buildUpon()
                .build()
                .toString();
    }

    public static forecastRes[] parseForecastSearchResults(String json) {
        Gson gson = new Gson();
        forecastSearchResults results = gson.fromJson(json, forecastSearchResults.class);
        if (results != null && results.items != null) {
            return results.items;
        } else {
            return null;
        }
    }
}
