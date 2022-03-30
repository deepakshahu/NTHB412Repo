package com.nit.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "HB_ANNO_INH_PAYMENT_TPCC")  //remains empty db table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment {
	
	@Id
	@GenericGenerator(name = "gen1", strategy = "sequence")
	@GeneratedValue(generator = "gen1")
	private Long txid;
	private Double amount;
	private LocalDateTime txDate;
}
