package com.example.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import lombok.SneakyThrows;

@Service
public class RazorPayImplService implements RazorPayService {
	
	@Value("${razorpay.key}")
	private String keyId;
	
	@Value("${razorpay.secret}")
	private String keySecret;
	
	private RazorpayClient razorPayClient;

	@Override
	@SneakyThrows
	public Order createPaymentOrder(double amount) {
		
		this.razorPayClient = new RazorpayClient(keyId, keySecret);
		
		JSONObject orderRequest = new JSONObject();
		
		orderRequest.put("amount", amount * 100);
		orderRequest.put("currency", "INR");
		orderRequest.put("payment_capture", 1);
		
		Order razorPayOrder = razorPayClient.orders.create(orderRequest);
		return razorPayOrder;
	}

}
