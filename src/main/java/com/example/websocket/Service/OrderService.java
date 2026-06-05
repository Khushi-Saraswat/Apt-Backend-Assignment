package com.example.websocket.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import com.example.websocket.Dto.CreateOrderRequest;
import com.example.websocket.Dto.DeleteResponse;
import com.example.websocket.Dto.OrderResponse;
import com.example.websocket.Model.Order;
import com.example.websocket.Repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private  SimpMessagingTemplate messagingTemplate;

    public OrderResponse createOrder(CreateOrderRequest request){

        
    Order order = Order.builder()
            .customerName(request.getCustomerName())
            .productName(request.getProductName())
            .status(request.getStatus())
            .build();
    Order saved=orderRepository.save(order);

    messagingTemplate.convertAndSend(
        "/topic/orders",
        saved
       );
    
    return mapToResponse(saved);
    }

    public List<OrderResponse> getAllOrders(){
        return orderRepository.findAll().stream().map(order ->
            mapToResponse(order)
        ).collect(Collectors.toList());
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public OrderResponse updateOrder(Long id, Order orderDetails){
        Order order = getOrderById(id);
        order.setCustomerName(orderDetails.getCustomerName());
        order.setProductName(orderDetails.getProductName());
        order.setStatus(orderDetails.getStatus());
        Order update=orderRepository.save(order);

        messagingTemplate.convertAndSend(
        "/topic/orders",
        update
        );

        return mapToResponse(update);
    }

    public DeleteResponse deleteOrder(Long id){
        Order order = getOrderById(id);

        orderRepository.delete(order);

        messagingTemplate.convertAndSend(
        "/topic/orders",
        "Order Deleted : " + id
       );
        return new DeleteResponse(
            "Order deleted successfully"
        );
    }

    private OrderResponse mapToResponse(Order order) {

    return OrderResponse.builder()
            .id(order.getId())
            .customerName(order.getCustomerName())
            .productName(order.getProductName())
            .status(order.getStatus())
            .updatedAt(order.getUpdatedAt())
            .build();
}
    

}
