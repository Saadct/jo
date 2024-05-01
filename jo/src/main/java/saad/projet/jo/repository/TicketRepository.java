package saad.projet.jo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saad.projet.jo.model.Ticket;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    Optional<Ticket> findOneByUuid(String uuid);




}
