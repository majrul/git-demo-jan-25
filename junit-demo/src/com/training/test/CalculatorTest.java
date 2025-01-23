package com.training.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

import com.training.demo.Calculator;

public class CalculatorTest {

	@Test
	void test_if_add_is_working_or_not() {
		//given
		Calculator c = new Calculator();
		
		//when
		int actualResult = c.add(10, 20);
		int expectedResult = 30;
		
		//then
		assertEquals(expectedResult, actualResult);
		
		assertThat(expectedResult, is(equalTo(actualResult)));
		
		// NO NO NO NO NO NO NO
		// no println in our test cases
		//System.out.println(result);
		
		/*if(result == 30)
			the test case should pass
		else
			the test case should fail*/	
	}

	@Test
	void test_if_sub_is_working_or_not() {
		Calculator c = new Calculator();
		int actualResult = c.sub(10, 20);
		int expectedResult = -10;
		
		assertEquals(expectedResult, actualResult);
	}

}
