package com.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.constants.Appconstants;
import com.main.entity.PlansEntity;
import com.main.repo.PlansRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlansRepo plansRepo;

	@Override
	public String upsertPlan(PlansEntity entity) {
		entity.setPlanmode(Appconstants.KEY_ACT);
		PlansEntity save = plansRepo.save(entity);
		return Appconstants.KEY_SUCC;
	}

	@Override
	public List<PlansEntity> getAllPlan() {
		return plansRepo.findAll();
	}

	@Override
	public PlansEntity getById(Integer id) {
		if (plansRepo.existsById(id)) {
			Optional<PlansEntity> findById = plansRepo.findById(id);
			if (findById.isPresent()) {
				return findById.get();
			}
		}
		return null;
	}

	@Override
	public String delete(Integer id) {

		if (plansRepo.existsById(id)) {
			plansRepo.deleteById(id);
			return Appconstants.KEY_DEL;
		} else {
			return Appconstants.KEY_NO_DEL;
		}
	}

}
