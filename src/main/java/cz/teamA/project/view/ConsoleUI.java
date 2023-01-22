package cz.teamA.project.view;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@NoArgsConstructor
@Component
public class ConsoleUI {
    private final String WELCOME_MESSAGE = "Welcome to WeatherLady app!";

    public Object latitude;
    private String enterCityName;

    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void mainMenu() {
        System.out.println("Press 1 for adding location for weather forecast");
        System.out.println("Press 2 for showing existing locations");
        System.out.println("Press 3 for downloading weather values");
        System.out.println("Press 0 for leaving the app");
    }

    public void unknownChoice() {
        System.out.println("Wrong Choice. Try again");
    }

    public void cityName() {
        System.out.println("Enter name of city, cannot stay empty");

    }

    public void countryName() {
        System.out.println("Enter name of country");
    }

    public void latitude() {
        System.out.println("Enter latitude of city or press Enter");
    }

    public void longitude() {
        System.out.println("Enter longitude of city or press Enter");
    }

    public void region() {
        System.out.println("Enter region or press Enter");
    }

    public void allLocations(List<Location> allLocations) {
        allLocations.forEach(location -> System.out.println(location));

    }

    public void weatherInfo(List<WeatherInfo> weatherInfo) {
        if (weatherInfo != null) {
            weatherInfo.forEach(System.out::println);
        }
    }

    public void appLoading() {
        System.out.println("Applicaton loading");
    }

    public void systemMessage(String message, Class c) {
        if (message != null) {
            switch (message) {
                case "UpdateDataFail" ->
                        System.err.println("Data " + c.getSimpleName() + " were not found, file was not created.");
                case "GetDataFail" ->
                        System.err.println("File " + c.getSimpleName() + " was not found, data were not loaded.");
            }
        }
    }

    public void reconfirmDesireToAddEnteredCityName(List<Location> existingRecordsWithSameCityName) {

        System.out.println("City with such name already exist in our database, see below: \n");
        allLocations(existingRecordsWithSameCityName);
        System.out.println("If your requested city is not in the list above, press C to continue adding new location with the city name");

    }
}



