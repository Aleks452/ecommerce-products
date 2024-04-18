package com.products.products.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.products.products.dtos.ProductsDTO;
import com.products.products.entities.ProductEntity;
import com.products.products.entities.ShoppingCartEntity;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.repositories.ProductRepository;
import com.products.products.repositories.ShoppingCarRepository;
import com.products.products.utils.Constants;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ShoppingCarRepository shoppingCarRepository;

	public Page<ProductsDTO> Allproducts(int pageNumber, int pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<ProductsDTO> products = productRepository.findAllProducts(pageable);

		if (products.isEmpty()) {
			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		}

		return products;
	}

	public Page<ProductsDTO> productsByCategory(int pageNumber, int pageSize, int categoryId) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<ProductsDTO> products = productRepository.findProductsByCategoryId(categoryId, pageable);

		if (products.isEmpty()) {
			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		}

		return products;
	}
	
	// Add product to shopping cart
	public ShoppingCartEntity addProducts(ProductEntity productEntity) {
		
		ShoppingCartEntity shoppping = new ShoppingCartEntity();
        shoppping.setUserId(1029009900);
        shoppping.setProductId(productEntity.getProductId());
        shoppping.setProductQuantity(productEntity.getProductQuantity());
        shoppping.setProductPrice(productEntity.getProductQuantity() * productEntity.getProductPrice());
        
        return shoppingCarRepository.save(shoppping);
	}

}
