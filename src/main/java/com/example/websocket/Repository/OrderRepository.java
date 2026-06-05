package com.example.websocket.Repository;
import com.example.websocket.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;




public interface OrderRepository extends JpaRepository<Order,Long>{
    
}
