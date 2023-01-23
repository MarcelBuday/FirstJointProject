package cz.teamA.project.service;

import cz.teamA.project.dto.WeatherInfoDto;
import cz.teamA.project.jpamodel.WeatherInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DtoService {

    public List<WeatherInfoDto> toWeatherInfoDtoList(List<WeatherInfo> weatherInfos){
        List<WeatherInfoDto> weatherInfoDtos = new ArrayList<>();
        for (WeatherInfo w: weatherInfos) {
            weatherInfoDtos.add(new WeatherInfoDto(w.getId(), w.getTemperatureMin(),w.getTemperatureMax(),w.getWindDirection(),w.getWindSpeed(),w.getLocation(),w.getDate()));
        }
        return weatherInfoDtos;
    }

    public List<WeatherInfo> toWeatherInfoList(List<WeatherInfoDto> WeatherInfoDtos){
        List<WeatherInfo> weatherInfos = new ArrayList<>();
        for (WeatherInfoDto w: WeatherInfoDtos) {
            weatherInfos.add(new WeatherInfo(w.getTemperatureMin(),w.getTemperatureMax(),w.getWindDirection(),w.getWindSpeed(),w.getLocation(), LocalDate.of(w.getYear(),w.getMonth(),w.getDay())));
        }
        return weatherInfos;
    }
}
