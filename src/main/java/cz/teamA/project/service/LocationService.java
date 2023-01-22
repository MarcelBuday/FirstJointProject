package cz.teamA.project.service;

import cz.teamA.project.jpamodel.Location;
import cz.teamA.project.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void insertLocation(String cityName, String countryName, String region, double longitude, double latitude) {
        Location location = new Location(cityName, countryName, region, longitude, latitude, 0);
        locationRepository.save(location);

    }

    public void insertLocation(Location location) {
        locationRepository.save(location);
    }

    public void insertListOfLocation(List<Location> locations) {
        locationRepository.saveAll(locations);
    }

    public List<Location> selectAllLocation() {
        return locationRepository.findAll();
    }

    public List<Location> selectAllLocationsWithEnteredCityName(String city) {
        return locationRepository.findByCityName(city);
    }

    public boolean notInDatabaseYet(Location location, List<Location> existingInDatabase ) {
        boolean notInDatabaseYet = true;
        for (Location loc : existingInDatabase) {
            if (location.getAccuWeatherKey() == loc.getAccuWeatherKey()) {
                notInDatabaseYet = false;
            }
        }
        return notInDatabaseYet;
    }


}
