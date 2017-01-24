package com.jsampler.pwvalidator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "password.validator")
public class PasswordValidatorProperty {

	private int minlen;

	private int maxlen;

	public int getMinlen() {
		return minlen;
	}

	public void setMinlen(int minlen) {
		this.minlen = minlen;
	}

	public int getMaxlen() {
		return maxlen;
	}

	public void setMaxlen(int maxlen) {
		this.maxlen = maxlen;
	}
}
