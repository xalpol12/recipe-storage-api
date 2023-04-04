package dev.xalpol12.recipestorageapi.repository.entities;

import dev.xalpol12.recipestorageapi.repository.validators.ExtendedEmailValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExtendedEmailValidator
    private String email;

    private String role;  //should be prefixed with ROLE_

    @NotEmpty
    @Length(min = 8)
    private String password;

    @OneToMany(mappedBy = "author")
    @CollectionTable(name = "added_recipes", joinColumns = @JoinColumn(name = "user_id"))
    private List<Recipe> addedRecipes;
}

