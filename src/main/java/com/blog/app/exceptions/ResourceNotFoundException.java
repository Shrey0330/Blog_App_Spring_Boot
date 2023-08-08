package com.blog.app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
String resourceName;
String fieldName;
long fieldValue;
String fieldString;
public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
	super(String.format("%s not found with %s:%s ",resourceName,fieldName,fieldValue ));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldValue = fieldValue;
}
public ResourceNotFoundException(String string, String string2, String username) {
	// TODO Auto-generated constructor stub
	super(String.format("%s not found with %s:%s ",string,string2,username ));
	this.resourceName = string;
	this.fieldName = string2;
	this.fieldString = username;
}
public String getResourceName() {
	return resourceName;
}
public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}
public String getFieldName() {
	return fieldName;
}
public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
}
public long getFieldValue() {
	return fieldValue;
}
public void setFieldValue(long fieldValue) {
	this.fieldValue = fieldValue;
}
}
