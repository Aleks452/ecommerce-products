package com.products.products.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TAXES")
@Entity
public class TaxesEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "taxes_id")
	private int taxesId;
	
	@Column(name = "tax_percentage")
	private Double taxPercentage;
	
	@Column(name = "tax_description")
	private String taxDescription;
}
