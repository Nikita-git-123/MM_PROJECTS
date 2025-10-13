package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
