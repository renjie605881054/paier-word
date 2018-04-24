package com.paier.word.base;

/**
 * core核心文件基础
 * @version 1.0.0
 *
 */
public class CoreBase {

	public static final String CHARSET = "UTF-8" ;
	
	public static final String PROPERTIES_URL = "properties/" ;
	
	//public static final String SSO_COOKIE_NAME = "treasureFinal" ;
	public static final String SSO_COOKIE_NAME = "JSESSIONID";
	
	public static final String SSO_COOKIE_VALI = "vali";
	
	public static final String SUCCESS_STRING = "SUCCESS" ; 
	
	public static final Integer PARAMS_VALIDATE = 3600;
	
	public static final String REDIS_SYSTEM_PARAMS = "system_params" ;
	
	public static final String REDIS_SYSTEM_MENUS = "system_menus" ; 
	
	public static final String REDIS_SYSTEM_ROLES = "system_roles" ;

	/**
	 * session缓存id
	 */
	public static final String SESSION_MANAGER = "session_manager" ;

	/** 短信模板--注册验证码*/
	public static final String SEND_TMPL_REGISTER_CODE = "1595329015215820802";
	
	/** 系统设置-所属类型--短信设置*/
	public static final String SYS_SMS_CONF = "1596144971048554500";

	/** 字典类型--项目类型*/
	public static final String ITEM_TYPE_PROJECT = "1597865474969833479";
	
	/** 字典类型--项目还款方式*/
	public static final String ITEM_TYPE_REPAY = "1597890872709484551";
	
	/** 字典类型--项目借款期限*/
	public static final String ITEM_TYPE_PROJECT_TIME_LIMIT = "1597871915721035777";
	
	/** 字典类型--项目借款期限（天）*/
	public static final String ITEM_TYPE_PROJECT_TIME_DAY = "1597870542920093699";


	/******************rabbit key*********************************/
	/**
	 * 充值
	 */
	public final static String RECHARGE_QUEUE_KEY = "recharge_queue_key";
	/**
	 * 提现
	 */
	public final static String WITHDRAW_QUEUE_KEY = "withdraw_queue_key";
	/**
	 * 投资
	 */
	public final static String ORDER_QUEUE_KEY = "order_queue_key";
	/**
	 * 满标
	 */
	public final static String FULL_QUEUE_KEY = "full_queue_key";
	/**
	 * 还款
	 */
	public final static String REPAY_QUEUE_KEY = "repay_queue_key";
	/**
	 * 待收
	 */
	public final static String COLLECT_QUEUE_KEY = "collect_queue_key";
	/******************rabbit key end*********************************/
}
