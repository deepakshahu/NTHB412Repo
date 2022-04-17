//child class
package com.nit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ANNO_HB_OTM_PHONE_NUMBERS")
public class PhoneNumber implements Serializable{
	@SequenceGenerator(name = "gen1", sequenceName = "PHONE_SEQ", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@Id
	private Integer regNo;
	private Long mobileNo;
	@Column(length = 20)
	private String numberType;
	@Column(length = 20)
	private String provider;
	//taking property for pk column is purely optional
	
	@Override
	public String toString() {
		return "PhoneNumber [regNo=" + regNo + ", mobileNo=" + mobileNo + ", numberType=" + numberType + ", provider="
				+ provider + "]";
	}
	
}
