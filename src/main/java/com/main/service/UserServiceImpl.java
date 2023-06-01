package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.binding.Forgot;
import com.main.binding.LoginForm;
import com.main.binding.UnlockForm;
import com.main.constants.Appconstants;
import com.main.entity.AdminEntity;
import com.main.repo.AdminRepo;
import com.main.utils.SendMailing;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private SendMailing sendMailing;

	@Override
	public String unlock(UnlockForm form) {
		AdminEntity entity = adminRepo.findByEmail(form.getEmail());

		if (entity.getPassword().equals(form.getTpass())) {
			entity.setPassword(form.getPassword());
			entity.setStatus(Appconstants.KEY_ULK);
			adminRepo.save(entity);
			return Appconstants.KEY_STA_ULK;
		}
		return Appconstants.KEY_GLOB_ERR;
	}

	@Override
	public String login(LoginForm form) {

		AdminEntity entity = adminRepo.findByEmailAndPassword(form.getEmail(), form.getPassword());

		if (entity == null) {
			return Appconstants.KEY_IN_CRED;
		}
		if (entity.getStatus().equals(Appconstants.KEY_LK)) {
			return Appconstants.KEY_ACC_UNLOCK;
		}
		return "Welcome " + entity.getFullname();
	}

	@Override
	public String forgot(Forgot form) {

		AdminEntity entity = adminRepo.findByEmail(form.getEmail());

		if (entity == null) {
			return Appconstants.KEY_MANULL;
		}

		String subject = Appconstants.KEY_UNL;
		String body = Appconstants.KEY_YOU_PASS + entity.getPassword();

		sendMailing.sendMailPwd(subject, body, form.getEmail());

		return Appconstants.KEY_MAIL_ST;
	}

}
