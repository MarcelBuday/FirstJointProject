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
    private final DtoService dtoService;


    public Service(LocationService locationService, APIService APIService, WeatherInfoService weatherInfoService, FileService fileService, DtoService dtoService) {
        this.locationService = locationService;
        this.APIService = APIService;
        this.weatherInfoService = weatherInfoService;
        this.fileService = fileService;
        this.dtoService = dtoService;
        this.scanner = new Scanner(System.in);


    }

//    public void getWeatherInfoByLocation(String location) {
//        APIService.getWeatherInfoByLocation(location);
//    }

    public void addLocation(String newCityProposal) {

        List<Location> locationInfoAccuWeather = APIService.getLocationInfo(newCityProposal);
        List<Location> locationsInDatabase = locationService.selectAllLocation();
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
            if (locationService.notInDatabaseYet(locationInfoAccuWeather.get(0), locationsInDatabase)) {
                locationService.insertLocation(locationInfoAccuWeather.get(0));
                fileService.updateDataInFile(List.of(locationInfoAccuWeather.get(0)));
            }
        } else {
            System.out.println("City not known");

        }
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
            for (WeatherInfo weatherInfo : weatherInfoFiveDays) {
                weatherInfo.setLocation(locations.get(Integer.parseInt(s) - 1));
                if (weatherInfo.getDate().equals(date)) {
                    actual = weatherInfo;
                }
            }
            weatherInfoService.insertWeatherFiveDaysForLocation(weatherInfoFiveDays);
            fileService.updateDataInFile(dtoService.toWeatherInfoDtoList(weatherInfoFiveDays));
            return List.of(actual);


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
        if (c.equals(Location.class)) {
            locationService.insertListOfLocation(dataFromFile);
        } else {
            List<WeatherInfo> list = dtoService.toWeatherInfoList(dataFromFile);
            Location temp = locationService.selectLocationByAccuWeatherKey(list.get(0).getLocation().getAccuWeatherKey());
            list.forEach(w -> w.getLocation().setUuid(temp.getUuid()));
            weatherInfoService.insertWeatherFiveDaysForLocation(list);
        }
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
