package com.products.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCarTotalsDTO {
	
	private int userId;
	private Long totalQuantity;
	private double totalPrice;
}
