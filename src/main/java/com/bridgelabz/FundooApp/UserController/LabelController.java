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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooApp.UserModell.Label;
import com.bridgelabz.FundooApp.UserService.LabelService;
import com.bridgelabz.FundooApp.Utility.TokenService;

@RestController
@RequestMapping("/label")
public class LabelController 
{ 
	@Autowired
	private	TokenService tokenservice;
	
	@Autowired
	private LabelService labelservice;
	
	@PostMapping("/create")
	public String CreateLabel(@RequestBody Label label,@RequestHeader String token)
	{
		String tokens=tokenservice.getUserIdFromToken(token);
		System.out.println("token is"+tokens);
		return labelservice.createlabels(label,tokens);
		
		
	}
	@DeleteMapping("/delete")
	public String delete(@RequestBody Label label, @RequestHeader String token) {
		String decodeToken =tokenservice.getUserIdFromToken(token);
		return labelservice.deleteLabel(label, decodeToken);

	}
	@PutMapping("/update")
	public String update(@RequestBody Label label, @RequestHeader String token) {
		String decodeToken = tokenservice.getUserIdFromToken(token);
		return labelservice.updateLabel(label, decodeToken);
	}
	@GetMapping("/getinfo")
	public List<Label> labelinfo()
	{
		return labelservice.labelinfo();
	}
	@GetMapping("/getinfolabel")
	public List<Label> getLabels(@RequestHeader String token) {
		String newToken = tokenservice.getUserIdFromToken(token);
		return labelservice.findAll(newToken);
		
	}
	@PostMapping("/addLabelToNote")
	public String add(@RequestHeader String token,@RequestParam int  noteId,@RequestHeader int  labelid) {
		System.out.println("heloooo");
		String tokens = tokenservice.getUserIdFromToken(token);
		System.out.println("token is "+tokens);
		return labelservice.addLabelToNote(tokens, noteId, labelid);
		
	}
	@PostMapping("/addNoteToLabel")
	public String addNote(@RequestHeader String token,@RequestParam int  noteId,@RequestHeader int  labelid) {
		
		String tokennew = tokenservice.getUserIdFromToken(token);
		return labelservice.addNoteToLabel(tokennew, noteId, labelid);
		
	}
	

}
