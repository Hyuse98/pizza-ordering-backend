package com.hyuse.pizzaOrderingBackend.service;

import com.hyuse.pizzaOrderingBackend.domain.enums.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.domain.product.Pizza;
import com.hyuse.pizzaOrderingBackend.exceptions.ResourceNotFoundException;
import com.hyuse.pizzaOrderingBackend.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Transactional
    public Pizza createPizza(Pizza pizza) {
        Optional<Pizza> existingPizza = pizzaRepository.findByPizzaFlavor(pizza.getPizzaFlavor());
        if(existingPizza.isPresent()){
            throw new IllegalArgumentException("Pizza com este sabor já está registrada");
        }
        return pizzaRepository.save(pizza);
    }

    @Transactional(readOnly = true)
    public Pizza getPizzaById(Long id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public Pizza getPizzaByFlavor(PizzaFlavor pizzaFlavor) {
        return pizzaRepository.findByPizzaFlavor(pizzaFlavor)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza não encontrada com sabor: " + pizzaFlavor));
    }

    @Transactional(readOnly = true)
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Transactional
    public Pizza updatePizza(Long id, Pizza pizzaDetails) {
        Pizza pizza = getPizzaById(id);
        pizza.setPizzaFlavor(pizzaDetails.getPizzaFlavor());
        pizza.setDescription(pizzaDetails.getDescription());
        pizza.setPrice(pizzaDetails.getPrice());
        return pizzaRepository.save(pizza);
    }

    @Transactional
    public void deletePizza(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pizza não encontrada com id: " + id);
        }
        pizzaRepository.deleteById(id);
    }
} 