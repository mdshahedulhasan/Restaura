package com.restaura.orderplacementservice.repository;

import com.restaura.orderplacementservice.entity.OrderPlacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPlacementRepository extends JpaRepository<OrderPlacement, Long> {
}

