package com.testing.wide.tecnologies.demo_test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.wide.tecnologies.demo_test.helpers.CustomResponse;
import com.testing.wide.tecnologies.demo_test.models.entities.Product;
import com.testing.wide.tecnologies.demo_test.services.ProductServices;

@RestController
@RequestMapping("/api/test/wide/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @GetMapping
    public ResponseEntity<CustomResponse<Page<Product>>> getAllProducts(Pageable pageable) {
        Page<Product> products = productServices.getAllProducts(pageable);
        CustomResponse<Page<Product>> response = new CustomResponse<>(
                "success",
                "Products retrieved successfully",
                products,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Product>> saveProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productServices.saveProduct(product);
            CustomResponse<Product> response = new CustomResponse<>(
                    "success",
                    "Product created successfully",
                    savedProduct,
                    HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            CustomResponse<Product> response = new CustomResponse<>(
                    "error",
                    e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Product>> updateProduct(@PathVariable Long id,
            @RequestBody Product product) {
        Product updatedProduct = productServices.updateProduct(id, product);
        CustomResponse<Product> response = new CustomResponse<>(
                "success",
                "Product updated successfully",
                updatedProduct,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteProduct(@PathVariable Long id) {
        try {
            productServices.deleteProduct(id);
            CustomResponse<Void> response = new CustomResponse<>(
                    "success",
                    "Product deleted successfully",
                    null,
                    HttpStatus.NO_CONTENT.value());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (IllegalStateException e) {
            CustomResponse<Void> response = new CustomResponse<>(
                    "error",
                    e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
