package cz.teamA.project.jpamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Entity
@Data
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


    public Location(@NonNull String cityName, @NonNull String countryName, String region, double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    //    id - optional: UUID format
//    longitude and latitude according to geographical values ​​(latitude: -90 -> S, 90 -> N,
//    longitude: -180 -> W, 180 -> E)
//    city name - cannot be empty
//    region - optional: may be null
//    Country name - cannot be empty
//    If incorrect data are entered, the user should be notified via an appropriate message.

}
