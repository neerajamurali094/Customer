package com.diviso.graeshoppe.client.model;

import java.util.HashSet;
import java.util.Set;

public class OTPResponse {
	
	private long balance;
	private long batch_id;
	private long cost;
	private long numMessages;
	
	private Message message;
	
    private Set<MessageRecipient> messageRecipient= new HashSet<>();

	private String receipt_url;
	private String custom;
	private String status;
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public long getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(long batch_id) {
		this.batch_id = batch_id;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	public long getNumMessages() {
		return numMessages;
	}
	public void setNumMessages(long numMessages) {
		this.numMessages = numMessages;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Set<MessageRecipient> getMessageRecipient() {
		return messageRecipient;
	}
	public void setMessageRecipient(Set<MessageRecipient> messageRecipient) {
		this.messageRecipient = messageRecipient;
	}
	public String getReceipt_url() {
		return receipt_url;
	}
	public void setReceipt_url(String receipt_url) {
		this.receipt_url = receipt_url;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
