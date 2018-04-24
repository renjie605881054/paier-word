/**
 * Copyright (c) <a href="http://git.oschina.net/eason_zhangfei">zhangfei_eason</a>.
 */
package com.paier.word.util.page;

import com.github.pagehelper.PageHelper;

/**
 * @author http://git.oschina.net/eason_zhangfei
 * @date 2016-11-03
 */
public class PageUtil {
	public static void startPage(Integer curPage, Integer pageSize) {
		// 1、设置分页
		if (curPage == null) {
			curPage = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
//		Map<String, Object> map = new HashMap<>();
//		map.put("pageNum", curPage);
//		map.put("pageSize", pageSize);
//		map.put("reasonable", false);
//		PageHelper.startPage(map);
		PageHelper.startPage(curPage, pageSize);
	}
}
