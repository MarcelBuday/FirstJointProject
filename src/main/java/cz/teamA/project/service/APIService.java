package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.service.api.AccuWeatherAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIService {
    private final AccuWeatherAPI accuWeatherAPI;

    public APIService(AccuWeatherAPI accuWeatherAPI) {
        this.accuWeatherAPI = accuWeatherAPI;
    }

    public void getLocationInfo(String location) {
        List<Location> locations =accuWeatherAPI.getLocationInfo(location);
        locations.forEach(location1 -> System.out.println(location1));
        //accuWeatherAPI.getWeatherInfo(123291);
    }

    public void getWeatherInfo() {
//        accuWeatherAPI.getWeatherInfo(0).forEach(d-> System.out.println(d));
    }

}
