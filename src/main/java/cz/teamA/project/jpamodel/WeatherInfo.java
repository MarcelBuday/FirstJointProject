package cz.teamA.project.jpamodel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor

public class WeatherInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // toto je potrebne
    private long id;
    private double temperatureMin;
    private double temperatureMax;
    private String windDirection;
    private double windSpeed;

    @ManyToOne
    private Location location;
    private LocalDate date;

    public WeatherInfo(double temperatureMin, double temperatureMax, String windDirection, double windSpeed,
                       Location location, LocalDate date) {
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.location = location;
        this.date = date;
    }

    public WeatherInfo(double temperatureMin, double temperatureMax, String windDirection, double windSpeed, LocalDate date) {
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                ", windDirection='" + windDirection + '\'' +
                ", windSpeed=" + windSpeed +
                ", location=" + location +
                ", date=" + date +
                '}';
    }
}
