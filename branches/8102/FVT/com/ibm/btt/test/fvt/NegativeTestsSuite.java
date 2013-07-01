package com.ibm.btt.test.fvt;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ibm.btt.test.fvt.scenario1.NegDirectMapping;
import com.ibm.btt.test.fvt.scenario2.NegDirectComputingMapping;
import com.ibm.btt.test.fvt.scenario3.NegDirectWildcardMappingTest;
import com.ibm.btt.test.fvt.scenario4.NegComputingWildcardMapping;
import com.ibm.btt.test.fvt.scenario5.NegComputingWildcardWithOpersMapping;
import com.ibm.btt.test.fvt.scenario6.NegExpressionToWildcardMapping;
import com.ibm.btt.test.fvt.scenario7.NegComputeWildcardWithFuncMapping;
import com.ibm.btt.test.fvt.scenario8.NegInfiniteAppendMapping;
import com.ibm.btt.test.fvt.scenario9.NegAsteriskToAsteriskMapping;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		// scenario 1
		NegDirectMapping.class,
		// scenario 2
		NegDirectComputingMapping.class,
		// scenario 3
		NegDirectWildcardMappingTest.class,
		// scenario 4
		NegComputingWildcardMapping.class,
		// scenario 5
		NegComputingWildcardWithOpersMapping.class,
		// scenario 6
		NegExpressionToWildcardMapping.class,
		// scenario 7
		NegComputeWildcardWithFuncMapping.class,
		// scenario 8 StackOverFlowError
		NegInfiniteAppendMapping.class,
		// scenario 9
		NegAsteriskToAsteriskMapping.class })
public class NegativeTestsSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("All negative testing cases.");
		return suite;
	}

}
