package cz.teamA.project;

import cz.teamA.project.consoleUI.ConsoleUI;
import cz.teamA.project.model.City;
import cz.teamA.project.repository.CityRepository;
import cz.teamA.project.service.CityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    static CityService cityService;

    public Main(CityService cityService) {
        this.cityService = cityService;
    }

    public static void main(String[] args) {
        final ConsoleUI consoleUI = new ConsoleUI();
        SpringApplication.run(Main.class, args);
        consoleUI.welcome();
        cityService.InsertCity(new City("mesto","zeme"));


    }

}
