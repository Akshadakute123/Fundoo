package com.bridgelabz.FundooApp.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

	public List<Label> findAll(String newToken) {
		List<Label> labels = new ArrayList<Label>();
		Optional<UserInformation> user = repository.findByEmail(newToken);
		if (user.isPresent())
			labels = labelrepository.findAll();
		return labels.stream().filter(i -> i.getUserinfo().equals(user.get())).collect(Collectors.toList());
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

}
