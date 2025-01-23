package com.training.demo;

import java.util.Comparator;
import java.util.List;

public class BMICalculator implements BMI {

	public BMICalculator() {
		System.out.println("constr..");
	}
	
	@Override
	public double calculateBMI(Person person) {
		double weight = person.getWeight();
		double height = person.getHeight();
		
		if(weight <=0 || height <= 0)
			throw new BMIException("Incorrect Data");
		
		double bmi = weight / (height * height);
		return Math.round(bmi);
	}

	@Override
	public boolean isDietRecommended(Person person) {
		double bmi = calculateBMI(person);
		if(bmi < 25)
			return false;
		else
			return true;
	}
	
	@Override
	public double[] getBMIValues(List<Person> persons) {
		return persons
				.stream()
				.mapToDouble(this::calculateBMI)
				.toArray();
	}
	
	@Override
	public Person findPersonWithLowestBMI(List<Person> persons) {
		return persons
				.stream()
				.sorted(Comparator.comparing(this::calculateBMI))
				.reduce((first, second) -> first)
				.orElseThrow(BMIException::new);
	}
}
