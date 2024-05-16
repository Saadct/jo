package saad.projet.jo.service;

import org.springframework.stereotype.Service;
import saad.projet.jo.model.Ticket;
import saad.projet.jo.model.User;
import saad.projet.jo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public User findById(String id) {
        return repository.findById(id).orElse(null);
    }

}
