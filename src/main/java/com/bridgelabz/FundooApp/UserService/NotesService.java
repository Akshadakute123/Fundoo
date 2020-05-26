package com.bridgelabz.FundooApp.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooApp.Exceptions.NoteException;
import com.bridgelabz.FundooApp.Exceptions.Response;
import com.bridgelabz.FundooApp.Exceptions.UserExceptions;
import com.bridgelabz.FundooApp.Repository.Collaboratorrepository;
import com.bridgelabz.FundooApp.Repository.NoteRepository;
import com.bridgelabz.FundooApp.Repository.UserRepository;
import com.bridgelabz.FundooApp.UserModell.Collaborator;
import com.bridgelabz.FundooApp.UserModell.Label;
import com.bridgelabz.FundooApp.UserModell.Notes;
import com.bridgelabz.FundooApp.UserModell.UserInformation;

@Service
public class NotesService {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private NoteRepository noterepository;
	@Autowired
	private Collaboratorrepository collaboratorrepo;

	public String createNewNote(Notes notes, String decodetoken) throws NoteException {

		if (notes.getTittle().isEmpty()) {
			throw new NoteException("tittle should not be empty");

		} else if (notes.getDescription().isEmpty()) {
			throw new NoteException("description should not be empty");

		} else {
			System.out.println("in create note");
			Optional<UserInformation> user = userrepository.findByEmail(decodetoken);
			System.out.println(user.get());
			notes.setUserid(user.get());
			notes.setAtcreate();
			noterepository.save(notes);
		}

		return "note created";
	}

	public List<Notes> noteinfo() {

		List<Notes> noteslist = noterepository.findAll();

		return noteslist;
	}

	public List<Notes> findAll(String newToken) {
		List<Notes> allNotes = new ArrayList<Notes>();
		Optional<UserInformation> user = userrepository.findByEmail(newToken);
		List<Notes> noteList = noterepository.findAll();
		allNotes = noteList.stream().filter(i -> i.getUserid().equals(user.get())).collect(Collectors.toList());
		// List<Notes> checkPinUnpin = allNotes.stream().filter(i->i.isPin() == false).collect(Collectors.toList());
		List<Notes> checkTrash = allNotes.stream().filter(i -> i.isIstrash() == false).collect(Collectors.toList());
		List<Notes> checkArchived = checkTrash.stream().filter(i -> i.isIsarchieve() == false)
		.collect(Collectors.toList());
		return checkArchived;


	}

	public String deleteNotess(Notes note1, String tok) throws NoteException, UserExceptions {

		Optional<UserInformation> user1 = userrepository.findByEmail(tok);

		Optional<Notes> noteid = noterepository.findByNoteId(note1.getNoteId());

		if ((noteid.isPresent()) && (user1.isPresent())) {

			noterepository.delete(note1);
			// noterepository.save(noteid.get());

		} else if (!noteid.isPresent()) {
			throw new NoteException("note is not present");
		} else {
			throw new UserExceptions("user not present");
		}
		return "succesfull";

	}

	public String updateNotes(Notes notes, String decode) throws NoteException, UserExceptions {
		Optional<UserInformation> user = userrepository.findByEmail(decode);
		Optional<Notes> note = noterepository.findById(notes.getNoteId());
		// Optional<Notes>note=noterepository.findByNoteId(notes.getNoteId());
		if ((user.isPresent()) && (note.isPresent())) {

			note.get().setTittle(notes.getTittle());
			note.get().setDescription(notes.getDescription());
			note.get().setAtModified();
			noterepository.save(note.get());
		} else if (!user.isPresent()) {
			throw new UserExceptions("user is not present");

		} else {
			throw new NoteException("note is not present");
		}

		return "updated succesfully";
	}

	public String pin(int noteId, String tokens) throws NoteException, UserExceptions {
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		// System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		// System.out.println(tokens);
		if (!notes.isPresent()) {
			throw new NoteException("note is not present");
		} else if (!user.isPresent()) {
			throw new UserExceptions("user is not present");
		} else {
			if (notes.get().isIspin()) {

				System.out.println("jgreguewuwu" + notes.get().isIspin());
				notes.get().setIspin(false);
				noterepository.save(notes.get());

			} else {
				System.out.println("jgreguewuwu" + notes.get().isIspin());
				notes.get().setIspin(true);
				noterepository.save(notes.get());
				return "unpinned";
			}

		}
		return "done";

	}

