package com.hyuse.pizzaOrderingBackend.products.service.impl;

import com.hyuse.pizzaOrderingBackend.infra.internal.exceptions.ResourceNotFoundException;
import com.hyuse.pizzaOrderingBackend.products.dto.PizzaDTO;
import com.hyuse.pizzaOrderingBackend.products.model.Pizza;
import com.hyuse.pizzaOrderingBackend.products.repository.PizzaRepository;
import com.hyuse.pizzaOrderingBackend.products.service.PizzaServiceInterface;
import com.hyuse.pizzaOrderingBackend.products.util.PizzaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class PizzaServiceImpl implements PizzaServiceInterface {

    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository, PizzaMapper pizzaMapper) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaMapper = pizzaMapper;
    }

    @Override
    @Transactional
    public Pizza createPizza(PizzaDTO pizzaDTO) {
        var existingPizza = pizzaRepository.findByPizzaFlavor(pizzaDTO.pizzaFlavor());
        if (existingPizza.isPresent()) {
            throw new RuntimeException("Pizza Flavor already Registered");
        }
        return pizzaRepository.save(pizzaMapper.toEntity(pizzaDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Pizza findPizzaById(Long id) {
        var existingPizza = pizzaRepository.findById(id);
        if (existingPizza.isEmpty()) {
            throw new ResourceNotFoundException("Pizza not Found: " + id);
        }
        return existingPizza.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Pizza findPizzaByFlavor(String pizzaFlavor) {
        var pizza = pizzaRepository.findByPizzaFlavor(pizzaFlavor);
        if (pizza.isEmpty()) {
            throw new ResourceNotFoundException("Pizza Not Found: " + pizzaFlavor);
        }
        return pizza.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Pizza> findAllPizzas() {
        Collection<Pizza> pizzas = pizzaRepository.findAll();
        if (pizzas.isEmpty()) {
            throw new ResourceNotFoundException("There is no pizzas registered");
        }
        return pizzas;
    }

    @Override
    @Transactional
    public Pizza updatePizza(Long id, PizzaDTO pizzaDTO) {
        var pizza = findPizzaById(id);
        pizza.setPizzaFlavor(pizzaDTO.pizzaFlavor());
        pizza.setDescription(pizzaDTO.description());
        pizza.setPrice(pizzaDTO.price());
        return pizzaRepository.save(pizza);
    }

    @Override
    @Transactional
    public void deletePizza(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pizza Not Found: " + id);
        }
        pizzaRepository.deleteById(id);
    }
}
