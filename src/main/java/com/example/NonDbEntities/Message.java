package com.example.NonDbEntities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.Entities.Customer;

public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;

	@ManyToOne
	@JoinColumn(name="sent_from")
	private Customer sentFrom;
	
	@ManyToOne
	@JoinColumn(name="sent_to")
	private Customer sentTo;
	
	public Message() {
		
	}
	
	public Message(int newId, String newName, Customer newSender, Customer newReceiver) {
		this.id = newId;
		this.name = newName;
		this.sentFrom = newSender;
		this.sentTo = newReceiver;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getSentFrom() {
		return sentFrom;
	}

	public void setSentFrom(Customer sentFrom) {
		this.sentFrom = sentFrom;
	}

	public Customer getSentTo() {
		return sentTo;
	}

	public void setSentTo(Customer sentTo) {
		this.sentTo = sentTo;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Message(String newContent) {
		name = newContent;
	}

}
