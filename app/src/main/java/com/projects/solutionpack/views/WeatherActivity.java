package com.projects.solutionpack.views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.projects.solutionpack.R;
import com.projects.solutionpack.databinding.ActivityWeatherBinding;
import com.projects.solutionpack.model.currentweathermodel.CurrentWeatherResponse;
import com.projects.solutionpack.viewmodel.WeatherViewModel;

public class WeatherActivity extends AppCompatActivity {
    private WeatherViewModel weatherViewModel;
    private ActivityWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing bindings
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize ViewModel
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        // Check weather button click listener
        binding.checkWeather.setOnClickListener(view -> {
            String cityName = binding.cityNameEdit.getText().toString();
            if (cityName.isEmpty()) {
                Toast.makeText(WeatherActivity.this, "Please enter a city name or wrong city name", Toast.LENGTH_SHORT).show();
                return;
            }



            weatherViewModel.getCurrentWeather(cityName).observe(WeatherActivity.this, new Observer<CurrentWeatherResponse>() {
                @Override
                public void onChanged(CurrentWeatherResponse weatherResponse) {
                    if (weatherResponse != null) {
                        // Update UI with current weather data
                        binding.cloudTextView.setText("Cloud Cover (%) : " + weatherResponse.getCurrent().getCloud().toString());
                        binding.humidityTextView.setText("Humidity (%) : " + weatherResponse.getCurrent().getHumidity().toString());
                        binding.windMphTextView.setText("Wind Speed (mph) : " + weatherResponse.getCurrent().getWindMph().toString());
                        binding.tempCTextView.setText("Temperature (°C) : " + weatherResponse.getCurrent().getTempC().toString());

                        // Update UI with location data
                        binding.locationNameTextView.setText("Location Name : " + weatherResponse.getLocation().getName());
                        binding.regionTextView.setText("Region : " + weatherResponse.getLocation().getRegion());
                        binding.countryTextView.setText("Country : " + weatherResponse.getLocation().getCountry());
                        binding.latitudeTextView.setText("Latitude : " + weatherResponse.getLocation().getLat().toString());
                        binding.longitudeTextView.setText("Longitude : " + weatherResponse.getLocation().getLon().toString());
                        binding.tzIdTextView.setText("Time Zone ID : " + weatherResponse.getLocation().getTzId());
                        binding.localtimeTextView.setText("Local Date & Time : " + weatherResponse.getLocation().getLocaltime());
                    } else {
                        // Handle error or no data
                        Toast.makeText(WeatherActivity.this, "Failed to load weather data", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });


    }
}