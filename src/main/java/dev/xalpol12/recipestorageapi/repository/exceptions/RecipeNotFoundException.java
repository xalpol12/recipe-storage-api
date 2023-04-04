package dev.xalpol12.recipestorageapi.repository.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends Exception{
    public RecipeNotFoundException() {
        super("Recipe with given id does not exist");
    }
}
