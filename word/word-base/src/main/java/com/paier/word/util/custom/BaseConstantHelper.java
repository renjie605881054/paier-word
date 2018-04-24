package com.paier.word.util.custom;

import java.util.HashMap;

public class BaseConstantHelper {

	public static final Integer MOLD_TYPE_ALL = 0 ;
	public static final Integer MOLD_TYPE_PC = 1;
	public static final Integer MOLD_TYPE_IOS = 2 ;
	public static final Integer MOLD_TYPE_AND = 3 ;
	public static final Integer MOLD_TYPE_WAP = 4 ;
	
	public static final HashMap<Object, String> MOLD_TYPE = new HashMap<Object,String>(){
		private static final long serialVersionUID = 1L;
		{
			put(MOLD_TYPE_ALL, "所有客戶端");
			put(MOLD_TYPE_PC, "电脑端");
			put(MOLD_TYPE_IOS, "IOS客户端");
			put(MOLD_TYPE_AND, "android客户端");
			put(MOLD_TYPE_WAP, "微信端");
		}
	};
	
	public static final Integer MOLD_PAY_P2P = 0 ;
	
	public static final HashMap<Object, String> MOLD_PAY = new HashMap<Object, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(MOLD_PAY_P2P, "p2p");
		}
	};
	
}
