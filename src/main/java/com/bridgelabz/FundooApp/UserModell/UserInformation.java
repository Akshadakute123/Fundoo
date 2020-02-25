package com.bridgelabz.FundooApp.UserModell;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity

public class UserInformation{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @OneToMany(mappedBy = )
	private int user_id;
	private String username;
	private String email;
	private String password;
	private String confirm_password;
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<Notes> notelist = new ArrayList<Notes>();

//	@ManyToMany
//	@JsonIgnoreProperties(value = "userlist")
//	@JoinTable(name = "usercollab", joinColumns = @JoinColumn(name = " user_id"), inverseJoinColumns = @JoinColumn(name = "noteId"))
//	private List<Notes>notelistss  = new ArrayList<Notes>();

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public List<Notes> getNotelist() {
		return notelist;
	}

	public void setNotelist(List<Notes> notelist) {
		this.notelist = notelist;
	}

//	public List<Notes> getNotelistss() {
//		return notelistss;
//	}
//
//	public void setNotelistss(List<Notes> notelistss) {
//		this.notelistss = notelistss;
//	}

	@Override
	public String toString() {
		return "UserInformation [user_id=" + user_id + ", username=" + username + ", email=" + email + ", password="
				+ password + ", confirm_password=" + confirm_password + "]";
	}

}
