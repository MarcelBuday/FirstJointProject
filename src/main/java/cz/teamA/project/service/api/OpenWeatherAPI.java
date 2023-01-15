package cz.teamA.project.service.api;

import com.google.gson.*;
import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OpenWeatherAPI {
    private GsonBuilder gsonBuilder;
    private Gson gson;
    private final String API_KEY = "b4309b52c7af87a0e23ab377df60f905";

    public OpenWeatherAPI() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }

    public List<Location> getLocationInfo(String city) {
        String urlString = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&appid=" + API_KEY;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray jsonArray = gson.fromJson(response.body(), JsonArray.class);
            List<Location> locationInfo = new ArrayList<>();
//            double latitude = jsonArray<
//            double longitude = jsonArray.get("lon").getAsDouble();
//            System.out.println(latitude + " " + longitude);
            for (JsonElement e : jsonArray) {
                double latitude = e.getAsJsonObject().get("lat").getAsDouble();
                double longitude = e.getAsJsonObject().get("lon").getAsDouble();
                System.out.println(latitude + " " + longitude);
            }

            return locationInfo;
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<WeatherInfo> getWeatherInfo(double lat, double lon) {
        String urlString = "http://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" +
                "" + lon + "&units=metric&appid=" + API_KEY;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            List<WeatherInfo> weatherInfos = new ArrayList<>();
            LocalDate localDate = LocalDate.now();
            JsonArray dailyForecasts = jsonObject.get("list").getAsJsonArray();
            List<Double> temperatures = new ArrayList<>();
            List<Double> windSpeeds = new ArrayList<>();
            List<Double> windDirections = new ArrayList<>();
            int count = 0;

            for (JsonElement e : dailyForecasts) {
                LocalDate day = LocalDate.parse(e.getAsJsonObject()
                        .get("dt_txt")
                        .getAsString().substring(0, 10));
                double temperature = e.getAsJsonObject().getAsJsonObject("main")
                        .get("temp")
                        .getAsDouble();
                double windSpeed = e.getAsJsonObject().getAsJsonObject("wind")
                        .get("speed")
                        .getAsDouble();
                double windDirection = e.getAsJsonObject().getAsJsonObject("wind")
                        .get("deg")
                        .getAsDouble();
                if (localDate.isEqual(day)) {
                    temperatures.add(temperature);
                    windSpeeds.add(windSpeed);
                    windDirections.add(windDirection);
                } else if (!localDate.isEqual(day)) {
                    double temperatureMin = temperatures.stream().min(Double::compare).get();
                    double temperatureMax = temperatures.stream().max(Double::compare).get();
                    temperatures = new ArrayList<>();
                    temperatures.add(temperature);
                    double windSpeedAverage = windSpeeds.stream().mapToDouble(ws -> ws).average().getAsDouble();
                    double windDirectionAverage = windDirections.stream().mapToDouble(wd -> wd).average().getAsDouble();
                    windSpeeds = new ArrayList<>();
                    windDirections = new ArrayList<>();
                    windSpeeds.add(windSpeed);
                    windDirections.add(windDirection);
                    weatherInfos.add(new WeatherInfo(temperatureMin, temperatureMax, windDirectionAverage, windSpeedAverage, localDate));
                    localDate = localDate.plusDays(1);
                }
                if (count == dailyForecasts.size() - 1) {
                    double temperatureMin = temperatures.stream().min(Double::compare).get();
                    double temperatureMax = temperatures.stream().max(Double::compare).get();
                    double windSpeedAverage = windSpeeds.stream().mapToDouble(ws -> ws).average().getAsDouble();
                    double windDirectionAverage = windDirections.stream().mapToDouble(wd -> wd).average().getAsDouble();
                    weatherInfos.add(new WeatherInfo(temperatureMin, temperatureMax, windDirectionAverage, windSpeedAverage, localDate));
                }
                count++;
            }
            System.out.println(weatherInfos);
            return weatherInfos;
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
