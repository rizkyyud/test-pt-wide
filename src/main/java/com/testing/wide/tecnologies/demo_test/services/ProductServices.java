package com.testing.wide.tecnologies.demo_test.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.testing.wide.tecnologies.demo_test.helpers.ResourceNotFoundException;
import com.testing.wide.tecnologies.demo_test.models.entities.Product;
import com.testing.wide.tecnologies.demo_test.models.repositories.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product saveProduct(Product product) {
        if (productRepository.existsByNameAndType(product.getName(), product.getType())) {
            throw new IllegalArgumentException("Product with the same name and type already exists");
        }

        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setType(updatedProduct.getType());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        // if (orderItemRepository.existsByProductId(id)) {
        // throw new IllegalStateException("Cannot delete product, it is used in order
        // items");
        // }
        productRepository.deleteById(id);
    }
}
