package com.products.products.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "CATEGORIES")
@Entity
public class CategoriesEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id")	
	private Integer categoryId;
	
	@Column(name = "product_category")	
	private String productCategory;
	
}
