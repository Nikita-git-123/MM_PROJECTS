package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OrderDto;
import com.example.dto.PaymentCallBackDto;
import com.example.request.PurchaseOrderRequest;
import com.example.response.ApiResponse;
import com.example.response.PurchaseOrderResponse;
import com.example.service.OrderService;

@RestController
public class OrderRestController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> createOrder(@RequestBody PurchaseOrderRequest request) {

		ApiResponse<PurchaseOrderResponse> response = new ApiResponse<>();

		PurchaseOrderResponse orderResponse = orderService.createOrder(request);

		if (orderResponse != null) {
			response.setStatus(200);
			response.setMessage("Order Created");
			response.setData(orderResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Order Creation Failed");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping("/order")
	//createOrder
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> updateOrder(@RequestBody PaymentCallBackDto request) {

		ApiResponse<PurchaseOrderResponse> response = new ApiResponse<>();

		PurchaseOrderResponse orderResponse = orderService.updateOrder(request);

		if (orderResponse != null) {
			response.setStatus(200);
			response.setMessage("Order updated");
			response.setData(orderResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Order Updation Failed");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/orders/{email}")
	public ResponseEntity<ApiResponse<List<OrderDto>>> getMyOrders(@PathVariable String email) {

		ApiResponse<List<OrderDto>> response = new ApiResponse<>();

		List<OrderDto> ordersByEmail = orderService.getOrdersByEmail(email);

		if (!ordersByEmail.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Fetched Orders");
			response.setData(ordersByEmail);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch Orders");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
