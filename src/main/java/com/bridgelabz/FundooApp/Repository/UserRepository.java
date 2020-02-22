package com.bridgelabz.FundooApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooApp.UserModell.UserInformation;
@Repository
public interface UserRepository extends JpaRepository<UserInformation, Integer> {
 
	//UserInformation findByEmail_id(String email_id);
	
    Optional<UserInformation >findByEmail(String emailid);
   // UserInformation findByPassword(String password);
}
