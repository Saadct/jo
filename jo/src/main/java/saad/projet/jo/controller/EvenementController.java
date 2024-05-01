package saad.projet.jo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import saad.projet.jo.dto.CreateTicket;
import saad.projet.jo.model.Category;
import saad.projet.jo.model.Evenement;
import saad.projet.jo.model.Ticket;
import saad.projet.jo.service.EvenementService;
import saad.projet.jo.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    private final EvenementService service;
    private final TicketService ticketService;

    public EvenementController(EvenementService service, TicketService ticketService){
        this.service = service;
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Evenement>> findAll(){
        return new ResponseEntity<>(service.findAllEvenement(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Evenement> create(@Valid @RequestBody Evenement evenement) {
        return new ResponseEntity<>(service.createEvenement(evenement), HttpStatus.CREATED);
    }
    @PostMapping("/{event_id}/acheterTicket")
    public ResponseEntity<Ticket> buyTicket(@PathVariable("event_id") String eventId, @Valid @RequestBody CreateTicket t) {
        return new ResponseEntity<>(ticketService.buyTicket(eventId,t), HttpStatus.CREATED);
    }

    @PostMapping("/{event_id}/acheterLotTicket")
    public ResponseEntity<List<Ticket>> buyLotTicket(@PathVariable("event_id") String eventId, @Valid @RequestBody List<CreateTicket> tickets) {
        return new ResponseEntity<>(ticketService.buyTickets(eventId,tickets), HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        if (service.deleteEvenement(uuid)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> mettreAJourTotalement(@PathVariable String uuid,
                                                   @Valid @RequestBody Evenement evenement){
        if (service.updateEvenement(uuid, evenement)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