	public String trash(Notes noteId, String tokens) throws NoteException, UserExceptions {
		Optional<Notes> notes = noterepository.findByNoteId(noteId.getNoteId());
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (!notes.isPresent()) {
			throw new NoteException("note is not present");
		}
		if (!user.isPresent()) {
			throw new UserExceptions("user is not present");
		}

		else {
			if (notes.get().isIspin()) {

				System.out.println("jgreguewuwu" + notes.get().isIspin());
				notes.get().setIspin(false);
				// noterepository.save(notes.get());

			}
			System.out.println("hhhggggg");
			notes.get().setIstrash(true);
			noterepository.save(notes.get());

		}
		return "trashed";
	}

	public String untrash(int noteId, String tokens) throws NoteException, UserExceptions {
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (!notes.isPresent()) {

			throw new NoteException("note is not present");
		}
		if (!user.isPresent()) {
			throw new UserExceptions("user is not present");
		} else {
			if (notes.get().isIstrash()) {
				notes.get().setIstrash(false);
				noterepository.save(notes.get());
			}
		}
		return "untrashed";
	}

	public String archieve(Notes noteId, String tokens) throws UserExceptions, NoteException {
		Optional<Notes> notes = noterepository.findByNoteId(noteId.getNoteId());
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (!notes.isPresent()) {
			throw new NoteException("note is not present");
		}
		if (!user.isPresent()) {
			throw new UserExceptions("user is not present");
		} else {
			if (notes.get().isIspin()) {
				notes.get().setIspin(false);

			}
			notes.get().setIsarchieve(true);
			noterepository.save(notes.get());

		}
		return "archieve done";
	}

	public String unarchieve(int noteId, String tokens) throws UserExceptions, NoteException {

		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (!notes.isPresent()) {
			throw new NoteException("note is not present");
		}
		if (!user.isPresent()) {
			throw new UserExceptions("user is not present");
		} else {
			if (notes.get().isIsarchieve()) {
				notes.get().setIsarchieve(false);
				noterepository.save(notes.get());
			}

		}
		return "uarchieve done";

	}

	public String trashdelete(int noteId, String tokens) throws UserExceptions, NoteException {
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		Optional<Notes> note = noterepository.findByNoteId(noteId);
		if (!note.isPresent()) {
			throw new NoteException("note is not present");
		}
		if (!user.isPresent()) {
			throw new UserExceptions("user is not present");
		} else {
			if (note.get().isIstrash() == true) {
				noterepository.delete(note.get());
			}
		}
		return "trash deleted succesfully";
	}

	public List<Notes> sortedlist(String tokens) {

		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		List<Notes> noteslist = new ArrayList<Notes>();
		if (user.isPresent()) {
			List<Notes> notelist1 = findAll(tokens);
			noteslist = notelist1.stream().sorted(Comparator.comparing(Notes::getTittle)).collect(Collectors.toList());
		}

		return noteslist;
	}

	public List<Notes> getnoteslist(String token) {
		// System.out.println(token);

		Optional<UserInformation> user = userrepository.findByEmail(token);
		List<Notes> noteslist = noterepository.findByUserid(user.get());
		List<Notes> notelists = noteslist.stream().filter(i -> i.isIstrash()).collect(Collectors.toList());
		return notelists;
	}
	public List<Notes> getarchievelist(String tokens) {
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		List<Notes> noteslist = noterepository.findByUserid(user.get());
		List<Notes> notelists = noteslist.stream().filter(i -> i.isIsarchieve()).collect(Collectors.toList());
		return notelists;
		}

	public List<Notes> searchBytitileDesc(String title, String token) {
		Optional<UserInformation> user1 = userrepository.findByEmail(token);
		List<Notes> noteslist = noterepository.findByUserid(user1.get());

		List<Notes> listnotes = noteslist.stream().filter(i -> i.getUserid().getemail().equals(token))
				.collect(Collectors.toList());
		List<Notes> notesearch = listnotes.stream().filter(i -> i.getTittle().equals(title))
				.collect(Collectors.toList());

		return notesearch;
	}

	public String collaborate(String tokens, String emailid, int noteId) throws NoteException, UserExceptions {

		Optional<UserInformation> user1 = userrepository.findByEmail(tokens);
		Optional<Notes> notes = noterepository.findByNoteId(noteId);

		Collaborator colaborate = new Collaborator();
		Optional<Collaborator> newid = collaboratorrepo.findByNewemail(emailid);
		if (!user1.isPresent()) {
			throw new UserExceptions("user is not present");
		} else if (!notes.isPresent()) {
			throw new NoteException("note is not present");
		}
		if (!newid.isPresent()) {
			colaborate.setNewemail(emailid);
			colaborate.getNoteslist().add(notes.get());

			collaboratorrepo.save(colaborate);
			return "new user added succesfully";

		}

		if (newid.isPresent()) {
			for (int i = 0; i < newid.get().getNoteslist().size(); i++) {
				if (newid.get().getNoteslist().get(i).getNoteId() == noteId) {
					return "already collbrated";
				}

			}

			newid.get().getNoteslist().add(notes.get());
			collaboratorrepo.save(newid.get());
		}

		return "collaborate";
	}

