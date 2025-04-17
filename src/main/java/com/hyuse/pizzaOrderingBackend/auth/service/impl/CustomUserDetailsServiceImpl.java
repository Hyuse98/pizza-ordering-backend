package com.hyuse.pizzaOrderingBackend.auth.service.impl;

import com.hyuse.pizzaOrderingBackend.user.internal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmailAddress(email)
                .map(user -> {
                    String encodedPassword = user.getPassword().getPasswordValue();
                    logger.debug("Encoded password from database: {}", encodedPassword);
                    return User.withUsername(user.getEmail().getEmailAddress())
                            .password(encodedPassword) //ByCrypt SHA-256
                            .authorities("USER")
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
