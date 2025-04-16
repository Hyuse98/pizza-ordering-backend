package com.hyuse.pizzaOrderingBackend.auth.api;

import com.hyuse.pizzaOrderingBackend.auth.UserCreationMessage;
import com.hyuse.pizzaOrderingBackend.auth.api.dto.AuthRequest;
import com.hyuse.pizzaOrderingBackend.auth.api.dto.AuthResponse;
import com.hyuse.pizzaOrderingBackend.auth.internal.service.JwtService;
import com.hyuse.pizzaOrderingBackend.auth.internal.service.UserCreationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserCreationProducer producer;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(JwtService jwtService, AuthenticationManager authManager, UserCreationProducer producer) {
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.producer = producer;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {

        UserCreationMessage message = new UserCreationMessage();

        message.setEmail(request.getEmail());
        message.setPassword(request.getPassword());

        producer.send(message);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário em processo de registro");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException e) {
            logger.error("Authentication failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new AuthResponse("Invalid credentials"));
        }
    }

}

