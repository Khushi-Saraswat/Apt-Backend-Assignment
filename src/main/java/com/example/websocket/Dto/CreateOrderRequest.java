package com.example.websocket.Dto;

import com.example.websocket.Enum.OrderStatus;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private String customerName;

    private String productName;

    private OrderStatus status;
}
