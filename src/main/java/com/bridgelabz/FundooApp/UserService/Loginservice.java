package com.bridgelabz.FundooApp.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooApp.Repository.UserRepository;
import com.bridgelabz.FundooApp.UserDTO.LoginDTO;
import com.bridgelabz.FundooApp.UserModell.UserInformation;

@Service
public class Loginservice 
{
 
	@Autowired
	UserRepository loginrepo;
	
	public String login(LoginDTO logindto)
	{
		String email=logindto.getemail();
		
		Optional<UserInformation> newlogin=loginrepo.findByEmail(email);
		System.out.println(newlogin);
		//	    String value=newlogin.get().getPassword();
//	    newlogin.get().getemail();
	  //  System.out.println(newlogin);
		if(logindto.getemail().equals(newlogin))
          {
			return "user is valid";
          }
		if(!newlogin.get().getPassword().equals(logindto.getPassword()))
		{
			return "password is wrong";
		}
		return "login succesfull";
	}
	

//	public List<UserInformation> userinfo() 
//	{
//		
//    List<UserInformation> userlist=new ArrayList<UserInformation>();
//     userRepository.findAll().forEach(userlist::add) ;
//     
//		
//
//		return userlist;
//	}
}
