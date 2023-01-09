package cz.teamA.project.repository;

import cz.teamA.project.jpamodel.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
}
