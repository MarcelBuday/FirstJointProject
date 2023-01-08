package cz.teamA.project.service.weatherapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
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

    public void getLocationInfo(String city) {
        String urlString = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + API_KEY + "&q=" + city;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray jsonArray = gson.fromJson(response.body(), JsonArray.class);
            Map<String, Integer> locationInfo = new HashMap<>();
            for (JsonElement e : jsonArray) {
                locationInfo.put(e.getAsJsonObject().getAsJsonObject("AdministrativeArea").get("LocalizedName").getAsString(), e.getAsJsonObject().get("Key").getAsInt());
            }
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getWeatherInfo(int key) {
        String urlString = "http://dataservice.accuweather.com/currentconditions/v1/" + key + "?apikey=" + API_KEY + "&details=true";
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray jsonArray = gson.fromJson(response.body(), JsonArray.class);
            JsonElement jsonElement = jsonArray.get(0);
            double temperature = jsonElement.getAsJsonObject().getAsJsonObject("Temperature").getAsJsonObject()
                    .getAsJsonObject("Metric").getAsJsonObject()
                    .get("Value")
                    .getAsDouble();
            double pressure = jsonElement.getAsJsonObject().getAsJsonObject("Pressure")
                    .getAsJsonObject("Metric")
                    .get("Value")
                    .getAsDouble();
            double humidity = jsonElement.getAsJsonObject().get("RelativeHumidity").getAsDouble();
            double windDirection = jsonElement.getAsJsonObject()
                    .getAsJsonObject("Wind")
                    .getAsJsonObject("Direction"
                    ).get("Degrees").getAsDouble();
            double windSpeed = jsonElement.getAsJsonObject().getAsJsonObject("Wind")
                    .getAsJsonObject("Speed")
                    .getAsJsonObject("Metric")
                    .get("Value")
                    .getAsDouble();
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
