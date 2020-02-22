package com.bridgelabz.FundooApp.UserModell;

public class Loginform {
	String email;
	String password;
	public Loginform() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return email;
	}
	public void setUsername(String email) {
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
		return "Loginform [username=" + email + ", password=" + password + "]";
	}
	

}
