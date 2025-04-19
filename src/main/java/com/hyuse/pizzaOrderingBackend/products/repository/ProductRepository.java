package com.hyuse.pizzaOrderingBackend.products.repository;

import com.hyuse.pizzaOrderingBackend.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

}