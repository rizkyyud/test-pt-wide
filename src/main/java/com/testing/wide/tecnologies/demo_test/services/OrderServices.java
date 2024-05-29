package com.testing.wide.tecnologies.demo_test.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.wide.tecnologies.demo_test.helpers.OrderRequestDTO;
import com.testing.wide.tecnologies.demo_test.helpers.ResourceNotFoundException;
import com.testing.wide.tecnologies.demo_test.models.entities.Order;
import com.testing.wide.tecnologies.demo_test.models.entities.OrderItem;
import com.testing.wide.tecnologies.demo_test.models.entities.Product;
import com.testing.wide.tecnologies.demo_test.models.repositories.OrderItemRepository;
import com.testing.wide.tecnologies.demo_test.models.repositories.OrderRepository;
import com.testing.wide.tecnologies.demo_test.models.repositories.ProductRepository;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order addProductToOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setCustomerName(orderRequestDTO.getCustomerName());
        order.setCustomerAddress(orderRequestDTO.getCustomerAddress());
        order.setOrderDate(LocalDateTime.now());

        order = orderRepository.save(order);

        for (OrderRequestDTO.OrderItemDTO itemDTO : orderRequestDTO.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setTotal(product.getPrice() * itemDTO.getQuantity());

            orderItemRepository.save(orderItem);
        }

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}
