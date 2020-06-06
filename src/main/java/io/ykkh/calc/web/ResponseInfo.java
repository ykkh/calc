package io.ykkh.calc.web;

import org.springframework.http.HttpStatus;

public class ResponseInfo {
	private HttpStatus status;
	private String message;
	private Object data;
	
	public ResponseInfo() {
		
	}
	public ResponseInfo(HttpStatus status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
