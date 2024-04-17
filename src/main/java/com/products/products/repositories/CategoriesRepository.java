package com.products.products.repositories;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.products.products.entities.CategoriesEntity;

@Repository
public interface CategoriesRepository extends PagingAndSortingRepository<CategoriesEntity, Integer> {
	
	List<CategoriesEntity> findAll();
}
