package dev.xalpol12.recipestorageapi.service;

import dev.xalpol12.recipestorageapi.repository.RecipeRepository;
import dev.xalpol12.recipestorageapi.repository.entities.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> findById(Long id) {
        return Optional.ofNullable(recipeRepository.findById(id).orElse(null));
    }

    public Long save(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe.getId();
    }

    public HttpStatus delete(Long id) {
        if(recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    public HttpStatus update(Recipe recipe, long id) {
        if(recipeRepository.existsById(id)) {
            recipe.setId(id);
            recipeRepository.save(recipe);
            return HttpStatus.NO_CONTENT;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    public HttpStatus deleteAll() {
        recipeRepository.deleteAll();
        return HttpStatus.NO_CONTENT;
    }

    public Optional<List<Recipe>> findByCategory(String category) {
        return Optional.ofNullable(recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category));
    }

    public Optional<List<Recipe>> findByName(String name) {
        return Optional.ofNullable(recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name));
    }
}

