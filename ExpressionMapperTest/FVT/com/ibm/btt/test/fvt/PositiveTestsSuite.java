package com.ibm.btt.test.fvt;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ibm.btt.test.fvt.scenario1.ExpressionMapperTest;
import com.ibm.btt.test.fvt.scenario1.TypedDataMappingTest;
import com.ibm.btt.test.fvt.scenario2.GlobalFuncTest;
import com.ibm.btt.test.fvt.scenario2Appendix.CompareDifferentEngineBoolean;
import com.ibm.btt.test.fvt.scenario2Appendix.CompareDifferentEngineBooleanMvel;
import com.ibm.btt.test.fvt.scenario2Appendix.CompareDifferentEngineNumber;
import com.ibm.btt.test.fvt.scenario2Appendix.CompareDifferentEngineNumberMvel;
import com.ibm.btt.test.fvt.scenario2Appendix.CompareDifferentEngineString;
import com.ibm.btt.test.fvt.scenario2Appendix.CompareDifferentEngineStringMvel;
import com.ibm.btt.test.fvt.scenario2Appendix.ExpressionTest;
import com.ibm.btt.test.fvt.scenario2Appendix.ExpressionWithOctHexNumberTest;
import com.ibm.btt.test.fvt.scenario3.DirectWildcardMappingTest1;
import com.ibm.btt.test.fvt.scenario3.DirectWildcardMappingTest2;
import com.ibm.btt.test.fvt.scenario4.ComputingWildcardMapping2;
import com.ibm.btt.test.fvt.scenario4.FunctionWildcardMappingTest1;
import com.ibm.btt.test.fvt.scenario5.ComputingWildcardWithOpersMapping1;
import com.ibm.btt.test.fvt.scenario5.ComputingWildcardWithOpersMapping2;
import com.ibm.btt.test.fvt.scenario6.ExpressionToWildcardMapping;
import com.ibm.btt.test.fvt.scenario6.MapExpressionResultToWildcard1;
import com.ibm.btt.test.fvt.scenario7.ComputeWildcardWithFuncToFieldMapping1;
import com.ibm.btt.test.fvt.scenario8.AppendValueTest;
import com.ibm.btt.test.fvt.scenario8.AppendValueTest2;
import com.ibm.btt.test.fvt.scenario9.AsteriskToAsteriskMapping;


@RunWith(Suite.class)
@Suite.SuiteClasses(
		{ 
			// scenario 1
			ExpressionMapperTest.class, 
			TypedDataMappingTest.class, 
			// scenario 2
			GlobalFuncTest.class,
			ExpressionTest.class,
			ExpressionWithOctHexNumberTest.class,
			//to test different from js engine and Mvel engine
			//js engine
			CompareDifferentEngineString.class,
			CompareDifferentEngineNumber.class,
			CompareDifferentEngineBoolean.class,
			//mvel engine
			/*CompareDifferentEngineStringMvel.class,
			CompareDifferentEngineNumberMvel.class,
			CompareDifferentEngineBooleanMvel.class,*/
			
			
			
			
			// scenario 3
			DirectWildcardMappingTest1.class,
			DirectWildcardMappingTest2.class,
			// scenario 4
			ComputingWildcardMapping2.class,
			FunctionWildcardMappingTest1.class,
			// scenario 5
			ComputingWildcardWithOpersMapping1.class,
			ComputingWildcardWithOpersMapping2.class,
			// scenario 6
			MapExpressionResultToWildcard1.class,
			ExpressionToWildcardMapping.class,
			// scenario 7
			ComputeWildcardWithFuncToFieldMapping1.class,
			// scenario 8 
			AppendValueTest.class,
			AppendValueTest2.class,
			// scenario 9
			AsteriskToAsteriskMapping.class
		}
)
public class PositiveTestsSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("All positive testing cases.");
		return suite;
	}

}
