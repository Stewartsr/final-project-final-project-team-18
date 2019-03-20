package com.example.android.steamLite;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.steamLite.utils.OpenSteamMapUtils;

public class FriendItemDetailActivity extends AppCompatActivity {

    private TextView mProfileUrl;
    private ImageView mAvatar;
    private TextView mPersonaName;
    private TextView mSteamId;
    private TextView mPersonaState;

    private OpenSteamMapUtils.Player mFriendItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_item_detail);

        mProfileUrl = findViewById(R.id.profileurl);
        mAvatar = findViewById(R.id.avatar);
        mPersonaName = findViewById(R.id.personaname);
        mSteamId = findViewById(R.id.steamid);
        mPersonaState = findViewById(R.id.personastate);

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
            // implicit intent for showing current user profile on web
            case R.id.action_profile:
                viewProfileOnWeb();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // this
    public void viewProfileOnWeb() {
        if(mFriendItem != null) {
            Uri profileURI = Uri.parse(mFriendItem.profileurl);
            Intent intent = new Intent(Intent.ACTION_VIEW, profileURI);

            if(intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }


    private void fillInLayout(OpenSteamMapUtils.Player FriendItem) {
        String profileUrl = "Profile URL:\n" + FriendItem.profileurl;
        String personaName = "Username:\n" + FriendItem.personaname;
        String steamId = "Steam ID:\n" + FriendItem.steamid;

        String a;
        switch (FriendItem.personastate){
            case 0:
                a = "Offline";
                break;
            case 1:
                a = "Online";
                break;
            case 2:
                a = "Busy";
                break;
            case 3:
                a = "Away";
                break;
            case 4:
                a = "Snooze";
                break;
            case 5:
                a = "Looking to Trade";
                break;
            case 6:
                a = "Looking to Play";
                break;
            default:
                a = "Thinking";
        }

        String personaState = "Status:\n" + a;

        mProfileUrl.setText(profileUrl);
        mPersonaName.setText(personaName);
        mSteamId.setText(steamId);
        mPersonaState.setText(personaState);

        String iconURL = OpenSteamMapUtils.buildIconURL(FriendItem.avatarfull);
        Glide.with(mAvatar.getContext()).load(iconURL).into(mAvatar);
    }
}
