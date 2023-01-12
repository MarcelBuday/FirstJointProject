package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static java.io.File.separator;

@Service
public class FileService<T> {

    private boolean dataLoaded = false;
    private final File locationFile;
    private final File weatherInfoFile;

    public FileService() {
        String repository = System.getProperty("user.dir") + "\\src\\main\\java\\cz\\teamA\\project\\repository";
        repository = !separator.equals("\\") ? repository.replaceAll(Matcher.quoteReplacement("\\"), separator) : repository;
        this.locationFile = new File(repository, "locations.txt");
        this.weatherInfoFile = new File(repository, "weatherInfo.txt");
    }


    public void updateData(List<T> data) {
        File tempFile = fileSwitcher((Class<T>) data.get(0).getClass());
        Method method;
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            method = data.get(0).getClass().getMethod("toCSV");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        data.forEach(d -> {
            try {
                Files.write(tempFile.toPath(), method.invoke(d).toString().getBytes(), StandardOpenOption.APPEND);
            } catch (IOException | NullPointerException | InvocationTargetException | IllegalAccessException e) {

            }
        });
    }

    public List<T> getData(Class c) {
        File tempFile = fileSwitcher(c);
        String data;
        if (tempFile.exists()) {
            try {
                data = Files.readString(tempFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            Constructor[] constructors = c.getConstructors();
//            try {
//                Constructor constructor = c.getConstructor();
//                System.out.println(constructor.getParameterCount());
//            } catch (NoSuchMethodException e) {
//                throw new RuntimeException(e);
//            }
//
//            String[] dataSplitByLine = data.split("\n");
//            List<Location> locations = new ArrayList<>();
//            for (String s : dataSplitByLine) {
//                String[] split = s.split(",");
//                try {
//                    locations.add((Location) constructors[0].newInstance(split[0],split[1],split[2],split[3],split[4],split[5]));
//                } catch (InstantiationException e) {
//                    throw new RuntimeException(e);
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                } catch (InvocationTargetException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            // System.out.println(locations);
        }
        return null;
    }


    private File fileSwitcher(Class c) {
        switch (c.getSimpleName()) {
            case "Location" -> {
                return locationFile;
            }
            case "WeatherInfo" -> {
                return weatherInfoFile;
            }
        }
        return null;
    }
}
