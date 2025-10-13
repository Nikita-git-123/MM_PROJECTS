package com.example.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private String title;
	private BigDecimal unitPrice;
	private String imageUrl;
	private boolean active;
	private int unitInStocks;
	private Date dateCreated;
	private Date lastUpdated;

}
