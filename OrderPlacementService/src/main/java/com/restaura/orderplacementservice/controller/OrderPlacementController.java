package com.restaura.orderplacementservice.controller;

import com.restaura.orderplacementservice.entity.OrderPlacement;
import com.restaura.orderplacementservice.service.OrderPlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderPlacementController {

    @Autowired
    private OrderPlacementService orderPlacementService;

    @PostMapping
    public ResponseEntity<OrderPlacement> createOrder(@RequestBody OrderPlacement orderPlacement) {
        OrderPlacement createdOrder = orderPlacementService.createOrder(orderPlacement);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderPlacement> getOrderById(@PathVariable Long id) {
        Optional<OrderPlacement> orderPlacement = orderPlacementService.getOrderById(id);
        return orderPlacement.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrderPlacement>> getAllOrders() {
        List<OrderPlacement> orders = orderPlacementService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderPlacement> updateOrder(@PathVariable Long id, @RequestBody OrderPlacement orderPlacement) {
        try {
            OrderPlacement updatedOrder = orderPlacementService.updateOrder(id, orderPlacement);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderPlacementService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

