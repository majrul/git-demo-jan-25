package com.training.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.training.demo.BMI;
import com.training.demo.BMICalculator;
import com.training.demo.Person;

public class BMITest3 {
	
	@ParameterizedTest
	//@CsvFileSource(resources = "/bmi.csv", numLinesToSkip = 1)
	@MethodSource("provideData")
	public void calculateBMI_FromCSVFileInput_ShouldReturnExpectedValue(double weight, double height, double expected) {
		BMI bmi = new BMICalculator();
		
		//given
		Person p = new Person(weight, height);
		
		//when
		double actual = bmi.calculateBMI(p);
		
		//then
		assertEquals(expected, actual);
	}
	
	
	public static Stream<Arguments> provideData() {
		return Stream.of(
				Arguments.of(70, 1.7, 24),
				Arguments.of(90, 1.7, 31),
				Arguments.of(120, 1.7, 42));
	}
	
}
