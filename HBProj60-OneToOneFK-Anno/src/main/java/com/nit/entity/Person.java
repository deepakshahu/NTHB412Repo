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
@Table(name = "HB_ANNO_PERSON")
public class Person implements Serializable {
	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "per_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer pid;
	@Column(length = 20)
	private String pname;
    @Column(length = 20)
	private String paddrs;
	
	public Person() {
		System.out.println("Person :: 0-param constructor :: "+this.getClass());
	}

	@Override
	public String toString() {
		return "Person [pid=" + pid + ", pname=" + pname + ", paddrs=" + paddrs + "]";
	}
	
}
