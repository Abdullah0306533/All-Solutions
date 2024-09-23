package com.projects.solutionpack.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.projects.solutionpack.model.currentweathermodel.CurrentWeatherResponse;
import com.projects.solutionpack.repository.weatherrepository.WeatherRepository;


/**
 * ViewModel class for managing and providing weather data.
 * This class interacts with the repository to fetch weather data and exposes it as LiveData.
 */
public class WeatherViewModel extends ViewModel {

    private WeatherRepository weatherRepository;
    private LiveData<CurrentWeatherResponse> weatherResponseLiveData;

    /**
     * Initializes the WeatherViewModel and creates an instance of WeatherRepository.
     */
    public WeatherViewModel() {
        weatherRepository = new WeatherRepository();
    }

    /**
     * Fetches the current weather data for a specified city.
     * This method returns a LiveData object containing the current weather response.
     *
     * @param city The name of the city for which to fetch the current weather.
     * @return A LiveData object containing the current weather response.
     */
    public LiveData<CurrentWeatherResponse> getCurrentWeather(String city) {
        weatherResponseLiveData = weatherRepository.getWeather(city);
        return weatherResponseLiveData;
    }
}
