package com.jsampler.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSamplerController {

	final static String USAGE_BASE = "Add the text parameter to be echoed back, as in http://HOST:PORT/";
	final static String USAGE_PLAIN = USAGE_BASE + "echo?text=something";
	final static String USAGE_JSON = USAGE_BASE + "echo-json?text=something%20else";
	final static String USAGE_POST = USAGE_BASE + "echo-json?text=something%20else";
	
	private final AtomicLong id = new AtomicLong(99);
	
	@RequestMapping(path="/echo", produces="text/plain")
	public String echo(@RequestParam(required=false, value="text") String text) {
		System.out.println("XXXXXXXXXX echo");
		if (StringUtils.isBlank(text))
			return USAGE_PLAIN;
		return text;
	}
	
	@RequestMapping(path="/echo-json", produces="application/json", method=RequestMethod.GET)
	public Echo echoJson(@RequestParam(required=false, value="text") String text) {
		System.out.println("XXXXXXXXXX echoJson");
		if (StringUtils.isBlank(text))
			return new Echo(-1,USAGE_JSON);
		return new Echo(id.incrementAndGet(),text);
	}
	
	@RequestMapping(path="/echo-json-post", produces="application/json", method=RequestMethod.POST)
	public @ResponseBody Echo echoJsonPost(@RequestBody(required=false) Echo echo) {
		System.out.println("XXXXXXXXXX echoJsonPost");
		if (echo == null)
			return new Echo(-1,USAGE_JSON);
		id.set(echo.getId());
		return new Echo(echo.getId(),echo.getText());
	}

}
