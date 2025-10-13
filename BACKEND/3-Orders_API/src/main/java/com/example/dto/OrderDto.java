package com.example.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDto {

	private Long orderId;

	private String orderTrackingNum;

	private String razorPayOrderId; // payment initiated

	private String email;

	private String orderStatus;

	private Double totalPrice;

	private Integer totalQuantity;

	private String razorPayPaymentId; // payment completed

	private String invoiceUrl;

	private LocalDate deliveryDate;

}
