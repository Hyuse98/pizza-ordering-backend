package com.hyuse.pizzaOrderingBackend.auth.api;

import com.hyuse.pizzaOrderingBackend.auth.api.dto.AuthRequest;
import com.hyuse.pizzaOrderingBackend.auth.api.dto.AuthResponse;
import com.hyuse.pizzaOrderingBackend.user.internal.service.UserServiceInterface;
import com.hyuse.pizzaOrderingBackend.auth.internal.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceInterface userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(UserServiceInterface userService, JwtService jwtService, AuthenticationManager authManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        var user = userService.createUser(request.getEmail(), request.getPassword());
        var token = jwtService.generateToken(user.getEmail().getEmailAddress());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var token = jwtService.generateToken(request.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            logger.error("Authentication failed: {}", e.getMessage()); // Log the error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new AuthResponse("Invalid credentials")); // Consistent error response
        }
    }
}

