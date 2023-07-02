package com.main.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.main.binding.Dashboard;
import com.main.constants.Appconstants;
import com.main.entity.AdminEntity;
import com.main.entity.PlansEntity;
import com.main.repo.AdminRepo;
import com.main.repo.PlansRepo;
import com.main.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlansRepo plansRepo;

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public String upsertPlan(PlansEntity entity , Integer id) {
		
		AdminEntity adid = adminRepo.findById(id).get();

		entity.setPlanmode(Appconstants.KEY_ACT);
		entity.setPlan(adid);
		plansRepo.save(entity);
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

	@Override
	public Dashboard dashboardPage(Integer id) {

		Dashboard dashboard = new Dashboard();

		Optional<AdminEntity> findById = adminRepo.findById(id);

		if (findById.isPresent()) {

			AdminEntity adminEntity = adminRepo.findById(id).get();

			List<PlansEntity> enquiry = adminEntity.getPlans();

			Integer total = enquiry.size();

			Integer approved = enquiry.stream().filter(e -> e.getPlanmode().equals("Approved"))
					.collect(Collectors.toList()).size();

			Integer denied = enquiry.stream().filter(e -> e.getPlanmode().equals("Denied")).collect(Collectors.toList())
					.size();

			Integer benefit = enquiry.stream().filter(e -> e.getPlanmode().equals("Benefit"))
					.collect(Collectors.toList()).size();

			dashboard.setTotal(total);
			dashboard.setApproved(approved);
			dashboard.setDenied(denied);

		}

		return dashboard;
	}

}
