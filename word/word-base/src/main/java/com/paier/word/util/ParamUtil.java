package com.paier.word.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数封装工具类
 * Created by fmy on 2017/12/5.
 */
public class ParamUtil {

    private static Map<String, String> data = new HashMap<>();

    public static void initParam(Map<String, String> map){
        ParamUtil.data = map;
    }

    public static String getValue(String key){
        return data.get(key);
    }

}
