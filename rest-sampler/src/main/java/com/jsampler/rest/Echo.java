package com.jsampler.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Echo {
	
	private long id;
	
	private String text;

	public Echo() {
		super();
	}

	public Echo(long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	@Override
	public String toString() {
	    return "{ \"id\" : " + id + ", \"text\" : \"" + text + "\" }";
	}

}
