package cz.teamA.project.service;

import cz.teamA.project.model.City;
import cz.teamA.project.repository.CityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository cityRepository;


    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void InsertCity(City city){
        cityRepository.save(city);
    }
}
