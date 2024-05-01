package saad.projet.jo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTicket {

    @NotBlank
    @Size(min=1 , max=25)
    private String name;

    @NotBlank
    @Size(min=1 , max=25)
    private String firstName;

    public CreateTicket(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
