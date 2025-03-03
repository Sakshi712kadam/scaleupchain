package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.ProductDTO;
import com.reactproject.startupinvestor.entities.Product;
import com.reactproject.startupinvestor.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Save product
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());

        // Save product and get saved entity back
        product = productRepository.save(product);

        // Return saved product as DTO
        return new ProductDTO(product.getId(), product.getName(), product.getDescription());
    }

    // Get all products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getDescription()))
                .collect(Collectors.toList());
    }

    // Get product by ID
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return new ProductDTO(product.getId(), product.getName(), product.getDescription());
    }

    // Update product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());

        product = productRepository.save(product);

        return new ProductDTO(product.getId(), product.getName(), product.getDescription());
    }

    // Delete product
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

}
