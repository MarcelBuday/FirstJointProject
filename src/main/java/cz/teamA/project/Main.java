package cz.teamA.project;

import cz.teamA.project.controller.Controller;
import cz.teamA.project.service.APIService;
import cz.teamA.project.service.CityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static CityService cityService;
    private static APIService apiService;
    private static Controller controller;

    public Main(CityService cityService, APIService apiService, Controller controller) {
        Main.cityService = cityService;
        Main.apiService = apiService;
        Main.controller = controller;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        controller.appStart();

        //test of database
        //cityService.InsertCity(new City("mesto", "zeme"));


       // test of connection to API AccuWeather
         //apiService.getInfo("Brno");
    }

}
