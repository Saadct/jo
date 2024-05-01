package saad.projet.jo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saad.projet.jo.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository <Category, String> {

    Category save(Category category);



    List<Category> findAll();
}
