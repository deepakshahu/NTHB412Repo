package com.nit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id  //Singular id field
	@GeneratedValue
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;
}