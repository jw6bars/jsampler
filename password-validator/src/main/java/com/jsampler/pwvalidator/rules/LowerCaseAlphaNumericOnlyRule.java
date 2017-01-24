package com.jsampler.pwvalidator.rules;

import org.springframework.stereotype.Component;

@Component
public class LowerCaseAlphaNumericOnlyRule implements PasswordRule {
	
	final static String ruleDescription = "All characters must be a lowercase letter or a numerical digit, and there must be one of each";

	@Override
	public boolean isCompliant(String password) {
		if  (password == null) {
			return false;
		}
		boolean hasOneLowerCaseAlpha = false;
		boolean hasOneNumeric = false;
		for (int idx = 0; idx < password.length(); idx++) {
			if (Character.isDigit(password.charAt(idx))) {
				hasOneNumeric = true;
			}
			else if (Character.isLowerCase(password.charAt(idx))) {
				hasOneLowerCaseAlpha = true;
			}
			else {
				return false;
			}
		}
		if (!hasOneLowerCaseAlpha || !hasOneNumeric) {
			return false;
		}
		return true;
	}

	@Override
	public String getRuleDescription() {
		return LowerCaseAlphaNumericOnlyRule.ruleDescription;
	}

}
