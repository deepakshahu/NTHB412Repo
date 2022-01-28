package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import com.nit.proxy.IMovie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name="MOVIE_INFO")
@AllArgsConstructor
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Proxy(lazy = true, proxyClass = com.nit.proxy.IMovie.class)
public final class Movie implements IMovie{
	@Id  //Singular id field
	@Column(name="MID")  //Mapping with column
	private Integer mid;
	@Column(name="MNAME", length=50)  //Mapping with column
	private String mname;
	@Column(name="HERONAME", length=50)  //Mapping with column
	private String heroName;
	@Column(name="BUDGET")  //Mapping with column
	//@Transient
	private Float budget;
	
	public Movie() {
		System.out.println("Movie :: 0-param constructor :: "+this.getClass());
	}
}