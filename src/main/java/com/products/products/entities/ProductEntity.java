package com.products.products.entities;

import java.io.Serializable;

import com.products.products.validations.FirstValidation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCTS")
@Entity
public class ProductEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Positive(groups = {FirstValidation.class})
	@NotNull(groups = {FirstValidation.class})
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "product_category")
	private String productCategory;
	
	@Column(name = "product_description")
	private String productDescription;
	
	@Column(name = "product_price")
	private Double productPrice;
	
	@Column(name = "product_quantity")
	private int productQuantity;
	
	@Column(name = "product_image")
	private String productImage;
	
	
	
}
