package cz.teamA.project;

import cz.teamA.project.controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class Main {
    private static Controller controller;

    public Main(Controller controller) {
        Main.controller = controller;
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        SpringApplication.run(Main.class, args);
        controller.appStart();
    }

}
