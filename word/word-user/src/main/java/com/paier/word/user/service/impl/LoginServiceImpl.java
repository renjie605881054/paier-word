package com.paier.word.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paier.word.user.dao.LoginDao;
import com.paier.word.user.entity.User;
import com.paier.word.user.service.LoginService;
import com.paier.word.user.util.USERErrorMsg;

@Service(value="loginService")
public class LoginServiceImpl implements LoginService{

	@Resource
	private LoginDao loginDao;
	
	@Override
	public USERErrorMsg login(User entity) {
		User user = loginDao.selectByPrimaryKey(entity.getId());
		System.out.println(user);
		return null;
	}
}
