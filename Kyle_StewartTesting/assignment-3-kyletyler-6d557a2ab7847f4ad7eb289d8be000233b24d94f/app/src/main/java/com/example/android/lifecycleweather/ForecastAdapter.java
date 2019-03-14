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

    private OpenWeatherMapUtils.ForecastItem[] mForecastItems;
    private OnForecastItemClickListener mForecastItemClickListener;

    public interface OnForecastItemClickListener {
        void onForecastItemClick(OpenWeatherMapUtils.ForecastItem forecastItem);
    }

    public ForecastAdapter(OnForecastItemClickListener clickListener) {
        mForecastItemClickListener = clickListener;
    }

    public void updateForecastItems(OpenWeatherMapUtils.ForecastItem[] forecastItems) {
        mForecastItems = forecastItems;
        //Log.d(TAG, "bind: persona" + forecastItems[0].personaname);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mForecastItems != null) {
            return mForecastItems.length;
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
        holder.bind(mForecastItems[position]);
    }

    class ForecastItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mForecastDateTV;

        public ForecastItemViewHolder(View itemView) {
            super(itemView);
            mForecastDateTV = itemView.findViewById(R.id.tv_forecast_date);
            itemView.setOnClickListener(this);
        }

        public void bind(OpenWeatherMapUtils.ForecastItem forecastItem) {
            mForecastDateTV.setText(forecastItem.personaname);
           // Log.d(TAG, "bind: persona" + forecastItem.personaname);
            //mForecastDateTV.setText(dateString);
            //mForecastTempDescriptionTV.setText(detailString);
        }

        @Override
        public void onClick(View v) {
            OpenWeatherMapUtils.ForecastItem forecastItem = mForecastItems[getAdapterPosition()];
            mForecastItemClickListener.onForecastItemClick(forecastItem);
        }
    }
}
