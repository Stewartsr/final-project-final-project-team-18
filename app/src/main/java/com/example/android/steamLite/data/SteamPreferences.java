package com.example.android.steamLite.data;

public class SteamPreferences {
    private static final String DEFAULT_Friend_LOCATION = "CLIFFORD";
    private static final String DEFAULT_TEMPERATURE_UNITS = "imperial";

    public static String getDefaultFriendLocation() {
        return DEFAULT_Friend_LOCATION;
    }

    public static String getDefaultTemperatureUnits() {
        return DEFAULT_TEMPERATURE_UNITS;
    }

}
