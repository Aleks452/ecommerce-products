package com.products.products.dtos;

import com.products.products.entities.ProductEntity;
import com.products.products.validations.PaginationValidation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
	
	@PositiveOrZero(groups = {PaginationValidation.class})
	private int pageNumber;
	
	@Positive(groups = {PaginationValidation.class})
	private int pageSize;
	
	@Valid
	private ProductEntity productEntity;
	
}
