package saad.projet.jo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import saad.projet.jo.dto.CreateTicket;
import saad.projet.jo.model.Category;
import saad.projet.jo.model.Evenement;
import saad.projet.jo.model.Ticket;
import saad.projet.jo.security.JwtService;
import saad.projet.jo.service.EvenementService;
import saad.projet.jo.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    private final EvenementService service;
    private final TicketService ticketService;
    private final JwtService jwtService;

    @Autowired
    public EvenementController(EvenementService service, TicketService ticketService, JwtService jwtService){
        this.service = service;
        this.ticketService = ticketService;
        this.jwtService = jwtService;
    }


    @GetMapping
    public ResponseEntity<List<Evenement>> findAll(){
      //  String token = authorizationHeader.substring(7);
       // System.out.println(token);
      //  System.out.println(jwtService.extractUsername(token));

        return new ResponseEntity<>(service.findAllEvenement(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evenement> findById(@PathVariable("id") String id, @RequestHeader("Authorization") String authorizationHeader){
        //  String token = authorizationHeader.substring(7);
        // System.out.println(token);
        //  System.out.println(jwtService.extractUsername(token));

        return new ResponseEntity<>(service.findEvenementById(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Evenement> create(@Valid @RequestBody Evenement evenement) {
        return new ResponseEntity<>(service.createEvenement(evenement), HttpStatus.CREATED);
    }
    @PostMapping("/{event_id}/acheterTicket")
    public ResponseEntity<String> buyTicket(@PathVariable("event_id") String eventId,
                                            @Valid @RequestBody CreateTicket ticket,
                                            @RequestHeader("Authorization") String token) {
      //  return new ResponseEntity<>(ticketService.buyTicket(eventId,t), HttpStatus.CREATED);
                token =  token.substring(7);

        if (ticketService.buyTicket(eventId, ticket, jwtService.extractUsername(token))) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Il n'y a plus de places disponibles pour cet événement.", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping("/{event_id}/acheterLotTicket")
    public ResponseEntity<String> buyLotTicket(@PathVariable("event_id") String eventId,
                                               @Valid @RequestBody List<CreateTicket> tickets,
                                               @RequestHeader("Authorization") String token) {
 //       return new ResponseEntity<>(ticketService.buyTickets(eventId,tickets), HttpStatus.CREATED);
        if (ticketService.buyTickets(eventId, tickets, jwtService.extractEmail(token))) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Il n'y a plus de places disponibles pour cet événement.", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping("/{event_id}/bookTicket")
    public ResponseEntity<String> bookTicket(@PathVariable("event_id") String eventId,
                                               @Valid @RequestBody CreateTicket ticket,
                                               @RequestHeader("Authorization") String token) {
        //       return new ResponseEntity<>(ticketService.buyTickets(eventId,tickets), HttpStatus.CREATED);
        token =  token.substring(7);

        if (ticketService.bookTicket(eventId, ticket, jwtService.extractEmail(token))) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Il n'y a plus de places disponibles pour cet événement.", HttpStatus.NOT_ACCEPTABLE);
        }

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
