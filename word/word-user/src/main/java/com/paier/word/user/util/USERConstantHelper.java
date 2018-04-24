package com.paier.word.user.util;

import java.util.HashMap;

import com.paier.word.util.custom.BaseConstantHelper;


public class USERConstantHelper extends BaseConstantHelper{
	
	public static final Integer USER_STATUS_LOGIN = 0 ;
	public static final Integer USER_STATUS_CLOSE = 1 ;
	
	public static final HashMap<Object, String> USER_STATUS = new HashMap<Object,String>(){
		private static final long serialVersionUID = 1L;
		{
			put(USER_STATUS_LOGIN, "正常登录");
			put(USER_STATUS_CLOSE, "账户锁定");
		}
	};
	
	public static final Integer USER_AUTH_NOT = 0 ;
	public static final Integer USER_AUTH_YES = 1 ;
	
	public static final HashMap<Object, String> USER_AUTH = new HashMap<Object, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(USER_AUTH_NOT, "未认证");
			put(USER_AUTH_YES, "已认证");
		}
	};
	
	public static final Integer USER_AUTH_CARD_ID = 0 ;
	public static final Integer USER_AUTH_CARD_OTHER = 1; 
	
	public static final HashMap<Object, String> USER_AUTH_CARD = new HashMap<Object, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(USER_AUTH_CARD_ID, "身份证");
			put(USER_AUTH_CARD_OTHER, "其他");
		}
	};
	
	public static final Integer USER_VALIDATE_NAME = 0 ;
	public static final Integer USER_VALIDATE_PHONE = 1 ;
	public static final Integer USER_VALIDATE_EMAIL = 2 ;
	public static final Integer USER_VALIDATE_REAL = 3 ; 
	
	public static final HashMap<Object, String> USER_VALIDATE_TYPE = new HashMap<Object, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(USER_VALIDATE_NAME, "验证用户名");
			put(USER_VALIDATE_PHONE, "验证手机号");
			put(USER_VALIDATE_EMAIL, "验证邮箱");
			put(USER_VALIDATE_REAL, "验证实名");
		}
	};
	
	public static HashMap<String, HashMap<Object, String>> TREASUREFINAL_USER_TYPE = new HashMap<String, HashMap<Object, String>>(){
		private static final long serialVersionUID = 1L;
		{
			put("mold_type", MOLD_TYPE);
			put("user_auth", USER_AUTH);
			put("user_status", USER_STATUS);
			put("user_auth_card", USER_AUTH_CARD);
			put("user_validate_type", USER_VALIDATE_TYPE);
		}
	};
	
}
