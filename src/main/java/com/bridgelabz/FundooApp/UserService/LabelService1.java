//package com.bridgelabz.FundooApp.UserService;
//
//mport java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.bridgelabz.fundoo.dto.LabelDto;
//import com.bridgelabz.fundoo.model.Label;
//import com.bridgelabz.fundoo.model.Notes;
//import com.bridgelabz.fundoo.model.User;
//import com.bridgelabz.fundoo.repository.LabelRepository;
//import com.bridgelabz.fundoo.repository.NotesRepository;
//import com.bridgelabz.fundoo.repository.UserRepository;
//
//@Service
//public class LabelService1 {
////	@Autowired
////	private LabelRepository labelRepository;
////	@Autowired
////	private UserRepository userRepository;
////	@Autowired
////	private NotesRepository noteRpository;
//
//	public String createLabel(Label, String decodeToken) {
//		Optional<Label> label_id = labelRepository.findById(label.getLabelId());
//		// Optional<Label> label_id = labelRepository.findById(label.getLabel_id());
//		Optional<User> userId = userRepository.findByEmail(decodeToken);
//		// System.out.println("in createLabel userId "+userId);
//		if ((label_id.isEmpty()) && (label.getLabel_name().isEmpty())) {
//			return " false in if";
//
//		} else {
//			// label_id.get().setUserid(userId.get());
//			label.setUserid(userId.get());
//			labelRepository.save(label);
//		}
//		return "label created";
//	}
//
//	public String deleteLabel(Label label, String decodeToken) {
//		Optional<User> userId = userRepository.findByEmail(decodeToken);
//		Optional<Label> label_id = labelRepository.findById((long) userId.get().getUserid());
//		if ((label_id.isEmpty()) && (userId.isEmpty())) {
//			return "label not present";
//		} else {
//			labelRepository.delete(label);
//		}
//		return "delete sucesfully";
//	}
//
//	public String updateLabel(Label label, String decodeToken) {
//		Optional<User> userId = userRepository.findByEmail(decodeToken);
//		Optional<Label> label_id = labelRepository.findById(label.getLabelId());
//		if (userId.isPresent()) {
//			if (label_id.isPresent()) {
//				label_id.get().setLabel_name(label.getLabel_name());
//				labelRepository.save(label_id.get());
//			}
//		}
//		return "updated succesfully";
//	}
//
//	public List<Label> findAll(String decodeToken) {
//		List<Label> allLabels = new ArrayList<Label>();
//		Optional<User> user = userRepository.findByEmail(decodeToken);
//		if (user.isPresent())
//			allLabels = labelRepository.findAll();
//		return allLabels;
//	}
//
//	public String addLabelToNote(String token, int noteId, int labelId) {
//		Optional<Label> label_Id = labelRepository.findByLabelId(labelId);
//		Optional<Notes> note_Id = noteRpository.findByNoteId(noteId);
//		Optional<User> user_Id = userRepository.findByEmail(token);
//		if (label_Id.isEmpty()) {
//			if (note_Id.isEmpty()) {
//				if (user_Id.isEmpty()) {
//					return "ERROR";
//				}
//			}
//		} else {
//			note_Id.get().getLabelNoteslist().add(label_Id.get());
//			noteRpository.save(note_Id.get());
//		}
//		return "update";
//
//	}
//
//	public String addNoteToLabel(String token, int noteId, int labelId) {
//		Optional<Label> label = labelRepository.findByLabelId(labelId);
//		Optional<Notes> note_Id = noteRpository.findByNoteId(noteId);
//		Optional<User> user_Id = userRepository.findByEmail(token);
//		if (label.isEmpty()) {
//			if (note_Id.isEmpty()) {
//				if (user_Id.isEmpty()) {
//					return "ERROR";
//				}
//			}
//		} else {
////			note_Id.get().getLabel_id().add(label_Id.get());
////			noteRpository.save(note_Id.get());
//
//
//			label.get().getNoteLabelList().add(note_Id.get());
//			labelRepository.save(label.get());
//		}
//		return "update";
//	}
//}


//public Response addCollaborator(String token, String email, long noteId) {
//		Email collabEmail = new Email();
//		long userId = userToken.tokenVerify(token);
//		
//		Optional<User> owner = userRepository.findById(userId);
//		Optional<User> user = userRepository.findByEmail(email);
//		
//		if(!user.isPresent())
//			throw new EmailException("No user exist", -4);
//		
//		Note note = notesRepository.findByIdAndUserId(noteId, userId);
//		
//		if(note == null)
//			throw new NotesException("No note exist", -5);
//		
//		if(user.get().getCollaboratedNotes().contains(note)) 
//			throw new NotesException("Note is already collaborated", -5);
//		
//		user.get().getCollaboratedNotes().add(note);
//		note.getCollaboratedUser().add(user.get());
//		
//		userRepository.save(user.get());
//		notesRepository.save(note);
//		
//		collabEmail.setFrom("fundootasif@gmail.com");
//		collabEmail.setTo(email);
//		collabEmail.setSubject("Note collaboration ");
//		collabEmail.setBody("Note from " + owner.get().getEmail() + " collaborated to you\nTitle : " + note.getTitle() +"\nDescription : " + note.getDescription());
//		generateEmail.send(collabEmail);
//		
//		Response response = StatusHelper.statusInfo(environment.getProperty("status.collab.success"),Integer.parseInt(environment.getProperty("status.success.code")));
//		return response;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.bridgelabz.fundoo.notes.service.INotesService#removeCollaborator(java.lang.String, java.lang.String, long)
//	 */
//	@Override
//	public Response removeCollaborator(String token, String email, long noteId) {
//		
//		long userId = userToken.tokenVerify(token);
//		Optional<User> user = userRepository.findByEmail(email);
//		
//		if(!user.isPresent()) 
//			throw new EmailException("No user exist", -4);
//		
//		Note note = notesRepository.findByIdAndUserId(noteId, userId);
//		
//		if(note == null)
//			throw new NotesException("No note exist", -5);
//		
//		user.get().getCollaboratedNotes().remove(note);
//		note.getCollaboratedUser().remove(user.get());
//		
//		userRepository.save(user.get());
//		notesRepository.save(note);
//		
//		Response response = StatusHelper.statusInfo(environment.getProperty("status.collab.remove"),Integer.parseInt(environment.getProperty("status.success.code")));
//		return response;
//	}
//
