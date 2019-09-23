package com.diviso.graeshoppe.client.model;

public class Message {
	
	private long numParts;
	private String sender;
	private String content;
	
	public long getNumParts() {
		return numParts;
	}
	public void setNumParts(long numParts) {
		this.numParts = numParts;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
