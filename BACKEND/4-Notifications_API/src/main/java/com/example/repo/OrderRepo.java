package com.example.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{
	
	public Order findByRazorPayOrderId(String razorPayOrderId);
	
	public List<Order> findByEmail(String email);
	
	public List<Order> findByDeliveryDate(LocalDate deliveryDate);
 
}
