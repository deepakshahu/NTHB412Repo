package com.nit.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ANNO_HB_DEPARTMENT")
public class Department {
	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "dept_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer deptNo;
	@Column(length = 20)
	private String deptName;
	@Column(length = 20)
	private String deptHead;
	@OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dept", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	//@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPTNO")
	private Set<Employee> employees;  //for one to many
	
	public Department() {
		System.out.println("Department :: 0-param constructor :: "+this.getClass());
	}
	
	@Override
	public String toString() {
		return "Department [deptNo=" + deptNo + ", deptName=" + deptName + ", deptHead=" + deptHead + "]";
	}
}
