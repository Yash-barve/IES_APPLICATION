package com.main.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IES_ROLES_MANAGER_TBL")
public class RolesManagerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer role_manager_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private AdminEntity user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private RolesEntity roles;

}
