package dev.xalpol12.recipestorageapi.controller;

import dev.xalpol12.recipestorageapi.repository.entities.User;
import dev.xalpol12.recipestorageapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
@Validated
public class RegistrationController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> register(@Validated @RequestBody User user) {
        return new ResponseEntity<>(userService.save(user));
    }
}
