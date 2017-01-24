package com.jsampler.pwvalidator.rules;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsampler.pwvalidator.PasswordValidatorProperty;

@Component
public class LengthRule implements PasswordRule {

	final private static Logger log = Logger.getLogger(LengthRule.class);

    @Autowired
	PasswordValidatorProperty property;
    
	@Override
	public boolean isCompliant(String password) {
		log.info("Using properties: minlen=" + property.getMinlen() + " maxlen="+ property.getMaxlen());
		if (password == null)
			return false;
		if (password.length() < property.getMinlen() || password.length() > property.getMaxlen())
			return false;
		return true;
	}

	@Override
	public String getRuleDescription() {
		return String.format("Number of characters must be between %d and %d inclusive", property.getMinlen(), property.getMaxlen());
	}

}
