package dev.xalpol12.recipestorageapi.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.xalpol12.recipestorageapi.repository.entities.User;
import dev.xalpol12.recipestorageapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final ObjectMapper mapper;

    @Override
    public void run(String... args) throws Exception {
//        loadUsers();
    }

    private void loadUsers() throws Exception {
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/sampleUsers.json")) {
            userService.saveAll(mapper.readValue(inputStream, new TypeReference<List<User>>() {}));
        }
    }
}

