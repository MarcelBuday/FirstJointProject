package cz.teamA.project.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AccuWeatherAPI {
    private GsonBuilder gsonBuilder;
    private Gson gson;

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
// convert Json into City object
//            JsonObject jobj = new Gson().fromJson(response.body(), JsonObject.class);
//            int result = jobj.get("key").getAsInt();
//            System.out.println(result);

        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getWeatherInfo(int key, boolean metric) {
        String urlString = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/" + key + "?apikey=mA7XoDQGB5OTqyq0R2zHL87GkBynWE2c&metric=" + metric;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//convert Json into Weather object
            System.out.println(response.body());
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.setPrettyPrinting();
//            Gson gson = gsonBuilder.create();
//            City cityTest = gson.fromJson(response.body(),City.class);
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}
