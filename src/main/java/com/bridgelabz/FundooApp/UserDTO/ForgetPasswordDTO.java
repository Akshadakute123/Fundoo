package com.bridgelabz.FundooApp.UserDTO;

public class ForgetPasswordDTO 
{
  private String email;

public ForgetPasswordDTO() {
	super();
	// TODO Auto-generated constructor stub
}

public String getEmialid() {
	return email;
}

public void setEmialid(String emialid) {
	this.email = emialid;
}

@Override
public String toString() {
	return "ForgetPasswordDTO [emialid=" + email + "]";
}
  
	
}
