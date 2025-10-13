package com.example.request;

import java.util.List;

import com.example.dto.AddressDto;
import com.example.dto.CustomerDto;
import com.example.dto.OrderDto;
import com.example.dto.OrderItemDto;

import lombok.Data;

@Data
public class PurchaseOrderRequest {
	
	private CustomerDto customerDto;
	
	private OrderDto orderDto;
	
	private AddressDto addressdto;
	
	private List<OrderItemDto> orderItemsDto;
	

}
