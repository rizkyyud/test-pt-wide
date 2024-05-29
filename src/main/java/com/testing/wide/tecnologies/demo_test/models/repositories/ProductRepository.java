package com.testing.wide.tecnologies.demo_test.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testing.wide.tecnologies.demo_test.models.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndType(String name, String type);
}
