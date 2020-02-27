package com.bridgelabz.FundooApp.UserModell;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Collaborator 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int colabid;
	
	private String newemail;
//	@ManyToOne
	//@ManyToMany(mappedBy = "collaboration")
	@ManyToMany
	  @JoinTable ( name = "coljoin", joinColumns = @JoinColumn (name
	  ="colabid"), inverseJoinColumns = @JoinColumn(name = "noteId"))
	@JsonIgnoreProperties(value="collaboration")
	private List<Notes>noteslist=new ArrayList<Notes>();
     // private Notes noteId;
	public String getNewemail() {
		return newemail;
	}
	public void setNewemail(String newemail) {
		this.newemail = newemail;
	}
	public List<Notes> getNoteslist() {
		return noteslist;
	}
	public void setNoteslist(List<Notes> noteslist) {
		this.noteslist = noteslist;
	}
	public int getColabid() {
		return colabid;
	}
//	@Override
//	public String toString() {
//		return "Collaborator [colabid=" + colabid + ", newemail=" + newemail + ", noteslist=" + noteslist + "]";
//	}
	public void setColabid(int colabid) {
		this.colabid = colabid;
	}
	
	
}
