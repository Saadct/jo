package saad.projet.jo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String availableSeats;

    private String totalSeats;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="event_id")
    private List<Ticket> tickets = new ArrayList<>();


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
