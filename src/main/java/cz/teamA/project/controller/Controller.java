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
        while (true) {
            if (whatShouldBeDone.equals("1")) {


                break;
            } else if (whatShouldBeDone.equals("2")) {

                break;
            } else if (whatShouldBeDone.equals("3")) {

                break;
            } else {
                consoleUI.unknownChoice();
                consoleUI.showWhatCanBeDone();
                whatShouldBeDone = scanner.nextLine();
            }
        }

        //test of database
        //model.InsertCity(new Location("mesto", "zeme"));


        // test of connection to API AccuWeather
        // model.getWeatherInfoByLocation("Brno");
    }
}
