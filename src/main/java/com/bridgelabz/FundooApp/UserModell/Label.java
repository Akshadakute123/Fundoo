
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

@Entity
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int labelid;
	private LocalDateTime createtime;
	private LocalDateTime modifiedtime;
	private String labelname;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInformation userinfo;

	@ManyToMany
	@JoinTable(name = "Label_Notes_join", joinColumns = @JoinColumn(name = "labelid"), inverseJoinColumns = @JoinColumn(name = "noteId"))
	
	private List<Notes> notelist = new ArrayList<Notes>();

	public Label() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Label(int labelid, LocalDateTime createtime, LocalDateTime modifiedtime, String labelname,
			UserInformation userinfo, List<Notes> notelist) {
		super();
		this.labelid = labelid;
		this.createtime = createtime;
		this.modifiedtime = modifiedtime;
		this.labelname = labelname;
		this.userinfo = userinfo;
		this.notelist = notelist;
	}

	public int getLabelid() {
		return labelid;
	}

	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}

	public LocalDateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}

	public LocalDateTime getModifiedtime() {
		return modifiedtime;
	}

	public void setModifiedtime(LocalDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public UserInformation getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInformation userinfo) {
		this.userinfo = userinfo;
	}

	public List<Notes> getNotelist() {
		return notelist;
	}

	public void setNotelist(List<Notes> notelist) {
		this.notelist = notelist;
	}

	public String toString() {
		return "Label [labelid=" + labelid + ", createtime=" + createtime + ", modifiedtime=" + modifiedtime
				+ ", labelname=" + labelname + ", userinfo=" + userinfo + ", notelist=" + notelist + "]";
	}

}
