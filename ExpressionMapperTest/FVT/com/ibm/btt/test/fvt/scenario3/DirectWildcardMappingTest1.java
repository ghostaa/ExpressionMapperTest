package com.ibm.btt.test.fvt.scenario3;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataElement;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class DirectWildcardMappingTest1 extends CommonTestCase {
	@Test
	public void testOneLvlIColl() {
		try {
			Context from = ContextFactory.createContext("SrcOneLvlICollKCollCtx");
			Context to = ContextFactory.createContext("DestOneLvlICollKCollCtx");
			IndexedCollection iColl = (IndexedCollection) from.getElementAt("SrcOneLvlIColl");
			KeyedCollection kColl = (KeyedCollection) DataElement.readObject("SrcTypedDataRec");
			kColl.setValueAt("SrcPlainField", "this plain field");
			kColl.setValueAt("SrcStringField", "this string field");
			kColl.setValueAt("SrcBDField", new BigDecimal(123456789));
			kColl.setValueAt("SrcBIField", new BigInteger("987654321"));
			kColl.setValueAt("SrcXMLGCField", parseStringToXMLGregorianCalendar("2012-12-12"));
			kColl.setValueAt("SrcBAField", "12ab".getBytes());
			iColl.addElement(kColl);
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("OneLvlICollFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals("this plain field", to.getValueAt("DestOneLvlIColl.0.PlainField"));
			Assert.assertEquals("this string field", to.getValueAt("DestOneLvlIColl.0.StringField"));
			Assert.assertEquals(new BigDecimal(123456789), to.getValueAt("DestOneLvlIColl.0.BDField"));
			Assert.assertEquals(new BigInteger("987654321"), to.getValueAt("DestOneLvlIColl.0.BIField"));
			Assert.assertEquals(parseStringToXMLGregorianCalendar("2012-12-12"), to.getValueAt("DestOneLvlIColl.0.XMLGCField"));
			Assert.assertEquals("12ab", new String((byte[]) to.getValueAt("DestOneLvlIColl.0.BAField")));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testThreeLvlIColl() {
		try {

			Context from = ContextFactory.createContext("SrcThreeLvlICollKCollCtx");
			Context to = ContextFactory.createContext("DestThreeLvlICollKCollCtx");

			String plainField3 = "SrcPlainField3";
			String stringField1 = "oneStringField";
			String stringField2 = "twoStringField";
			String stringField3 = "threeStringField";
			XMLGregorianCalendar calendar1 = parseStringToXMLGregorianCalendar("2012-12-12");
			XMLGregorianCalendar calendar3 = parseStringToXMLGregorianCalendar("2012-12-32");
			BigDecimal bigDecimal1 = new BigDecimal(1234567891);
			BigDecimal bigDecimal2 = new BigDecimal(1234567892);
			BigDecimal bigDecimal3 = new BigDecimal(1234567893);
			byte[] byte2 = "22ab".getBytes();
			byte[] byte3 = "32ab".getBytes();
			BigInteger bigInteger13 = new BigInteger("111113");

			// first level icoll
			IndexedCollection srcThreeLvlIColl = (IndexedCollection) from.getElementAt("SrcThreeLvlIColl");
			KeyedCollection srcNestKColl = (KeyedCollection) srcThreeLvlIColl.createElement(true);
			srcNestKColl.setValueAt("SrcStringField", stringField1);
			srcNestKColl.setValueAt("SrcBDField", bigDecimal1);
			srcNestKColl.setValueAt("SrcXMLGCField", calendar1);

			// second level icoll
			IndexedCollection srcNestIColl = (IndexedCollection) srcNestKColl.getElementAt("SrcNestIColl");
			KeyedCollection srcInnerNestKColl = (KeyedCollection) srcNestIColl.createElement(true);
			srcInnerNestKColl.setValueAt("SrcInnerStringField", stringField2);
			srcInnerNestKColl.setValueAt("SrcInnerBDField", bigDecimal2);
			srcInnerNestKColl.setValueAt("SrcInnerBAField", byte2);

			// third level icoll
			IndexedCollection srcInnerNestIColl = (IndexedCollection) srcInnerNestKColl.getElementAt("SrcInnerNestIColl");
			KeyedCollection srcTypedDataRec = (KeyedCollection) srcInnerNestIColl.createElement(true);
			srcTypedDataRec.setValueAt("SrcStringField", stringField3);
			srcTypedDataRec.setValueAt("SrcPlainField", plainField3);
			srcTypedDataRec.setValueAt("SrcBDField", bigDecimal3);
			srcTypedDataRec.setValueAt("SrcBIField", bigInteger13);
			srcTypedDataRec.setValueAt("SrcBAField", byte3);
			srcTypedDataRec.setValueAt("SrcXMLGCField", calendar3);

			srcThreeLvlIColl.addElement(srcNestKColl);
			srcNestIColl.addElement(srcInnerNestKColl);
			srcInnerNestIColl.addElement(srcTypedDataRec);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("ThreeLvlICollFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(stringField1, to.getValueAt("DestThreeLvlIColl.0.StringField"));
			Assert.assertEquals(bigDecimal1, to.getValueAt("DestThreeLvlIColl.0.BDField"));
			Assert.assertEquals(calendar1, to.getValueAt("DestThreeLvlIColl.0.XMLGCField"));
			Assert.assertEquals(null, to.tryGetValueAt("DestThreeLvlIColl.0.BAField"));

			Assert.assertEquals(stringField2, to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerPlainField"));
			Assert.assertEquals(bigDecimal2, to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerBDField"));
			Assert
					.assertEquals(new String(byte2), new String((byte[]) to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerBAField")));

			Assert.assertEquals(stringField3, to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.StringField"));
			Assert.assertEquals(plainField3, to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.PlainField"));
			Assert.assertEquals(bigDecimal3, to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.BDField"));
			Assert.assertEquals(bigInteger13, to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.BIField"));
			Assert.assertEquals(new String(byte3),
					new String((byte[]) to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.BAField")));
			Assert.assertEquals(calendar3, to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.XMLGCField"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testThreeLvlICollKColl() {
		try {

			Context from = ContextFactory.createContext("SrcThreeLvlICollKCollCtx");
			Context to = ContextFactory.createContext("DestThreeLvlICollKCollCtx");

			String stringField = "stringField";
			XMLGregorianCalendar calendar = parseStringToXMLGregorianCalendar("2012-12-12");
			BigDecimal bigDecima = new BigDecimal(1234567893);
			byte[] byteArray = "22ab".getBytes();
			BigInteger bigInteger = new BigInteger("111113");

			// first level icoll
			IndexedCollection srcThreeLvlIColl = (IndexedCollection) from.getElementAt("SrcThreeLvlIColl");
			KeyedCollection srcNestKColl = (KeyedCollection) srcThreeLvlIColl.createElement(true);

			// second level icoll
			IndexedCollection srcNestIColl = (IndexedCollection) srcNestKColl.getElementAt("SrcNestIColl");
			KeyedCollection srcInnerNestKColl = (KeyedCollection) srcNestIColl.createElement(true);

			// third level icoll
			IndexedCollection srcInnerNestIColl = (IndexedCollection) srcInnerNestKColl.getElementAt("SrcInnerNestIColl");
			KeyedCollection srcTypedDataRec = (KeyedCollection) srcInnerNestIColl.createElement(true);
			KeyedCollection srcTypedDataInnerKColl = (KeyedCollection) srcTypedDataRec.getElementAt("TypedDataInnerKColl");
			srcTypedDataInnerKColl.setValueAt("StringField", stringField);
			srcTypedDataInnerKColl.setValueAt("XMLGCField", calendar);
			srcTypedDataInnerKColl.setValueAt("BDField", bigDecima);
			srcTypedDataInnerKColl.setValueAt("BIField", bigInteger);
			srcTypedDataInnerKColl.setValueAt("BAField", byteArray);

			srcThreeLvlIColl.addElement(srcNestKColl);
			srcNestIColl.addElement(srcInnerNestKColl);
			srcInnerNestIColl.addElement(srcTypedDataRec);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("ThreeLvlICollKCollFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(stringField,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.StringField"));
			Assert.assertEquals(calendar,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.XMLGCField"));
			Assert.assertEquals(bigDecima,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.BDField"));
			Assert.assertEquals(bigInteger,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.BIField"));
			Assert.assertEquals(new String(byteArray),
					new String((byte[]) to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.BAField")));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testThreeLvlICollKCollWildcard() {
		try {

			Context from = ContextFactory.createContext("SrcThreeLvlICollKCollCtx");
			Context to = ContextFactory.createContext("DestThreeLvlICollKCollCtx");

			String stringField = "stringField";
			XMLGregorianCalendar calendar = parseStringToXMLGregorianCalendar("2012-12-12");
			BigDecimal bigDecima = new BigDecimal(1234567893);
			byte[] byteArray = "22ab".getBytes();
			BigInteger bigInteger = new BigInteger("111113");

			// first level icoll
			IndexedCollection srcThreeLvlIColl = (IndexedCollection) from.getElementAt("SrcThreeLvlIColl");
			KeyedCollection srcNestKColl = (KeyedCollection) srcThreeLvlIColl.createElement(true);

			// second level icoll
			IndexedCollection srcNestIColl = (IndexedCollection) srcNestKColl.getElementAt("SrcNestIColl");
			KeyedCollection srcInnerNestKColl = (KeyedCollection) srcNestIColl.createElement(true);

			// third level icoll
			IndexedCollection SrcInnerNestIColl = (IndexedCollection) srcInnerNestKColl.getElementAt("SrcInnerNestIColl");
			KeyedCollection srcTypedDataRec = (KeyedCollection) SrcInnerNestIColl.createElement(true);
			KeyedCollection srcTypedDataInnerKColl = (KeyedCollection) srcTypedDataRec.getElementAt("TypedDataInnerKColl");
			srcTypedDataInnerKColl.setValueAt("StringField", stringField);
			srcTypedDataInnerKColl.setValueAt("XMLGCField", calendar);
			srcTypedDataInnerKColl.setValueAt("BDField", bigDecima);
			srcTypedDataInnerKColl.setValueAt("BIField", bigInteger);
			srcTypedDataInnerKColl.setValueAt("BAField", byteArray);

			srcThreeLvlIColl.addElement(srcNestKColl);
			srcNestIColl.addElement(srcInnerNestKColl);
			SrcInnerNestIColl.addElement(srcTypedDataRec);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("ThreeLvlICollKCollWildcardFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(stringField,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.StringField"));
			Assert.assertEquals(calendar,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.XMLGCField"));
			Assert.assertEquals(bigDecima,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.BDField"));
			Assert.assertEquals(bigInteger,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.BIField"));
			Assert.assertEquals(new String(byteArray),
					new String((byte[]) to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerKColl.BAField")));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testThreeLvlICollIColl() {
		try {

			Context from = ContextFactory.createContext("SrcThreeLvlICollKCollCtx");
			Context to = ContextFactory.createContext("DestThreeLvlICollKCollCtx");

			String stringField = "stringField";
			XMLGregorianCalendar calendar = parseStringToXMLGregorianCalendar("2012-12-12");
			BigDecimal bigDecima = new BigDecimal(1234567893);
			byte[] byteArray = "22ab".getBytes();
			BigInteger bigInteger = new BigInteger("111113");

			// first level icoll
			IndexedCollection srcThreeLvlIColl = (IndexedCollection) from.getElementAt("SrcThreeLvlIColl");
			KeyedCollection srcNestKColl = (KeyedCollection) srcThreeLvlIColl.createElement(true);

			// second level icoll
			IndexedCollection srcNestIColl = (IndexedCollection) srcNestKColl.getElementAt("SrcNestIColl");
			KeyedCollection SrcInnerNestKColl = (KeyedCollection) srcNestIColl.createElement(true);

			// third level icoll
			IndexedCollection srcInnerNestIColl = (IndexedCollection) SrcInnerNestKColl.getElementAt("SrcInnerNestIColl");
			KeyedCollection srcTypedDataRec = (KeyedCollection) srcInnerNestIColl.createElement(true);

			// fourth level icoll
			IndexedCollection typedDataInnerIColl = (IndexedCollection) srcTypedDataRec.getElementAt("TypedDataInnerIColl");
			KeyedCollection typedDataInnerKColl = (KeyedCollection) typedDataInnerIColl.createElement(true);

			typedDataInnerKColl.setValueAt("StringField", stringField);
			typedDataInnerKColl.setValueAt("XMLGCField", calendar);
			typedDataInnerKColl.setValueAt("BDField", bigDecima);
			typedDataInnerKColl.setValueAt("BIField", bigInteger);
			typedDataInnerKColl.setValueAt("BAField", byteArray);

			srcThreeLvlIColl.addElement(srcNestKColl);
			srcNestIColl.addElement(SrcInnerNestKColl);
			srcInnerNestIColl.addElement(srcTypedDataRec);
			typedDataInnerIColl.addElement(typedDataInnerKColl);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("ThreeLvlICollICollFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(stringField,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.StringField"));
			Assert.assertEquals(calendar,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.XMLGCField"));
			Assert.assertEquals(bigDecima,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.BDField"));
			Assert.assertEquals(bigInteger,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.BIField"));
			Assert.assertEquals(new String(byteArray),
					new String((byte[]) to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.BAField")));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testThreeLvlICollICollWildcard() {
		try {

			Context from = ContextFactory.createContext("SrcThreeLvlICollKCollCtx");
			Context to = ContextFactory.createContext("DestThreeLvlICollKCollCtx");

			String stringField = "stringField";
			XMLGregorianCalendar calendar = parseStringToXMLGregorianCalendar("2012-12-12");
			BigDecimal bigDecima = new BigDecimal(1234567893);
			byte[] byteArray = "22ab".getBytes();
			BigInteger bigInteger = new BigInteger("111113");

			// first level icoll
			IndexedCollection srcThreeLvlIColl = (IndexedCollection) from.getElementAt("SrcThreeLvlIColl");
			KeyedCollection srcNestKColl = (KeyedCollection) srcThreeLvlIColl.createElement(true);

			// second level icoll
			IndexedCollection srcNestIColl = (IndexedCollection) srcNestKColl.getElementAt("SrcNestIColl");
			KeyedCollection SrcInnerNestKColl = (KeyedCollection) srcNestIColl.createElement(true);

			// third level icoll
			IndexedCollection srcInnerNestIColl = (IndexedCollection) SrcInnerNestKColl.getElementAt("SrcInnerNestIColl");
			KeyedCollection srcTypedDataRec = (KeyedCollection) srcInnerNestIColl.createElement(true);

			// fourth level icoll
			IndexedCollection typedDataInnerIColl = (IndexedCollection) srcTypedDataRec.getElementAt("TypedDataInnerIColl");
			KeyedCollection typedDataInnerKColl = (KeyedCollection) typedDataInnerIColl.createElement(true);

			typedDataInnerKColl.setValueAt("StringField", stringField);
			typedDataInnerKColl.setValueAt("XMLGCField", calendar);
			typedDataInnerKColl.setValueAt("BDField", bigDecima);
			typedDataInnerKColl.setValueAt("BIField", bigInteger);
			typedDataInnerKColl.setValueAt("BAField", byteArray);

			srcThreeLvlIColl.addElement(srcNestKColl);
			srcNestIColl.addElement(SrcInnerNestKColl);
			srcInnerNestIColl.addElement(srcTypedDataRec);
			typedDataInnerIColl.addElement(typedDataInnerKColl);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("ThreeLvlICollICollWildcardFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(stringField,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.StringField"));
			Assert.assertEquals(calendar,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.XMLGCField"));
			Assert.assertEquals(bigDecima,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.BDField"));
			Assert.assertEquals(bigInteger,
					to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.BIField"));
			Assert.assertEquals(new String(byteArray),
					new String((byte[]) to.getValueAt("DestThreeLvlIColl.0.NestIColl.0.InnerNestIColl.0.TypedDataInnerIColl.0.BAField")));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testOneLvlICollAppendIngore() {
		try {
			Context from = ContextFactory.createContext("SrcOneLvlICollKCollCtx");
			Context to = ContextFactory.createContext("DestOneLvlICollKCollCtx");
			IndexedCollection iColl = (IndexedCollection) from.getElementAt("SrcOneLvlIColl");
			IndexedCollection destOneLvlIColl = (IndexedCollection) to.getElementAt("DestOneLvlIColl");
			for (int i = 0; i < 4; i++) {
				KeyedCollection kColl = (KeyedCollection) DataElement.readObject("SrcTypedDataRec");
				kColl.setValueAt("SrcPlainField", "this plain field"+i);
				iColl.addElement(kColl);
				
			}
			for (int i = 0; i < 10; i++) {
				
				KeyedCollection destOneLvlICollKColl = (KeyedCollection) DataElement.readObject("TypedDataRec");
				destOneLvlICollKColl.setValueAt("PlainField", ""+i);
				destOneLvlIColl.addElement(destOneLvlICollKColl);
			}
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("OneLvlICollAppendIgnoreFmt");
			fmt.mapContents(from, to);
			for (int i = 0; i < 4; i++) {
				
				Assert.assertEquals("this plain field"+i, to.getValueAt("DestOneLvlIColl."+i+".PlainField"));
			}
			for (int i = 4; i < 10; i++) {
				
				Assert.assertEquals(""+i, to.getValueAt("DestOneLvlIColl."+i+".PlainField"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
}
