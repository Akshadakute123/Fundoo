package com.bridgelabz.FundooApp.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooApp.Repository.UserRepository;
import com.bridgelabz.FundooApp.UserDTO.ForgetPasswordDTO;
import com.bridgelabz.FundooApp.UserDTO.LoginDTO;
import com.bridgelabz.FundooApp.UserDTO.ResetPasswordDto;
import com.bridgelabz.FundooApp.UserModell.UserInformation;
import com.bridgelabz.FundooApp.Utility.JMS;
import com.bridgelabz.FundooApp.Utility.TokenService;

@Service
public class UserService {
	


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenservice;
	@Autowired
	private JMS jms;
	
	public String userRegistration(UserInformation userInformation) {
		String emailnew=userInformation.getemail();
		System.out.println(emailnew);
		Optional<UserInformation> neuser1 =userRepository.findByEmail(emailnew);
		System.out.println(emailnew);
		if(!neuser1.isPresent())
		{
		   System.out.println("heuuuu");	
			userRepository.save(userInformation);	
			return "new user added";
			
		}
		return "user already present";
	
		
	}

	public List<UserInformation> userinfo() 
	{
		
    List<UserInformation> userlist=new ArrayList<UserInformation>();
     userRepository.findAll().forEach(userlist::add) ;
     
		

		return userlist;
	}
	
	public String login(LoginDTO logindto)
	{
		String email=logindto.getemail();
		
		Optional<UserInformation> newlogin=userRepository.findByEmail(email);
		System.out.println(newlogin);
		//	    String value=newlogin.get().getPassword();
//	    newlogin.get().getemail();
	  //  System.out.println(newlogin);
		if(!newlogin.isPresent())
          {
			return "user is invalid";
          }
		if(!newlogin.get().getPassword().equals(logindto.getPassword()))
		{
			return "password mismatch";
		}
		String usertoken=tokenservice.createToken(newlogin.get().getemail());
		System.out.println(usertoken);
	//	jms.sendMail(logindto.getemail(), usertoken);
		return usertoken;
	}

	public String forgetPassword(ForgetPasswordDTO forgetpassword)
	{
		Optional<UserInformation>userexist=userRepository.findByEmail(forgetpassword.getEmialid());
		System.out.println(userexist);
		if(userexist.isPresent())
		{
			jms.sendMail(forgetpassword.getEmialid(),"http://localhost:8080/home/reset");
		}
		return "click on the link";
	}

	public String resetPasswords(ResetPasswordDto restepassworddto) 
	{
		Optional<UserInformation>userexist=userRepository.findByEmail(restepassworddto.getEmail());
		System.out.println(userexist);
		if(userexist.isPresent())
		{
			if(restepassworddto.getPassword().equals(restepassworddto.getConfirm_password())
					) 
			{
				System.out.println("hiiii");
				userexist.get().setPassword(restepassworddto.getPassword());
				userexist.get().setConfirm_password(restepassworddto.getConfirm_password());
				userRepository.save(userexist.get());
				jms.sendMail(restepassworddto.getEmail(), "password updated succesfully");
			}
			else
			{
				return "username is not exist";
			}
		}
		return "password updated";
	}
	}


