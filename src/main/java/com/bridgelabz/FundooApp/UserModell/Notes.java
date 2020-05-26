package com.bridgelabz.FundooApp.UserModell;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Notes                                                                                                                                                                                                                                       
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
     @NotEmpty(message = "tittle should not be empty")
	private String tittle;
     @NotEmpty(message = "description should not be empty")
	private String description;
	@NotNull
	private boolean istrash;
	@NotNull
	private boolean ispin;
	@NotNull
	private boolean isarchieve;
//	private String reminder;
	private boolean remindercheck;
	private String remindertime;
	private LocalDateTime atcreate;
	private LocalDateTime atModified;
	private String backgroundColor;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInformation userid;
	@ManyToMany
	//@JoinColumn(name="newemail")
	
	
	@JoinTable ( name = "coljoin", joinColumns = @JoinColumn (name
			  ="noteId"), inverseJoinColumns = @JoinColumn(name = "colabid"))
	@JsonIgnoreProperties(value="noteslist")
	private List<Collaborator>collaboration=new ArrayList<Collaborator>();
	
	@ManyToMany
	@JoinTable ( name = "labelnote", joinColumns = @JoinColumn (name
			  ="noteId"), inverseJoinColumns = @JoinColumn(name = "labelid"))
	@JsonIgnoreProperties(value="notelist")
	private List<Label> labellist = new ArrayList<Label>() ;

//	@ManyToMany()
//	@JsonIgnoreProperties(value="notelistss")
//	@JoinTable ( name = "usercollab", joinColumns = @JoinColumn (name
//			  ="noteId"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private List<UserInformation>userlist = new ArrayList<UserInformation>();
	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Label> getLabellist() {
		return labellist;
	}
	public void setLabellist(List<Label> labellist) {
		this.labellist = labellist;
	}
	
	public UserInformation getUserid() {
		return userid;
	}
	public void setUserid(UserInformation userid) {
		this.userid = userid;
	}
	
	public boolean isIstrash() {
		return istrash;
	}
	public void setIstrash(boolean istrash) {
		this.istrash = istrash;
	}
	public boolean isIspin() {
		return ispin;
	}
	public void setIspin(boolean ispin) {
		this.ispin = ispin;
	}
	public boolean isIsarchieve() {
		return isarchieve;
	}
	public void setIsarchieve(boolean isarchieve) {
		this.isarchieve = isarchieve;
	}
	
//	
//	public List<UserInformation> getUserlist() {
//		return userlist;
//	}
//	public void setUserlist(List<UserInformation> userlist) {
//		this.userlist = userlist;
//	}
//	
	
	public LocalDateTime getAtcreate() {
		return atcreate;
	}
	public void setAtcreate() {
		this.atcreate = LocalDateTime.now();
	}
	public LocalDateTime getAtModified() {
		return atModified;
	}
	public void setAtModified() {
		this.atModified = LocalDateTime.now();
	}
	public List<Collaborator> getCollaboration() {
		return collaboration;
	}
	public void setCollaboration(List<Collaborator> collaboration) {
		this.collaboration = collaboration;
	}
	
	
	
	public boolean isRemindercheck() {
		return remindercheck;
	}
	public void setRemindercheck(boolean remindercheck) {
		this.remindercheck = remindercheck;
	}
	public String getRemindertime() {
		return remindertime;
	}
	public void setRemindertime(String remindertime) {
		this.remindertime = remindertime;
	}

//	@Override
//	public String toString() {
//		return "Notes [noteId=" + noteId + ", tittle=" + tittle + ", description=" + description + ", istrash="
//				+ istrash + ", ispin=" + ispin + ", isarchieve=" + isarchieve + " userid="
//				+ userid + ", collaboration=" + collaboration  
//				 +"]";
//	}
//	
//	
	
	

}
