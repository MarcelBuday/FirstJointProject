package cz.teamA.project.service;

import cz.teamA.project.repository.WeatherInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherInfoService  {
    private final WeatherInfoRepository weatherInfoRepository;

    public WeatherInfoService(WeatherInfoRepository weatherInfoRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
    }
}
