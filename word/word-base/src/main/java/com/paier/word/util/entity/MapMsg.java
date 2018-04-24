package com.paier.word.util.entity;

import java.util.Map;

public  class MapMsg {
	
	private String errMsg;
	private Map<String,Object> map;
	
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public Map<String,Object> getMap() {
		return map;
	}
	public void setMap(Map<String,Object> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return "MapMsg [errMsg=" + errMsg + ", map=" + map + "]";
	}
	
	

}
