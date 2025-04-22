package com.hyuse.pizzaOrderingBackend.products.service.impl;

import com.hyuse.pizzaOrderingBackend.infra.internal.exceptions.ResourceNotFoundException;
import com.hyuse.pizzaOrderingBackend.products.dto.ProductDTO;
import com.hyuse.pizzaOrderingBackend.products.model.Product;
import com.hyuse.pizzaOrderingBackend.products.repository.ProductRepository;
import com.hyuse.pizzaOrderingBackend.products.service.ProductServiceInterface;
import com.hyuse.pizzaOrderingBackend.products.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public Product createProduct(ProductDTO productDTO) {
        return productRepository.save(productMapper.toEntity(productDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        var existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new ResourceNotFoundException("Product not Found: " + id);
        }
        return existingProduct.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Product> findAllProducts() {
        Collection<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("There is no products registered");
        }
        return products;
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductDTO productDTO) {
        var product = findProductById(id);
        product.setProductName(productDTO.productName());
        product.setPrice(productDTO.price());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product Not Found: " + id);
        }
        productRepository.deleteById(id);
    }
}
