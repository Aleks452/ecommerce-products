package com.products.products.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.products.products.entities.TaxesEntity;

public interface TaxesRepository extends PagingAndSortingRepository<TaxesEntity, Integer> {
	
	Optional<TaxesEntity> findByTaxesId(int taxesId);
}
