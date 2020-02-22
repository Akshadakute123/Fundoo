package com.bridgelabz.FundooApp.Repository;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooApp.UserModell.Notes;
import com.bridgelabz.FundooApp.UserModell.UserInformation;
@Repository
public interface NoteRepository extends JpaRepository<Notes, Integer>
{
 Optional<Notes>findByNoteId(int noteId);
// Optional<Notes>findByUserid(UserInformation user_id);
 List<Notes> findByUserid(UserInformation user_id);
   
}
