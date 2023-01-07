package cz.teamA.project.controller;

import cz.teamA.project.model.Model;
import cz.teamA.project.view.ConsoleUI;

import java.util.Scanner;

@org.springframework.stereotype.Controller
public class Controller {
    private final ConsoleUI consoleUI;
    private final Model model;

    private Scanner scanner = new Scanner(System.in);

    public Controller(ConsoleUI consoleUI, Model model) {
        this.consoleUI = consoleUI;
        this.model = model;
    }

    public void appStart() {
        consoleUI.showWelcomeMessage();
        consoleUI.showWhatCanBeDone();
        String whatShouldBeDone = scanner.nextLine();

        label:
        while (true) {
            switch (whatShouldBeDone) {
                case "1":
                    consoleUI.cityName();
                    String city = scanner.nextLine();
                    consoleUI.countryName();
                    String country = scanner.nextLine();
                    consoleUI.latitude();
                    double latitude = Double.parseDouble(scanner.nextLine());
                    consoleUI.longitude();
                    double longitude = Double.parseDouble(scanner.nextLine());
                    consoleUI.region();
                    String region = scanner.nextLine();
                    break label;
                case "2":


                    break label;
                case "3":

                    break label;
                default:
                    consoleUI.unknownChoice();
                    consoleUI.showWhatCanBeDone();
                    whatShouldBeDone = scanner.nextLine();
                    break;
            }


        }

        //test of database
        //model.InsertCity(new Location("mesto", "zeme"));


        // test of connection to API AccuWeather
        // model.getWeatherInfoByLocation("Brno");
    }
}
