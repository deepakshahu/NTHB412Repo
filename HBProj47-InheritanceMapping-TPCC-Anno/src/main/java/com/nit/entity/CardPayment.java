package com.nit.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "HB_ANNO_INH_CARD_TPCC")
public class CardPayment extends Payment {
	
	private Long cardNo;
	private String cardType;
	private String gateway;
	
	@Override
	public String toString() {
		return "CardPayment [cardNo=" + cardNo + ", cardType=" + cardType + ", gateway=" + gateway + super.toString()+"]";
	}
}
