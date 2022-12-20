package cz.teamA.project.view;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class ConsoleUI {
    private final String welcomeMessage = "Welcome to WeatherLady app!";

    public void welcome() {
        System.out.println(welcomeMessage);
    }
}
