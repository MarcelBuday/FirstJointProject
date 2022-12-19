package cz.teamA.project.service.api;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AccuWeatherAPI {

    public void getLocationInfo(String city) {
        String urlString = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=mA7XoDQGB5OTqyq0R2zHL87GkBynWE2c&q=" + city;
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(urlString)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//TODO convert Json into City object

//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.setPrettyPrinting();
//            Gson gson = gsonBuilder.create();
//            City cityTest = gson.fromJson(response.body(),City.class);
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
