package com.training.datamodel;

import java.util.List;

public class Question {

	private String text;
	private List<Option> options;
	
	public Question() {
	}

	public String getText() {
		return text;
	}
	public Question(String text) {
		super();
		this.text = text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	//private String option1;
	//private String option2;
	//...
	
	
}
