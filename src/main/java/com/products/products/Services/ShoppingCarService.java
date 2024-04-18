package com.products.products.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.products.dtos.ShoppingCarDTO;
import com.products.products.dtos.ShoppingCarTotalsDTO;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.repositories.ShoppingCarRepository;
import com.products.products.utils.Constants;

@Service
public class ShoppingCarService {

	@Autowired
	ShoppingCarRepository shoppingCarRepository;


	public List<ShoppingCarDTO> userShoppingValues(int userId) {

		List<ShoppingCarDTO> shopping = shoppingCarRepository.userShoppingValues(userId);

		if (shopping.isEmpty()) {
			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		}

		return shopping;
	}

	public ShoppingCarTotalsDTO userShoppingTotals(int userId) {

		Optional<ShoppingCarTotalsDTO> shopping = shoppingCarRepository.userShoppingTotals(userId);

		if (shopping.isPresent()) {
			return shopping.get();
		}
		
		throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		
	}
}
