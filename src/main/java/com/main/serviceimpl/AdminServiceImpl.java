package com.main.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.constants.Appconstants;
import com.main.entity.AdminEntity;
import com.main.entity.RolesManagerEntity;
import com.main.repo.AdminRepo;
import com.main.repo.RoleRepo;
import com.main.service.AdminService;
import com.main.utils.PwdGenerator;
import com.main.utils.SendMailing;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private SendMailing sendMailing;

	@Override
	public String upsert(AdminEntity entity, Set<RolesManagerEntity> userRoles) {

		AdminEntity email = adminRepo.findByEmail(entity.getEmail());

		if (null != email) {
			return Appconstants.KEY_UNI_EMAIL;
		}

		for (RolesManagerEntity ur : userRoles) {
			roleRepo.save(ur.getRoles());
		}
		entity.getUserRoles().addAll(userRoles);

		String tpass = PwdGenerator.PasswordGenerate();

		entity.setMode(Appconstants.KEY_ACT);
		entity.setStatus(Appconstants.KEY_LOCK);
		entity.setPassword(tpass);

		String to = entity.getEmail();
		String subject = Appconstants.KEY_UNL;
		StringBuffer body = new StringBuffer("");

		body.append(Appconstants.KEY_TEMP_MSG_);
		body.append(Appconstants.KEY_PASS + "<b style=color:red >" + tpass + "</b>");
		body.append("<br>");
		body.append("<br>");
		body.append(Appconstants.KEY_LINK + to + Appconstants.KEY_CLICK);

		sendMailing.sendMailPwd(subject, body.toString(), to);

		adminRepo.save(entity);
		return Appconstants.KEY_SUCC;
	}

	@Override
	public List<AdminEntity> getAllRecords() {
		List<AdminEntity> list = adminRepo.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}

	}

	@Override
	public AdminEntity getById(Integer id) {

		Optional<AdminEntity> findById = adminRepo.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public String deleteById(Integer id) {

		if (adminRepo.existsById(id)) {
			adminRepo.deleteById(id);
			return Appconstants.KEY_DEL;
		} else {
			return Appconstants.KEY_NO_DEL;
		}
	}

}
