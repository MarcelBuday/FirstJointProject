package cz.teamA.project.controller;

import cz.teamA.project.model.Model;
import cz.teamA.project.view.ConsoleUI;

@org.springframework.stereotype.Controller
public class Controller {
    private final ConsoleUI consoleUI;
    private final Model model;

    public Controller(ConsoleUI consoleUI, Model model) {
        this.consoleUI = consoleUI;
        this.model = model;
    }

    public void appStart() {
        consoleUI.showWelcomeMessage();


        //test of database
        //model.InsertCity(new Location("mesto", "zeme"));


        // test of connection to API AccuWeather
       // model.getWeatherInfoByLocation("Brno");
    }
}
