package com.main.service;

import java.util.List;
import java.util.Set;

import com.main.entity.AdminEntity;
import com.main.entity.RolesManagerEntity;

public interface AdminService {

	public String upsert(AdminEntity entity, Set<RolesManagerEntity> userRoles);
	
	public List<AdminEntity> getAllRecords();
	
	public AdminEntity getById(Integer id);
	
	public String deleteById(Integer id);
	
}
