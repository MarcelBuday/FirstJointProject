package cz.teamA.project.model;

import cz.teamA.project.jpamodel.Coordinates;
import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void insertLocation(String cityName, String countryName, String region, double longitude, double latitude){
        Location location = new Location(cityName,countryName,region,longitude, latitude);
        locationRepository.save(location);

    }

    public List<Location> selectAllLocation() {
        return locationRepository.findAll();
    }

}
