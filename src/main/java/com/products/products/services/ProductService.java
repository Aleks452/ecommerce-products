package com.products.products.services;

import java.util.Optional;

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
import com.products.products.entities.TaxesEntity;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.exceptions.InternalErrorException;
import com.products.products.repositories.ProductRepository;
import com.products.products.repositories.ShoppingCarRepository;
import com.products.products.repositories.TaxesRepository;
import com.products.products.utils.Constants;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ShoppingCarRepository shoppingCarRepository;

	@Autowired
	TaxesRepository taxesRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	public Page<ProductsDTO> Allproducts(int pageNumber, int pageSize) {

		try {
			// Get all products with pagination
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<ProductsDTO> products = productRepository.findAllProducts(pageable);

			if (products.isEmpty()) {
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_FOUND\"}");
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
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_FOUND\"}");
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

			Optional<TaxesEntity> taxes = taxesRepository.findByTaxesId(productEntity.getTaxesId());

			if (taxes.isPresent()) {
				// Values to add 

				Optional<ShoppingCartEntity> shoppingCar = shoppingCarRepository.findByUserIdAndProductId(1029009900, productEntity.getProductId());	
				if (shoppingCar.isPresent()) {

					ShoppingCartEntity shoppping = shoppingCar.get();
					shoppping.setProductQuantity(shoppingCar.get().getProductQuantity() + productEntity.getProductQuantity());
					shoppping.setProductPrice((shoppingCar.get().getProductPrice() + (productEntity.getProductQuantity() * productEntity.getProductPrice())));
					shoppping.setProductTaxes((shoppingCar.get().getProductTaxes() + (productEntity.getProductQuantity() * productEntity.getProductPrice()) * taxes.get().getTaxPercentage()));

					return shoppingCarRepository.save(shoppping);
				}

				ShoppingCartEntity shoppping = new ShoppingCartEntity();
				shoppping.setUserId(1029009900);
				shoppping.setProductId(productEntity.getProductId());
				shoppping.setProductQuantity(productEntity.getProductQuantity());
				shoppping.setProductPrice(productEntity.getProductQuantity() * productEntity.getProductPrice());
				shoppping.setProductTaxes((productEntity.getProductQuantity() * productEntity.getProductPrice()) * taxes.get().getTaxPercentage());
				shoppping.setStatusId(1);

				return shoppingCarRepository.save(shoppping);
			}

			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_FOUND\"}");

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}
	}

	// Add product to shopping cart
	public ShoppingCartEntity modifyProducts(ProductEntity productEntity) {

		try {
			Optional<TaxesEntity> taxes = taxesRepository.findByTaxesId(productEntity.getTaxesId());

			if (taxes.isPresent()) {
				// Values to add 

				Optional<ShoppingCartEntity> shoppingCar = shoppingCarRepository.findByUserIdAndProductId(1029009900, productEntity.getProductId());	
				if (shoppingCar.isPresent()) {

					ShoppingCartEntity shoppping = shoppingCar.get();
					shoppping.setProductQuantity(productEntity.getProductQuantity());
					shoppping.setProductPrice((productEntity.getProductQuantity() * productEntity.getProductPrice()));
					shoppping.setProductTaxes((productEntity.getProductQuantity() * productEntity.getProductPrice()) * taxes.get().getTaxPercentage());

					return shoppingCarRepository.save(shoppping);
				}
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_FOUND\"}");
			}
			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		}catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}

	}
}	
