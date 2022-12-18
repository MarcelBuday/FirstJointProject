package cz.teamA.project.consoleUI;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


@NoArgsConstructor
public class ConsoleUI {
    private final String welcomeMessage = "Welcome to WeatherLady app!";

    public void welcome() {
        System.out.println(welcomeMessage);
    }
}
