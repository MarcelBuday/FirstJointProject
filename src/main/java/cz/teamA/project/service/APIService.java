package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;
import cz.teamA.project.service.api.AccuWeatherAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIService {
    private final AccuWeatherAPI accuWeatherAPI;

    public APIService(AccuWeatherAPI accuWeatherAPI) {
        this.accuWeatherAPI = accuWeatherAPI;
    }

    public List<Location> getLocationInfo(String location) {
        return accuWeatherAPI.getLocationInfo(location);
    }

    public List<WeatherInfo> getWeatherInfo(int key) {
        return accuWeatherAPI.getWeatherInfo(key);
    }

}
