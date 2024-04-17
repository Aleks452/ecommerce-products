package com.products.products.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "CATEGORIES")
@Entity
public class CategoriesEntity {
	
	@Id
	@Column(name = "category_id")	
	private Integer categoryId;
	
	@Column(name = "product_category")	
	private String productCategory;
	
}
