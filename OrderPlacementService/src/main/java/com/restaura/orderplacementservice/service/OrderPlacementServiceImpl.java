package com.restaura.orderplacementservice.service;

import com.restaura.orderplacementservice.entity.OrderPlacement;
import com.restaura.orderplacementservice.repository.OrderPlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderPlacementServiceImpl implements OrderPlacementService {

    @Autowired
    private OrderPlacementRepository orderPlacementRepository;

    @Override
    public OrderPlacement createOrder(OrderPlacement orderPlacement) {
        return orderPlacementRepository.save(orderPlacement);
    }

    @Override
    public Optional<OrderPlacement> getOrderById(Long id) {
        return orderPlacementRepository.findById(id);
    }

    @Override
    public List<OrderPlacement> getAllOrders() {
        return orderPlacementRepository.findAll();
    }

    @Override
    public OrderPlacement updateOrder(Long id, OrderPlacement orderPlacement) {
        if (orderPlacementRepository.existsById(id)) {
            orderPlacement.setOrderId(id);
            return orderPlacementRepository.save(orderPlacement);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        orderPlacementRepository.deleteById(id);
    }
}
