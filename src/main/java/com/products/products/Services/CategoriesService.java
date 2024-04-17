package com.products.products.Services;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.products.entities.CategoriesEntity;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.repositories.CategoriesRepository;
import com.products.products.utils.Constants;

@Service
public class CategoriesService {
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	public List<CategoriesEntity> allCategories() {
		
		
		List<CategoriesEntity> categories = categoriesRepository.findAll();
		
		if (categories.isEmpty()) {
			throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_FOUND + "\",\"code\": \"INFO_FOUND\"}");
		}
		
		categories.sort(Comparator.comparing(CategoriesEntity::getProductCategory));
		return categories;
	}
}
