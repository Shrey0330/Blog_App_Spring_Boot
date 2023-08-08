package com.blog.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private String message;
	private boolean success;

public ApiResponse() {
		super();
	}


public String getMessage() {
	return message;
}
public ApiResponse(String message, boolean success) {
	super();
	this.message = message;
	this.success = success;
}


public void setMessage(String message) {
	this.message = message;
}
public boolean isSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}

}
