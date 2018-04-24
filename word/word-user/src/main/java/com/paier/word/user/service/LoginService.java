package com.paier.word.user.service;

import com.paier.word.user.entity.User;
import com.paier.word.user.util.USERErrorMsg;

public interface LoginService {
	
	public USERErrorMsg login(User entity);
}
