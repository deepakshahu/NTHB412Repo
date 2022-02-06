package com.nit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammerProjectInfo {
	//HAS-A property of ID class
	private PrgmrProjId id;
	private String prgmrName;
	private String projName;
	private double salary;
	private double budget;
}
