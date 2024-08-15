package com.restaura.orderplacementservice.service;

import com.restaura.orderplacementservice.entity.OrderPlacement;

import java.util.List;
import java.util.Optional;

public interface OrderPlacementService {
    OrderPlacement createOrder(OrderPlacement orderPlacement);
    Optional<OrderPlacement> getOrderById(Long id);
    List<OrderPlacement> getAllOrders();
    OrderPlacement updateOrder(Long id, OrderPlacement orderPlacement);
    void deleteOrder(Long id);
}
