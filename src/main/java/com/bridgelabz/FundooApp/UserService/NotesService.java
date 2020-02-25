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
// public String createNewNote(Notes note, String decodetoken) 
//{
//	Optional<UserInformation>user=userrepository.findByEmail(decodetoken);
//	System.out.println(user);
//	
//	return "strinhghjn";
//}

	public String createNewNote(Notes notes, String decodetoken) {

		if ((notes.getTittle().isEmpty()) && (notes.getDescription().isEmpty())) {

			return "no created";

		} else {
			System.out.println("in create note");
			Optional<UserInformation> user = userrepository.findByEmail(decodetoken);
			// notes.setUserinfo(user.get());
			System.out.println(user.get());
			notes.setUserid(user.get());
			notes.setAtcreate();
			noterepository.save(notes);
			// return "creation failed";
		}

		return "note created";
	}

	public List<Notes> noteinfo() {

		List<Notes> noteslist = noterepository.findAll();

// forEach(noteslist::add) ;

		return noteslist;
	}

	public List<Notes> findAll(String newToken) {
		List<Notes> notlist = new ArrayList<Notes>();
		System.out.println(notlist);
		Optional<UserInformation> user = userrepository.findByEmail(newToken);
		if (user.isPresent())
			notlist = noterepository.findAll();
		return notlist;
	}

	public String deleteNotess(Notes note1, String tok) {
		System.out.println("token from controllerclass " + tok);
		System.out.println("hello");
		Optional<UserInformation> user1 = userrepository.findByEmail(tok);
		System.out.println(note1.getNoteId());
		// System.out.println("user"+user1.get());
//	Optional<Notes>noteid=noterepository.findByNoteId(id);
		Optional<Notes> noteid = noterepository.findByNoteId(note1.getNoteId());
//			if(user1.isPresent() )
//			{
//				noterepository.deleteById(id);
//				return "note deleted";
//			}
//			return "not deleted";
//			
//		
//	System.out.println(noteid.get());

		if (noteid.isPresent()) {
			if ((user1.isPresent())) {
				noterepository.delete(note1);
				// noterepository.save(noteid.get());

			}
		}

		else {
			return "note not found";

		}

//return "helloo";
		return "deleted succesfully";
	}

	public String updateNotes(Notes notes, String decode) {
		Optional<UserInformation> user = userrepository.findByEmail(decode);
		Optional<Notes> note = noterepository.findById(notes.getNoteId());
		// Optional<Notes>note=noterepository.findByNoteId(notes.getNoteId());
		if (user.isPresent()) {
			if (note.isPresent()) {
				note.get().setTittle(notes.getTittle());
				note.get().setDescription(notes.getDescription());
				note.get().setAtModified();
				noterepository.save(note.get());
			}
			//notes.get().getuser();
			//System.out.println(ow.);
		}

		return "updated succesfully";
	}

	public String pin(int noteId, String tokens) {
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (notes.isPresent() && user.isPresent()) {
			System.out.println("jgreguewuwu" + notes.get().isIspin());
			System.out.println("hello");
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
		return "pinned";
	}

	public String trash(int noteId, String tokens) {
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (!notes.isPresent()) {
			return "nots not present";
		}
		if (!user.isPresent()) {
			return "user not present";
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

	public String untrash(int noteId, String tokens) {
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (!notes.isPresent()) {
			return "nots not present";
		}
		if (!user.isPresent()) {
			return "user not present";
		} else {
			if (notes.get().isIstrash()) {
				notes.get().setIstrash(false);
				noterepository.save(notes.get());
			}
		}
		return "untrashed";
	}

	public String archieve(int noteId, String tokens) {
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (!notes.isPresent()) {
			return "nots not present";
		}
		if (!user.isPresent()) {
			return "user not present";
		} else {
			if (notes.get().isIspin()) {
				notes.get().setIspin(false);

			}
			notes.get().setIsarchieve(true);
			noterepository.save(notes.get());

		}
		return "archieve done";
	}

	public String unarchieve(int noteId, String tokens) {

		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		System.out.println(noteId);
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		System.out.println(tokens);
		if (!notes.isPresent()) {
			return "nots not present";
		}
		if (!user.isPresent()) {
			return "user not present";
		} else {
			if (notes.get().isIsarchieve()) {
				notes.get().setIsarchieve(false);
				noterepository.save(notes.get());
			}

		}
		return "uarchieve done";

	}

	public String trashdelete(int noteId, String tokens) {
		Optional<UserInformation> user = userrepository.findByEmail(tokens);
		Optional<Notes> note = noterepository.findByNoteId(noteId);
		if (!note.isPresent()) {
			return "nots not present";
		}
		if (!user.isPresent()) {
			return "user not present";
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
		// int id=user.get().getUser_id();
		if (user.isPresent()) {
			List<Notes> notelist1 = findAll(tokens);
			noteslist = notelist1.stream().sorted(Comparator.comparing(Notes::getTittle)).collect(Collectors.toList());
		}
 
		return noteslist;
	}

	public List<Notes> getnoteslist(String token) {
		// System.out.println(token);

		Optional<UserInformation> user = userrepository.findByEmail(token);
		// int id=user.get().getUser_id();
		// System.out.println("id is"+id);
//	    List<Notes>noteslist=new ArrayList<Notes>();
//	    System.out.println(noteslist);
		List<Notes> noteslist = noterepository.findByUserid(user.get());
		// System.out.println("notelistis"+noteslist);
		List<Notes> notelists = noteslist.stream().filter(i -> i.isIstrash()).collect(Collectors.toList());
		return notelists;
	}

	public List<Notes> searchBytitileDesc(String title,String token) 
	{
		Optional<UserInformation> user1 = userrepository.findByEmail(token);
		//int id=user1.get().getUser_id();
		List<Notes> noteslist = noterepository.findByUserid(user1.get());
		//System.out.println(noteslist.toString());
	
		
//		Optional<Notes>notes1 = noterepository.findByUserid(user1.get());
	List<Notes>listnotes=noteslist.stream().filter(i ->i.getUserid().getemail().equals(token)).collect(Collectors.toList());
	List<Notes>notesearch=listnotes.stream().filter(i->i.getTittle().equals(title)).collect(Collectors.toList());
			
		return notesearch;
	}

	public String collaborate(String tokens, String emailid, int noteId)
	{
		
		Optional<UserInformation>user1=userrepository.findByEmail(tokens);
	//	Optional<UserInformation>email=userrepository.findByEmail(emailid);
		Optional<Notes>notes=noterepository.findByNoteId(noteId);

		Collaborator colaborate=new Collaborator();
		Optional<Collaborator>newid=collaboratorrepo.findByNewemail(emailid);
		if(!user1.isPresent())
		{
			return "user is not present";
		}
		else if(!notes.isPresent())
		{
			return "notes not present";  
		}
		if (!newid.isPresent()) {
			colaborate.setNewemail(emailid);
			colaborate.getNoteslist().add(notes.get());
			
			  collaboratorrepo.save(colaborate);
			//  collaboratorrepo.save(newid.get());
			return "new user added succesfully";
			// newid.get().getNoteslist().add(notes.get());
			 
			 
		}
		
	 
		//System.out.println(notes.get());
		/*if((user1.isPresent())&&(notes.isPresent()))
		{*/
			System.out.println("hhewiieijejhj");
			if(newid.isPresent())
			{
				for (int i = 0; i < newid.get().getNoteslist().size(); i++) {
					if(newid.get().getNoteslist().get(i).getNoteId()==noteId)
					{
						return "already collbrated";
					}
					
				}
				System.out.println("hhdshjashjdhxgg");
				//System.out.println(newid);
				newid.get().getNoteslist().add(notes.get());
				collaboratorrepo.save(newid.get());
			}
						
					//user1.get().getNotelistss().add(notes.get());
					//userrepository.save(user1.get());
			
			   
			
		
		
		return "collaborate";
	}
	

	public String addreminder(String tokens, String remindertime, int noteId)
	{
		System.out.println("hiiiiiiiiiiiiiiiiiiii");
		System.out.println(remindertime);
		Optional<UserInformation>decodetoken=userrepository.findByEmail(tokens);
		Optional<Notes>noteid=noterepository.findById(noteId);
	    
	    DateTimeFormatter datetimeformater=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime reminderTime=LocalDateTime.parse(remindertime,datetimeformater);
		if(decodetoken.isPresent())
		{
			System.out.println("in ifff");
			noteid.get().setRemindercheck(true);
		    noteid.get().setRemindertime(reminderTime);
		    System.out.println("tttttttttttttttttttttttttttttttttttttttttt");
			noterepository.save(noteid.get());
			return "reminder added succesfully";
		}
		else
		{
			return "user not exist";

		}
	
		
	}

	public String removereminder(String tokens,  int noteId) {
	System.out.println("hiiiiiiiiiiiiiiiiiiii");
			//System.out.println(remindertime);
			Optional<UserInformation>decodetoken=userrepository.findByEmail(tokens);
			Optional<Notes>noteid=noterepository.findById(noteId);
		    
		   // DateTimeFormatter datetimeformater=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//    LocalDateTime reminderTime=LocalDateTime.parse(remindertime,datetimeformater);
			if(decodetoken.isPresent())
			{
				System.out.println("in ifff");
				noteid.get().setRemindercheck(false);
			    noteid.get().setRemindertime(null);
			    System.out.println("tttttttttttttttttttttttttttttttttttttttttt");
				noterepository.save(noteid.get());
				return "reminder deleted succesfully";
			}
			else
			{
				return "user not exist";

			}
		
			
		}

	

	public List<Collaborator> getcollablist() {
		List<Collaborator>listcollab=collaboratorrepo.findAll();
		return listcollab;
	}
	
	
	

	public List<Collaborator> findAllcollab(String newToken) {
		List<Collaborator> colablist = new ArrayList<Collaborator>();
		System.out.println(colablist);
		Optional<UserInformation> user = userrepository.findByEmail(newToken);
		if (user.isPresent())
			colablist = collaboratorrepo.findAll();
		return colablist;
	}

	public String removeCollaborate(String decodeToken, String email, int noteId) {
		Optional<UserInformation> user = userrepository.findByEmail(decodeToken);
		Optional<Notes> notes = noterepository.findByNoteId(noteId);
		Optional<Collaborator>collab = collaboratorrepo.findByNewemail(email);
		if((collab.isPresent())&&(notes.isPresent()))
		{
			collab.get().getNoteslist().remove(collab.get());
		//	System.out.println(collab.get().getNewemail());
			
		collaboratorrepo.delete(collab.get());
			
		//	collaboratorrepo.save(collab.get());
		}
		
		return "collaborator removed";
	}

}

