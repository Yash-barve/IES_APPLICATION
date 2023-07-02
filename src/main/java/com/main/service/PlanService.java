package com.main.service;

import java.util.List;

import com.main.binding.Dashboard;
import com.main.entity.PlansEntity;

public interface PlanService {
	
	public String upsertPlan(PlansEntity entity,Integer id);
	
	public List<PlansEntity> getAllPlan();
	
	public PlansEntity getById(Integer id);
	
	public String delete(Integer id);
	
	public Dashboard dashboardPage(Integer id);

}
