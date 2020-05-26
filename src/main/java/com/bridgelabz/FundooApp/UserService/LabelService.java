package com.bridgelabz.FundooApp.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooApp.Exceptions.LabelException;
import com.bridgelabz.FundooApp.Exceptions.NoteException;
import com.bridgelabz.FundooApp.Exceptions.UserExceptions;
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

	public String createlabels(Label label, String tokens) throws UserExceptions /* throws UserExceptions */ {
		String labelnames = label.getLabelname();
		System.out.println(labelnames);
		Optional<UserInformation> user = repository.findByEmail(tokens);

		Optional<Label> labelname = labelrepository.findByLabelname(labelnames);
		if (!labelname.isPresent()) {

			label.setUserinfo(user.get());
			label.setCreatetime();
			labelrepository.save(label);

			return "user added";

		} else {
			throw new UserExceptions("label is already present");
		}

	}

	public String deleteLabel(Label label, String decodeToken) throws LabelException, UserExceptions {
		Optional<UserInformation> userId = repository.findByEmail(decodeToken);
		System.out.println(label.getLabelid());
		Optional<Label> label_id = labelrepository.findByLabelid(label.getLabelid());
		if (!label_id.isPresent()) {
			throw new LabelException("label is not present");
		} else if (!userId.isPresent()) {
			throw new UserExceptions("user not found");

		}

		else {
			labelrepository.delete(label);

		}
		return "label deleted succesfully";

	}

	public String updateLabel(Label label, String decodeToken) throws UserExceptions, LabelException {

		Optional<UserInformation> userId = repository.findByEmail(decodeToken);
		// System.out.println("label id is"+label.getLabelid());
		Optional<Label> label_id = labelrepository.findById(label.getLabelid());
		if (!userId.isPresent()) {
			throw new UserExceptions("user not found");
		} else if (!label_id.isPresent()) {
			throw new LabelException("label is not present");
		}

		else {
			label_id.get().setLabelname(label.getLabelname());
			label_id.get().setModifiedtime(LocalDateTime.now());

			labelrepository.save(label_id.get());
		}

		return "updated succesfully";
	}

	

	public String addLabelToNote(String tokens, int noteId, int labelid)
			throws LabelException, NoteException, UserExceptions {

		System.out.println("addd label method");
		Optional<Notes> noteid = noterepository.findByNoteId(noteId);
		// System.out.println(noteid.get());
		Optional<UserInformation> userId = repository.findByEmail(tokens);
		// System.out.println(userId.get());
		Optional<Label> labelid1 = labelrepository.findById(labelid);
		// System.out.println("label id is "+labelid1);
		if (!labelid1.isPresent()) {
			throw new LabelException("label is not present");
		} else if (!noteid.isPresent()) {
			throw new NoteException("note is not present");
		} else if (!userId.isPresent()) {
			throw new UserExceptions("user is not present");
		} else {
			noteid.get().getLabellist().add(labelid1.get());

			noterepository.save(noteid.get());

		}

		return "updateddddd";

	}

	public String addNoteToLabel(String tokennew, int noteId, int labelid)
			throws LabelException, NoteException, UserExceptions {
		Optional<Notes> noteid = noterepository.findByNoteId(noteId);
		Optional<UserInformation> userId = repository.findByEmail(tokennew);
		Optional<Label> labelid2 = labelrepository.findById(labelid);
		System.out.println("labelid is" + labelid);
		if (!labelid2.isPresent()) {
			throw new LabelException("label is not present");

		} else if (!noteid.isPresent()) {
			throw new NoteException("note is not present");

		} else if (!userId.isPresent())

		{
			throw new UserExceptions("user is not present");

		} else {

			labelid2.get().getNotelist().add(noteid.get());
			noterepository.save(noteid.get());
			// labelrepository.save(labelid2.get());

		}

		return "added";
	}

	public List<Label> labelinfo() {

		List<Label> labellist = labelrepository.findAll();

// forEach(noteslist::add) ;

		return labellist;

	}
	public List<Label> findAll(String newToken) {
		List<Label> labels = new ArrayList<Label>();
		Optional<UserInformation> user = repository.findByEmail(newToken);
		if (user.isPresent())
			labels = labelrepository.findAll();
		return labels.stream().filter(i -> i.getUserinfo().equals(user.get())).collect(Collectors.toList());
	}

	public List<Label> displayaddedlabels(String token, int noteId) {
		Optional<UserInformation> user = repository.findByEmail(token);
		System.out.println("token of user"+token);
		System.out.println(noteId);
		if (user.isPresent()) {
			System.out.println("inside user");
			List<Label> labelModelAll = labelrepository.findAll();
			System.out.println("==================="+labelModelAll);
			List<Label> labelModelUserCreate = labelModelAll.stream().filter(i-> i.getUserinfo().getUser_id()==user.get().getUser_id()).collect(Collectors.toList());
			System.out.println("==================="+labelModelUserCreate);

			List<Label>sortlabel=new ArrayList<Label>();
		    for(int j=0;j<labelModelUserCreate.size();j++)
		    { System.out.println("inside j");
		    	for(int k=0;k<labelModelUserCreate.get(j).getNotelist().size();k++)
		    	{
		    		System.out.println("inside k");
		    		if((labelModelUserCreate.get(j).getNotelist().get(k).getNoteId())==noteId)
		    		{
		    			sortlabel.add(labelModelUserCreate.get(j));
		    			System.out.println("label"+labelModelUserCreate.get(j));
		    		}
		    	}
		    }
		    System.out.println("sortlabel"+sortlabel);
		return (sortlabel);

		}

		return null;
	}

	public List<Label> displayunaddedlabels(String token, int noteId)
	{	
			Optional<UserInformation> registrationModel = repository.findByEmail(token);
			Optional<Notes>notesinfo=noterepository.findByNoteId(noteId);
			if (registrationModel.isPresent())
			{
				List<Label> labelModelAll = labelrepository.findAll();
				List<Label> labelModelUserCreate = labelModelAll.stream()
						.filter(i -> (i.getUserinfo().getUser_id() == registrationModel.get().getUser_id())).collect(Collectors.toList());
				List<Label> sortLabel = new ArrayList<Label>();
				sortLabel.addAll(labelModelUserCreate);
				for (int j = 0; j < labelModelUserCreate.size(); j++)
				{
					for (int j2 = 0; j2 < labelModelUserCreate.get(j).getNotelist().size(); j2++) 
					{
						if((labelModelUserCreate.get(j).getNotelist().get(j2).getNoteId()) == noteId) 
						{
//							sortLabel.add(labelModelUserCreate.get(j));
							sortLabel.remove(labelModelUserCreate.get(j));
							System.out.println("label"+labelModelUserCreate.get(j));
							
						}
					}
//					sortLabel.add(labelModelUserCreate.get(j));
				}
				return( sortLabel);
			}
			return null;
			
		}

	
		

	public String removelabelfromnote(String tokennew, int noteId, int labelid) {
		// TODO Auto-generated method stub
		Optional<UserInformation> registrationModel = repository.findByEmail(tokennew);
		if (registrationModel.isPresent()) {
			Optional<Notes> noteModel = noterepository.findByNoteId(noteId);
			Optional<Label> labelModel = labelrepository.findByLabelid(labelid);
			if (noteModel.isPresent() && labelModel.isPresent()) {
				noteModel.get().getLabellist().remove(labelModel.get());
				noterepository.save(noteModel.get());
			
			}
		
		}
		return "note remove";
	
	}
		
	}
	
	


