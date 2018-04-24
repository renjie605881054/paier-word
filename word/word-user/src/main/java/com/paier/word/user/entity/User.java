package com.paier.word.user.entity;

import com.paier.word.base.BaseEntity;

public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String password;
	
	public User() {}
	
	public User(Long id) {
		setId(id);
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
