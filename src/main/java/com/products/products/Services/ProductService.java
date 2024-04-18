package com.products.products.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.products.products.dtos.ProductsDTO;
import com.products.products.entities.ProductEntity;
import com.products.products.entities.ShoppingCartEntity;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.exceptions.InternalErrorException;
import com.products.products.repositories.ProductRepository;
import com.products.products.repositories.ShoppingCarRepository;
import com.products.products.utils.Constants;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ShoppingCarRepository shoppingCarRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	public Page<ProductsDTO> Allproducts(int pageNumber, int pageSize) {

		try {
			// Get all products with pagination
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<ProductsDTO> products = productRepository.findAllProducts(pageable);

			if (products.isEmpty()) {
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
			}

			return products;

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}
	}

	public Page<ProductsDTO> productsByCategory(int pageNumber, int pageSize, int categoryId) {

		try {
			// Get product using a category with pagination
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<ProductsDTO> products = productRepository.findProductsByCategoryId(categoryId, pageable);

			if (products.isEmpty()) {
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
			}

			return products;

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}
	}

	// Add product to shopping cart
	public ShoppingCartEntity addProducts(ProductEntity productEntity) {

		try {
			
			// Values to add 
			ShoppingCartEntity shoppping = new ShoppingCartEntity();
			shoppping.setUserId(1029009900);
			shoppping.setProductId(productEntity.getProductId());
			shoppping.setProductQuantity(productEntity.getProductQuantity());
			shoppping.setProductPrice(productEntity.getProductQuantity() * productEntity.getProductPrice());

			return shoppingCarRepository.save(shoppping);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}
	}

}
