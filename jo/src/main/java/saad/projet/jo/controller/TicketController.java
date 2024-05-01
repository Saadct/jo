package saad.projet.jo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saad.projet.jo.dto.CreateTicket;
import saad.projet.jo.model.Category;
import saad.projet.jo.model.Ticket;
import saad.projet.jo.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll(){
        return new ResponseEntity<>(service.findAllTicket(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@Valid @RequestBody Ticket t) {
        return new ResponseEntity<>(service.createTicket(t), HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        if (service.deleteTicket(uuid)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> mettreAJourTotalement(@PathVariable String uuid,
                                                   @Valid @RequestBody Ticket t){
        if (service.updateTicket(uuid, t)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
