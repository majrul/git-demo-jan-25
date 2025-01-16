package com.training.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionBank {

	private Map<String, List<Question>> questionBank = new HashMap<>();
	
	public void addSubject(String subject) {
		questionBank.put(subject, new ArrayList<Question>());
	}
	
	public void addQuestion(String subject, Question question) {
		questionBank.get(subject).add(question);
	}
	
	public List<Question> getQuestions(String subject) {
		return questionBank.get(subject);
	}
}
