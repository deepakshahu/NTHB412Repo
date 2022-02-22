package com.nit.entity;

import lombok.Data;

@Data
public class Product {
	private String pid;
	private String pname;
	private Float price;
	private Float qty;
}