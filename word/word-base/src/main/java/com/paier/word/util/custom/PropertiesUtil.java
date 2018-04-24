package com.paier.word.util.custom;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取properties工具
 * @author Administrator
 *
 */
public class PropertiesUtil {

	private static final Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");//支持简单变量使用
	
	public static String get(String files , String key){
		Properties properties = new Properties();
		try{
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(files+".properties"));
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
		}catch(IOException e){
			return "" ;
		}
	}
	
}
