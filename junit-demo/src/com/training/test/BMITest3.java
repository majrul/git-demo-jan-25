package com.training.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.training.demo.BMI;
import com.training.demo.BMICalculator;
import com.training.demo.Person;

public class BMITest3 {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/bmi.csv", numLinesToSkip = 1)
	public void calculateBMI_FromCSVFileInput_ShouldReturnExpectedValue(double weight, double height, double expected) {
		BMI bmi = new BMICalculator();
		
		//given
		Person p = new Person(weight, height);
		
		//when
		double actual = bmi.calculateBMI(p);
		
		//then
		assertEquals(expected, actual);
	}
	
}
