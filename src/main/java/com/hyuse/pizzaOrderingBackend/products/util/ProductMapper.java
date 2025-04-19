package com.hyuse.pizzaOrderingBackend.products.util;

import com.hyuse.pizzaOrderingBackend.products.dto.ProductDTO;
import com.hyuse.pizzaOrderingBackend.products.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO){

        return new Product(
                productDTO.description(),
                productDTO.price()
        );
    }

    public ProductDTO toDto(Product product){

        return new ProductDTO(
                product.getDescription(),
                product.getPrice()
        );
    }
}
