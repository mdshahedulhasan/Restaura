package com.restaura.orderplacementservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class OrderPlacement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String customerId;  // ID of the customer
    private String restaurantId;  // ID of the restaurant
    private String pickupTime;  // "Real time pickup (3-4 hours)" or "Another day pickup"
    private String status;  // Status of the order (e.g., "Pending", "Completed", etc.)
}

