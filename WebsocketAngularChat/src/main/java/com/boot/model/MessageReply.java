package com.boot.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MessageReply {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long convRepId;
	private String reply;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	private Date timestamp;

	public long getConvRepId() {
		return convRepId;
	}

	public void setConvRepId(long convRepId) {
		this.convRepId = convRepId;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
