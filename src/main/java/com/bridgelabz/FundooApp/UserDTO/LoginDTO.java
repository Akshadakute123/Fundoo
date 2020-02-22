package com.bridgelabz.FundooApp.UserDTO;

public class LoginDTO 
{
	String email;
	String password;
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [username=" + email + ", password=" + password + "]";
	}
	
}
