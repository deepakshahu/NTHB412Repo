package com.nit.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Data
@Entity
@Table(name = "Product")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Product {
	@Id
	@SequenceGenerator(name = "gen1", initialValue = 100, sequenceName = "prod_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer pid;
	@Column(length = 20)
	private String pname;
	private Float price;
	private Float qty;
	
	public Product() {
		System.out.println("Product :: 0-param constructor "+this.getClass());
	}
}
