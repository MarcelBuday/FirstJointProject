package cz.teamA.project.model;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class Model {
    private final LocationRepository locationRepository;
    private final WeatherAPIService weatherAPIService;


    public Model(LocationRepository locationRepository, WeatherAPIService weatherAPIService) {
        this.locationRepository = locationRepository;
        this.weatherAPIService = weatherAPIService;

    }

    public void getWeatherInfoByLocation(String location) {
        weatherAPIService.getWeatherInfoByLocation(location);
    }

    public void InsertLocation(Location location){
        locationRepository.save(location);
    }
}
