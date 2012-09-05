package com.ibm.btt.mapper.test;

import com.ibm.btt.mapper.test.impl.Basic;
import com.ibm.btt.mapper.test.impl.Error;
import com.ibm.btt.mapper.test.impl.Expression;
import com.ibm.btt.mapper.test.impl.ExpressionWildardTarget;
import com.ibm.btt.mapper.test.impl.Type;
import com.ibm.btt.mapper.test.impl.Wildcard;
import com.ibm.btt.mapper.test.impl.WildcardAppend;
import com.ibm.btt.mapper.test.impl.WildcardFunctionFieldTarget;
import com.ibm.btt.mapper.test.impl.WildcardFunctionICollTarget;
import com.ibm.btt.mapper.test.impl.WildcardFunctionICollTargetSelf;
import com.ibm.btt.mapper.test.impl.WildcardOperator;
import com.ibm.btt.mapper.test.impl.WildcardOperatorSelf;
import com.ibm.btt.mapper.test.impl.WildcardSelf;

import junit.framework.Test;
import junit.framework.TestSuite;

public class UnitTestCases {

	public static Test suite() {
		TestSuite suite = new TestSuite(UnitTestCases.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(Type.class);
		suite.addTestSuite(WildcardOperator.class);
		suite.addTestSuite(WildcardOperatorSelf.class);
		suite.addTestSuite(ExpressionWildardTarget.class);
		suite.addTestSuite(Error.class);
		suite.addTestSuite(WildcardFunctionFieldTarget.class);
		suite.addTestSuite(Basic.class);
		suite.addTestSuite(WildcardFunctionICollTarget.class);
		suite.addTestSuite(WildcardFunctionICollTargetSelf.class);
		suite.addTestSuite(WildcardAppend.class);
		suite.addTestSuite(Expression.class);
		suite.addTestSuite(Wildcard.class);
		suite.addTestSuite(WildcardSelf.class);
		//$JUnit-END$
		return suite;
	}

}
