package com.paier.word.util.custom;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paier.word.util.DateUtil;
import com.paier.word.util.uuid.CustomUUID;
import com.paier.word.util.uuid.CustomUUIDBase;


/**
 * 基类
 * hj
 * @version 1.0
 * @date 2017年4月19日
 * Copyright 浙江荷银控股有限公司 All Rights Reserved
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class GlobalP2P {
	public static Logger payLog = LoggerFactory.getLogger("p2pPayFile");
	
	private static final Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");

	public static final Long NOW_MACHINE_NUM = CustomUUIDBase.P2P_MACHINE_NUM ;
	
	public static final Integer CODEVALIDATE = 600 ;
	
	public static final String UPLOAD_DIR = "/upload/" ;
	
	//public static final String LOGIN_PREFIX = "treasureFinal_system_" ;
	
	public static final Integer LOGIN_TIME = 3600 ;
	
	public static final Integer REDIS_LOGIN_DB = 0;
	public static final Integer REDIS_LOGIN_SSO = 1 ;
	public static final Integer REDIS_PARAM_DB = 2 ;
	
	public Long Generate2(){
		CustomUUID uuid = new CustomUUID(GlobalP2P.NOW_MACHINE_NUM, ThreadLocalRandom.current().nextInt(8));
		return uuid.generate();
	}
	
	public Long Generate(){
		String a = String.valueOf(DateUtil.getTime(DateUtil.getNow()));
		String b = String.valueOf(Math.random());
		b = b.replace(".", "");
		b = b.substring(0, 9);
		return Long.valueOf(a + b);
	}
	
	public static String getProperties(String files , String key){
		payLog.info("进入调试方法------------------files：" + files + ",----key:" + key);
		Properties properties = new Properties();
		try{
			properties.load(GlobalP2P.class.getClassLoader().getResourceAsStream(files+".properties"));
			String value = properties.getProperty(key);
			payLog.info("进入调试方法------------value:" + value);
			Matcher matcher = PATTERN.matcher(value);
			StringBuffer sb = new StringBuffer();
			while(matcher.find()){
				String matcherKey = matcher.group(1);
	            String matchervalue = properties.getProperty(matcherKey);
	            if (matchervalue != null) {
	                matcher.appendReplacement(sb, matchervalue);
	            }
			}
			matcher.appendTail(sb);
	        return sb.toString();
		}catch(Exception e){
			return "" ;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			System.out.println(new GlobalP2P().Generate());
//			System.out.println(new GlobalP2P().Generate2());
		}
	}
	
}
