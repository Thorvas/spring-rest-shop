package com.example.NonDbEntities;


public class Greeting {
	
	private String content;

	public Greeting() {
		
	}
	
	public Greeting(String newContent) {
		content = newContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
