package com.bridgelabz.FundooApp.Exceptions;

import java.util.Date;

public class Response
{
 private Date timestamp;
 private String message;
 private int status;
 private Object result;
public Response(Date timestamp, String message, int status, Object result) {
	super();
	this.timestamp = timestamp;
	this.message = message;
	this.status = status;
	this.result = result;
}
public Date getTimestamp() {
	return timestamp;
}
public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public Object getResult() {
	return result;
}
public void setResult(Object result) {
	this.result = result;
}

 
}
