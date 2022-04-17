package com.nit.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ANNO_HB_DOCTOR")
public class Doctor {
	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "doc_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer docId;
	@Column(length = 20)
	private String docName;
	@Column(length = 20)
	private String hospital;
	@ManyToMany(targetEntity = Patient.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ANNO_HB_DOCTORS_PATIENTS", joinColumns = @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCID"),
											inverseJoinColumns = @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATID"))
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Patient> patients;  //to hold bunch of patients
	
	public Doctor() {
		System.out.println("Doctor :: 0-param constructor");
	}

	@Override
	public String toString() {
		return "Doctor [docId=" + docId + ", docName=" + docName + ", hospital=" + hospital + "]";
	}
	
}
