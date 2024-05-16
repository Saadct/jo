package saad.projet.jo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saad.projet.jo.constants.State;
import saad.projet.jo.dto.CreateTicket;
import saad.projet.jo.model.Evenement;
import saad.projet.jo.model.Operation;
import saad.projet.jo.model.Ticket;
import saad.projet.jo.model.User;
import saad.projet.jo.repository.TicketRepository;
import saad.projet.jo.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository repository;
    private final EvenementService evenementService;
    private final OperationService operationService;
    private final UserService userService;

    @Autowired
    public TicketService(TicketRepository repository,
                         EvenementService evenementService,
                         OperationService operationService,
                         UserService userService){
        this.repository = repository;
        this.evenementService = evenementService;
        this.operationService = operationService;
        this.userService = userService;
    }

    public List<Ticket> findAllTicket () {
        System.out.println("Toutes les tickets");
        return repository.findAll();
    }

    public Ticket findTicketById(String uuid) {

        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Ticket createTicket (Ticket ticket){
        System.out.println("Ticket créer");
        return repository.save(ticket);
    }

    public Boolean buyTicket(String eventId, CreateTicket t, String email){
        System.out.println("email " + email);

        Evenement event = evenementService.findEvenementById(eventId);
        User user = userService.findByEmail(email);
        if(!(event.getAvailableSeats() == 0)) {
        LocalDateTime date = LocalDateTime.now();
        Ticket ticket = new Ticket(
                    t.getName(),
                    t.getFirstName(),
                    event,
                    State.Acheter.toString(),
                    event.getStandartPrice(),
                    user,
                    date
            );
            Ticket boughtTicket = repository.save(ticket);
            Operation op = new Operation(State.Achat_Unique_Ticket.toString(), date, user);
            operationService.createOperation(op);
            evenementService.updateSeatsAvailable(eventId, 1);
            return true;
        }
        return false;
    }

    public Boolean bookTicket(String eventId, CreateTicket t, String email) {
        Evenement event = evenementService.findEvenementById(eventId);
        User user = userService.findByEmail(email);
        if (!(event.getAvailableSeats() == 0)) {
            String state = State.Reserver.toString();
            LocalDateTime date = LocalDateTime.now();
            Ticket ticket = new Ticket(
                    t.getName(),
                    t.getFirstName(),
                    event,
                    state,
                    event.getStandartPrice(),
                    user,
                    date
            );
            Ticket boughtTicket = repository.save(ticket);
            Operation op = new Operation(State.Reservation_Ticket.toString(), date, user);
            operationService.createOperation(op);
            evenementService.updateSeatsAvailable(eventId, 1);
            return true;
        }
        return false;
    }

    public boolean paidBookTicket(String ticketId, String email){
        Ticket ticket = findTicketById(ticketId);
        User user = userService.findByEmail(email);
        if(ticket != null){
            String state = State.Acheter.toString();
            LocalDateTime date = LocalDateTime.now();
            ticket.setState(state);
            ticket.setDateUpdate(date);
            repository.save(ticket);
            Operation op = new Operation(State.Paiement_Ticket_Reserver.toString(), date, user);
            operationService.createOperation(op);

         return true;
        }
        return false;
    }


    public boolean buyTickets(String eventId, List<CreateTicket> tickets, String email){
        Evenement event = evenementService.findEvenementById(eventId);
        User user = userService.findByEmail(email);
        if(event.getAvailableSeats() > tickets.size()) {
            LocalDateTime date = LocalDateTime.now();
            List<Ticket> boughtTickets = new ArrayList<>();
            for (CreateTicket createTicket : tickets) {
                Ticket ticket = new Ticket(
                        createTicket.getName(),
                        createTicket.getFirstName(),
                        event,
                        State.Acheter_Par_Lot.toString(),
                        event.getStandartPrice(),
                        user,
                        date
                );
                boughtTickets.add(repository.save(ticket));
            }
            String type = "Achat par lot de ticket " + tickets.size() + " tickets";
            Operation op = new Operation(type, date, user);
            operationService.createOperation(op);
            evenementService.updateSeatsAvailable(eventId, tickets.size());
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean deleteTicket (String id){
        System.out.println("ticket supprimée");
        Ticket ticket = findTicketById(id);
        if(ticket != null) {
            repository.deleteById(id);
            Evenement evenement = ticket.getEvenement();
            evenementService.updateSeatsAvailable(evenement.getUuid(), 1);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateTicket (String id, Ticket t){
        Ticket ticket = findTicketById(id);
        if(ticket != null) {
            ticket.setPrice(t.getPrice());
            ticket.setState(t.getState());
            repository.save(ticket);
            return true;
        }
        return false;
    }


}
