package com.products.products.entities;

import java.io.Serializable;
import java.util.Date;

import com.products.products.validations.FirstValidation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHOPPING_CART")
@Entity
public class ShoppingCartEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopping_id")
	private int shoppingId;
	
	@Positive(groups = {FirstValidation.class})
	@NotNull(groups = {FirstValidation.class})
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "product_quantity")
	private int productQuantity;
	
	@Column(name = "product_price")
	private Double productPrice;
	
	@Column(name = "created_at")
	private Date createdAt;

}
