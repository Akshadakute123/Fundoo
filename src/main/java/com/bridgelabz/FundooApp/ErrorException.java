package com.bridgelabz.FundooApp;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="error")
public class ErrorException {
	
private String msg;
private List<String>details;
public ErrorException(String msg, List<String> details) {
	super();
	this.msg = msg;
	this.details = details;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public List<String> getDetails() {
	return details;
}
public void setDetails(List<String> details) {
	this.details = details;
}

}
