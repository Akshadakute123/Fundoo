package com.bridgelabz.FundooApp.UserController;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooApp.Exceptions.NoteException;
import com.bridgelabz.FundooApp.Exceptions.Response;
import com.bridgelabz.FundooApp.Exceptions.UserExceptions;
import com.bridgelabz.FundooApp.UserModell.Collaborator;
import com.bridgelabz.FundooApp.UserModell.Notes;
import com.bridgelabz.FundooApp.UserService.NotesService;
import com.bridgelabz.FundooApp.Utility.TokenService;

@RestController
@RequestMapping("/note")
public class NoteController {
	@Autowired
	private NotesService noteservice;
	@Autowired
	private TokenService tokenservice;

	@PostMapping("/create")
	public Response createNote(@Valid @RequestBody Notes note, @RequestHeader String token) throws NoteException {
		String decodetoken = tokenservice.getUserIdFromToken(token);
		System.out.println("decoded token" + decodetoken);
		noteservice.createNewNote(note, decodetoken);
		return new Response(new Date(), "note created succesfully ", 200, "OK");

	}

	@GetMapping("/get")
	public List<Notes> getinformation() {
		return noteservice.noteinfo();
	}

	@DeleteMapping("/delete")
	public Response deleteNote(@RequestBody Notes note1, @RequestHeader String token) throws NoteException, UserExceptions {
		String tok = tokenservice.getUserIdFromToken(token);
		System.out.println("hiiii " + tok);
		 noteservice.deleteNotess(note1, tok);
			return new Response(new Date(), "note deleted succesfully ", 200, "OK");

	}

	@PutMapping("/update")
	public Response updateNote(@Valid @RequestBody Notes notes, @RequestHeader String token) throws NoteException, UserExceptions {
		String decode = tokenservice.getUserIdFromToken(token);
		System.out.println("token is" + decode);
		noteservice.updateNotes(notes, decode);
		return new Response(new Date(), "note updated succesfully", 200, "OK");

	
	}

	@PostMapping("/pin")
	public Response ispin(@RequestHeader int noteId, @RequestHeader String token) throws NoteException, UserExceptions {
		String tokens = tokenservice.getUserIdFromToken(token);
		noteservice.pin(noteId, tokens);
		return new Response(new Date(), "note pinned", 200, "OK");

	}

	@PostMapping("/untrash")
	public Response untrash(@RequestHeader int noteId, @RequestHeader String token) throws NoteException, UserExceptions {
		String tokens = tokenservice.getUserIdFromToken(token);
		 noteservice.untrash(noteId, tokens);
			return new Response(new Date(), "Untrashed", 200, "OK");

	}

	@PostMapping("/trash")
	public Response trash(@RequestHeader int noteId, @RequestHeader String token) throws NoteException, UserExceptions {
		String tokens = tokenservice.getUserIdFromToken(token);
		 noteservice.trash(noteId, tokens);
			return new Response(new Date(), "note trashed", 200, "OK");

	}

	@PostMapping("/archieve")
	public Response archieve(@RequestHeader int noteId, @RequestHeader String token) throws UserExceptions, NoteException {
		String tokens = tokenservice.getUserIdFromToken(token);
		 noteservice.archieve(noteId, tokens);
			return new Response(new Date(), "note archieve", 200, "OK");

	}

	@PostMapping("/unarchieve")
	public Response unarchieve(@RequestHeader int noteId, @RequestHeader String token) throws UserExceptions, NoteException {
		String tokens = tokenservice.getUserIdFromToken(token);
		 noteservice.unarchieve(noteId, tokens);
			return new Response(new Date(), "note unarchieve", 200, "OK");

	}

	@DeleteMapping("/deletetrash")
	public Response delete(@RequestHeader int noteId, @RequestHeader String token) throws UserExceptions, NoteException {

		String tokens = tokenservice.getUserIdFromToken(token);
		 noteservice.trashdelete(noteId, tokens);
			return new Response(new Date(), "trash deleted succesfully", 200, "OK");


	}

	@GetMapping("/getnote")
	public List<Notes> findnotes(@RequestHeader String token) {
		String tokens = tokenservice.getUserIdFromToken(token);
		return noteservice.findAll(tokens);
	}

	@GetMapping("/sortlist")
	public List<Notes> sortelist(@RequestHeader String token) {

		String tokens = tokenservice.getUserIdFromToken(token);
		return noteservice.sortedlist(tokens);
	}

	@GetMapping("/trashlist")
	public List<Notes> trashfile(@RequestHeader String token) {
		String tokens = tokenservice.getUserIdFromToken(token);
		return noteservice.getnoteslist(tokens);
	}

	@PostMapping("/search")
	public List<Notes> search(@RequestHeader String tittle, @RequestHeader String token) {
		String tokens = tokenservice.getUserIdFromToken(token);
		return noteservice.searchBytitileDesc(tittle, tokens);

	}

	@PostMapping("/collaborate")
	public Response colaborate(@RequestHeader String token, @RequestHeader String emailid, @RequestHeader int noteId) throws NoteException, UserExceptions {
		String tokens = tokenservice.getUserIdFromToken(token);
		noteservice.collaborate(tokens, emailid, noteId);
		return new Response(new Date(), "collaborate succesfully", 200, "OK");

	}
	@PostMapping("/removecollab")
	public String removecollaborate(@RequestHeader String token, @RequestHeader String email ,@RequestHeader int noteId) {
		String decodeToken = tokenservice.getUserIdFromToken(token);
		return noteservice.removeCollaborate(decodeToken,email,noteId);
	}

	@GetMapping("/collaboratelist")
	public List<Collaborator> collablist(@RequestHeader String token ,@RequestHeader int noteId) {

		String tokens = tokenservice.getUserIdFromToken(token);
		return noteservice.findAllcollab(tokens,noteId);

	}

	@PostMapping("/reminder")
	public Response addreminders(@RequestHeader String token, @RequestHeader String times, @RequestHeader int noteId) throws UserExceptions, NoteException {
		System.out.println("helloolloo");
		String tokens = tokenservice.getUserIdFromToken(token);
	    noteservice.addreminder(tokens, times, noteId);
		return new Response(new Date(), "reminder added succesfully", 200, "OK");

	}

	@PutMapping("/removereminder")
	public Response remove(@RequestHeader String token,@RequestHeader int noteId) throws UserExceptions, NoteException {
		String tokens = tokenservice.getUserIdFromToken(token);
		noteservice.removereminder(tokens,noteId);
		return new Response(new Date(), "reminder deleted succesfully", 200, "OK");

	}
	
}
