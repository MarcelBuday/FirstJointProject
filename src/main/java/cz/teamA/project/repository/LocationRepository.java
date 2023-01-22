package cz.teamA.project.repository;

import cz.teamA.project.jpamodel.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    List<Location> findByCityName(String cityName);

    List<Location> findByAccuWeatherKey(int accuWeatherKey);
}
