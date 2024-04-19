package com.products.products.services;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.products.entities.CategoriesEntity;
import com.products.products.exceptions.CriteriaIssuesException;
import com.products.products.exceptions.InternalErrorException;
import com.products.products.repositories.CategoriesRepository;
import com.products.products.utils.Constants;

@Service
public class CategoriesService {

	@Autowired
	CategoriesRepository categoriesRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoriesService.class);

	public List<CategoriesEntity> allCategories() {

		try {
			// Get categories to being used in products endpoint
			List<CategoriesEntity> categories = categoriesRepository.findAll();

			if (categories.isEmpty()) {
				throw new CriteriaIssuesException("{\"message\": \"" + Constants.INFO_NOT_FOUND + "\",\"code\": \"INFO_FOUND\"}");
			}

			categories.sort(Comparator.comparing(CategoriesEntity::getProductCategory));
			return categories;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new InternalErrorException("{\"message\": \"" +  Constants.INTERNAL_ERROR + "\",\"code\": \"INTERNAL_ERROR\"}");

		}
	}
}
