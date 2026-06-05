package com.example.websocket.Dto;

import java.time.LocalDateTime;

import com.example.websocket.Enum.OrderStatus;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderResponse {
    
     private Long id;

    private String customerName;

    private String productName;

    private OrderStatus status;

    private LocalDateTime updatedAt;
}
