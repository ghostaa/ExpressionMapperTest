package com.ibm.btt.allmappertest;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ibm.btt.test.conditionmapping.fvt.TestConditionalSuite;
import com.ibm.btt.test.fvt.NegativeTestsSuite;
import com.ibm.btt.test.fvt.PositiveTestsSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		//Wildcard test
		//mapping test
		//NegativeTestsSuite.class,
		PositiveTestsSuite.class,
		//conditional mapping test
		TestConditionalSuite.class 
		
})


public class AllMappingTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("All mapping testing cases.");
		return suite;
	}
}
