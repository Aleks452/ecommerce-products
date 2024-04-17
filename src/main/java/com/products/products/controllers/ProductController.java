package com.products.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.products.Services.ProductService;
import com.products.products.dtos.PaginationDTO;
import com.products.products.entities.ProductEntity;
import com.products.products.validations.FirstValidation;
import com.products.products.validations.PaginationValidation;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<Page<ProductEntity>> allProducts(@Validated({PaginationValidation.class}) @RequestBody PaginationDTO paginationDTO) {
		return ResponseEntity.ok(productService.Allproducts(paginationDTO.getPageNumber(), paginationDTO.getPageSize()));
	}
	
	@GetMapping("/by-category")
	public ResponseEntity<Page<ProductEntity>> productsByCategory(@Validated({PaginationValidation.class, FirstValidation.class}) @RequestBody PaginationDTO paginationDTO) {
		return ResponseEntity.ok(productService.productsByCategory(paginationDTO.getPageNumber(), paginationDTO.getPageSize(), paginationDTO.getProductEntity().getCategoryId()));
	}
}
