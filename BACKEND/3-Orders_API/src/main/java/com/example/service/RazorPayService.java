package com.example.service;

import com.razorpay.Order;

public interface RazorPayService {
	
	public Order createPaymentOrder(double amount);

}
