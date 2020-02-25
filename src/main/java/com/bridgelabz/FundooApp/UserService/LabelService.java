package com.bridgelabz.FundooApp.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooApp.Repository.LabelRepository;
import com.bridgelabz.FundooApp.Repository.NoteRepository;
import com.bridgelabz.FundooApp.Repository.UserRepository;
import com.bridgelabz.FundooApp.UserModell.Label;
import com.bridgelabz.FundooApp.UserModell.Notes;
import com.bridgelabz.FundooApp.UserModell.UserInformation;

@Service
public class LabelService {
	
	@Autowired
	LabelRepository labelrepository;
	
	@Autowired
	UserRepository repository;
	@Autowired
	NoteRepository noterepository;

	//private UserInformation userInformation;
	
	public String createlabels(Label label, String tokens)
	{
		System.out.println(label);
//		Optional<Label>labelss=labelrepository.findByLabelid(label);
		//System.out.println(labelss.get());
		
		 Optional<UserInformation>user=repository.findByEmail(tokens);
		 
		 if(!user.isPresent())
		 {
			 return "user not found";
		 }
		 else
		 {
		
			 
			 label.setUserinfo(user.get());
			 label.setCreatetime();
			 //label.setModifiedtime(LocalDateTime.now());
			 labelrepository.save(label);
			 
			 return "label created";
		 }
//				 labelss.get().setUserinfo(user.get());
		
	}


	public String deleteLabel(Label label, String decodeToken) {
		Optional<UserInformation> userId = repository.findByEmail(decodeToken);
		System.out.println(label.getLabelid());
		Optional<Label> label_id = labelrepository.findByLabelid(label.getLabelid());					
		if (label_id.isPresent())
				{
			if(userId.isPresent())
			{
				labelrepository.delete(label);
				
			}
				} 
		else
		{
			return "user not found";
		}
		return "label deleted succesfully";

	}


	public String updateLabel(Label label, String decodeToken) {
		
			Optional<UserInformation> userId = repository.findByEmail(decodeToken);
			//System.out.println("label id is"+label.getLabelid());
			Optional<Label> label_id = labelrepository.findById(label.getLabelid());					
			if (userId.isPresent()) 
			{
				if (label_id.isPresent())
				{
					System.out.println("hiiiiiii");
					label_id.get().setLabelname(label.getLabelname());
					//label.getModifiedtime();
					label_id.get().setModifiedtime(LocalDateTime.now());
					
					//System.out.println(label.getModifiedtime());
					labelrepository.save(label_id.get());
				}
			}
			return "updated succesfully";
		}
	
	


	public List<Label> findAll(String newToken) {
		List<Label> labels = new ArrayList<Label>();
		Optional<UserInformation> user = repository.findByEmail(newToken);
		if (user.isPresent())
			labels = labelrepository.findAll();
		return labels;
	}


	public String addLabelToNote(String tokens, int noteId, int labelid) {

		System.out.println("addd label method");
		Optional<Notes>noteid=noterepository.findByNoteId(noteId);
		System.out.println(noteid.get());
		Optional<UserInformation> userId = repository.findByEmail(tokens);
		System.out.println(userId.get());
		Optional<Label> labelid1= labelrepository.findById(labelid);
		System.out.println("label id is "+labelid1);
		if (labelid1.isPresent()) {
			
			if (noteid.isPresent()) {
				if (userId.isPresent()) {
					noteid.get().getLabellist().add(labelid1.get());
					
					noterepository.save(noteid.get());
					
				}
			}
		} else {
			return "not found ";
		}
		return "updateddddd";

	}
	


	public String addNoteToLabel(String tokennew, int noteId, int labelid) {
		Optional<Notes>noteid=noterepository.findByNoteId(noteId);
		Optional<UserInformation> userId = repository.findByEmail(tokennew);
		Optional<Label> labelid2 = labelrepository.findById(labelid);
		System.out.println("labelid is"+labelid);
		if (labelid2.isPresent()) {
			if (noteid.isPresent()) {
				if (userId.isPresent()) {
					labelid2.get().getNotelist().add(noteid.get());
					labelrepository.save(labelid2.get());

					

					
									}
			}
		} else {
			return "ERROR";
		}
		
		
		return "added";
	}


	public List<Label> labelinfo() {

		List<Label> labellist = labelrepository.findAll();

// forEach(noteslist::add) ;

		return labellist;
		
	}

}
