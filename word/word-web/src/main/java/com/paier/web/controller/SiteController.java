package com.paier.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paier.web.base.BaseWebController;
import com.paier.word.user.entity.User;
import com.paier.word.user.service.LoginService;

@Controller
public class SiteController extends BaseWebController{
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String actionToLogin(HttpServletRequest request){
		return "redirect: /login" ;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		loginService.login(new User(1L));
		return WebRoot + "site/login";
	}
}
