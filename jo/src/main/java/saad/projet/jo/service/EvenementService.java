package saad.projet.jo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saad.projet.jo.model.Evenement;
import saad.projet.jo.repository.EvenementRepository;

import java.util.List;

@Service
public class EvenementService {

    private final EvenementRepository repository;

    @Autowired
    public EvenementService(EvenementRepository repository) {
        this.repository = repository;
    }

    public List<Evenement> findAllEvenement () {
        System.out.println("Toutes les evenements");
        return repository.findAll();
    }

    public Evenement findEvenementById(String uuid) {
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public Evenement createEvenement (Evenement evenement){
        System.out.println("Evenement créer");
        return repository.save(evenement);
    }

    @Transactional
    public Boolean deleteEvenement (String id){
        System.out.println("evenement supprimée");
        Evenement evenementASupr = findEvenementById(id);
        if(evenementASupr != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean updateEvenement (String id, Evenement evenement){
        Evenement evenementAModifier = findEvenementById(id);
        if(evenementAModifier != null) {
            evenementAModifier.setAvailableSeats(evenement.getAvailableSeats());
            evenementAModifier.setTotalSeats(evenement.getTotalSeats());
            repository.save(evenementAModifier);
            return true;
        }
        return false;
    }

    public void updateSeatsAvailable(String uuid, Integer billetCount ) {
        Evenement evenement = findEvenementById(uuid);
        Integer availableSeat = evenement.getAvailableSeats() - billetCount;
        evenement.setAvailableSeats(availableSeat);
        repository.save(evenement);
    }
}
