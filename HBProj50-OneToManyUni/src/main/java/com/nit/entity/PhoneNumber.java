//child class
package com.nit.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class PhoneNumber implements Serializable{
	
	private Integer regNo;
	private Long mobileNo;
	private String numberType;
	private String provider;
	//taking property for pk column in purely optional
	
}
