package cz.teamA.project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import cz.teamA.project.jpamodel.WeatherInfo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static java.io.File.separator;


@Service
public class FileService<T> {
    private final File locationFile;
    private final File weatherInfoFile;
    private final Gson gson;

    public FileService() {
        this.gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
        String repository = System.getProperty("user.dir") + "\\src\\main\\java\\cz\\teamA\\project\\repository\\data";
        repository = !separator.equals("\\") ? repository.replaceAll(Matcher.quoteReplacement("\\"), separator) : repository;
        this.locationFile = new File(repository, "locations.json");
        this.weatherInfoFile = new File(repository, "weatherInfo.json");
    }


    public void updateDataInFile(List<T> data) {
        File tempFile = fileSwitcher((Class<T>) data.get(0).getClass());
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data.forEach(d -> {
            try {
                Files.write(tempFile.toPath(), gson.toJson(d).getBytes(), StandardOpenOption.APPEND);
            } catch (NullPointerException | IOException ignored) {
            }
        });
    }

    public List<T> getDataFromFile(Class<T> c) {
        File tempFile = fileSwitcher(c);
        String data;
        if (tempFile.exists()) {
            try {
                data = Files.readString(tempFile.toPath());
                JsonReader jsonReader = new JsonReader(new StringReader(data));
                jsonReader.setLenient(true);
                List<T> list = new ArrayList<>();

                while (jsonReader.hasNext()) {
                    list.add(gson.fromJson(jsonReader, c));
                }

                return list;
            } catch (IOException ignored) {
            }
        }
        return null;
    }


    private File fileSwitcher(Class<T> c) {
        switch (c.getSimpleName()) {
            case "Location" -> {
                return locationFile;
            }
            case "WeatherInfoDto" -> {
                return weatherInfoFile;
            }
        }
        return null;
    }
}
