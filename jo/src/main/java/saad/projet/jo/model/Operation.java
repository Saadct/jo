package saad.projet.jo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String type;

    private Date date;

    public String getUuid() {
        return uuid;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
