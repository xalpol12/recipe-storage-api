package dev.xalpol12.recipestorageapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import recipes.repository.enitites.Recipe;
import recipes.repository.enitites.User;
import recipes.service.RecipeService;

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

