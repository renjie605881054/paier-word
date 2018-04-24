package com.paier.word.util.custom;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paier.word.util.StringUtil;
import com.paier.word.util.XorEncrypt;


/**
 * 公共util
 * @author Administrator
 *
 */
public class BaseUtil {
	public static Logger payLog = LoggerFactory.getLogger("p2pPayFile");
	public static final String VALICODE_SALT = "treasureFinal@heyin~salt";
	public static final String NULLSALT = "";
	
	public static <T> String validateObj(T t){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> set = validator.validate(t);
		StringBuilder sb = null ;
		if(set.size() > 0){
			sb = new StringBuilder() ; 
			for(ConstraintViolation<T> val: set){
				sb.append(val.getMessage());
			}
		}
		return sb == null ? null : sb.toString();
	}
		
	public static final String encrypt(String srcStr, String salt) {
		try {
			if (!salt.equals("")) {
				srcStr += salt;
			}
			String result = "";
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
			for (byte b : bytes) {
				String hex = Integer.toHexString(b & 0xFF).toUpperCase();
				result += ((hex.length() == 1) ? "0" : "") + hex;
			}
			return result;
		} catch (Exception e) {
			return "";
		}
	}

	public static Cookie getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		payLog.info("---------cookies------:"+cookies + "-----key----:"+key);
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				payLog.info(cookies[i].getName());
				String name = cookies[i].getName();
				payLog.info("---------cookie++++++++:"+name + "-----key----:"+key);
				if (name.equals(key)) {
					return cookies[i];
				}
			}
		}
		return null;
	}
	
	public static boolean validate(String md5RandomCode,String inputRandomCode, String salt) {
		if (StringUtil.isBlank(md5RandomCode)|| StringUtil.isBlank(inputRandomCode)) {
			return false;
		}
		inputRandomCode = inputRandomCode.toUpperCase();
		inputRandomCode = encrypt(inputRandomCode, salt);
		return inputRandomCode.equals(md5RandomCode);
	}
	
	public static String encryptCookieValue(String data) {
		XorEncrypt xor = new XorEncrypt(VALICODE_SALT);
		String encode = xor.encrypt(data);
		return encode;
	}

	public static String decryptCookie(String encStr) {
		try {
			XorEncrypt xor = new XorEncrypt(VALICODE_SALT);
			return xor.decrypt(encStr);
		} catch (Exception e) {
			return "";
		}

	}
	
	public static String generatorToken(String str) {
		return md5(VALICODE_SALT + str + VALICODE_SALT);
	}

	public static boolean validToken(String userCookie, String tokenCookie) {
		return md5( VALICODE_SALT + userCookie + VALICODE_SALT).equals( tokenCookie );
	}

	public static boolean validCookie(Cookie userCookie, Cookie tokenCookie) {
		return md5(VALICODE_SALT+ userCookie.getValue() + VALICODE_SALT).equals( tokenCookie.getValue() );
	}
	
	public static String get_pass(Long userId, String password) {
		return md5(SHA1(md5(password).substring(7, 31) + userId));
	}
	
	public static String md5(String strs) {
		byte[] source = strs.getBytes();
		char str[] = new char[16 * 2];
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
		} catch (NoSuchAlgorithmException e) {
		}
		return new String(str);

	}

	public static String SHA1(String strs) {
		byte[] _bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(strs.getBytes());
			_bytes = md.digest();
		} catch (NoSuchAlgorithmException ex) {

		}
		return new BigInteger(1, _bytes).toString(16);

	}

	public static String md5file(File file) throws Exception {
		byte[] _bytes = null;
		InputStream is = new FileInputStream(file);
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] buffer = new byte[8192];
		int read = 0;
		while ((read = is.read(buffer)) > 0) {
			digest.update(buffer, 0, read);
		}
		_bytes = digest.digest();
		if (is != null) {
			is.close();
			is = null;
		}
		return new BigInteger(1, _bytes).toString(16);
	}

	public static String getCRC32(String str) {
		java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
		crc32.update(str.getBytes());
		String retcrc32 = Long.toHexString(crc32.getValue());
		return retcrc32;
	}
	
	/**
	 * 将对象转为Map，为null的字段跳过，同时保持有序
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map bean2MapNull(Object bean) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Class type = bean.getClass();
		Map returnMap = new TreeMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					//没有值的字段直接跳过
				}
			}
		}
		return returnMap;
	}
	
	public static String date2Time(String dateStr){
		if(dateStr!=null && !"".equals(dateStr) ){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = dateFormat.parse(dateStr);
				Long timeStamp = date.getTime()/1000l;
				return timeStamp.toString();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public static String time2Date(String timestampString, String pattern) {
		Long timestamp = Long.valueOf(timestampString.length() < 13 ? Long.parseLong(timestampString) * 1000L : Long.parseLong(timestampString));
		String date = new SimpleDateFormat(pattern).format(new Date(timestamp.longValue()));
		return date;
	}
	
	public static String dateAddTime(String time, int type, int add){
		String result = "" ;
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time2Date(time, "yyyy-MM-dd HH:mm:ss"));
			calendar.setTime(date);
			if(type == 0){
				calendar.add(2, add);
			}else if(type == 1){
				calendar.add(6, add);
			}
			result = String.valueOf(calendar.getTimeInMillis()/1000l);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Integer timestams(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return (int) (calendar.getTime().getTime()/1000l);
	}
	
	public static List<String> getTimeList(Integer time, String format){
		List<String> timeList = new ArrayList<>();
		Integer nowTime = timestams();
		for( int i = time; i > 0; i -- ){
			Integer nowTimeStamp = nowTime - ( i * 3600 * 24 ) ;
			String nowTimeFormat = time2Date(nowTimeStamp.toString(), format);
			timeList.add(nowTimeFormat);
		}
		return timeList;
	}

	public static void main(String[] args) {
		System.out.print(get_pass(151686218596561L, "abc123456"));
		//System.out.print(md5("abc123").substring(7, 31) + 100l);
	}

}
