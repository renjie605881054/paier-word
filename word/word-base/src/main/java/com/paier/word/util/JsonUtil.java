package com.paier.word.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.paier.word.base.CoreBase;

/**
 * JSON工具类
 * @author hj
 *
 */
public class JsonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private JSONObject jsonObject;

    private JsonUtil() {
    }

    public static JsonUtil newJson() {
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.jsonObject = new JSONObject();
        jsonUtil.jsonObject.put("message", CoreBase.SUCCESS_STRING);
        jsonUtil.jsonObject.put("code", 0000);
        return jsonUtil;
    }

    public JsonUtil addData(String key, Object value) {
        this.jsonObject.put(key, value);
        return this;
    }

    public JsonUtil addMessage(Object value) {
        this.jsonObject.put("message", value);
        return this;
    }

    public JsonUtil addCode(Object value) {
        this.jsonObject.put("code", value);
        return this;
    }

    public JsonUtil addPageInfo(PageInfo<?> pageInfo){
        JSONObject page = new JSONObject();
        page.put("currentPage", Integer.valueOf(pageInfo.getPageNum()));
        page.put("pages", Integer.valueOf(pageInfo.getPages()));
        page.put("total", Long.valueOf(pageInfo.getTotal()));
        this.jsonObject.put("page",page);
        return this;
    }

    public String toJsonString() {
        return this.jsonObject.toJSONString();
    }


    public JSON toJson() {
        return this.jsonObject;
    }
    
    /**
	 * 根据key获取json字符串中的值
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static String get(String jsonStr, String key) {
		if (StringUtil.isBlank(jsonStr) || StringUtil.isBlank(key)) {
			return "";
		}
		try {
			JSONObject obj = JSONObject.parseObject(jsonStr);
			if (null == obj) {
				return "";
			}
			return obj.getString(key);
		} catch (JSONException e) {
			LOGGER.error("转换JSON异常：" + jsonStr);
			return "";
		}
	}

}
