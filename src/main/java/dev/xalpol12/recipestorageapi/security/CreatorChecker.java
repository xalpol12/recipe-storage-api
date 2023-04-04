package dev.xalpol12.recipestorageapi.security;

import dev.xalpol12.recipestorageapi.repository.entities.Recipe;
import dev.xalpol12.recipestorageapi.repository.entities.User;
import dev.xalpol12.recipestorageapi.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreatorChecker {

    private final RecipeService recipeService;

    public boolean check(Long id, Authentication auth) {
        Optional<Recipe> recipe = recipeService.findById(id);
        User user = recipe.map(Recipe::getAuthor).orElse(null);
        return user != null && user.getEmail().equals(((UserDetailsImpl)auth.getPrincipal()).getUsername());
    }

    public boolean check(Recipe recipe, Authentication auth) {
        return recipe.getAuthor().getEmail().equals(((UserDetailsImpl)auth.getPrincipal()).getUsername());
    }
}

