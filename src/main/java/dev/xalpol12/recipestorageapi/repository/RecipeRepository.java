package dev.xalpol12.recipestorageapi.repository;

import dev.xalpol12.recipestorageapi.repository.entities.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String name);

}
