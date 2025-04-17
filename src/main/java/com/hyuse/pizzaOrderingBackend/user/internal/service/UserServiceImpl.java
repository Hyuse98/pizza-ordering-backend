package com.hyuse.pizzaOrderingBackend.user.internal.service;

import com.hyuse.pizzaOrderingBackend.user.api.dto.UserDTO;
import com.hyuse.pizzaOrderingBackend.user.internal.util.UserMapper;
import com.hyuse.pizzaOrderingBackend.infra.internal.exceptions.ResourceNotFoundException;
import com.hyuse.pizzaOrderingBackend.user.internal.exceptions.UserAlreadyExistsException;
import com.hyuse.pizzaOrderingBackend.user.internal.repository.UserRepository;
import com.hyuse.pizzaOrderingBackend.user.internal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User createUser(String email, String rawPassword) {
        Optional<User> existingUser = userRepository.findByEmailAddress(email);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Já existe um usuário com o email: " + email);
        }

        Email userEmail = new Email(email);
        Password password = new Password(passwordEncoder.encode(rawPassword));

        User user = new User();
        user.setEmail(userEmail);
        user.setPassword(password);
        user.setName(new Name("", ""));
        user.setPhone(new Phone(""));
        user.setAddress(new Address("", "", "", "", "", "", ""));

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
    }

    //TODO Tratar Upper e Lower Case
    @Override
    @Transactional(readOnly = true)
    public User findUserByName(Name name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com nome: " + name));
    }

    //TODO Validar Formato do Email
    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + emailAddress));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User updateUser(UUID id, UserDTO userDTO) {
        User user = findUserById(id);

        if (!user.getEmail().getEmailAddress().equals(userDTO.emailAddress())) {
            validateEmailNotExists(userDTO.emailAddress());
            user.setEmail(new Email(userDTO.emailAddress()));
        }

        updateUserData(user, userDTO);
        return userRepository.save(user);
    }

    private void validateEmailNotExists(String email) {
        Optional<User> existingUser = userRepository.findByEmailAddress(email);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Já existe um usuário com o email: " + email);
        }
    }

    private void updateUserData(User user, UserDTO userDTO) {

        user.setName(new Name(userDTO.firstName(), userDTO.lastName()));
        user.setPhone(new Phone(userDTO.phoneNumber()));

        Address address = user.getAddress();

        address.setCountry(userDTO.country());
        address.setState(userDTO.state());
        address.setCity(userDTO.city());
        address.setNeighborhood(userDTO.neighborhood());
        address.setStreet(userDTO.street());
        address.setHouseNumber(userDTO.houseNumber());
        address.setZipCode(userDTO.zipCode());
        user.setAddress(address);
    }
}
