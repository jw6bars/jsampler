package com.jsampler.pwvalidator.service;

public class PasswordValidationResponse {

	private boolean valid;
	
	private String reason;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
