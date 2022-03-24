package com.nit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="HB_COMP_PERSON")
public class Person implements Serializable {
	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.AUTO)
	private Integer pid;
	@Column(length = 20)
	private String pname;
	@Column(length = 20)
	private String paddrs;
	@Embedded  //for component property
	private JobDetails details;
}