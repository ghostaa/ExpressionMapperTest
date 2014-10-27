package com.ibm.btt.test.fvt.scenario2Appendix;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class CompareDifferentEngine   extends CommonTestCase{
	@Test
	public void testFrontStringSimpleTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("frontStringSimpleTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals("abcnull", target.getValueAt("testString1"));
			Assert.assertEquals("NaN", target.getValueAt("testString2"));
			Assert.assertEquals("NaN", target.getValueAt("testString3"));
			Assert.assertEquals("NaN", target.getValueAt("testString4"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			Assert.assertEquals(null, target.getValueAt("testNumber4"));
			
			Assert.assertEquals(null, target.getValueAt("testBoolean1"));
			Assert.assertEquals(null, target.getValueAt("testBoolean2"));
			Assert.assertEquals(null, target.getValueAt("testBoolean3"));
			Assert.assertEquals(null, target.getValueAt("testBoolean4"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testRearStringSimpleTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("rearStringSimpleTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals("nullabc", target.getValueAt("testString1"));
			Assert.assertEquals("NaN", target.getValueAt("testString2"));
			Assert.assertEquals("NaN", target.getValueAt("testString3"));
			Assert.assertEquals("NaN", target.getValueAt("testString4"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	
	
	
}
