package com.training.datamodel;

public class Option {

	private String text;
	private boolean isRightAnswer;

	public Option() {
	}

	public Option(String text, boolean isRightAnswer) {
		super();
		this.text = text;
		this.isRightAnswer = isRightAnswer;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isRightAnswer() {
		return isRightAnswer;
	}
	public void setRightAnswer(boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}
	
	
}