	public String addreminder(String tokens, String remindertime, int noteId) throws UserExceptions, NoteException {

		System.out.println(remindertime);
		Optional<UserInformation> decodetoken = userrepository.findByEmail(tokens);
		Optional<Notes> noteid = noterepository.findById(noteId);
 
//		DateTimeFormatter datetimeformater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime reminderTime = LocalDateTime.parse(remindertime, datetimeformater);
		if (!decodetoken.isPresent()) {
			throw new UserExceptions("user not present");
		}
		if (!noteid.isPresent()) {
			throw new NoteException("note is not present");
		} else {
			System.out.println("in ifff");
			noteid.get().setRemindercheck(true);
			noteid.get().setRemindertime(remindertime);
			System.out.println("hikiihhxhhhhhhhhhhhhhhhhhhhhhhhhnbnhh");
			noterepository.save(noteid.get());
			return "reminder added succesfully";
		}
	}

	public String removereminder(String tokens, int noteId) throws UserExceptions, NoteException {
		Optional<UserInformation> decodetoken = userrepository.findByEmail(tokens);
		Optional<Notes> noteid = noterepository.findById(noteId);

		if (!decodetoken.isPresent()) {
			throw new UserExceptions("user not present");
		}
		if (!noteid.isPresent()) {
			throw new NoteException("note is not present");
		}

		else {
			System.out.println("in ifff");
			noteid.get().setRemindercheck(false);
			noteid.get().setRemindertime(null);
			noterepository.save(noteid.get());
			return "reminder deleted succesfully";
		}

	}

	public List<Collaborator> getcollablist() {
		List<Collaborator> listcollab = collaboratorrepo.findAll();
		return listcollab;
	}

	public List<Collaborator> findAllcollab(String newToken, int noteId) {
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		List<Collaborator> colablist = notes.get().getCollaboration();

		return colablist;
	}

	public String removeCollaborate(String decodeToken, String email, int noteId) {
		Optional<UserInformation> user = userrepository.findByEmail(decodeToken);
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		Optional<Collaborator> collab = collaboratorrepo.findByNewemail(email);
		if ((collab.isPresent()) && (notes.isPresent())) {
			collab.get().getNoteslist().remove(collab.get());

			collaboratorrepo.delete(collab.get());

		}

		return "collaborator removed";
	}
	private boolean checkUserExit(String token) {
		System.out.println(token);

		Optional<UserInformation> user =userrepository.findByEmail(token);
		if (user.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public String getSingleNote(int id, String token) {
		Optional<UserInformation> registrationModel = userrepository.findByEmail(token);
		
		if (checkUserExit(token)) {
			Optional<Notes> singleNote = noterepository.findByNoteId(id);
			if (singleNote.isPresent()) {
				System.out.println("sigle note " + singleNote.get());
				return "saveNote";
			}
			return "note not present";
		}
		return "envalid token";
	}

	public List<Notes> getreminderlist(String tokens) {
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		List<Notes> noteslist = noterepository.findByUserid(user.get());
		List<Notes> notelists = noteslist.stream().filter(i -> i.isRemindercheck()).collect(Collectors.toList());
		return notelists;
		}

	public List<Collaborator> getAllCollaborator(String decodeString, int noteId) {
		// TODO Auto-generated method stub
		Optional<Notes> list = noterepository.findByNoteId(noteId);
		List<Collaborator> collablist = list.get().getCollaboration();
		return collablist;
		
	}

	public List<Notes> getListOfNoteBySearchField(String decodeString, String typeText) {
		Optional<UserInformation> user = userrepository.findByEmail(decodeString);
		if (user.isPresent()) {
			List<Notes> allNoteList = noterepository.findAll();
			List<Notes> loginUserCreateNoteList = allNoteList.stream().filter(t -> (t.getUserid().getUser_id()) == user.get().getUser_id())
					.collect(Collectors.toList());
			List<Notes> searchNoteList = loginUserCreateNoteList.parallelStream()
					.filter(i -> (!i.isIstrash()) && (i.getTittle().indexOf(typeText) >= 0)).collect(Collectors.toList());
			return (searchNoteList);
		}
	
		return null;
	}

	public String Backgroundcolor(Notes noteModel, String decodeString) {
		Optional<UserInformation> registrationModel = userrepository.findByEmail(decodeString);
		long noteId =noteModel.getNoteId();
		if (registrationModel.isPresent()) {
			Optional<Notes> note = noterepository.findById((int) noteId);
			note.get().setBackgroundColor(noteModel.getBackgroundColor());
			noterepository.save(note.get());
			return  "colorChangeSuccessFully";
		}
		return "not valid token";
	}

	

}
