package com.example.NonDbEntities;

public class Message {
	
	private String name;

	public Message() {
		
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
