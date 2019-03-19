package com.example.android.steamLite;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.steamLite.data.SteamPreferences;
import com.example.android.steamLite.utils.NetworkUtils;
import com.example.android.steamLite.utils.OpenSteamMapUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements com.example.android.steamLite.FriendAdapter.OnFriendItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mFriendLocationTV;
    private RecyclerView mFriendItemsRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;
    private com.example.android.steamLite.FriendAdapter mFriendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Remove shadow under action bar.
        getSupportActionBar().setElevation(0);

        mFriendLocationTV = findViewById(R.id.tv_friend_location);
        mFriendLocationTV.setText(SteamPreferences.getDefaultFriendLocation());

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        mLoadingErrorMessageTV = findViewById(R.id.tv_loading_error_message);
        mFriendItemsRV = findViewById(R.id.rv_friend_items);

        mFriendAdapter = new com.example.android.steamLite.FriendAdapter(this);
        mFriendItemsRV.setAdapter(mFriendAdapter);
        mFriendItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mFriendItemsRV.setHasFixedSize(true);

        loadFriend();
    }

    @Override
    public void onFriendItemClick(OpenSteamMapUtils.Player FriendItem) {
        Intent intent = new Intent(this, com.example.android.steamLite.FriendItemDetailActivity.class);
        intent.putExtra(OpenSteamMapUtils.EXTRA_Friend_ITEM, FriendItem);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.action_location:
                showFriendLocation();
                return true;
            */
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadFriend() {
        String openSteamMapFriendURL = OpenSteamMapUtils.buildFriendURL(
                SteamPreferences.getDefaultFriendLocation(),
                SteamPreferences.getDefaultTemperatureUnits()
        );
        Log.d(TAG, "got Friend url: " + openSteamMapFriendURL);
        new OpenSteamMapFriendTask().execute(openSteamMapFriendURL);
    }

    public void showFriendLocation() {
        Uri geoUri = Uri.parse("geo:0,0").buildUpon()
                .appendQueryParameter("q", SteamPreferences.getDefaultFriendLocation())
                .build();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    class OpenSteamMapFriendTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicatorPB.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String openSteamMapURL = params[0];
            String FriendJSON = null;
            try {
                FriendJSON = NetworkUtils.doHTTPGet(openSteamMapURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return FriendJSON;
        }

        @Override
        protected void onPostExecute(String FriendJSON) {
            mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
            if (FriendJSON != null) {
                mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
                mFriendItemsRV.setVisibility(View.VISIBLE);
                ArrayList<OpenSteamMapUtils.Player> repos = OpenSteamMapUtils.parseFriendJSON(FriendJSON);
                mFriendAdapter.updateFriendItems(repos);
            } else {
                mFriendItemsRV.setVisibility(View.INVISIBLE);
                mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
            }
        }
    }
}
