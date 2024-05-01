package saad.projet.jo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saad.projet.jo.model.Category;
import saad.projet.jo.model.Evenement;
import saad.projet.jo.service.EvenementService;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    private final EvenementService service;

    public EvenementController(EvenementService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Evenement>> findAll(){
        return new ResponseEntity<>(service.findAllEvenement(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Evenement> create(@Valid @RequestBody Evenement evenement) {
        return new ResponseEntity<>(service.createEvenement(evenement), HttpStatus.CREATED);
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
