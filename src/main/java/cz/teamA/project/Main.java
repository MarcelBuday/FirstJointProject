package cz.teamA.project;

import cz.teamA.project.consoleUI.ConsoleUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        final ConsoleUI consoleUI = new ConsoleUI();
        SpringApplication.run(Main.class, args);
        consoleUI.welcome();
    }

}
