package com.chinaops.web.edesktop.service;

import java.util.List;

import com.chinaops.web.edesktop.dao.LoginUserDaoImpl;
import com.chinaops.web.edesktop.entity.User;

public class LoginUserService {
	
	private LoginUserDaoImpl loginUserDaoImpl;

	public LoginUserDaoImpl getLoginUserDaoImpl() {
		return loginUserDaoImpl;
	}
	public void setLoginUserDaoImpl(LoginUserDaoImpl loginUserDaoImpl) {
		this.loginUserDaoImpl = loginUserDaoImpl;
	}


	/**
	 * Description: 根据用户名获取用户
	 * @Version 1.0 2013-8-21 上午09:28:23崔万哲(cuiwanzhe@china-ops.com) 创建
	 * @param username
	 * @return
	 */
	public List<User> getUserByUsername(String username){
		return this.loginUserDaoImpl.getUserByUsername(username);
	}
}
