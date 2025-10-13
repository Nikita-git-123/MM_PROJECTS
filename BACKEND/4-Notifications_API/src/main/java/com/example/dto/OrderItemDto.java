package com.example.dto;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private Long id;
	private int quantity;
	private double unitPrice;
	private String imageUrl;
	private String productName;


}
