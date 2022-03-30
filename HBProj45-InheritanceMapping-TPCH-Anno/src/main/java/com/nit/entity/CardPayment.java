package com.nit.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue("card")
public class CardPayment extends Payment {
	
	private Long cardNo;
	private String cardType;
	private String gateway;
	
	@Override
	public String toString() {
		return "CardPayment [cardNo=" + cardNo + ", cardType=" + cardType + ", gateway=" + gateway + super.toString()+"]";
	}
}
