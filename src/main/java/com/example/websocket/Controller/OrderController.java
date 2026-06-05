package com.example.websocket.Controller;
import com.example.websocket.Dto.CreateOrderRequest;
import com.example.websocket.Dto.DeleteResponse;
import com.example.websocket.Dto.OrderResponse;
import com.example.websocket.Model.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.websocket.Service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    
    @Autowired
    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody CreateOrderRequest order){
        return orderService.createOrder(order);
    }
 
    @GetMapping
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public OrderResponse updateOrder(@PathVariable Long id, @RequestBody Order orderDetails){
        return orderService.updateOrder(id, orderDetails);
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deleteOrder(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }

}
