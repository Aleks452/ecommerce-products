package com.products.products.Services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.products.dtos.ShoppingCarDTO;
import com.products.products.dtos.ShoppingCarTotalsDTO;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.exceptions.InternalErrorException;
import com.products.products.repositories.ShoppingCarRepository;
import com.products.products.utils.Constants;

@Service
public class ShoppingCarService {

	@Autowired
	ShoppingCarRepository shoppingCarRepository;

	private static final Logger logger = LoggerFactory.getLogger(ShoppingCarService.class);

	public List<ShoppingCarDTO> userShoppingValues(int userId) {

		try
		{	
			// Get consolidate values for user
			List<ShoppingCarDTO> shopping = shoppingCarRepository.userShoppingValues(userId);

			if (shopping.isEmpty()) {
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
			}

			return shopping;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}
	}

	public ShoppingCarTotalsDTO userShoppingTotals(int userId) {

		try {
			// Get consolidate totals by user 
			Optional<ShoppingCarTotalsDTO> shopping = shoppingCarRepository.userShoppingTotals(userId);

			if (shopping.isPresent()) {
				return shopping.get();
			}

			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}

	}
}
