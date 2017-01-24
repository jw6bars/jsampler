package com.jsampler.pwvalidator.rules;

public interface PasswordRule {
	
	public boolean isCompliant(String password);
	
	public String getRuleDescription();

}
