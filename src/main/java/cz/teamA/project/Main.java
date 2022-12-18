package cz.teamA.project;

import cz.teamA.project.service.APIService;
import cz.teamA.project.view.ConsoleUI;
import cz.teamA.project.model.City;
import cz.teamA.project.service.CityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {
    private static  CityService cityService;
    private static APIService apiService;

    public Main(CityService cityService,APIService apiService) {
        this.cityService = cityService;
       this.apiService = apiService;
    }

    public static void main(String[] args){
        final ConsoleUI consoleUI = new ConsoleUI();
        SpringApplication.run(Main.class, args);

        cityService.InsertCity(new City("mesto","zeme"));
        //test připojení k API AccuWeather
       // apiService.getLocation("Brno");
    }

}
