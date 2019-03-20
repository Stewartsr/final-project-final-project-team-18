package com.example.android.steamLite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.steamLite.R;
import com.example.android.steamLite.utils.OpenSteamMapUtils;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendItemViewHolder> {

    private ArrayList<OpenSteamMapUtils.Player> mFriendItems;
    private OnFriendItemClickListener mFriendItemClickListener;

    public interface OnFriendItemClickListener {
        void onFriendItemClick(OpenSteamMapUtils.Player FriendItem);
    }

    public FriendAdapter(OnFriendItemClickListener clickListener) {
        mFriendItemClickListener = clickListener;
    }

    public void updateFriendItems(ArrayList<OpenSteamMapUtils.Player> FriendItems) {
        mFriendItems = FriendItems;
        //Log.d(TAG, "bind: persona" + FriendItems[0].personaname);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mFriendItems != null) {
            return mFriendItems.size();
        } else {
            return 0;
        }
    }

    @Override
    public FriendItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.friend_list_item, parent, false);
        return new FriendItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriendItemViewHolder holder, int position) {
        holder.bind(mFriendItems.get(position));
    }

    class FriendItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mFriendDateTV; //this is user name
        private TextView mUserStatus;
        private ImageView mSteamIconTV;


        public FriendItemViewHolder(View itemView) {
            super(itemView);
            mFriendDateTV = itemView.findViewById(R.id.tv_friend_date);
            mUserStatus = itemView.findViewById(R.id.tv_friend_temp_description);
            mSteamIconTV = itemView.findViewById(R.id.iv_steam_icon);
            itemView.setOnClickListener(this);
        }

        public void bind(OpenSteamMapUtils.Player FriendItem) {
            mFriendDateTV.setText(FriendItem.personaname);
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

            mUserStatus.setText(a);

            String iconURL = OpenSteamMapUtils.buildIconURL(FriendItem.avatarfull);
            Glide.with(mSteamIconTV.getContext()).load(iconURL).into(mSteamIconTV);
        }

        @Override
        public void onClick(View v) {
            OpenSteamMapUtils.Player FriendItem = mFriendItems.get(getAdapterPosition());
            mFriendItemClickListener.onFriendItemClick(FriendItem);
        }
    }
}
