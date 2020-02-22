package com.bridgelabz.FundooApp.UserDTO;

import com.bridgelabz.FundooApp.UserModell.UserInformation;

public class NotesDTO {
	private int noteId;

	private String tittle;
	private String description;
	UserInformation userinformations;
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserInformation getUserinformations() {
		return userinformations;
	}
	public void setUserinformations(UserInformation userinformations) {
		this.userinformations = userinformations;
	}
	@Override
	public String toString() {
		return "NotesDTO [noteId=" + noteId + ", tittle=" + tittle + ", description=" + description
				+ ", userinformations=" + userinformations + "]";
	}
	
}
