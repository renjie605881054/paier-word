package com.paier.word.util.entity;

import java.io.Serializable;

public abstract class ErrorMsg implements Serializable{

	private static final long serialVersionUID = 1L;
	private String errMsg;
	private String url;
	private Object obj;
	private Object size;
	
	public abstract void setErrCode(Integer errCode);
	public abstract Integer getErrCode();
	
	public ErrorMsg() {
		
	}	
	
	public ErrorMsg(String url) {
		super();
		this.url = url;
	}

	public ErrorMsg(String errMsg, String url) {
		super();
		this.errMsg = errMsg;
		this.url = url;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public Object getSize() {
		return size;
	}
	public void setSize(Object size) {
		this.size = size;
	}
	
}
