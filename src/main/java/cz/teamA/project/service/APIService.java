package cz.teamA.project.service;

import cz.teamA.project.service.apiservice.AccuWeatherAPIService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class APIService {
private final AccuWeatherAPIService accuWeatherAPIService;

public APIService(AccuWeatherAPIService accuWeatherAPIService){
    this.accuWeatherAPIService = accuWeatherAPIService;
}

public void getLocationInfo(String city){
    accuWeatherAPIService.getLocationInfo(city);
}

}
