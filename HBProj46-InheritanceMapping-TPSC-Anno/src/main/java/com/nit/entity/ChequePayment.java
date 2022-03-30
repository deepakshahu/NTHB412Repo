package com.nit.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "HB_ANNO_INH_CHEQUE_TPSC")
@PrimaryKeyJoinColumn(name = "PAYMENT_ID", referencedColumnName = "TXID")
public class ChequePayment extends Payment {
	
	private Long chequeNo;
	private String chequeType;
	
	@Override
	public String toString() {
		return "ChequePayment [chequeNo=" + chequeNo + ", chequeType=" + chequeType + super.toString()+"]";
	}
}
