package com.example.websocket.Model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.example.websocket.Enum.OrderStatus;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;


    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }







}
