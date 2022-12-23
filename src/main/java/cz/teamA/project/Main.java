package cz.teamA.project;

import cz.teamA.project.controller.Controller;
import cz.teamA.project.model.Model;
import cz.teamA.project.model.WeatherAPIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static Model model;
    private static WeatherAPIService weatherApiService;
    private static Controller controller;

    public Main(Controller controller) {
        Main.controller = controller;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        controller.appStart();
    }

}
