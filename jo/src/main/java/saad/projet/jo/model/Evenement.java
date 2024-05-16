package saad.projet.jo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;


    private Integer totalSeats;

    private Integer availableSeats;

    private Float standartPrice;

    private String state;

    private String name;

    private LocalDateTime date;

    private LocalDateTime dateLastUpdate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="event_id")
    private List<Ticket> tickets = new ArrayList<>();

    public Float getStandartPrice() {
        return standartPrice;
    }

    public void setStandartPrice(Float standartPrice) {
        standartPrice = standartPrice;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public String getUuid() {
        return uuid;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(LocalDateTime dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }
}
