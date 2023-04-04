package dev.xalpol12.recipestorageapi.security;

import dev.xalpol12.recipestorageapi.repository.UserRepository;
import dev.xalpol12.recipestorageapi.repository.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + email);
        }

        return new UserDetailsImpl(user);
    }
}
