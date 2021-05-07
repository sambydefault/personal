package com.boot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long convId;

	public long getConvId() {
		return convId;
	}

	public void setConvId(long convId) {
		this.convId = convId;
	}

	public User getUserOne() {
		return userOne;
	}

	public void setUserOne(User userOne) {
		this.userOne = userOne;
	}

	public User getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(User userTwo) {
		this.userTwo = userTwo;
	}

	public List<MessageReply> getConvRepList() {
		return convRepList;
	}

	public void setConvRepList(List<MessageReply> convRepList) {
		this.convRepList = convRepList;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	User userOne, userTwo;
	@OneToMany(cascade = CascadeType.ALL)
	List<MessageReply> convRepList;
}
