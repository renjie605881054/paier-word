package com.paier.word.util.custom;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

	public static String join(String join,String[] arr){
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < arr.length ; i ++ ){
			if(i == arr.length - 1){
				sb.append(arr[i]);
			}else{
				sb.append(arr[i]).append(join);
			}
		}
		return new String(sb);
	}
	
	public static String joinSqlIn(String join, String[] arr){
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < arr.length ; i ++ ){
			if(i == arr.length - 1){
				sb.append("\"").append(arr[i]).append("\"");
			}else{
				sb.append("\"").append(arr[i]).append("\"").append(join);
			}
		}
		return new String(sb);
	}
	
	public static String[] explode(String tag,String str){
		String[] arr = str.split("\\" + tag);
		return arr ;
	}
	
	public static Integer gerYear(){
		Calendar calendar = Calendar.getInstance();
		Integer year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	public static String getBirthDay(String card){
		StringBuffer sb = new StringBuffer();
		String prefix = "-";
		String year = card.substring(6, 10);
		String month = card.substring(10,12);
		String day = card.substring(12,14);
		sb.append(year).append(prefix).append(month).append(prefix).append(day);
		return sb.toString();
	}
	
	public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    } 
	
}
