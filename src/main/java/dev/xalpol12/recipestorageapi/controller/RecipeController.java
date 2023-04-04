package dev.xalpol12.recipestorageapi.controller;

import dev.xalpol12.recipestorageapi.repository.UserRepository;
import dev.xalpol12.recipestorageapi.repository.entities.Recipe;
import dev.xalpol12.recipestorageapi.repository.entities.User;
import dev.xalpol12.recipestorageapi.security.CreatorChecker;
import dev.xalpol12.recipestorageapi.security.UserDetailsImpl;
import dev.xalpol12.recipestorageapi.service.RecipeService;
import dev.xalpol12.recipestorageapi.service.dto.NewRecipeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserRepository userRepository;
    private final CreatorChecker creatorChecker;

    @PostMapping("/new")
    public ResponseEntity<NewRecipeDTO> postRecipe(@Valid @RequestBody Recipe recipe, Authentication auth) {
        User user = userRepository.findUserByEmail(((UserDetailsImpl)auth.getPrincipal()).getUsername());          //TODO: can I simplify this?
        recipe.setAuthor(user);
        var id = recipeService.save(recipe);
        var dto = new NewRecipeDTO(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
        return ResponseEntity.of(recipeService.findById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@creatorChecker.check(#id, #auth)")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable long id, Authentication auth) {
        return new ResponseEntity<>(recipeService.delete(id));
    }

    @DeleteMapping("/all") //TODO: Add admin-only permission for this endpoint
    public ResponseEntity<HttpStatus> deleteAllRecipes() {
        return new ResponseEntity<>(recipeService.deleteAll());
    }

    @PutMapping("/{id}")
    @PreAuthorize("@creatorChecker.check(#id, #auth)")
    public ResponseEntity<HttpStatus> updateRecipe(@Valid @RequestBody Recipe recipe,
                                                   @PathVariable long id, Authentication auth) {
        return new ResponseEntity<>(recipeService.update(recipe, id));
    }

    @GetMapping("/search")
    public Object getRecipeByCategory(@RequestParam(name = "category", required = false) String category,
                                      @RequestParam(name = "name", required = false) String name) {
        if(category != null && name == null) {      //exclusive param search
            return ResponseEntity.of(Optional.ofNullable(recipeService.findByCategory(category)));
        } else if(category == null && name != null) {
            return ResponseEntity.of(Optional.ofNullable(recipeService.findByName(name)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }
}

