package cz.teamA.project.jpamodel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
@Setter
@NoArgsConstructor

public class WeatherInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // toto je potrebne
    private long id;
    private double temperatureMin;
    private double temperatureMax;
    private double windDirection;
    private double windSpeed;

    @ManyToOne
    private Location location;
    private LocalDate date;

    public WeatherInfo(double temperatureMin, double temperatureMax, double windDirection, double windSpeed,
                       Location location, LocalDate date) {
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.location = location;
        this.date = date;
    }

    public WeatherInfo(double temperatureMin, double temperatureMax, double windDirection, double windSpeed, LocalDate date) {
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.date = date;
    }

    public WeatherInfo(WeatherInfo weatherInfo, Location location ){
        this.temperatureMin = weatherInfo.getTemperatureMin();
        this.temperatureMax = weatherInfo.getTemperatureMax();
        this.windDirection = weatherInfo.getWindDirection();
        this.windSpeed = weatherInfo.getWindSpeed();
        this.location = location;
        this.date = weatherInfo.getDate();
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
