package cz.teamA.project.model;

import cz.teamA.project.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class Model {
    private final CityRepository cityRepository;
    private final WeatherAPIService weatherAPIService;


    public Model(CityRepository cityRepository, WeatherAPIService weatherAPIService) {
        this.cityRepository = cityRepository;
        this.weatherAPIService = weatherAPIService;
    }

    public void getWeatherInfoByLocation(String location) {
        weatherAPIService.getWeatherInfoByLocation(location);
    }
//test
//    public void InsertCity(City city){
//        cityRepository.save(city);
//    }
}
