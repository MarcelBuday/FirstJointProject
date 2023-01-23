package cz.teamA.project.dto;

import cz.teamA.project.jpamodel.Location;
import lombok.Data;

import java.time.LocalDate;
@Data
public class WeatherInfoDto {
    private long id;
    private double temperatureMin;
    private double temperatureMax;
    private double windDirection;
    private double windSpeed;
    private Location location;
    private int year;
    private int month;
    private int day;

    public WeatherInfoDto(long id, double temperatureMin, double temperatureMax, double windDirection, double windSpeed, Location location, LocalDate date) {
        this.id = id;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.location = location;
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
    }
}
