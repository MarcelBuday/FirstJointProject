package cz.teamA.project.controller;

import cz.teamA.project.jpamodel.Coordinates;
import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.service.Service;
import cz.teamA.project.view.ConsoleUI;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@org.springframework.stereotype.Controller
public class Controller {
    private final ConsoleUI consoleUI;
    private final Service service;

    private Scanner scanner = new Scanner(System.in);

    public Controller(ConsoleUI consoleUI, Service service) {
        this.consoleUI = consoleUI;
        this.service = service;
    }

    public void appStart() throws IOException, InvocationTargetException, IllegalAccessException {
        consoleUI.showWelcomeMessage();


        label:
        while (true) {
            consoleUI.showWhatCanBeDone();
            String whatShouldBeDone = scanner.nextLine();
            switch (whatShouldBeDone) {
                case "1":
                    service.addLocation();
                    break;
                case "2":
                    consoleUI.allLocations(service.selectAllLocation());
                    break;
                case "3":
                    consoleUI.weatherInfo(service.getWeatherData());
                    break;
                    //development choice
                case "test":
                 //  service.updateData(service.selectAllLocation());
                 //   service.getData(Location.class);
                    break;
                default:
                    consoleUI.unknownChoice();
                    break;
            }


        }

        //test of database
        //service.InsertCity(new Location("mesto", "zeme"));


        // test of connection to API AccuWeather
        // service.getWeatherInfoByLocation("Brno");
    }
}
