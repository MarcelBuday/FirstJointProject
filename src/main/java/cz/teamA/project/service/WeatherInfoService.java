package cz.teamA.project.service;

import cz.teamA.project.repository.WeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherInfoService  {
    private final WeatherRepository weatherRepository;

    public WeatherInfoService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }
}
