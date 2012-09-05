package com.ibm.btt.test.conditionmapping.fvt;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ibm.btt.test.conditionmapping.fvt.index.IndexCondition;
import com.ibm.btt.test.conditionmapping.fvt.normal.TestNormalCondition;
import com.ibm.btt.test.conditionmapping.fvt.wildcard.WildcardAndIndexCondition;
import com.ibm.btt.test.conditionmapping.fvt.wildcard.WildcardAppend;
import com.ibm.btt.test.conditionmapping.fvt.wildcard.WildcardCommonCondition;
import com.ibm.btt.test.conditionmapping.fvt.wildcard.WildcardCondition;




@RunWith(Suite.class)
@Suite.SuiteClasses(
		{ 
			// normal
			TestNormalCondition.class, 
			// wildcard
			WildcardAndIndexCondition.class,
			WildcardAppend.class,
			WildcardCommonCondition.class,
			WildcardCondition.class,
			// index 
			IndexCondition.class,
			
		}
)
public class TestConditionalSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("All positive testing cases.");
		return suite;
	}

}
