package saad.projet.jo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String availableSeats;

    private String totalSeats;

}
