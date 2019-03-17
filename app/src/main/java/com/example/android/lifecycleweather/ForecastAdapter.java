package com.example.android.lifecycleweather;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.lifecycleweather.data.WeatherPreferences;
import com.example.android.lifecycleweather.utils.OpenWeatherMapUtils;

import java.text.DateFormat;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastItemViewHolder> {

    private ArrayList<OpenWeatherMapUtils.Player> mForecastItems;
    private OnForecastItemClickListener mForecastItemClickListener;

    public interface OnForecastItemClickListener {
        void onForecastItemClick(OpenWeatherMapUtils.Player forecastItem);
    }

    public ForecastAdapter(OnForecastItemClickListener clickListener) {
        mForecastItemClickListener = clickListener;
    }

    public void updateForecastItems(ArrayList<OpenWeatherMapUtils.Player> forecastItems) {
        mForecastItems = forecastItems;
        //Log.d(TAG, "bind: persona" + forecastItems[0].personaname);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mForecastItems != null) {
            return mForecastItems.size();
        } else {
            return 0;
        }
    }

    @Override
    public ForecastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastItemViewHolder holder, int position) {
        holder.bind(mForecastItems.get(position));
    }

    class ForecastItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mForecastDateTV; //this is user name
        private TextView mUserStatus;
        private ImageView mWeatherIconTV;


        public ForecastItemViewHolder(View itemView) {
            super(itemView);
            mForecastDateTV = itemView.findViewById(R.id.tv_forecast_date);
            mUserStatus = itemView.findViewById(R.id.tv_forecast_temp_description);
            mWeatherIconTV = itemView.findViewById(R.id.iv_weather_icon);
            itemView.setOnClickListener(this);
        }

        public void bind(OpenWeatherMapUtils.Player forecastItem) {
            mForecastDateTV.setText(forecastItem.personaname);
            String a;
            switch (forecastItem.personastate){
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

            String iconURL = OpenWeatherMapUtils.buildIconURL(forecastItem.avatar);
            Glide.with(mWeatherIconTV.getContext()).load(iconURL).into(mWeatherIconTV);
        }

        @Override
        public void onClick(View v) {
            OpenWeatherMapUtils.Player forecastItem = mForecastItems.get(getAdapterPosition());
            mForecastItemClickListener.onForecastItemClick(forecastItem);
        }
    }
}
