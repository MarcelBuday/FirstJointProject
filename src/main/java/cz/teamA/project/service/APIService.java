package cz.teamA.project.service;

import cz.teamA.project.service.api.AccuWeatherAPI;
import org.springframework.stereotype.Service;

@Service
public class APIService {
private final AccuWeatherAPI accuWeatherAPI;

public APIService(AccuWeatherAPI accuWeatherAPI){
    this.accuWeatherAPI = accuWeatherAPI;
}

public void getLocationInfo(String city){
    accuWeatherAPI.getLocationInfo(city);
}

}
