package com.products.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.products.Services.CategoriesService;
import com.products.products.entities.CategoriesEntity;


@RestController
@RequestMapping("/categories")
public class CategoriesController {
	
	@Autowired
	CategoriesService categoriesService;
	
	@GetMapping
	public ResponseEntity<List<CategoriesEntity>> allProducts() {
		return ResponseEntity.ok(categoriesService.allCategories());
	}
}
