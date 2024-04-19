package com.products.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.products.dtos.ShoppingCarDTO;
import com.products.products.dtos.ShoppingCarTotalsDTO;
import com.products.products.entities.ShoppingCartEntity;
import com.products.products.services.ShoppingCarService;
import com.products.products.utils.Constants;
import com.products.products.validations.FirstValidation;
import com.products.products.validations.SecondValidation;

@RestController
@RequestMapping("/shopping")
public class ShoppingCarController {

	@Autowired
	ShoppingCarService shoppingCarService;
	
	@GetMapping("/values-by-user")
	public ResponseEntity<List<ShoppingCarDTO>> userShoppingValues(@Validated(FirstValidation.class) @RequestBody ShoppingCartEntity shoppingCartEntity) {
		return ResponseEntity.ok(shoppingCarService.userShoppingValues(shoppingCartEntity.getUserId(), shoppingCartEntity.getStatusId()));
	}
	
	@GetMapping("/totals-by-user")
	public ResponseEntity<ShoppingCarTotalsDTO> userShoppingTotals(@Validated(FirstValidation.class) @RequestBody ShoppingCartEntity shoppingCartEntity) {
		return ResponseEntity.ok(shoppingCarService.userShoppingTotals(shoppingCartEntity.getUserId(), shoppingCartEntity.getStatusId()));
	}
	
	@DeleteMapping("/by-user-and-product")
	public ResponseEntity<String> deleteByUserIdAndProductId(@Validated(SecondValidation.class) @RequestBody ShoppingCartEntity shoppingCartEntity) {
		shoppingCarService.deleteByUserIdAndProductId(shoppingCartEntity.getUserId(), shoppingCartEntity.getProductId());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"message\": \"" + Constants.DELETED + "\",\"code\": \"ACCEPTED\"}");
	}
}
