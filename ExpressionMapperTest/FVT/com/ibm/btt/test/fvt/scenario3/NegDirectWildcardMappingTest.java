package com.ibm.btt.test.fvt.scenario3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DSEInvalidArgumentException;
import com.ibm.btt.base.DSEInvalidRequestException;
import com.ibm.btt.base.DSEObjectNotFoundException;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegDirectWildcardMappingTest extends CommonTestCase {
	protected boolean isLogVerificationEnabled() {
		return true;
	}

	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}

	/**
	 * ICOLL.*.ICOLL.*.ELE to ICOLL.*.ELE
	 */
	@Test
	public void testWildCardNumberMissmatchMapping() {
		try {
			Context source = getContextByName("ThreeLevelICollCtxt");
			Context target = getContextByName("ThreeLevelICollCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_WildcardNotMatchFormat");

			// initialize the source context
			initializeTheIColl((IndexedCollection) source.getElementAt("SrcIColl"));

			// mapping the values, check if there is error info in logs
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E014: invalid wildcard mapping: Invalid mapping configuration, wildcard number in source [SrcIColl.*.SrcNestIColl.*.SrcInnerBDField] and target [DestIColl.*.SrcXMLGCField] are not consistent.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map from=\"SrcIColl.*.SrcNestIColl.*.SrcInnerBDField\" to=\"DestIColl.*.SrcXMLGCField\" />] from context [ThreeLevelICollCtxt] to [ThreeLevelICollCtxt]:Invalid mapping configuration, wildcard number in source [SrcIColl.*.SrcNestIColl.*.SrcInnerBDField] and target [DestIColl.*.SrcXMLGCField] are not consistent.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * ICOLL.*.*.*.*.*.KCOLL to ICOLL.*.*.*.*.*.ICOLL
	 */
	@Test
	public void testLeafDataNotMatchMapping() {
		try {
			Context source = getContextByName("FiveDimensionalMatrixCtxt");
			Context target = getContextByName("FiveDimensionalMatrixCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_LeafDataNotMatchFormat");
			initializeTheSourceContext(source);

			// mapping the values, check if there is error info in logs
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'TypedDataInnerKColl' with element type 'KeyedCollection' to 'TypedDataInnerIColl' with element type 'IndexedCollection'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map from=\"SrcFiveDimensionalMatrix.*.*.*.*.*.TypedDataInnerKColl\" to=\"FiveDimensionalMatrix.*.*.*.*.*.TypedDataInnerIColl\" />] from context [FiveDimensionalMatrixCtxt] to [FiveDimensionalMatrixCtxt]:From data element [TypedDataInnerKColl](KeyedCollection) is not match with the To data element [TypedDataInnerIColl](IndexedCollection).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * ICOLL.* to ICOLL.* while the leaf element are of different data types
	 */
	@Test
	public void testLeafDataIsAstrriksButNotMatchMapping() {
		try {
			Context source = getContextByName("LeafDataNotMatchCtxt");
			Context target = getContextByName("LeafDataNotMatchCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_LeafDataIsAstrriksButNotMatchFormat");

			// mapping the values, check if there is error info in logs
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'KC1' with element type 'KeyedCollection' to 'F1' with element type 'DataField'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map from=\"L1IK.*\" to=\"L1IF.*\" />] from context [LeafDataNotMatchCtxt] to [LeafDataNotMatchCtxt]:From data element [KC1](KeyedCollection) is not match with the To data element [F1](DataField).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);

			fmt = getFormatByName("USPT_LeafDataIsAstrriksButNotMatchFormat2");

			// mapping the values, check if there is error info in logs
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			logContents = getLogContentsInLines();
			line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'KC1' with element type 'KeyedCollection' to 'F1' with element type 'DataField'.";
			lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map from=\"L1IK.*\" to=\"L1IF.*\" />] from context [LeafDataNotMatchCtxt] to [LeafDataNotMatchCtxt]:From data element [KC1](KeyedCollection) is not match with the To data element [F1](DataField).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * ICOLL.* to ICOLL.* while the leaf element are of different data types
	 */
	@Test
	public void testLeafDataIsAstrriksButNotMatchMapping2() {
		try {
			Context source = getContextByName("LeafDataNotMatchCtxt");
			Context target = getContextByName("LeafDataNotMatchCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_LeafDataIsAstrriksButNotMatchFormat2");

			// mapping the values, check if there is error info in logs
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'KC1' with element type 'KeyedCollection' to 'L2IF' with element type 'IndexedCollection'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map from=\"L1IK.*\" to=\"L1II.*\" />] from context [LeafDataNotMatchCtxt] to [LeafDataNotMatchCtxt]:From data element [KC1](KeyedCollection) is not match with the To data element [L2IF](IndexedCollection).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	private void initializeTheSourceContext(Context source) throws DSEInvalidRequestException, DSEInvalidArgumentException,
			DSEObjectNotFoundException, ParseException, DatatypeConfigurationException {
		source.getKeyedCollection().setDynamic(true);
		// initialize the source context values
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					for (int ii = 0; ii < 3; ii++) {
						for (int jj = 0; jj < 2; jj++) {
							source.setValueAt("SrcFiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".PlainField", ""
									+ i + j + k + ii + jj);
							source.setValueAt("SrcFiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".BIField",
									new BigInteger("8192" + i + j + k + ii + jj));
							source.setValueAt("SrcFiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".XMLGCField",
									parseStringToXMLGregorianCalendar("201" + i + "-0" + (j + 1) + "-1" + (k + 1)));
							source.setValueAt("SrcFiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".BAField", ("" + i
									+ j + k + ii + jj).getBytes());
							KeyedCollection innerKColl = (KeyedCollection) source.getElementAt("SrcFiveDimensionalMatrix." + i + "." + j
									+ "." + k + "." + ii + "." + jj + ".TypedDataInnerKColl");
							innerKColl.setValueAt("StringField", String.valueOf(i * j * k + ii + jj));
							innerKColl.setValueAt("BIField", new BigInteger(String.valueOf(i * j * k + ii + jj)));
							IndexedCollection innerIColl = (IndexedCollection) source.getElementAt("SrcFiveDimensionalMatrix." + i + "." + j
									+ "." + k + "." + ii + "." + jj + ".TypedDataInnerIColl");
							for (int iii = 0; iii < 2; iii++) {
								KeyedCollection aKColl = (KeyedCollection) innerIColl.createElement(true);
								aKColl.setValueAt("StringField", String.valueOf(i * j * k + ii + jj));
								aKColl.setValueAt("BDField", new BigDecimal(String.valueOf(i * j * k + ii + jj)));
								innerIColl.addElement(aKColl);
							}
						}
					}
				}
			}
		}
	}

	private void initializeTheIColl(IndexedCollection SrcIColl) throws ParseException, DatatypeConfigurationException,
			DSEInvalidArgumentException, DSEObjectNotFoundException, DSEInvalidRequestException {
		XMLGregorianCalendar aXmlGc = parseStringToXMLGregorianCalendar("1983-01-21");
		BigDecimal aAcctBalance = new BigDecimal("1024.128"), bAcctBalance = new BigDecimal("2048.1024");
		byte[] aArray = "ILOVEBTT".getBytes(), bArray = "YOULOVEBTT".getBytes();
		Object aPF = "THINK", bPF = "PAD";
		BigInteger aBI = new BigInteger("1048576"), bBI = new BigInteger("2048576");

		KeyedCollection k10 = (KeyedCollection) SrcIColl.createElement(false);
		k10.setDynamic(true);
		k10.setValueAt("SrcXMLGCField", aXmlGc);
		IndexedCollection SrcNestIColl0 = (IndexedCollection) k10.getElementAt("SrcNestIColl");
		KeyedCollection k100 = (KeyedCollection) SrcNestIColl0.createElement(true);
		k100.setValueAt("SrcInnerBDField", aAcctBalance);
		k100.setValueAt("SrcInnerBAField", aArray);

		IndexedCollection SrcInnerNestIColl00 = (IndexedCollection) k100.getElementAt("SrcInnerNestIColl");
		KeyedCollection k1000 = (KeyedCollection) SrcInnerNestIColl00.createElement(true);
		KeyedCollection k1001 = (KeyedCollection) SrcInnerNestIColl00.createElement(true);
		k1000.setValueAt("SrcPlainField", aPF);
		k1000.setValueAt("SrcBIField", aBI);
		k1001.setValueAt("SrcPlainField", bPF);
		k1001.setValueAt("SrcBIField", bBI);
		SrcInnerNestIColl00.addElement(k1000);
		SrcInnerNestIColl00.addElement(k1001);
		KeyedCollection k101 = (KeyedCollection) SrcNestIColl0.createElement(true);
		k101.setValueAt("SrcInnerBDField", bAcctBalance);
		k101.setValueAt("SrcInnerBAField", bArray);
		IndexedCollection SrcInnerNestIColl01 = (IndexedCollection) k101.getElementAt("SrcInnerNestIColl");
		KeyedCollection k1010 = (KeyedCollection) SrcInnerNestIColl01.createElement(true);
		KeyedCollection k1011 = (KeyedCollection) SrcInnerNestIColl01.createElement(true);
		k1010.setValueAt("SrcPlainField", aPF);
		k1010.setValueAt("SrcBIField", aBI);
		k1010.setValueAt("SrcStringField", "FT");
		k1011.setValueAt("SrcPlainField", bPF);
		k1011.setValueAt("SrcBIField", bBI);
		SrcInnerNestIColl01.addElement(k1010);
		SrcInnerNestIColl01.addElement(k1011);
		SrcNestIColl0.addElement(k100);
		SrcNestIColl0.addElement(k101);
		SrcIColl.addElement(k10);
	}

}
