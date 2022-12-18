package cz.teamA.project.repository;

import cz.teamA.project.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<Todo,Long> {
}
