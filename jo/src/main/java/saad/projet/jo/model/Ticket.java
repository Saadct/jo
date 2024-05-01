package saad.projet.jo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String price;

    private String state;

    private String name;

    private String firstname;


    @ManyToOne
    @JoinColumn(name = "event_id")
    private Evenement evenement;


    public Ticket(String name, String firstName,Evenement event,String state) {
        this.name = name;
        this.firstname = firstName;
        this.evenement = event;
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
