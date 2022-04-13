package com.nit.entity;

import java.util.Set;

public interface IDepartment {
	public Integer getDeptNo();
	public void setDeptNo(Integer deptNo);
	public String getDeptName();
	public void setDeptName(String deptName);
	public String getDeptHead();
	public void setDeptHead(String deptHead);
	public Set<Employee> getEmployees();
	public void setEmployees(Set<Employee> list);
}
