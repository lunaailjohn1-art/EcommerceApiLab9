package com.ws101.luna.EcommerceApiLab9.service;

import com.ws101.luna.EcommerceApiLab9.model.Product;
import com.ws101.luna.EcommerceApiLab9.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}

