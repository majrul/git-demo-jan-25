package com.training.test.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.training.test.BMITest;
import com.training.test.BMITest2;
import com.training.test.BMITest3;

@Suite
@SelectClasses({ BMITest.class, BMITest2.class, BMITest3.class })
public class BMITestSuite {

}
