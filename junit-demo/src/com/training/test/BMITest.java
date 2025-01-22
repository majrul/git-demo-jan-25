package com.training.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

import com.training.demo.BMI;
import com.training.demo.BMICalculator;
import com.training.demo.BMIException;
import com.training.demo.Person;


@TestMethodOrder(OrderAnnotation.class)
public class BMITest {

	@Test
	@Order(1)
	//@RepeatedTest(5)
	public void calculateBMI_ShouldReturnExpectedValue() {
		BMI bmi = new BMICalculator();

		//given
		Person p = new Person(70, 1.7);
		
		//when
		double actualbmi = bmi.calculateBMI(p);
		
		//then
		double expectedbmi = 24;
		assertEquals(expectedbmi, actualbmi);
	}
	
	@Test
	@Order(2)
	public void calculateBMI_ShouldThrowBMIException() {
		BMI bmi = new BMICalculator();

		//given
		Person p = new Person(0, 1.7);
		
		//when
		Executable e = ()-> bmi.calculateBMI(p);
		
		//then
		assertThrows(BMIException.class, e);
	}

	@Test
	@Order(3)
	public void isDietRecommended_ShouldReturnTrue() {
		BMI bmi = new BMICalculator();

		//given
		Person p = new Person(80, 1.7);
		
		//when
		boolean recommended = bmi.isDietRecommended(p);
		
		//then
		assertTrue(recommended);
	}
	
	@Test
	@Order(4)
	public void isDietRecommended_ShouldReturnFalse() {
		BMI bmi = new BMICalculator();

		//given
		Person p = new Person(70, 1.7);
		
		//when
		boolean recommended = bmi.isDietRecommended(p);
		
		//then
		assertFalse(recommended);
	}
	
	@Test
	public void getBMIValues_ShouldReturn_ExpectedResult() {
		BMI bmi = new BMICalculator();

		//given
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(70, 1.7));
		persons.add(new Person(90, 1.7));
		persons.add(new Person(120, 1.7));
		
		//when
		double[] actual = bmi.getBMIValues(persons);
		
		//then
		double[] expected = {24, 31, 42};
		assertArrayEquals(expected, actual, 0);
	}
	
	@Test
	public void findPersonWithLowestBMI_ShouldReturn_ExpectedResult() {
		BMI bmi = new BMICalculator();

		//given
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(70, 1.7));
		persons.add(new Person(90, 1.7));
		persons.add(new Person(120, 1.7));
		
		//when
		Person actual = bmi.findPersonWithLowestBMI(persons);
		
		//then
		Person expected = persons.get(0);
		assertEquals(expected, actual);
		
	}
	
}
