package com.products.products.Services;

import org.springdoc.core.converters.models.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.products.products.entities.ProductEntity;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.repositories.ProductRepository;
import com.products.products.utils.Constants;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Page<ProductEntity> Allproducts(int pageNumber, int pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<ProductEntity> products = productRepository.findAll(pageable);

		if (products.isEmpty()) {
			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		}

		return products;
	}

	public Page<ProductEntity> productsByCategory(int pageNumber, int pageSize, int categoryId) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<ProductEntity> products = productRepository.findByCategoryId(categoryId, pageable);

		if (products.isEmpty()) {
			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		}

		return products;
	}

}
