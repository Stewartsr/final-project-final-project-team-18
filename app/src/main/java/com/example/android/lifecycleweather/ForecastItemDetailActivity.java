package com.example.android.lifecycleweather;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.lifecycleweather.data.WeatherPreferences;
import com.example.android.lifecycleweather.utils.OpenWeatherMapUtils;

import java.text.DateFormat;

public class ForecastItemDetailActivity extends AppCompatActivity {

    private TextView mDateTV;
    private TextView mTempDescriptionTV;
    private TextView mLowHighTempTV;
    private TextView mWindTV;
    private TextView mHumidityTV;

    private OpenWeatherMapUtils.Player mForecastItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_item_detail);

        mDateTV = findViewById(R.id.tv_date);
        mTempDescriptionTV = findViewById(R.id.tv_temp_description);
        mLowHighTempTV = findViewById(R.id.tv_low_high_temp);
        mWindTV = findViewById(R.id.tv_wind);
        mHumidityTV = findViewById(R.id.tv_humidity);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(OpenWeatherMapUtils.EXTRA_FORECAST_ITEM)) {
            mForecastItem = (OpenWeatherMapUtils.Player)intent.getSerializableExtra(
                    OpenWeatherMapUtils.EXTRA_FORECAST_ITEM
            );
            fillInLayout(mForecastItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forecast_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fillInLayout(OpenWeatherMapUtils.Player forecastItem) {
        String dateString = forecastItem.profileurl;
        String detailString = forecastItem.avatar;
        String lowHighTempString = forecastItem.personaname;
        String windString = forecastItem.steamid;
        int humidityString = forecastItem.personastate;

        mDateTV.setText(dateString);
        mTempDescriptionTV.setText(detailString);
        mLowHighTempTV.setText(lowHighTempString);
        mWindTV.setText(windString);
        mHumidityTV.setText(humidityString);
    }
}
