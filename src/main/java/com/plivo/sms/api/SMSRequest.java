package com.plivo.sms.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SMSRequest {

	@JsonProperty
	private String from;
	
	@JsonProperty
	private String to;
	
	@JsonProperty
	private String text;
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private String auth_id;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTp(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
}
