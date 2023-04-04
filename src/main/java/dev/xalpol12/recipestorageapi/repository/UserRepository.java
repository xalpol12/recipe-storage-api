package dev.xalpol12.recipestorageapi.repository;

import dev.xalpol12.recipestorageapi.repository.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    User findUserByEmail(String email);
}

