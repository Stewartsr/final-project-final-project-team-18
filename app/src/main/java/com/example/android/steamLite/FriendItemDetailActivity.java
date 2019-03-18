package com.example.android.steamLite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.steamLite.utils.OpenSteamMapUtils;

public class FriendItemDetailActivity extends AppCompatActivity {

    private TextView mProfileUrl;
    private TextView mAvatar;
    private TextView mPersonaName;
    private TextView mSteamId;
    private TextView mPersonaState;

    private OpenSteamMapUtils.Player mFriendItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_item_detail);

        mProfileUrl = findViewById(R.id.tv_date);
        mAvatar = findViewById(R.id.tv_temp_description);
        mPersonaName = findViewById(R.id.tv_low_high_temp);
        mSteamId = findViewById(R.id.tv_wind);
        mPersonaState = findViewById(R.id.tv_humidity);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(OpenSteamMapUtils.EXTRA_Friend_ITEM)) {
            mFriendItem = (OpenSteamMapUtils.Player)intent.getSerializableExtra(
                    OpenSteamMapUtils.EXTRA_Friend_ITEM
            );
            fillInLayout(mFriendItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.friend_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fillInLayout(OpenSteamMapUtils.Player FriendItem) {
        String profileUrl = FriendItem.profileurl;
        String avatar = FriendItem.avatar;
        String personaName = FriendItem.personaname;
        String steamId = FriendItem.steamid;
        int personaState = FriendItem.personastate;

        mProfileUrl.setText(profileUrl);
        mAvatar.setText(avatar);
        mPersonaName.setText(personaName);
        mSteamId.setText(steamId);
        mPersonaState.setText(personaState);
    }
}
