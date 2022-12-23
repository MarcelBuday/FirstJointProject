package cz.teamA.project.model;

import cz.teamA.project.model.weatherapi.AccuWeatherAPI;
import org.springframework.stereotype.Service;

@Service
public class WeatherAPIService {
    private final AccuWeatherAPI accuWeatherAPI;

    public WeatherAPIService(AccuWeatherAPI accuWeatherAPI) {
        this.accuWeatherAPI = accuWeatherAPI;
    }

    public void getWeatherInfoByLocation(String location) {
        // accuWeatherAPI.getLocationInfo(location);
        //accuWeatherAPI.getWeatherInfo(123291);
    }

}
