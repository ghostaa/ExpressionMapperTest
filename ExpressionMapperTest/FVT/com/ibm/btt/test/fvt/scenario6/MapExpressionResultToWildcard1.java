package com.ibm.btt.test.fvt.scenario6;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DSEInvalidRequestException;
import com.ibm.btt.base.DSEObjectNotFoundException;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class MapExpressionResultToWildcard1 extends CommonTestCase {
	/**
	 * 
	* @Title: testConstantToMultiLevelIColl 
	* @Description:   CONSTANT to ICOLL0.*.DataElement
	* void    
	* Mar 16, 2012 2:49:54 PM
	* TODO
	 */
	@Test
	public void testConstantToMultiLevelIColl() {
		try {
			Context source = getContextByName("OperSixLevelICollCtxKCollCtx");
			Context target = getContextByName("OperSixLevelICollCtxKCollCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("testConstantToMultiLevelICollFmt");

			target.getKeyedCollection().setDynamic(true);
			IndexedCollection iColl=(IndexedCollection) target.getElementAt("OperL1IColl");
			createSixLevelICollStructure(iColl);
			fmt.mapContents(source, target);
			Assert.assertEquals(
					"aaa",
					target.getValueAt("OperL1IColl.0.OperatorTypedDataRec.StringFieldResult"));
			Assert.assertEquals(
					127,
					target.getValueAt("OperL1IColl.0.OperL2IColl.0.OperatorTypedDataRec.IntegerFieldResult"));
			Assert.assertEquals(
					new BigDecimal("666").setScale(9),
					target.getValueAt("OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.BDFieldResult"));
			Assert.assertEquals(
					new BigInteger("777"),
					target.getValueAt("OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperL4IColl.0.OperatorTypedDataRec.BIFieldResult"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case ["
					+ getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}

	}
	
	/**
	 * 
	* @Title: testtestDataElementToMultiLevelIColl 
	* @Description:   DataElement to ICOLL0.*.DataElement 
	* void    
	* Mar 16, 2012 2:49:47 PM
	* TODO
	 */
	@Test
	public void testDataElementToMultiLevelIColl() {
		try {
			
			String testString1="aaa";
			String testString2="bbb";
			String testString3="ccc";
			Integer testInteger1=111;
			Integer testInteger2=222;
			Integer testInteger3=333;
			Context source = getContextByName("MixICollKCollDataFaildCtx");
			Context target = getContextByName("MixICollKCollDataFaildCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("testDataElementToMultiLevelICollFmt");

			source.getKeyedCollection().setDynamic(true);
			KeyedCollection kCollRoot=(KeyedCollection) target.getElementAt("MixICollKCollDataFaildKColl");
			IndexedCollection iColl=(IndexedCollection)kCollRoot.getElementAt("OperL1IColl");
			createSixLevelICollStructure(iColl);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldOne",testString1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.IntegerFieldOne",testInteger1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperatorTypedDataRec.StringFieldOne",testString2);
			source.setValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperatorTypedDataRec.IntegerFieldOne",testInteger2);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperatorTypedDataRec.StringFieldOne",testString3);
			source.setValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperatorTypedDataRec.IntegerFieldOne",testInteger3);
			
			fmt.mapContents(source, target);
			Assert.assertEquals(testString1,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.StringFieldResult"));
			Assert.assertEquals(testInteger1,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.IntegerFieldResult"));
			Assert.assertEquals(testString2,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperatorTypedDataRec.StringFieldResult"));
			Assert.assertEquals(testInteger2,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperatorTypedDataRec.IntegerFieldResult"));
			Assert.assertEquals(testString3,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperatorTypedDataRec.StringFieldOne"));
			Assert.assertEquals(testInteger3,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperatorTypedDataRec.IntegerFieldOne"));
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case ["
					+ getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}

	}
	
	/**
	 * 
	* @Title: testDataElementToICollDataElement 
	* @Description:   Function(DataElement , DataElement…) to ICOLL0.*.DataElement -->
	* void    
	* Mar 16, 2012 4:51:05 PM
	* TODO
	 */
	@Test
	public void testDataElementToICollDataElement() {
		try {
			
			String testString1="aaa";
			String testString2="bbb";
			String testString3="ccc";
			Integer testInteger1=111;
			Integer testInteger2=222;
			Context source = getContextByName("MixICollKCollDataFaildCtx");
			Context target = getContextByName("MixICollKCollDataFaildCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("testDataElementToICollDataElementFmt");

			source.getKeyedCollection().setDynamic(true);
			KeyedCollection kCollRoot=(KeyedCollection) target.getElementAt("MixICollKCollDataFaildKColl");
			IndexedCollection iColl=(IndexedCollection)kCollRoot.getElementAt("OperL1IColl");
			createSixLevelICollStructure(iColl);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldOne",testString1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldTwo",testString2);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldResult",testString3);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.IntegerFieldOne",testInteger1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.IntegerFieldTwo",testInteger2);
			
			fmt.mapContents(source, target);
			Assert.assertEquals(testInteger1+testInteger2,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.IntegerFieldResult"));
			Assert.assertEquals(testString1+"-"+testString2+"-"+testString3,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.StringFieldResult"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case ["
					+ getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}

	}
	/**
	 * 
	* @Title: testDataElementOperatorToICollDataElement 
	* @Description:   DataElement Operator DataElement to  ICOLL0.*.DataElement
	* void    
	* Mar 16, 2012 5:01:58 PM
	* TODO
	 */
	@Test
	public void testDataElementOperatorToICollDataElement() {
		try {
			
			String testString1="aaa";
			String testString2="bbb";
			Integer testInteger1=111;
			Integer testInteger2=222;
			BigInteger bigInteger1=new BigInteger("20");
			BigInteger bigInteger2=new BigInteger("30");
			BigDecimal bigDecimal1=new BigDecimal("50");
			BigDecimal bigDecimal2=new BigDecimal("5");
 			Context source = getContextByName("MixICollKCollDataFaildCtx");
			Context target = getContextByName("MixICollKCollDataFaildCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("testDataElementOperatorToICollDataElementFmt");

			source.getKeyedCollection().setDynamic(true);
			KeyedCollection kCollRoot=(KeyedCollection) target.getElementAt("MixICollKCollDataFaildKColl");
			IndexedCollection iColl=(IndexedCollection)kCollRoot.getElementAt("OperL1IColl");
			createSixLevelICollStructure(iColl);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldOne",testString1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldTwo",testString2);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.IntegerFieldOne",testInteger1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.IntegerFieldTwo",testInteger2);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BIFieldOne",bigInteger1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BIFieldTwo",bigInteger2);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BDFieldOne",bigDecimal1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BDFieldTwo",bigDecimal2);
			
			
			fmt.mapContents(source, target);
			Assert.assertEquals(testInteger1 - testInteger2,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.IntegerFieldResult"));
			Assert.assertEquals(testString1 + testString2,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.StringFieldResult"));
			Assert.assertEquals(bigInteger1.multiply(bigInteger2),
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.BIFieldResult"));
			Assert.assertEquals(bigDecimal1.divide(bigDecimal2).setScale(9),
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.BDFieldResult"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case ["
					+ getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}

	}
	/**
	 * 
	* @Title: testDataElementOperatorDataElementOperatorToICollDataElement 
	* @Description:   DataElement Operator DataElement Operator DataElement ... to  ICOLL0.*.DataElement
	* void    
	* Mar 16, 2012 5:03:02 PM
	* TODO
	 */
	@Test
	public void testDataElementOperatorDataElementOperatorToICollDataElement() {
		try {
			
			String testString1="aaa";
			String testString2="bbb";
			String testString3="ccc";
			Integer testInteger1=111;
			Integer testInteger2=222;
			Integer testInteger3=333;
			BigInteger bigInteger1=new BigInteger("20");
			BigInteger bigInteger2=new BigInteger("30");
			BigInteger bigInteger3=new BigInteger("30");
			BigDecimal bigDecimal1=new BigDecimal("50");
			BigDecimal bigDecimal2=new BigDecimal("5");
			BigDecimal bigDecimal3=new BigDecimal("5");
 			Context source = getContextByName("MixICollKCollDataFaildCtx");
			Context target = getContextByName("MixICollKCollDataFaildCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("testDataElementOperatorDataElementOperatorToICollDataElementFmt");

			source.getKeyedCollection().setDynamic(true);
			KeyedCollection kCollRoot=(KeyedCollection) target.getElementAt("MixICollKCollDataFaildKColl");
			IndexedCollection iColl=(IndexedCollection)kCollRoot.getElementAt("OperL1IColl");
			createSixLevelICollStructure(iColl);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldOne",testString1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldTwo",testString2);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.StringFieldResult",testString3);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.IntegerFieldOne",testInteger1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.IntegerFieldTwo",testInteger2);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.IntegerFieldResult",testInteger3);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BIFieldOne",bigInteger1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BIFieldTwo",bigInteger2);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BIFieldResult",bigInteger3);
			
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BDFieldOne",bigDecimal1);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BDFieldTwo",bigDecimal2);
			source.setValueAt("MixICollKCollDataFaildKColl.OperatorTypedDataRec.BDFieldResult",bigDecimal3);
			
			
			fmt.mapContents(source, target);
			Assert.assertEquals(testString1 + testString2 + testString3,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.StringFieldResult"));
			Assert.assertEquals(testInteger1 - testInteger2 + testInteger3,
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.IntegerFieldResult"));
			Assert.assertEquals(bigInteger1.multiply(bigInteger2).divide(bigInteger3),
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.BIFieldResult"));
			Assert.assertEquals(bigDecimal1.divide(bigDecimal2).add(bigDecimal3).setScale(9),
					target.getValueAt("MixICollKCollDataFaildKColl.OperL1IColl.0.OperL2IColl.0.OperL3IColl.0.OperatorTypedDataRec.BDFieldResult"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case ["
					+ getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}

	}
	/**
	 * 
	* @Title: createSixLevelICollStructure 
	* @Description: The method used only to OperL1IColl IColl.
	* @param iColl
	* @throws DSEInvalidRequestException
	* @throws DSEObjectNotFoundException  
	* void    
	* Mar 16, 2012 3:35:12 PM
	* TODO
	 */
	private void createSixLevelICollStructure(IndexedCollection iColl) throws DSEInvalidRequestException, DSEObjectNotFoundException{
		IndexedCollection iCollTemp = null;
		KeyedCollection kCollTemp = null;
		for (int i = 1; i <= 6; i++) {
			if (iCollTemp==null) {
				iCollTemp=iColl;
				kCollTemp=(KeyedCollection)iCollTemp.createElement(true);
				
			}else{
				iCollTemp = (IndexedCollection) kCollTemp.getElementAt("OperL"+i+"IColl");
				kCollTemp=(KeyedCollection)iCollTemp.createElement(true);
			}
			iCollTemp.addElement(kCollTemp);
		}

	}

	/*@Test
	public void a() throws Exception{

		IndexedCollection iColl=(IndexedCollection)DataElement.readObject("OperL1IColl");
		KeyedCollection kColl=(KeyedCollection)iColl.createElement(true);
		kColl.setDynamic(true);
		kColl.setValueAt("OperatorTypedDataRec.StringFieldOne", "aaa");
		kColl.setValueAt("OperatorTypedDataRec.StringFieldTwo", "bbb");
		kColl.setValueAt("OperatorTypedDataRec.StringFieldResult", "aaa");
		iColl.addElement(kColl);
		CustomTestFunction.filterICollByKeywords(iColl, "aaa");
		System.out.println();
	}*/
}
