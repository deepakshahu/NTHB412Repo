package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="MOVIE_INFO")
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	@Id  //Singular id field
	@Column(name="MID")  //Mapping with column
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@SequenceGenerator(name="gen1", sequenceName = "JPA_MID_SEQ", initialValue = 700, allocationSize = 5)
	//@SequenceGenerator(name="gen1", sequenceName = "JPA_MID_SEQ1")
	/*@TableGenerator(name = "gen1", table = "ID_TAB", pkColumnName = "PK_COL", pkColumnValue = "MID", valueColumnName = "VAL_COL", 
																						initialValue = 400, allocationSize = 2)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.TABLE)*/
	
	//@GeneratedValue(strategy = GenerationType.TABLE)
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
	/*@SequenceGenerator(name="gen1", sequenceName = "JPA_MID_SEQ", initialValue = 700, allocationSize = 5)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.AUTO)*/
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	@Column(name="MNAME", length=50)  //Mapping with column
	private String mname;
	
	@Column(name="HERONAME", length=50)  //Mapping with column
	private String heroName;
	
	@Column(name="BUDGET")  //Mapping with column
	private Float budget;
}