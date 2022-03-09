package com.nit.entity;

import lombok.Data;

@Data
public class SpecialProduct {
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;
	
	public SpecialProduct() {
		System.out.println("Product :: 0-param constructor :: "+this.hashCode());		
	}
}