package com.paier.word.base;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.paier.word.util.page.PageBean;

/**
 * 实体基类
 */
@XmlRootElement
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public BaseEntity() {
		super();
	}
	
	@JsonSerialize(using=ToStringSerializer.class)
	private Long id ;
	private String orderStr ;
	private String groupStr ;
	private String customUuid;
	private String updateTime;
	private String updateIp;
	private String createTime;
	private String createIp;
	
	private PageBean page;
	
	public void preInsert(String ip){
		this.createIp = ip ;
		this.customUuid = UUID.randomUUID().toString();
		this.createTime = String.valueOf(System.currentTimeMillis()/1000l);
		this.updateTime = this.createTime;
		this.updateIp = this.createIp ;
	}
	
	public void preUpdate(String ip){
		this.updateIp = ip ;
		this.updateTime = String.valueOf(System.currentTimeMillis()/1000l);
	}
		
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public String getGroupStr() {
		return groupStr;
	}

	public void setGroupStr(String groupStr) {
		this.groupStr = groupStr;
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomUuid() {
		return customUuid;
	}

	public void setCustomUuid(String customUuid) {
		this.customUuid = customUuid;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateIp() {
		return updateIp;
	}

	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	
}
