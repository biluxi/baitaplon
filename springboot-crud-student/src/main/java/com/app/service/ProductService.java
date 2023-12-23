package com.app.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Product;
import com.app.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
            // Log thông báo xóa thành công
            System.out.println("Product with ID " + id + " deleted successfully");
        } catch (Exception e) {
            // Log lỗi xóa
            System.err.println("Error deleting product with ID " + id);
            e.printStackTrace();
            throw new RuntimeException("Error deleting product with ID " + id, e);
        }
    }
    

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
    public Product getProById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}