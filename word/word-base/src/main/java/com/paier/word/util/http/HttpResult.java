package com.paier.word.util.http;

public class HttpResult {

	/** 状态码 */
	private Integer statusCode;

	/** 返回内容 */
	private String content;

	public HttpResult(Integer statusCode, String content) {
		super();
		this.statusCode = statusCode;
		this.content = content;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "HttpResult [statusCode=" + statusCode + ", content=" + content + "]";
	}
	
}
