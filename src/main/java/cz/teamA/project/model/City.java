package cz.teamA.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class City {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generuje to unikatne ID do DB
    @Id // toto je potrebne
    private long id;

//    id - optional: UUID format
//    longitude and latitude according to geographical values ​​(latitude: -90 -> S, 90 -> N, longitude: -180 -> W, 180 -> E)
//    city name - cannot be empty
//    region - optional: may be null
//    Country name - cannot be empty
//    If incorrect data are entered, the user should be notified via an appropriate message.

}
