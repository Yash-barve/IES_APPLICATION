package com.main.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
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
	private LocalDate createdate;
	
	@UpdateTimestamp
	private LocalDate updatedate;
	
	@OneToMany(mappedBy = "plan" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<PlansEntity> plans;

	
}
