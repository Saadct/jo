package saad.projet.jo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saad.projet.jo.model.Evenement;
import saad.projet.jo.model.Ticket;
import saad.projet.jo.repository.TicketRepository;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository){
        this.repository= repository;
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
