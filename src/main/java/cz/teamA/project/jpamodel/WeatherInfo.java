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
    private double temperature;
    private double pressure;
    private double windDirection;
    private double windSpeed;
    private double humidity;
    @ManyToOne
    private Location location;
    private LocalDate date;

    public WeatherInfo(double temperature, double pressure, double windDirection, double windSpeed,
                       double humidity, Location location, LocalDate date) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.location = location;
        this.date = date;
    }
}
