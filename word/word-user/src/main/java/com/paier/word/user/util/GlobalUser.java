package com.paier.word.user.util;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.paier.word.util.uuid.CustomUUID;
import com.paier.word.util.uuid.CustomUUIDBase;


/**
 * 
 * @author Administrator
 *
 */
public class GlobalUser {
	
	private static final Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");

	public static final Long NOW_MACHINE_NUM = CustomUUIDBase.USER_MACHINE_NUM ;
	
	public static final Integer CODEVALIDATE = 600 ;
	
	public static final String LOGIN_PREFIX = "treasureFinal_user_" ;
	
	public static final Integer LOGIN_TIME = 3600 ;
	
	public static final Integer REDIS_LOGIN_DB = 3;
	public static final Integer REDIS_LOGIN_SSO = 4 ;
	
	public Long Generate(){
		CustomUUID uuid = new CustomUUID(GlobalUser.NOW_MACHINE_NUM, ThreadLocalRandom.current().nextInt(8));
		return uuid.generate();
	}
	
	public static String getProperties(String files , String key){
		Properties properties = new Properties();
		try{
			properties.load(GlobalUser.class.getClassLoader().getResourceAsStream(files+".properties"));
			String value = properties.getProperty(key);
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
	
}
