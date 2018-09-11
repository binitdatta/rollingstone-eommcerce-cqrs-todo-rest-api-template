package com.rollingstone.domain;

public class RSResponse<T> {

	private String message;
	private T payload;
	private String errorMessage;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public RSResponse(String message, T payload, String errorMessage) {
		super();
		this.message = message;
		this.payload = payload;
		this.errorMessage = errorMessage;
	}
	public RSResponse() {
		super();
	}
	@Override
	public String toString() {
		return "RSResponse [message=" + message + ", payload=" + payload + ", errorMessage=" + errorMessage + "]";
	}
	
	
}
