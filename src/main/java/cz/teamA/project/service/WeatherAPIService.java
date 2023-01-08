package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.service.weatherapi.AccuWeatherAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherAPIService {
    private final AccuWeatherAPI accuWeatherAPI;

    public WeatherAPIService(AccuWeatherAPI accuWeatherAPI) {
        this.accuWeatherAPI = accuWeatherAPI;
    }

    public void getLocationInfo(String location) {
        List<Location> locations =accuWeatherAPI.getLocationInfo(location);
        locations.forEach(location1 -> System.out.println(location1));
        //accuWeatherAPI.getWeatherInfo(123291);
    }

}
