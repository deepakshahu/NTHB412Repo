package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
	//@GenericGenerator(name = "gen1", strategy = "increment")
	//@GenericGenerator(name = "gen1", strategy = "identity")
	//@GenericGenerator(name = "gen1", strategy = "sequence")
	//@GenericGenerator(name = "gen1", strategy = "sequence", parameters = {@Parameter(name = "sequence_name", value = "HB_PRODID_SEQ1")})
	@GenericGenerator(name = "gen1", strategy = "sequence", 
									 parameters = {@Parameter(name = "sequence_name", value = "NIT_PRODID_SEQ1"),
									 			   @Parameter(name = "initial_value", value = "100"),
									 			   @Parameter(name = "increment_size", value = "1")})
	@GeneratedValue(generator = "gen1")
	private Integer mid;
	
	@Column(name="MNAME", length=50)  //Mapping with column
	private String mname;
	
	@Column(name="HERONAME", length=50)  //Mapping with column
	private String heroName;
	
	@Column(name="BUDGET")  //Mapping with column
	private Float budget;
}