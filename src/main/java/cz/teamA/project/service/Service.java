package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@org.springframework.stereotype.Service
public class Service<T> {
    private final LocationService locationService;
    private final APIService APIService;
    private final Scanner scanner;
    private final WeatherInfoService weatherInfoService;
    private final FileService fileService;


    public Service(LocationService locationService, APIService APIService, WeatherInfoService weatherInfoService, FileService fileService) {
        this.locationService = locationService;
        this.APIService = APIService;
        this.weatherInfoService = weatherInfoService;
        this.fileService = fileService;
        this.scanner = new Scanner(System.in);


    }

//    public void getWeatherInfoByLocation(String location) {
//        APIService.getWeatherInfoByLocation(location);
//    }

    public void addLocation() {

        System.out.println("Enter name of city");
        String city = scanner.nextLine();
        while (city.equals("") /*|| city.matches("^[^0-9]+$")*/) {
            System.out.println("You did not enter city name:");
            city = scanner.nextLine();
        }
        List<Location> locationInfo = APIService.getLocationInfo(city);
        if (locationInfo.size() > 1) {
            System.out.println("Select location, more locations were found, chose one by number or press A to chose all");
            for (int i = 0; i < locationInfo.size(); i++) {
                System.out.println(i + 1 + " " + locationInfo.get(i));
            }
            String pressed = scanner.nextLine();
            if (pressed.equals("A")) {
                for (Location location : locationInfo) {
                    locationService.insertLocation(location);
                }
            } else {
                int s = Integer.parseInt(pressed);
                locationService.insertLocation(locationInfo.get(s - 1));
            }
        } else if (locationInfo.size() == 1) {
            locationService.insertLocation(locationInfo.get(0));
        } else {
            System.out.println("City not known");

        }





//        System.out.println("Enter name of country");
//        String country = scanner.nextLine();
//        while (country.equals("")) {
//            System.out.println("Country can not be empty. Add country.");
//            country = scanner.nextLine();
//        }
//        System.out.println("Enter latitude of city or press Enter");
//
//        String trylatitude = scanner.nextLine();
//        double latitude = 0d;
//        if (!trylatitude.isEmpty()) {
//            latitude = Double.parseDouble(trylatitude);
//        }
//        System.out.println("Enter longitude of city or press Enter");
//        String trylongitude = scanner.nextLine();
//        double longitude = 0d;
//        if (!trylongitude.isEmpty()) {
//            longitude = Double.parseDouble(trylongitude);
//        }
//        System.out.println("Enter region or press Enter");
//        String region = scanner.nextLine();
//        locationService.insertLocation(city, country, region, longitude, latitude);
    }

    public List<Location> selectAllLocation() {
        return locationService.selectAllLocation();
    }

    public List<WeatherInfo> getWeatherData() {
        List<Location> locations = locationService.selectAllLocation();
        System.out.println("Select location");
        if (!locations.isEmpty()) {
            for (int i = 0; i < locations.size(); i++) {
                System.out.println((i + 1) + " " + locations.get(i));
            }
            String s = scanner.nextLine();
            System.out.println("Select date: ");
            for (int i = 0; i < 5; i++) {
                System.out.println((i + 1) + " = " + LocalDate.now().plusDays(i));
            }
            String s1 = scanner.nextLine();
            LocalDate date;

            //TODO switch neplní žádnou funkci z hlediska vybírání dat o počasí, pouze nastavuje datum.
            label:
            while (true) {
                switch (s1) {
                    case "1":
                        date = LocalDate.now();
                        break label;
                    case "2":
                        date = LocalDate.now().plusDays(1);
                        break label;
                    case "3":
                        date = LocalDate.now().plusDays(2);
                        break label;
                    case "4":
                        date = LocalDate.now().plusDays(3);
                        break label;
                    case "5":
                        date = LocalDate.now().plusDays(4);
                        break label;
                    default:
                        System.out.println("Unknown Choice - enter 1 or 2 or 3 or 4 or 5");
                        s1 = scanner.nextLine();
                        break;

                }
            }
//            LocalDate date;
//            try {
//                date = LocalDate.parse(s1);
//            } catch (DateTimeParseException e) {
//                date = LocalDate.now();
//                date = date.plusDays(1);
//                System.out.println("Wrong format");
//            }

            final List<Location> locationInfo = APIService.getLocationInfo(locations.get(Integer.
                    parseInt(s) - 1).getCityName());

            if (locationInfo.size() > 1) {
                System.out.println("Select location, more locations were found");
                for (int i = 0; i < locationInfo.size(); i++) {
                    System.out.println(i + 1 + " " + locationInfo.get(i));
                }
                s = scanner.nextLine();
                return APIService.getWeatherInfo(locationInfo.get(Integer.parseInt(s) - 1).getAccuWeatherKey());
            } else if (locationInfo.size() == 1) {
                return APIService.getWeatherInfo(locationInfo.get(0).getAccuWeatherKey());
            } else {
                System.out.println("No location known");
            }


        } else {
            System.out.println("No location known");
        }
        return null;

    }

    public void updateData(List<T> data){
            fileService.updateData(data);
    }

    public void getData(Class c){
        fileService.getData(c);
    }


}
