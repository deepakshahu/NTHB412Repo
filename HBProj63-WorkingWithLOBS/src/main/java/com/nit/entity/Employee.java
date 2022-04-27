package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ANNO_HB_EMPLOYEE_LOBs")
public class Employee {
	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "emp_lob_seq", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer empNo;
	@Column(length = 20)
	private String empName;
	private Float empSalary;
	@Lob
	private byte[] empPhoto;
	@Lob
	private char[] empResume;
	
	public Employee() {
		System.out.println("Employee :: 0-param constructor");
	}

}
