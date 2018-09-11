package com.rollingstone.exception;

/*
* A sample class for adding error information in the response
*/

public class RestAPIExceptionInfo {
	public final String detail;
	public final String message;

	public RestAPIExceptionInfo(Exception ex, String detail) {
		this.message = ex.getLocalizedMessage();
		this.detail = detail;
	}
}
