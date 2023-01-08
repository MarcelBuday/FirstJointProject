package cz.teamA.project.service.weatherapi;

import com.google.gson.*;
import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;
import cz.teamA.project.service.WeatherInfoService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccuWeatherAPI {
    private GsonBuilder gsonBuilder;
    private Gson gson;
    private final String API_KEY = System.getenv("API_KEY");

    public AccuWeatherAPI() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }

    public List<Location> getLocationInfo(String city) {
        String urlString = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + API_KEY + "&q=" + city;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray jsonArray = gson.fromJson(response.body(), JsonArray.class);
            List<Location> locations = new ArrayList<>();
            for (JsonElement e : jsonArray) {
                String region = e.getAsJsonObject().getAsJsonObject("AdministrativeArea").get("LocalizedName").getAsString();
                double longitude = e.getAsJsonObject().getAsJsonObject("GeoPosition").get("Longitude").getAsDouble();
                double latitude = e.getAsJsonObject().getAsJsonObject("GeoPosition").get("Latitude").getAsDouble();
                int key = e.getAsJsonObject().get("Key").getAsInt();
                String country = e.getAsJsonObject().getAsJsonObject("Country").get("LocalizedName").getAsString();
                Location location = new Location(city, country, region, longitude, latitude, key);
                locations.add(location);
            }
            return locations;
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<WeatherInfo> getWeatherInfo(int key) {
        String urlString = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/124930?apikey=mA7XoDQGB5OTqyq0R2zHL87GkBynWE2c&details=true&metric=true";
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            List<WeatherInfo> weatherInfos = new ArrayList<>();
            JsonArray dailyForecasts = jsonObject.get("DailyForecasts").getAsJsonArray();
            for (JsonElement e : dailyForecasts) {

                double temperatureMin = e.getAsJsonObject().getAsJsonObject("Temperature").getAsJsonObject()
                        .getAsJsonObject("Minimum").getAsJsonObject()
                        .get("Value")
                        .getAsDouble();
                double temperatureMax = e.getAsJsonObject().getAsJsonObject("Temperature")
                        .getAsJsonObject("Maximum")
                        .get("Value")
                        .getAsDouble();
//               double humidity = e.getAsJsonObject().get("RelativeHumidity").getAsDouble();
                String windDirection = e.getAsJsonObject().getAsJsonObject("Day")
                        .getAsJsonObject("Wind")
                        .getAsJsonObject("Direction")
                        .get("Localized")
                        .getAsString();
                double windSpeed = e.getAsJsonObject().getAsJsonObject("Day")
                        .getAsJsonObject("Wind")
                        .getAsJsonObject("Speed")
                        .get("Value")
                        .getAsDouble();
                LocalDate date = LocalDate.parse(e.getAsJsonObject().get("Date").getAsString().substring(0,10));

               WeatherInfo weatherInfo = new WeatherInfo(temperatureMin,temperatureMax,windDirection,windSpeed,date);
               weatherInfos.add(weatherInfo);
            }
            return weatherInfos;
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
