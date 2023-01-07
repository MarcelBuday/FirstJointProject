package cz.teamA.project.view;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class ConsoleUI {
    private final String WELCOME_MESSAGE = "Welcome to WeatherLady app!";
    private final String FIRST_CHOICE = "Press 1 for adding location for weather forecast";
    private final String SECOND_CHOICE = "Press 2 for showing existing locations";
    private final String THIRD_CHOICE = "Press 3 for downloading weather values";


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
}
