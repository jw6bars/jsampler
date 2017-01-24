package com.jsampler.pwvalidator.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsampler.pwvalidator.rules.PasswordRule;

@Component
public class PasswordValidator implements PasswordValidatorService {

	final static Logger log = Logger.getLogger(PasswordValidator.class);

	@Autowired
	private List<PasswordRule> passwordRuleList;
	
	@Override
	public PasswordValidationResponse validatePassword(String password) {
		PasswordValidationResponse result = new PasswordValidationResponse();
		for (PasswordRule rule : passwordRuleList) {
			if (!rule.isCompliant(password)) {
				result.setValid(false);
				result.setReason(rule.getRuleDescription());
				log.debug("Failed validation: password=" + password + " rule=" + rule.getClass().getSimpleName());
				return result;
			}
			log.debug("Passed validation: password=" + password + " rule=" + rule.getClass().getSimpleName());
		}
		result.setValid(true);
		return result;
	}

}
