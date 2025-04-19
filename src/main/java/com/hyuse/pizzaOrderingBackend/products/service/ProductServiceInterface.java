package com.hyuse.pizzaOrderingBackend.products.service;

import com.hyuse.pizzaOrderingBackend.products.dto.ProductDTO;
import com.hyuse.pizzaOrderingBackend.products.model.Product;

import java.util.Collection;

public interface ProductServiceInterface {

    Product createProduct(ProductDTO productDTO);

    Product findProductById(Long id);

    Collection<Product> findAllProducts();

    Product updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}
