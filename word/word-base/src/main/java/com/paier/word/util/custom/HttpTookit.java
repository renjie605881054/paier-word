package com.paier.word.util.custom;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;

public final class HttpTookit {
	
	public static String doGet(String url, String queryString) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			if (StringUtils.isNotBlank(queryString))
				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == 200)
				response = method.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	public static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		Iterator<Entry<String, String>> iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			method.addParameter((String) entry.getKey(), (String) entry.getValue());
		}
		method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		try {
			client.executeMethod(method);
			if (method.getStatusCode() == 200)
				response = method.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}

		return response;
	}

	public static String sendGet(String url) {
		String msg = "";
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
			msg = creatConnection(url, httpURLConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("信息发送情况1：" + msg);
		return msg;
	}

	private static String creatConnection(String url, HttpURLConnection httpURLConnection) {
		String msg = "";
		try {
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
			httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("Content-Type", "text/html;charset=utf-8");
			msg = receiveMessage(httpURLConnection);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(httpURLConnection);
		}
		return msg;
	}

	private static void closeConnection(HttpURLConnection httpURLConnection) {
		try {
			if (httpURLConnection != null)
				httpURLConnection.disconnect();
		} catch (Exception localException) {
		}
	}

	private static String receiveMessage(HttpURLConnection httpURLConnection) {
		String responseBody = null;
		try {
			InputStream httpIn = httpURLConnection.getInputStream();
			if (httpIn != null) {
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				while (-1 != ((byte) httpIn.read())) {
					byte tempByte1 = 0;
					byteOut.write(tempByte1);
				}
				responseBody = new String(byteOut.toByteArray(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return responseBody;
	}
	
	/**
	 * 获取真实IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIpAddr(HttpServletRequest request){
		if  (request ==  null )  
	            return null ;   
	        String ip = request.getHeader("X-Forwarded-For");  
	        if  (ip ==  null  || ip.length()==  0  ||  "unknown".equalsIgnoreCase(ip))  
	            ip = request.getHeader("Proxy-Client-IP");  
	        if  (ip ==  null  || ip.length()==  0  ||  "unknown".equalsIgnoreCase(ip))  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        if  (ip ==  null  || ip.length()==  0  ||  "unknown".equalsIgnoreCase(ip))  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	        if  (ip ==  null  || ip.length()==  0  ||  "unknown".equalsIgnoreCase(ip))  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	        if  (ip ==  null  || ip.length()==  0  ||  "unknown".equalsIgnoreCase(ip))  
	            ip = request.getRemoteAddr();  
	        if("127.0.0.1".equals(ip) ||  "0:0:0:0:0:0:0:1".equals(ip))
	        	try{  
	                ip = InetAddress.getLocalHost().getHostAddress();  
	            }  
	            catch  (UnknownHostException unknownhostexception){  
	            } 
	        return  ip;
	}

	public static String getHost(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String domain = url.delete(url.length() - request.getRequestURI().length(), url.length())
				.append(request.getContextPath()).toString();
		return domain;
	}
	
}
