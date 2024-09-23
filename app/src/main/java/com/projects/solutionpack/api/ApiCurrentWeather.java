package com.projects.solutionpack.api;



import com.projects.solutionpack.model.currentweathermodel.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Interface for API calls
public interface ApiCurrentWeather {
    @GET("current.json") // Assuming the endpoint is 'weather'
    Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("q") String city,
            @Query("key") String apiKey // Add API key directly as a query parameter

    );
}