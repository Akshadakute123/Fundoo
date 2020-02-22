package com.bridgelabz.FundooApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.FundooApp.UserModell.Collaborator;

public interface Collaboratorrepository extends JpaRepository<Collaborator, Integer> 
{
	Optional<Collaborator>findByNewemail(String email);

}
