package com.main.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "IES_USERS_TBL")
public class AdminEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String fullname;
	
	private String email;
	
	private String password;
	
	private Long number;
	
	private String gender;
	
	private LocalDate dob;
	
	private Long ssn;
	
	private String mode;
	
	private String status;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdate;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "plan" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<PlansEntity> plans;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
	private Set<RolesManagerEntity> userRoles = new HashSet<>();

	
}
