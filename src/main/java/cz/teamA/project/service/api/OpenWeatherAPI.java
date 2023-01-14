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
import java.util.ArrayList;
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

    public void getWeatherInfo(double lat, double lon) {
        String urlString = "http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon=" +
                ""+lon+"&units=metric&appid="+ API_KEY;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            List<WeatherInfo> weatherInfos = new ArrayList<>();
            JsonArray dailyForecasts = jsonObject.get("list").getAsJsonArray();
            for (JsonElement e : dailyForecasts) {

                double temperatureMin = e.getAsJsonObject().getAsJsonObject("Temperature").getAsJsonObject()
                        .getAsJsonObject("Minimum").getAsJsonObject()
                        .get("Value")
                        .getAsDouble();}
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
