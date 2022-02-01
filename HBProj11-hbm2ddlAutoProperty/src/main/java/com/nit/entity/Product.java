//Entity/BO/Model/Persistence class (Java Bean class)
package com.nit.entity;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Product {
	private Integer pid;
	@Column(name="PNAME", length=20, unique=true, nullable=false)
	private String pname;
	private Float price;
	private Float qty;
	//private String status;
}
