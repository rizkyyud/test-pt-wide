package com.testing.wide.tecnologies.demo_test.helpers;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private String customerName;
    private String customerAddress;
    private List<OrderItemDTO> orderItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemDTO {
        private Long productId;
        private Integer quantity;
    }
}