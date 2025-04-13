package com.hyuse.pizzaOrderingBackend.service;

import com.hyuse.pizzaOrderingBackend.domain.enums.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.domain.product.Pizza;
import com.hyuse.pizzaOrderingBackend.interfaces.PizzaServiceInterface;
import com.hyuse.pizzaOrderingBackend.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaServiceInterface {

    private final PizzaRepository pizzaRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    @Override
    @Transactional
    public Pizza createPizza(Pizza pizza) {

        Optional<Pizza> existingPizza = pizzaRepository.findByPizzaFlavor(pizza.getPizzaFlavor());

        if(existingPizza.isPresent()){
            throw new RuntimeException("Pizza Flavor already Registered");
        }

        return pizzaRepository.save(pizza);
    }

    @Override
    @Transactional
    public Pizza findPizzaById(Long id) {

        Optional<Pizza> existingPizza = pizzaRepository.findById(id);

        if(existingPizza.isEmpty()){
            throw new RuntimeException("Pizza not Found");
        }

        return existingPizza.get();
    }

    @Override
    @Transactional
    public Pizza findPizzaByFlavor(PizzaFlavor pizzaFlavor) {

        Optional<Pizza> pizza = pizzaRepository.findByPizzaFlavor(pizzaFlavor);

        if(pizza.isEmpty()){
            throw new RuntimeException("Pizza Not Found");
        }

        return pizza.get();
    }

    @Override
    @Transactional
    public Collection<Pizza> findAllPizzas() {

        Collection<Pizza> pizzas = pizzaRepository.findAll();

        if(pizzas.isEmpty()){
            throw new RuntimeException("There is no pizzas registered");
        }

        return pizzas;
    }

    @Override
    @Transactional
    public Pizza updatePizza(Long id, Pizza pizza) {
        return null;
    }

    @Override
    @Transactional
    public void deletePizza(Long id) {

        pizzaRepository.deleteById(id);
    }
}
