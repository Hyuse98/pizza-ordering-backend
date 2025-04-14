package com.hyuse.pizzaOrderingBackend.service;

import com.hyuse.pizzaOrderingBackend.domain.user.*;
import com.hyuse.pizzaOrderingBackend.exceptions.ResourceNotFoundException;
import com.hyuse.pizzaOrderingBackend.exceptions.UserAlreadyExistsException;
import com.hyuse.pizzaOrderingBackend.interfaces.UserServiceInterface;
import com.hyuse.pizzaOrderingBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * Obtém o usuário atualmente autenticado.
     * Atualmente, como não há autenticação implementada, retorna o primeiro usuário encontrado ou cria um se não existir.
     * Isso deve ser substituído por uma implementação real com o Spring Security.
     * 
     * @return O usuário autenticado
     */
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        List<User> users = userRepository.findAll();
        
        if (users.isEmpty()) {
            // Se não houver usuários, cria um usuário padrão para teste
            Name name = new Name("Cliente", "Padrão");
            Email email = new Email("cliente.padrao@example.com");
            Phone phone = new Phone("11987654321");
            Password password = new Password("senha12345");
            Address address = new Address(
                "Brasil", 
                "São Paulo", 
                "São Paulo", 
                "Centro", 
                "Rua das Pizzas", 
                "123", 
                "01234567"
            );
            
            User defaultUser = new User(name, email, password, address, phone);
            return createUser(defaultUser);
        }
        
        // Retorna o primeiro usuário da lista (apenas para fins de teste)
        return users.get(0);
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
    public User updateUser(UUID id, User userDetails) {

        var user = findUserById(id);

        if (!user.getEmail().getEmailAddress().equalsIgnoreCase(userDetails.getEmail().getEmailAddress())) {
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
