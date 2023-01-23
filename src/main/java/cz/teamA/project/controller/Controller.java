package cz.teamA.project.controller;

import cz.teamA.project.dto.WeatherInfoDto;
import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;
import cz.teamA.project.service.Service;
import cz.teamA.project.view.ConsoleUI;

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

    public void appStart(){
        consoleUI.appLoading();
        consoleUI.systemMessage(service.getDataFromFile(Location.class),Location.class);
        consoleUI.systemMessage(service.getDataFromFile(WeatherInfoDto.class),WeatherInfoDto.class);
        consoleUI.showWelcomeMessage();


        label:
        while (true) {
            consoleUI.mainMenu();
            String whatShouldBeDone = scanner.nextLine();
            switch (whatShouldBeDone) {
                case "1":
                    consoleUI.cityName();
                    String newCityProposal = service.enterNewCityNameForValidation();
                    if (service.cityNameForValidationAlreadyExistsInOurDatabase(newCityProposal)) {
                        consoleUI.reconfirmDesireToAddEnteredCityName(service.listOfLocationRecordsWithSameCityNameAsArgument(newCityProposal));
                        if (service.needToSaveNewLocationWithTheSameCityName()){
                            service.addLocation(newCityProposal);
                        }
                        // asi to uklada vzdy / pores breaky, zkus napsat obecne zadani nextline do klavesnice a obecnou volbu z klavesnice

                    }
                    service.addLocation(newCityProposal);
                    break;
                case "2":
                    consoleUI.allLocations(service.selectAllLocation());
                    break;
                case "3":
                    consoleUI.weatherInfo(service.getWeatherData());
                    break;
                case "0":
                    System.exit(0);
                    break;
                case "test":
                    service.test(48.7172272,21.2496774);
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
