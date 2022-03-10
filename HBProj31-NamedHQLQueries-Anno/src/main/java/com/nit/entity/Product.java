package com.nit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import lombok.Data;

@Data
@Entity
@Table(name = "Product")
@NamedQuery(name = "HQL_GET_PRODUCTS_BY_PRICE_RANGE", query = "from Product where price>=:min and price<=:max")
@NamedQuery(name = "INCREASE_PRODUCT_PRICE_BY_PRICE_RANGE", query = "update Product set price=price+:newValue where price<=:range")
public class Product {
	@Id
	@GeneratedValue
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;
	
	public Product() {
		System.out.println("Product :: 0-param constructor :: "+this.hashCode());		
	}
}