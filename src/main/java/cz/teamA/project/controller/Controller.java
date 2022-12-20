package cz.teamA.project.controller;

import cz.teamA.project.view.ConsoleUI;
@org.springframework.stereotype.Controller
public class Controller {
    private final ConsoleUI consoleUI;

    public Controller(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    public void appStart() {
        consoleUI.welcome();
    }
}
