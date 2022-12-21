package cz.teamA.project.service.api;

import com.google.gson.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccuWeatherAPI {
    private GsonBuilder gsonBuilder;
    private Gson gson;
    private int locationKey;

    public AccuWeatherAPI() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }

    public void getLocationInfo(String city) {
        String urlString = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=mA7XoDQGB5OTqyq0R2zHL87GkBynWE2c&q=" + city;
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
        String urlString = "http://dataservice.accuweather.com/currentconditions/v1/" + key + "?apikey=mA7XoDQGB5OTqyq0R2zHL87GkBynWE2c&details=true";
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray jsonArray = gson.fromJson(response.body(), JsonArray.class);


            System.out.println(jsonArray);
            List<JsonElement> jsonElements = jsonArray.asList();
            for (JsonElement e:jsonElements) {
                System.out.println(e);
            }

//            double temperature = jsonArray.("Temperature").getAsJsonObject()
//                    .getAsJsonObject("Metric")
//                    .get("Value")
//                    .getAsDouble();
            double pressure = jsonArray.getAsJsonObject().getAsJsonObject("Pressure")
                    .getAsJsonObject("Metric")
                    .get("Value")
                    .getAsDouble();
            double humidity = jsonArray.getAsJsonObject().get("RelativeHumidity").getAsDouble();
            double windDirection = jsonArray.getAsJsonObject()
                    .getAsJsonObject("Wind")
                    .getAsJsonObject("Direction"
                    ).get("Degrees").getAsDouble();
            double windSpeed =  jsonArray.getAsJsonObject().getAsJsonObject("Wind")
                    .getAsJsonObject("Speed")
                    .getAsJsonObject("Metric")
                    .get("Degrees")
                    .getAsDouble();

           // System.out.println("temperature: " + temperature + ", pressure:  " + pressure + ", humidity: " + humidity + ", wind direction: " + windDirection + ", speed: " + windSpeed);

//convert Json into Weather object

//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.setPrettyPrinting();
//            Gson gson = gsonBuilder.create();
//            City cityTest = gson.fromJson(response.body(),City.class);

//            Wind": {
//      "Direction": {
//        "Degrees": 180,
//        "Localized": "S",
//        "English": "S"
//      },
//      "Speed": {
//        "Metric": {
//          "Value": 7.4,

//            "Pressure": {
//                "Metric": {
//                    "Value": 1017,

//            "RelativeHumidity": 100,

//            "Temperature": {
//                "Metric": {
//                    "Value": 0,
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    //TODO in progress
    private String getWeatherValue(JsonArray jsonArray, String... keys){
       // jsonArray.asList().stream().map(e -> e.getAsJsonObject().get())
        return null;
    }

}
