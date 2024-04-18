package com.products.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
	
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private int productQuantity;
	private String productImage;
}
