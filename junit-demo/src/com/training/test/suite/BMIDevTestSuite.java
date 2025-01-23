package com.training.test.suite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.training.test.BMITest4;

@Suite
//@SelectClasses({CalculatorTest.class, BMITest.class, BMITest2.class })
@SelectClasses(BMITest4.class)
@IncludeTags("dev")
public class BMIDevTestSuite {

}
