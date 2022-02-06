package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PROGRAMMERPROJECTINFO_ANNO")
public class ProgrammerProjectInfo {
	//HAS-A property of ID class
	@EmbeddedId
	private PrgmrProjId id;
	@Column(name="PRGMRNAME", length=20)
	private String prgmrName;
	@Column(name="PROJNAME", length=20)
	private String projName;
	@Column(name="SALARY")
	private double salary;
	@Column(name="BUDGET")
	private double budget;
}
