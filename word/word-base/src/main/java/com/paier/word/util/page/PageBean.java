 package com.paier.word.util.page;
 
import java.io.Serializable;
import java.util.Map;

/**
 * 分页bean
 * @author Administrator
 *
 */
 public class PageBean implements Serializable {
 	
 	private static final long serialVersionUID = 1L;
 	
 	private int page = 1;
 	
 	private int pageSize = 17;//每页显示条数

 	private int begin;// 起始行

 	private int totalCount;// 总行数

 	private int totalPage;// 总页数
 	
 	private Map<String, Object> condition;
 	 	
 	public PageBean(){
 		super();
 	}
 	
 	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
 		return page;
 	}

 	public void setPage(int page) {
 		this.page = page;
 	}

 	public int getPageSize() {
 		return pageSize;
 	}

 	public void setPageSize(int pageSize) {
 		this.pageSize = pageSize;
 	}

 	/**
 	 * 获取开始条数（当前页-1*每页条数）
 	 * @return
 	 */
 	public int getBegin() {
 		page = (page == 0) ? 1 : page ;
 		begin = (page - 1) * pageSize;
 		return begin;
 	}

 	public void setBegin(int begin) {
 		this.begin = begin;
 	}

 	public int getTotalCount() {
 		return totalCount;
 	}

 	public void setTotalCount(int totalCount) {
 		this.totalCount = totalCount;
 	}

 	/**
 	 * 获取最大页数（已判断是否整除+1）
 	 * @return
 	 */
 	public int getTotalPage() {
 		if (totalCount % pageSize == 0) {
 			totalPage = totalCount / pageSize;
 		} else {
 			totalPage = totalCount / pageSize + 1;
 		}
 		return totalPage;
 	}

 	public void setTotalPage(int totalPage) {
 		this.totalPage = totalPage;
 	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", pageSize=" + pageSize + ", begin=" + begin + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", condition=" + condition + "]";
	}

 }
