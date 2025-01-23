package com.training.test.suite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.training.test.BMITest4;

@Suite
@SelectClasses({BMITest4.class })
@IncludeTags("qa")
public class BMIQATestSuite {

}
