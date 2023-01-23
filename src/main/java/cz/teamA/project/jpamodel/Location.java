package cz.teamA.project.jpamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Entity
@Data
@Setter
@NoArgsConstructor

public class Location {
    @GeneratedValue(strategy = GenerationType.UUID) // Generuje to unikatne ID do DB
    @Id // toto je potrebne
    private UUID uuid;

    private double longitude;
    private double latitude;
    private String region;
    @NonNull
    private String cityName;
    @NonNull
    private String countryName;
    private int accuWeatherKey;


    public Location(@NonNull String cityName, @NonNull String countryName, String region, double longitude, double latitude, int accuWeatherKey) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.cityName = cityName;
        this.countryName = countryName;
        this.accuWeatherKey = accuWeatherKey;
    }

    public Location(UUID uuid, double longitude, double latitude, String region, @NonNull String cityName, @NonNull String countryName, int accuWeatherKey) {
        this.uuid = uuid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.cityName = cityName;
        this.countryName = countryName;
        this.accuWeatherKey = accuWeatherKey;
    }

    public int getAccuWeatherKey() {
        return accuWeatherKey;
    }

    @Override
    public String toString() {
        return cityName + " Country: " + countryName + " Region: " + region + " Longitude: " + longitude + " Latitude: " + latitude + "\n";
    }
}

//    id - optional: UUID format
//    longitude and latitude according to geographical values ​​(latitude: -90 -> S, 90 -> N,
//    longitude: -180 -> W, 180 -> E)
//    city name - cannot be empty
//    region - optional: may be null
//    Country name - cannot be empty
//    If incorrect data are entered, the user should be notified via an appropriate message.


