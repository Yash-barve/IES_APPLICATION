package com.main.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "IES_PlANS_TBL")
public class PlansEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planid;
	private String planname;
	
	private LocalDate planstartdate;
	private LocalDate planenddate;
	
	private String plancategory;
	private String planmode;
	
	@CreationTimestamp
	private LocalDate plancreatedate;
	
	@UpdateTimestamp
	private LocalDate planupdatedate;
	
	@ManyToOne
	@JoinColumn(name = "ADMINPLAN")
	private AdminEntity plan;
	

}
