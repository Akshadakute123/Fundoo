
package com.bridgelabz.FundooApp.UserController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooApp.Repository.UserRepository;
import com.bridgelabz.FundooApp.UserDTO.ForgetPasswordDTO;
import com.bridgelabz.FundooApp.UserDTO.LoginDTO;
import com.bridgelabz.FundooApp.UserDTO.ResetPasswordDto;
import com.bridgelabz.FundooApp.UserModell.UserInformation;
import com.bridgelabz.FundooApp.UserService.Loginservice;
import com.bridgelabz.FundooApp.UserService.UserService;

@RestController
@RequestMapping("/home")
public class Usercontroller {
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private UserService userSevice;
	 @Autowired
	private Loginservice loginservice;
	 
	 
	 @PostMapping("/newRegistration")
	 public String newregistration(@RequestBody UserInformation userInformation) {
		
		
		 return userSevice.userRegistration(userInformation); 
	 }
	 @GetMapping("/get")
	 public List<UserInformation> getinformation()
	 {
		 return userSevice.userinfo();
	 }

 @PostMapping("/login")	 
 public String Logininfo(@RequestBody LoginDTO logindto)	  {
		return userSevice.login(logindto);
  }
 @PostMapping("/forget")
 public String forgetpassword(@RequestBody ForgetPasswordDTO forgetpassword)
 {
	return userSevice.forgetPassword(forgetpassword);
	 
 }
 @PostMapping("/reset")
 public String resetpassword(@RequestBody ResetPasswordDto restepassworddto)
 {
	 return userSevice.resetPasswords(restepassworddto);
 }
}
