package com.bridgelabz.FundooApp.UserDTO;

public class ResetPasswordDto 
{ 
	
	private String email;
	 private String password;
	 private String confirm_password;
	public ResetPasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	@Override
	public String toString() {
		return "ResetPasswordDto [email=" + email + ", password=" + password + ", confirm_password=" + confirm_password
				+ "]";
	}
	 
	 

}
