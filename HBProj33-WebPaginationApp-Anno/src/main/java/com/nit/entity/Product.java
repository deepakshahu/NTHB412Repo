package com.nit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product {
	@Id
	@GeneratedValue
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;
}