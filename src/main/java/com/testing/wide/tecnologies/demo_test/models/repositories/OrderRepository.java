package com.testing.wide.tecnologies.demo_test.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testing.wide.tecnologies.demo_test.models.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
