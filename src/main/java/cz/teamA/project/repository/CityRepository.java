package cz.teamA.project.repository;

import cz.teamA.project.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
