package com.hyuse.pizzaOrderingBackend.service;

import com.hyuse.pizzaOrderingBackend.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmailAddress(email)
                .map(user -> {
                    String encodedPassword = user.getPassword().getPasswordValue(); // Get the encoded password
                    logger.debug("Encoded password from database: {}", encodedPassword);
                    return User.withUsername(user.getEmail().getEmailAddress())
                            .password(encodedPassword) // Use the encoded password string
                            .authorities("USER")
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
