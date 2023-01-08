package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Coordinates;
import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.repository.LocationRepository;

import java.util.List;
import java.util.Scanner;

@org.springframework.stereotype.Service
public class Service {
    private final LocationService locationService;
    private final WeatherAPIService weatherAPIService;
    private final Scanner scanner;


    public Service(LocationService locationService, WeatherAPIService weatherAPIService) {
        this.locationService = locationService;
        this.weatherAPIService = weatherAPIService;
        this.scanner = new Scanner(System.in);


    }

//    public void getWeatherInfoByLocation(String location) {
//        weatherAPIService.getWeatherInfoByLocation(location);
//    }

    public void addLocation() {

        System.out.println("Enter name of city");
        String city = scanner.nextLine();
        while (city.equals("") /*|| city.matches("^[^0-9]+$")*/) {
            System.out.println("Unknown city, try again:");
            city = scanner.nextLine();
        }
        System.out.println("Enter name of country");
        String country = scanner.nextLine();
        while (country.equals("")) {
            System.out.println("Country can not be empty. Add country.");
            country = scanner.nextLine();
        }
        System.out.println("Enter latitude of city or press Enter");

        String trylatitude = scanner.nextLine();
        double latitude = 0d;
        if (!trylatitude.isEmpty()) {
            latitude = Double.parseDouble(trylatitude);
        }
        System.out.println("Enter longitude of city or press Enter");
        String trylongitude = scanner.nextLine();
        double longitude = 0d;
        if (!trylongitude.isEmpty()) {
            longitude = Double.parseDouble(trylongitude);
        }
        System.out.println("Enter region or press Enter");
        String region = scanner.nextLine();
        locationService.insertLocation(city, country, region, longitude, latitude);
    }

    public List<Location> selectAllLocation(){
        return locationService.selectAllLocation();
    }

}
