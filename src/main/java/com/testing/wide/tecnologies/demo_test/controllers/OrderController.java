package com.testing.wide.tecnologies.demo_test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.wide.tecnologies.demo_test.helpers.CustomResponse;
import com.testing.wide.tecnologies.demo_test.helpers.OrderRequestDTO;
import com.testing.wide.tecnologies.demo_test.helpers.ResourceNotFoundException;
import com.testing.wide.tecnologies.demo_test.models.entities.Order;
import com.testing.wide.tecnologies.demo_test.models.entities.OrderItem;
import com.testing.wide.tecnologies.demo_test.services.OrderServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test/wide/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Order>> addProductToOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = orderServices.addProductToOrder(orderRequestDTO);
        CustomResponse<Order> response = new CustomResponse<>(
                "success",
                "Order created successfully",
                order,
                HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderServices.getAllOrders();
        CustomResponse<List<Order>> response = new CustomResponse<>(
                "success",
                "Orders retrieved successfully",
                orders,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Order>> getOrderById(@PathVariable Long id) {
        Order order = orderServices.getOrderById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        CustomResponse<Order> response = new CustomResponse<>(
                "success",
                "Order retrieved successfully",
                order,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<CustomResponse<List<OrderItem>>> getOrderItemsByOrderId(@PathVariable Long id) {
        List<OrderItem> orderItems = orderServices.getOrderItemsByOrderId(id);
        CustomResponse<List<OrderItem>> response = new CustomResponse<>(
                "success",
                "Order items retrieved successfully",
                orderItems,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
