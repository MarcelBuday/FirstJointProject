package cz.teamA.project.controller;

import cz.teamA.project.jpamodel.Coordinates;
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


        label:
        while (true) {
            consoleUI.showWhatCanBeDone();
            String whatShouldBeDone = scanner.nextLine();
            switch (whatShouldBeDone) {
                case "1":
                    consoleUI.cityName();
                    String city = scanner.nextLine();
                    while(city.equals("") /*|| city.matches("^[^0-9]+$")*/){
                        System.out.println("Unknown city, try again:");
                        city = scanner.nextLine();
                    }
                    consoleUI.countryName();
                    String country = scanner.nextLine();
                    while(country.equals("")){
                        System.out.println("Country can not be empty. Add country.");
                        country = scanner.nextLine();
                    }
                    consoleUI.latitude();

                    String trylatitude = scanner.nextLine();
                    double latitude = 0d;
                    if (!trylatitude.isEmpty()){
                        latitude = Double.parseDouble(trylatitude);
                    }
                    consoleUI.longitude();
                    String trylongitude = scanner.nextLine();
                    double longitude = 0d;
                    if (!trylongitude.isEmpty()){
                        longitude = Double.parseDouble(trylongitude);
                    }

                    Coordinates coordinates = new Coordinates(longitude,latitude);
                    consoleUI.region();
                    String region = scanner.nextLine();
                    model.insertLocation(city,country,region,longitude,latitude);
                    break;
                case "2":
                    consoleUI.allLocations(model.selectAllLocation());

                    break;
                case "3":

                    break;
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
