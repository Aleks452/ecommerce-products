package com.products.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCarDTO {
	
	private int userId;
	private int productId;
	private String productName;
	private double productPrice;
	private Long totalQuantity;
	private double totalPrice;
	
}
