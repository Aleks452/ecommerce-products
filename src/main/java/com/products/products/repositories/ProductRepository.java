package com.products.products.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.products.products.dtos.ProductsDTO;
import com.products.products.entities.ProductEntity;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
	
	@Query("SELECT new com.products.products.dtos.ProductsDTO(a.productId, a.productName, a.productDescription, a.productPrice, a.productQuantity, a.productImage) "
			+ "FROM ProductEntity a INNER JOIN CategoriesEntity b ON a.categoryId = b.categoryId "
			+ " WHERE a.categoryId = :categoryId ORDER BY a.productId")
	Page<ProductsDTO> findProductsByCategoryId(int categoryId, Pageable pageable);
	
	@Query("SELECT new com.products.products.dtos.ProductsDTO(a.productId, a.productName, a.productDescription, a.productPrice, a.productQuantity, a.productImage) "
			+ "FROM ProductEntity a INNER JOIN CategoriesEntity b ON a.categoryId = b.categoryId ORDER BY a.productId")
	Page<ProductsDTO> findAllProducts(Pageable pageable);
}
