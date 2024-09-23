package com.projects.solutionpack.repository.weatherrepository;



import static com.projects.solutionpack.utils.Utils.API_KEY;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.projects.solutionpack.api.ApiCurrentWeather;
import com.projects.solutionpack.api.RetrofitCurrentWeather;
import com.projects.solutionpack.model.currentweathermodel.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for fetching current weather and forecast data from the API.
 * This class handles the data retrieval using Retrofit and exposes the data via LiveData.
 */
public class WeatherRepository {

    private static final String TAG = "Repository"; // Use a single tag for logging
    private final ApiCurrentWeather apiInterface;


    /**
     * Constructor that initializes the Retrofit API interfaces for current weather and forecast weather.
     */
    public WeatherRepository() {
        apiInterface = RetrofitCurrentWeather.getApiInterface();

    }

    /**
     * Fetches the current weather data for a specified city.
     * The data is fetched asynchronously and returned as LiveData.
     *
     * @param city The name of the city for which to fetch the current weather.
     * @return A LiveData object containing the current weather response.
     */
    public LiveData<CurrentWeatherResponse> getWeather(String city) {
        MutableLiveData<CurrentWeatherResponse> mutableLiveData = new MutableLiveData<>();
        Call<CurrentWeatherResponse> response = apiInterface.getCurrentWeather(city, API_KEY);

        // Enqueue the API call asynchronously and set the response to LiveData on success.
        response.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Log the fetched JSON response
                    Log.d(TAG, "Fetched JSON: " + response.body().toString());
                    // Set the fetched data to the MutableLiveData object
                    mutableLiveData.setValue(response.body());
                } else {
                    // Log an error if the response is not successful
                    Log.e(TAG, "Failed to fetch JSON: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable throwable) {
                // Log an error if the network request fails
                Log.e(TAG, "Network request failed: " + throwable.getMessage());
            }
        });
        return mutableLiveData;
    }


}
