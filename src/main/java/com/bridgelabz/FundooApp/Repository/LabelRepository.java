package com.bridgelabz.FundooApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooApp.UserModell.Label;


@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
	 Optional<Label>findByLabelid(int labelid);

}
