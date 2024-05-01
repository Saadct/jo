package saad.projet.jo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saad.projet.jo.dto.CreateTicket;
import saad.projet.jo.model.Evenement;
import saad.projet.jo.model.Operation;
import saad.projet.jo.model.Ticket;
import saad.projet.jo.repository.TicketRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;
    private final EvenementService evenementService;
    private final OperationService operationService;

    public TicketService(TicketRepository repository, EvenementService evenementService, OperationService operationService){
        this.repository = repository;
        this.evenementService = evenementService;
        this.operationService = operationService;
    }

    public List<Ticket> findAllTicket () {
        System.out.println("Toutes les ticket");
        return repository.findAll();
    }

    public Ticket findTicketById(String uuid) {
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Ticket createTicket (Ticket ticket){
        System.out.println("Ticket créer");
        return repository.save(ticket);
    }

    public Ticket buyTicket(String eventId, CreateTicket t){
        Evenement event = evenementService.findEvenementById(eventId);
        String state = "achat";
        Ticket ticket = new Ticket(
                t.getName(),
                t.getFirstName(),
                event,
                state
        );
        Ticket boughtTicket = repository.save(ticket);
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.toString();
        Operation op = new Operation("achat unique ticket", date);
        operationService.createOperation(op);
        return boughtTicket;
    }


    public List<Ticket> buyTickets(String eventId, List<CreateTicket> tickets){

        Evenement event = evenementService.findEvenementById(eventId);
        String state = "achat par lot";


        List<Ticket> boughtTickets = new ArrayList<>();

        for (CreateTicket createTicket : tickets) {
            Ticket ticket = new Ticket(
                    createTicket.getName(),
                    createTicket.getFirstName(),
                    event,
                    state
            );
            boughtTickets.add(repository.save(ticket));
        }
        String type = "Achat par lot de ticket promo special " + tickets.size() + " tickets";
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.toString();
        Operation op = new Operation(type, date);
        operationService.createOperation(op);

        return boughtTickets;
    }

    @Transactional
    public Boolean deleteTicket (String id){
        System.out.println("ticket supprimée");
        Ticket ticket = findTicketById(id);
        if(ticket != null) {
            repository.deleteById(id);
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
