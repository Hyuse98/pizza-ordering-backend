package com.hyuse.pizzaOrderingBackend.service;

import com.hyuse.pizzaOrderingBackend.domain.user.Name;
import com.hyuse.pizzaOrderingBackend.domain.user.User;
import com.hyuse.pizzaOrderingBackend.exceptions.ResourceNotFoundException;
import com.hyuse.pizzaOrderingBackend.exceptions.UserAlreadyExistsException;
import com.hyuse.pizzaOrderingBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(User user) {

        Optional<User> existingUser = userRepository.findByEmailAddress(user.getEmail().getEmailAddress());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Já existe um usuário com o email: " + user.getEmail().getEmailAddress());
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByName(Name name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com nome: " + name));
    }

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
    public User updateUser(UUID id, User userDetails) {
        User user = findUserById(id);

        if (!user.getEmail().getEmailAddress().equals(userDetails.getEmail().getEmailAddress())) {
            Optional<User> existingUser = userRepository.findByEmailAddress(userDetails.getEmail().getEmailAddress());
            if (existingUser.isPresent()) {
                throw new UserAlreadyExistsException("Já existe um usuário com o email: " + userDetails.getEmail().getEmailAddress());
            }
        }

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setPassword(userDetails.getPassword());
        user.setAddress(userDetails.getAddress());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
        userRepository.deleteById(id);
    }
}
