package com.products.products.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.products.products.dtos.ShoppingCarDTO;
import com.products.products.dtos.ShoppingCarTotalsDTO;
import com.products.products.entities.ShoppingCartEntity;

@Repository
public interface ShoppingCarRepository extends PagingAndSortingRepository<ShoppingCartEntity, Integer> {
	
	ShoppingCartEntity save(ShoppingCartEntity shoppingCartEntity);
	
	// Get information to shopping car by user
	@Query("SELECT new com.products.products.dtos.ShoppingCarDTO(b.userId, a.productId, a.productName, "
			+ "SUM(b.productQuantity) AS productQuantity, SUM(b.productPrice) AS productPrice) "
			+ "FROM ProductEntity a INNER JOIN ShoppingCartEntity b ON a.productId = b.productId "
			+ "WHERE b.userId = :userId "
			+ "GROUP BY b.userId, a.productId, a.productName ORDER BY a.productId")
	List<ShoppingCarDTO> userShoppingValues(@Param("userId") int userId);
	
	//get total values
	@Query("SELECT new com.products.products.dtos.ShoppingCarTotalsDTO(b.userId, SUM(b.productQuantity) AS productQuantity, SUM(b.productPrice) AS productPrice) "
			+ "FROM ProductEntity a INNER JOIN ShoppingCartEntity b ON a.productId = b.productId "
			+ "WHERE b.userId = :userId "
			+ "GROUP BY b.userId")
	Optional<ShoppingCarTotalsDTO> userShoppingTotals(@Param("userId") int userId);
	
	
	
}
