package com.bridgelabz.FundooApp.UserDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class LoginDTO 
{
	@NotEmpty(message = "username shouls not empty")
	@Email(message = "please enter valid email adress")
	String email;
	@Pattern(regexp = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})",message = "password is wrong")

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
