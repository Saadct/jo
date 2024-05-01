package saad.projet.jo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String price;

    private String state;

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
}
