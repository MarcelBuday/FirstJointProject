package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;
import cz.teamA.project.repository.WeatherInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherInfoService  {
    private final WeatherInfoRepository weatherInfoRepository;

    public WeatherInfoService(WeatherInfoRepository weatherInfoRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
    }

    public void insertWeatherFiveDaysForLocation(List<WeatherInfo> weatherInfos) {
        weatherInfoRepository.saveAll(weatherInfos);
    }

}
