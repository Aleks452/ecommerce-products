package com.products.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.products.dtos.PaginationDTO;
import com.products.products.dtos.ProductsDTO;
import com.products.products.entities.ProductEntity;
import com.products.products.services.ProductService;
import com.products.products.utils.Constants;
import com.products.products.validations.FirstValidation;
import com.products.products.validations.PaginationValidation;
import com.products.products.validations.SecondValidation;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<Page<ProductsDTO>> allProducts(@Validated({PaginationValidation.class}) @RequestBody PaginationDTO paginationDTO) {
		return ResponseEntity.ok(productService.Allproducts(paginationDTO.getPageNumber(), paginationDTO.getPageSize()));
	}

	@GetMapping("/by-category")
	public ResponseEntity<Page<ProductsDTO>> productsByCategory(@Validated({PaginationValidation.class, FirstValidation.class}) @RequestBody PaginationDTO paginationDTO) {
		return ResponseEntity.ok(productService.productsByCategory(paginationDTO.getPageNumber(), paginationDTO.getPageSize(), paginationDTO.getProductEntity().getCategoryId()));
	}

	@PostMapping("/add-products")
	public ResponseEntity<String> addProducts(@Validated({SecondValidation.class}) @RequestBody ProductEntity productEntity) {
	    productService.addProducts(productEntity);
	    return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"" + Constants.CREATED + "\",\"code\": \"CREATED\"}");
	}
	
	@PostMapping("/modify-products")
	public ResponseEntity<String> modifyProducts(@Validated({SecondValidation.class}) @RequestBody ProductEntity productEntity) {
	    productService.modifyProducts(productEntity);
	    return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"" + Constants.CREATED + "\",\"code\": \"CREATED\"}");
	}
}
