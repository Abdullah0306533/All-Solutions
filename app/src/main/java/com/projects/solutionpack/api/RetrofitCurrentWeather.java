package com.projects.solutionpack.api;




import com.projects.solutionpack.utils.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitCurrentWeather {

    // Singleton instance of the ApiInterface
    static ApiCurrentWeather apiInterface;

    // Method to get the singleton instance of ApiInterface
    public static ApiCurrentWeather getApiInterface() {
        if (apiInterface == null) {
            // Build Retrofit instance
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL) // Set the base URL for the API
                    .addConverterFactory(GsonConverterFactory.create()) // Convert JSON responses to Java objects
                    .build();

            // Create ApiInterface instance
            apiInterface = retrofit.create(ApiCurrentWeather.class);
        }
        return apiInterface;
    }
}
