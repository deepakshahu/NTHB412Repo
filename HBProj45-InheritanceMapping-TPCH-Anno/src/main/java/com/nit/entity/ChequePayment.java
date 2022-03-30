package com.nit.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue("cheque")
public class ChequePayment extends Payment {
	
	private Long chequeNo;
	private String chequeType;
	
	@Override
	public String toString() {
		return "ChequePayment [chequeNo=" + chequeNo + ", chequeType=" + chequeType + super.toString()+"]";
	}
}
