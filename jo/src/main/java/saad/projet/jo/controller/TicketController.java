package saad.projet.jo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saad.projet.jo.model.Ticket;
import saad.projet.jo.security.JwtService;
import saad.projet.jo.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    @Autowired
    public TicketController(TicketService service, JwtService jwtService){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll(){
        List<Ticket> tickets = service.findAllTicket();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }



    @GetMapping("/{uuid}")
    public ResponseEntity<Ticket> findById(@PathVariable("uuid") String uuid) {
        Ticket ticket = service.findTicketById(uuid);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
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

    @PatchMapping("/{ticketId}/paidBookTicket")
    public ResponseEntity<String> bookTicket(@PathVariable("ticketId") String ticketId,
                                             @RequestHeader("Authorization") String token) {
        //       return new ResponseEntity<>(ticketService.buyTickets(eventId,tickets), HttpStatus.CREATED);
        token =  token.substring(7);
        if (service.paidBookTicket(ticketId, token)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Il n'y a plus de places disponibles pour cet événement.", HttpStatus.NOT_ACCEPTABLE);
        }

    }


}
