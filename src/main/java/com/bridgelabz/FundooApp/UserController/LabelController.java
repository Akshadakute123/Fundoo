package com.bridgelabz.FundooApp.UserController;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooApp.Exceptions.LabelException;
import com.bridgelabz.FundooApp.Exceptions.NoteException;
import com.bridgelabz.FundooApp.Exceptions.Response;
import com.bridgelabz.FundooApp.Exceptions.UserExceptions;
import com.bridgelabz.FundooApp.UserModell.Label;
import com.bridgelabz.FundooApp.UserService.LabelService;
import com.bridgelabz.FundooApp.Utility.TokenService;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController 
{ 
	@Autowired
	private	TokenService tokenservice;
	
	@Autowired
	private LabelService labelservice;
	
	@PostMapping("/create")
	public Response CreateLabel(@Valid @RequestBody Label label,@RequestHeader String token) throws LabelException, UserExceptions
	{
		String tokens=tokenservice.getUserIdFromToken(token);
		System.out.println("token is"+tokens);
		labelservice.createlabels(label,tokens);
		return new Response(new Date(), "label created succesfully ", 200, "OK");
		
		
	}
	@PostMapping("/delete")
	public Response deletelabel(@RequestBody Label label, @RequestParam String token) throws LabelException, UserExceptions {
		String decodeToken =tokenservice.getUserIdFromToken(token);
		labelservice.deleteLabel(label, decodeToken);
		return new Response(new Date(), "label deleted succesfully ", 200, "OK");


	}
	@PostMapping("/update")
	public Response updatelabel(@Valid @RequestBody Label label, @RequestParam String token) throws UserExceptions, LabelException {
		String decodeToken = tokenservice.getUserIdFromToken(token);
		 
		labelservice.updateLabel(label, decodeToken);
		return new Response(new Date(), "label updated succesfully ", 200, "OK");

	}
	@GetMapping("/getinfo")
	public List<Label> labelinfo()
	{
		return labelservice.labelinfo();
	}
	@GetMapping("/getinfolabel")
	public List<Label> getLabels(@RequestHeader String token) {
		
		System.out.println(("backenddd"));
		String newToken = tokenservice.getUserIdFromToken(token);
		return labelservice.findAll(newToken);
		
	}
	@GetMapping("displaylabeladdedtonote")
	public List<Label> LabelAddedToNote(@RequestParam String token, @RequestParam int noteId) {
		System.out.println("token of users is");
		
		String newToken = tokenservice.getUserIdFromToken(token);
		return labelservice.displayaddedlabels(newToken,noteId);
		
	}
	@GetMapping(value = "/DisplayUnAddedlabel")
	public List<Label> displayLabelUnAdded(@RequestParam String token, @RequestParam int noteId) {
		String newToken = tokenservice.getUserIdFromToken(token);
		return labelservice.displayunaddedlabels(newToken,noteId);
			}
	@PostMapping("/addLabelToNote")
	public Response addLabeltonote(@RequestHeader String token,@RequestParam int  noteId,@RequestParam int  labelid) throws LabelException, NoteException, UserExceptions {
		System.out.println("heloooo");
		String tokens = tokenservice.getUserIdFromToken(token);
		System.out.println("token is "+tokens);
	labelservice.addLabelToNote(tokens, noteId, labelid);
	return new Response(new Date(), "label added to note succesfully ", 200, "OK");

		
	}
	@PostMapping("/addNoteToLabel")
	public Response addNote(@RequestHeader String token,@RequestParam int  noteId,@RequestHeader int  labelid) throws LabelException, NoteException, UserExceptions {
		
		String tokennew = tokenservice.getUserIdFromToken(token);
		 labelservice.addNoteToLabel(tokennew, noteId, labelid);
			return new Response(new Date(), "labeladded label to note succesfully ", 200, "OK");

		
	}

	@PostMapping( "/removeLabelFromNotes")
	public Response removeToLabelFromNote(@RequestParam int noteId, @RequestParam int labelid,@RequestParam String token) {
		String tokennew = tokenservice.getUserIdFromToken(token);
		System.out.println("inside remove label");
		 labelservice.removelabelfromnote(tokennew, noteId, labelid);
			return new Response(new Date(), "label remove from note succesfully ", 200, "OK");
	}

}
 