//parent class
package com.nit.entity;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class PersonDetails implements Serializable {
	
	private Integer pid;
	private String pname;
	private String paddrs;
	//special property to hold bunch of child class objects (one to many association)
	private Set<PhoneNumber> phones;
	
	//toString
	@Override
	public String toString() {
		return "PersonDetails [pid=" + pid + ", pname=" + pname + ", paddrs=" + paddrs + "]";
	}
}
