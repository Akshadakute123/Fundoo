package com.bridgelabz.FundooApp.UserController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String createNote(@RequestBody Notes note, @RequestHeader String token) {
		String decodetoken = tokenservice.getUserIdFromToken(token);
		System.out.println("decoded token" + decodetoken);
		return noteservice.createNewNote(note, decodetoken);

	}

	@GetMapping("/get")
	public List<Notes> getinformation() {
		return noteservice.noteinfo();
	}

	@DeleteMapping("/delete")
	public String deleteNote(@RequestBody Notes note1, @RequestHeader String token) {
		String tok = tokenservice.getUserIdFromToken(token);
		System.out.println("hiiii " + tok);
		return noteservice.deleteNotess(note1, tok);
	}

	@PutMapping("/update")
	public String updateNote(@RequestBody Notes notes, @RequestHeader String token) {
		String decode = tokenservice.getUserIdFromToken(token);
		System.out.println("token is" + decode);
		return noteservice.updateNotes(notes, decode);

	}
	@PostMapping("/pin")
	public String ispin(@RequestHeader int noteId,@RequestHeader String token)
	{
		String tokens=tokenservice.getUserIdFromToken(token);
		return noteservice.pin(noteId,tokens);
	}

   @PostMapping("/untrash")
   public String untrash(@RequestHeader int noteId,@RequestHeader String token)
   {
	   String tokens=tokenservice.getUserIdFromToken(token);
		return noteservice.untrash(noteId,tokens);
   }
   @PostMapping("/trash")
   public String trash(@RequestHeader int noteId,@RequestHeader String token)
   {
	   String tokens=tokenservice.getUserIdFromToken(token);
		return noteservice.trash(noteId,tokens);
   }
   @PostMapping("/archieve")
   public String archieve(@RequestHeader int noteId,@RequestHeader String token)
   {
	   String tokens=tokenservice.getUserIdFromToken(token);
		return noteservice.archieve(noteId,tokens);
   }
   @PostMapping("/unarchieve")
   public String unarchieve(@RequestHeader int noteId,@RequestHeader String token)
   {
	   String tokens=tokenservice.getUserIdFromToken(token);
		return noteservice.unarchieve(noteId,tokens);
   }
  @DeleteMapping("/deletetrash")
  public String delete(@RequestHeader int noteId,@RequestHeader String token)
  {
	 
		   String tokens=tokenservice.getUserIdFromToken(token);
			return noteservice.trashdelete(noteId,tokens);
	   
  }
  @GetMapping("/getnote")
  public List<Notes> findnotes(@RequestHeader String token)
  {
	  String tokens=tokenservice.getUserIdFromToken(token);
	  return noteservice.findAll(tokens);
  }
  @GetMapping("/sortlist")
  public List<Notes> sortelist(@RequestHeader String token)
  {

	   String tokens=tokenservice.getUserIdFromToken(token);
		return noteservice.sortedlist(tokens);  
  }
	 
  @GetMapping("/trashlist")
  public List<Notes> trashfile(@RequestHeader String token)
  {
	  String tokens=tokenservice.getUserIdFromToken(token);
	  return noteservice.getnoteslist(tokens);
  }
  
  @PostMapping("/search")
  public List<Notes> search(@RequestHeader String tittle, @RequestHeader String token)
  {
	  String tokens=tokenservice.getUserIdFromToken(token);
	  return noteservice.searchBytitileDesc(tittle,tokens);
	  
  }
  @PostMapping("/collaborate")
  public String colaborate(@RequestHeader String token,@RequestHeader String emailid,@RequestHeader int noteId )
  {
	  String tokens=tokenservice.getUserIdFromToken(token);
	  return noteservice.collaborate(tokens,emailid,noteId);
  }
  @GetMapping("/collaboratelist")
  public List<Collaborator> collablist(@RequestHeader String token)
  {

	  String tokens=tokenservice.getUserIdFromToken(token);
	  return noteservice.findAllcollab(tokens);
	 
  }
  
  
  
  
  
  
  @PostMapping("/reminder")
  public String addreminder(@RequestHeader String token,@RequestHeader String reminder,@RequestHeader int noteId )
  {
	  String tokens=tokenservice.getUserIdFromToken(token);
	  return noteservice.addreminder(tokens,reminder,noteId);
  }
  @PutMapping("/removereminder")
  public String remove(@RequestHeader String token,@RequestHeader int noteId)
  {
	  String tokens=tokenservice.getUserIdFromToken(token);
	  return noteservice.removereminder(tokens,noteId);
  }
}
