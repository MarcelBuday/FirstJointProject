package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.jpamodel.WeatherInfo;

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

    public void addLocation(String newCityProposal) {
//
//        System.out.println("Enter name of city");
//        String city = scanner.nextLine();
//        while (city.equals("") /*|| city.matches("^[^0-9]+$")*/) {
//            System.out.println("You did not enter city name:");
//            city = scanner.nextLine();
//        }
        List<Location> locationInfoAccuWeather = APIService.getLocationInfo(newCityProposal);
        List<Location> locationsInDatabase = locationService.selectAllLocationsWithEnteredCityName(newCityProposal);
        if (locationInfoAccuWeather.size() > 1) {
            System.out.println("Select location, more locations were found, chose one by number or press A to chose all");
            for (int i = 0; i < locationInfoAccuWeather.size(); i++) {
                System.out.println(i + 1 + " " + locationInfoAccuWeather.get(i));
            }
            String pressed = scanner.nextLine();
            if (pressed.equals("A")) {
                for (Location location : locationInfoAccuWeather) {
                    if (locationService.notInDatabaseYet(location, locationsInDatabase)) {
                        locationService.insertLocation(location);
                        fileService.updateDataInFile(List.of(location));
                    }
                }
            } else {
                int s = Integer.parseInt(pressed);
                if (locationService.notInDatabaseYet(locationInfoAccuWeather.get(s - 1), locationsInDatabase)) {
                    locationService.insertLocation(locationInfoAccuWeather.get(s - 1));
                    fileService.updateDataInFile(List.of(locationInfoAccuWeather.get(s - 1)));
                }
            }
        } else if (locationInfoAccuWeather.size() == 1) {
            if (locationService.notInDatabaseYet(locationInfoAccuWeather.get(0),locationsInDatabase)) {
                locationService.insertLocation(locationInfoAccuWeather.get(0));
                fileService.updateDataInFile(List.of(locationInfoAccuWeather.get(0)));
            }
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
            List<WeatherInfo> weatherInfoFiveDays = APIService.getWeatherInfo(locations.get(Integer.parseInt(s) - 1).getAccuWeatherKey());
            WeatherInfo actual = null;
            for (WeatherInfo weatherInfo:weatherInfoFiveDays) {
                weatherInfo.setLocation(locations.get(Integer.parseInt(s) - 1));
                if (weatherInfo.getDate().equals(date)) {
                    actual = weatherInfo;
                }
            }
            weatherInfoService.insertWeatherFiveDaysForLocation(weatherInfoFiveDays);

//            fileService.updateDataInFile(weatherInfoFiveDays);
            return List.of(actual);
//            } else if (locationInfo.size() == 1) {
//                return APIService.getWeatherInfo(locationInfo.get(0).getAccuWeatherKey());
//            } else {
//                System.out.println("No location known");
//            }


        } else {
            System.out.println("No location known");
        }
        return null;

    }

    public String updateDataInFile(List<T> data) {
        if (data.isEmpty()) {
            return "UpdateDataFail";
        }
        fileService.updateDataInFile(data);
        return null;
    }

    public String getDataFromFile(Class c) {
        List dataFromFile = fileService.getDataFromFile(c);
        if (dataFromFile == null) {
            return "GetDataFail";
        }
        locationService.insertListOfLocation(dataFromFile);
        return null;
    }

    public void test(double lat, double lon) {
        APIService.test(lat, lon);
    }

    public String enterNewCityNameForValidation() {
        String cityProposal = scanner.nextLine();
        while (cityProposal.isEmpty()) {
            cityProposal = scanner.nextLine();
        }
        return cityProposal;
    }

    public boolean cityNameForValidationAlreadyExistsInOurDatabase(String cityNameForValidation) {
        if (locationService.selectAllLocationsWithEnteredCityName(cityNameForValidation).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Location> listOfLocationRecordsWithSameCityNameAsArgument(String city) {
        return locationService.selectAllLocationsWithEnteredCityName(city);
    }

    public boolean needToSaveNewLocationWithTheSameCityName() {
        if (scanner.nextLine().equals("C")) {
            return true;
        } else {
            return false;
        }
    }

}
