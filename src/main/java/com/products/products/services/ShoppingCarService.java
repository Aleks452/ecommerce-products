package com.products.products.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.products.dtos.ShoppingCarDTO;
import com.products.products.dtos.ShoppingCarTotalsDTO;
import com.products.products.entities.ShoppingCartEntity;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.exceptions.InternalErrorException;
import com.products.products.repositories.ShoppingCarRepository;
import com.products.products.utils.Constants;

@Service
public class ShoppingCarService {

	@Autowired
	ShoppingCarRepository shoppingCarRepository;

	private static final Logger logger = LoggerFactory.getLogger(ShoppingCarService.class);

	public List<ShoppingCarDTO> userShoppingValues(int userId, int statusId) {

		try
		{	
			// Get consolidate values for user
			List<ShoppingCarDTO> shopping = shoppingCarRepository.userShoppingValues(userId, statusId);

			if (shopping.isEmpty()) {
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_NOT_FOUND\"}");
			}

			return shopping;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}
	}

	public ShoppingCarTotalsDTO userShoppingTotals(int userId, int statusId) {

		try {
			// Get consolidate totals by user 
			Optional<ShoppingCarTotalsDTO> shopping = shoppingCarRepository.userShoppingTotals(userId, statusId);

			if (shopping.isPresent()) {
				return shopping.get();
			}

			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_NOT_FOUND\"}");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}

	}
	
	
	public void deleteByUserIdAndProductId(int userId, int productId) {
		try {
			// Delete info from shopping table
			Optional<ShoppingCartEntity> shopping = shoppingCarRepository.findByUserIdAndProductId(userId, productId);
			if (shopping.isPresent()) {
				shoppingCarRepository.deleteByUserIdAndProductId(userId, productId);
			}
			else {
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_NOT_FOUND\"}");
			}
			
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}
		
	}
	
}
