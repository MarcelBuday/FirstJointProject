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
    private final String FIRST_CHOICE = "Press 1 for adding location for weather forecast";
    private final String SECOND_CHOICE = "Press 2 for showing existing locations";
    private final String THIRD_CHOICE = "Press 3 for downloading weather values";
    public Object latitude;
    private String enterCityName;

    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void showWhatCanBeDone() {
        System.out.println(FIRST_CHOICE);
        System.out.println(SECOND_CHOICE);
        System.out.println(THIRD_CHOICE);
    }

    public void unknownChoice() {
        System.out.println("Wrong Choice. Try again");
    }

    public void cityName() {
        System.out.println("Enter name of city");

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
        weatherInfo.forEach(System.out::println);
    }

    public void appLoading(){
        System.out.println("Applicaton loading");
    }
    public void systemMessage(String message, Class c) {
        if (message != null) {
            switch (message) {
                case "UpdateDataFail" ->
                        System.out.println("Data " + c.getSimpleName() + " were not found, file was not created.");
                case "GetDataFail" ->
                        System.out.println("File " + c.getSimpleName() + " was not found, data were not loaded.");
            }
        }
    }
}



