package com.main.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	@Column(updatable = false)
	private LocalDate plancreatedate;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate planupdatedate;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private AdminEntity plan;
	

}
