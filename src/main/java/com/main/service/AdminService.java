package com.main.service;

import java.util.List;

import com.main.entity.AdminEntity;

public interface AdminService {

	public String upsert(AdminEntity entity);
	
	public List<AdminEntity> getAllRecords();
	
	public AdminEntity getById(Integer id);
	
	public String deleteById(Integer id);
	
}
