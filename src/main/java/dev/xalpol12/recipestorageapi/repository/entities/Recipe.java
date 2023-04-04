package dev.xalpol12.recipestorageapi.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotEmpty
    @NotBlank
    @Size(max = 200)
    private String name;

    @NotEmpty
    @NotBlank
    private String category;

    @NotEmpty
    @NotBlank
    private String description;

    @ElementCollection
    @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name= "id"))
    @NotEmpty
    @Size(min = 1, max = 40)
    private List<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "directions", joinColumns = @JoinColumn(name= "id"))
    @NotEmpty
    @Size(min = 1, max = 40)
    private List<String> directions;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User author;

    @UpdateTimestamp
    private LocalDateTime date;

}
