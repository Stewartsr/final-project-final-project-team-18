package com.example.android.steamLite.utils;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class OpenSteamMapUtils {

    public static final  String EXTRA_Friend_ITEM = "com.example.android.steamLite.utils.FriendItem";
    private final static String OWM_Friend_BASE_URL = " http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002";
    private final static String OWM_Friend_APPID_PARAM = "key";
    private final static String OWM_Friend_STEAMID_PARAM = "steamids";
    private final static String OWM_Friend_APPID = "36CA30F19E38A1E627CED21BA4CD3342";
    private final static String OWM_Friend_USERID = "76561198278383790,76561197972970824,76561198064301222,76561198041911745,76561197988781934,76561197983803170,76561197987126495,76561198034302446";

    /*
     * This class is used as a final representation of a single Friend item.  It condenses the
     * classes below that are used for parsing the OWN JSON response with Gson.
     */
    public class MyObject {
        public Response response;
    }

    public class Response {
        private ArrayList<Player> players = new ArrayList<Player>();

    }

    public static class Player implements Serializable {
        public String steamid;
        public String personaname;
        public String profileurl;
        public String avatarfull;
        public int personastate;
    }

    public static String buildFriendURL() {
        return Uri.parse(OWM_Friend_BASE_URL).buildUpon()
                .appendQueryParameter(OWM_Friend_APPID_PARAM, OWM_Friend_APPID)
                .appendQueryParameter(OWM_Friend_STEAMID_PARAM, OWM_Friend_USERID)
                .build()
                .toString();
    }

    public static String buildIconURL(String icon) {
        return icon;
    }

    public static ArrayList<Player> parseFriendJSON(String FriendJSON) {
        Gson gson = new Gson();
        MyObject response = gson.fromJson(FriendJSON, MyObject.class);

        if (response != null && response.response != null) {
            return response.response.players;
        }
        else {
            return null;
        }
    }
}
