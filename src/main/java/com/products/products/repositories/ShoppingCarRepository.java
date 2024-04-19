package com.products.products.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.products.products.dtos.ShoppingCarDTO;
import com.products.products.dtos.ShoppingCarTotalsDTO;
import com.products.products.entities.ShoppingCartEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ShoppingCarRepository extends PagingAndSortingRepository<ShoppingCartEntity, Integer> {
	
	ShoppingCartEntity save(ShoppingCartEntity shoppingCartEntity);
	
	// Get information to shopping car by user
	@Query("SELECT new com.products.products.dtos.ShoppingCarDTO(b.userId, a.productId, a.productName, (SUM(b.productPrice) / SUM(b.productQuantity)) AS productPrice, "
			+ "SUM(b.productQuantity) AS totalQuantity, SUM(b.productPrice) AS totalPrice) "
			+ "FROM ProductEntity a INNER JOIN ShoppingCartEntity b ON a.productId = b.productId "
			+ "WHERE b.userId = :userId AND b.statusId = :statusId "
			+ "GROUP BY b.userId, a.productId, a.productName, b.productPrice ORDER BY a.productId")
	List<ShoppingCarDTO> userShoppingValues(@Param("userId") int userId, @Param("statusId") int statusId);
	
	//get total values
	@Query("SELECT new com.products.products.dtos.ShoppingCarTotalsDTO(b.userId, (SUM(b.productPrice) - SUM(b.productTaxes)) AS subtotalPrice, SUM(b.productTaxes) AS totalTaxes, SUM(b.productPrice) AS totalPrice) "
			+ "FROM ProductEntity a INNER JOIN ShoppingCartEntity b ON a.productId = b.productId "
			+ "WHERE b.userId = :userId AND b.statusId = :statusId "
			+ "GROUP BY b.userId")
	Optional<ShoppingCarTotalsDTO> userShoppingTotals(@Param("userId") int userId, @Param("statusId") int statusId);
	
	
	Optional<ShoppingCartEntity> findByUserIdAndProductId(int userId, int productId);
	
	@Transactional
    @Modifying
	@Query("DELETE FROM ShoppingCartEntity s WHERE s.userId = :userId AND s.productId = :productId")
    void deleteByUserIdAndProductId(int userId, int productId);
	
	
	
	
}
