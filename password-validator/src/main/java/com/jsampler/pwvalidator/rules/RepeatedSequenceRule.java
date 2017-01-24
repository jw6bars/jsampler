package com.jsampler.pwvalidator.rules;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class RepeatedSequenceRule implements PasswordRule {
	
	final static String ruleDescription = "There must not be a sequence of characters immediately adjacent to an identical sequence";

	@Override
	public boolean isCompliant(String password) {
		if (password == null)
			return false;
		int len = password.length();
		for (int i = 1; i < len; i++) {
			HashMap<String, Integer> sequenceMap = new HashMap<String, Integer>();
			for (int j = 0; j + i <= len; j++) {
				String sequence = password.substring(j, j + i);
				Integer lastPosOfMatchingSeq = sequenceMap.get(sequence);
				if (lastPosOfMatchingSeq != null && lastPosOfMatchingSeq.compareTo(j - 1) == 0)
					return false;
				sequenceMap.put(sequence, j + i - 1);
			}
		}
		return true;
	}

	@Override
	public String getRuleDescription() {
		return RepeatedSequenceRule.ruleDescription;
	}
	
}
