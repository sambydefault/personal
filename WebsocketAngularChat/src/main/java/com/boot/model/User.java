package com.boot.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String userName;
	private String passWord;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Message> convoList;
	@OneToMany(cascade = CascadeType.ALL)
	private List<MessageReply> convoRepList;
	private Date userLastLogin;
	private String userStatus;
	

	public Date getUserLastLogin() {
		return userLastLogin;
	}

	public void setUserLastLogin(Date userLastLogin) {
		this.userLastLogin = userLastLogin;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<MessageReply> getConvoRepList() {
		return convoRepList;
	}

	public void setConvoRepList(List<MessageReply> convoRepList) {
		this.convoRepList = convoRepList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public List<Message> getConvoList() {
		return convoList;
	}

	public void setConvoList(List<Message> convoList) {
		this.convoList = convoList;
	}

}
