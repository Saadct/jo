package saad.projet.jo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saad.projet.jo.model.Category;
import saad.projet.jo.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }


    public List<Category> findAllCategory() {
        System.out.println("Toutes les category");
        return repository.findAll();
    }

    public Category createCategory(Category category){
        System.out.println("Categorie créer");
        return repository.save(category);

    }
}
