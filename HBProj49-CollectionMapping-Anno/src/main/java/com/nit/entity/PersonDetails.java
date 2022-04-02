package com.nit.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ListIndexBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HB_ANNO_PER_DETAILS_COLL")
public class PersonDetails {
	@Id
	@GenericGenerator(name = "gen1", strategy = "sequence")
    @GeneratedValue(generator = "gen1")
	private Integer pid;
	@Column(length = 15)
	private String pname;
	@Column(length = 15)
	private String paddrs;
	
	@ElementCollection
	@CollectionTable(name="HB_ANNO_NICKNAMES_COLL", joinColumns = @JoinColumn(name="PERSON_ID", referencedColumnName = "PID"))
	@Column(name = "NICKNAMES", length = 15)
	@OrderColumn(name = "NNAME_INDX")
	@ListIndexBase(value = 1)
	private List<String> nickNames;
	
	@ElementCollection
	@CollectionTable(name="HB_ANNO_FRIENDS_COLL", joinColumns = @JoinColumn(name="PERSON_ID", referencedColumnName = "PID"))
	@Column(name = "FRIENDS", length = 15)
	private List<String> friends;
	
	@ElementCollection
	@CollectionTable(name="HB_ANNO_CONTACTS_COLL", joinColumns = @JoinColumn(name="PERSON_ID", referencedColumnName = "PID"))
	@Column(name = "CONTACTS", length = 15)
	private Set<Long> contactNumbers;
	
	@ElementCollection
	@CollectionTable(name="HB_ANNO_IDDETAILS_COLL", joinColumns = @JoinColumn(name="PERSON_ID", referencedColumnName = "PID"))
	@Column(name = "IDDETAILS", length = 15)
	@MapKeyColumn(name = "IDTYPE", length = 10)
	private Map<String, Long> idDetails;
}
