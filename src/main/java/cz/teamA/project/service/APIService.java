package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;
import cz.teamA.project.service.api.AccuWeatherAPI;
import cz.teamA.project.service.api.OpenWeatherAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//TODO napojit zbylé API
//vypocet prumeru
public class APIService {
    private final AccuWeatherAPI accuWeatherAPI;
    private final OpenWeatherAPI openWeatherAPI;

    public APIService(AccuWeatherAPI accuWeatherAPI, OpenWeatherAPI openWeatherAPI) {
        this.accuWeatherAPI = accuWeatherAPI;
        this.openWeatherAPI = openWeatherAPI;
    }

    public List<Location> getLocationInfo(String location) {
        return accuWeatherAPI.getLocationInfo(location);
    }

    public List<WeatherInfo> getWeatherInfo(Location location) {
        final List<WeatherInfo> weatherInfo = accuWeatherAPI.getWeatherInfo(location.getAccuWeatherKey());
        final List<WeatherInfo> weatherInfo1 = openWeatherAPI.getWeatherInfo(location.getLatitude(), location.getLongitude());
        return null;
    }
//public void test (double lat, double lon){
//        openWeatherAPI.getWeatherInfo(lat,lon);
//}
}
