//package com.bridgelabz.FundooApp.UserController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bridgelabz.FundooApp.UserModell.Label;
//
//@RestController
//@RequestMapping("/label")
//public class LabelController1 {
//	@Autowired
//	private LabelService service;
//	@Autowired
//	private TokenService tokenService;
//
//	@PostMapping("/addlabel")
//	public String addLabel(@RequestBody Label label, @RequestHeader String token) {
//		String decodeToken = tokenService.getUserIdFromToken(token);
//		return service.createLabel(label, decodeToken);
//
//	}
//
//	@DeleteMapping("/delete")
//	public String delete(@RequestBody Label label, @RequestHeader String token) {
//		String decodeToken = tokenService.getUserIdFromToken(token);
//		return service.deleteLabel(label, decodeToken);
//
//	}
//	@PutMapping("/update")
//	public String update(@RequestBody Label label, @RequestHeader String token) {
//		String decodeToken = tokenService.getUserIdFromToken(token);
//		return service.updateLabel(label, decodeToken);
//	}
//	@GetMapping("/findAll")
//	public List<Label> getLabels(@RequestHeader String token) {
//		String decodeToken = tokenService.getUserIdFromToken(token);
//		return service.findAll(decodeToken);
//		
//	}
//	@PostMapping("/addLabelToNote")
//	public String add(@RequestHeader String decodeToken,@RequestParam int  noteId,@RequestHeader int  labelId) {
//		//System.out.println("hey1");
//		String token = tokenService.getUserIdFromToken(decodeToken);
//		//System.out.println("hey3");
//		return service.addLabelToNote(token, noteId, labelId);
//		
//	}
//	@PostMapping("/addNoteToLabel")
//	public String addNote(@RequestHeader String decodeToken,@RequestParam int  noteId,@RequestHeader int  labelId) {
//		//System.out.println("hey1");
//		String token = tokenService.getUserIdFromToken(decodeToken);
//		//System.out.println("hey3");
//		return service.addNoteToLabel(token, noteId, labelId);
//		
//	}
//	
//
//}