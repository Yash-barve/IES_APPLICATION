package com.main.service;

import com.main.binding.Forgot;
import com.main.binding.LoginForm;
import com.main.binding.UnlockForm;

public interface UserService {

	public String unlock(UnlockForm form);

	public String login(LoginForm form);

	public String forgot(Forgot form);

}
