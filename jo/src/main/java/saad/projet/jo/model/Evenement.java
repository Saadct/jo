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

    public String getAvailableSeats() {
        return availableSeats;
    }

    public String getTotalSeats() {
        return totalSeats;
    }

    public String getUuid() {
        return uuid;
    }

    public void setAvailableSeats(String availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setTotalSeats(String totalSeats) {
        this.totalSeats = totalSeats;
    }
}
