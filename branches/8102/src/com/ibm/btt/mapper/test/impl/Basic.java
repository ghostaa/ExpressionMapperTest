package com.ibm.btt.mapper.test.impl;

import java.math.BigInteger;
import java.util.Date;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.mapper.test.MapperTestCase;

public class Basic extends MapperTestCase {

	/** Field->Field **/
	public void testField2FieldMapping() throws Exception {
		Context from = ContextFactory.createContext("singleFieldCtx");
		Context to = ContextFactory.createContext("singleFieldCtx");
		from.setValueAt("testField", "123");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("singleFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("123", to.getValueAt("testField"));
	}
	
	/** Field <-> Data **/
	public void testFieldDataMapping() throws Exception {
		Context from = ContextFactory.createContext("fieldDataCtx");
		Context to = ContextFactory.createContext("fieldDataCtx");
		from.setValueAt("testField", "234565789");
		from.setValueAt("testBigInteger", "123456578");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fieldDataFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals(new BigInteger("123456578"), to.getValueAt("testField"));
		Assert.assertEquals(new BigInteger("234565789"), to.getValueAt("testBigInteger"));
	}
	
	/**
	 * Field in nested kColl <-> Field 
	 */
	public void testEmbeddedKCollMapping() throws Exception {
		Context embeddedCtx = ContextFactory.createContext("embeddedKCollCtx");
		Context fieldDataCtx = ContextFactory.createContext("fieldDataCtx");
		embeddedCtx.setValueAt("fieldData.testField", "234565789");
		embeddedCtx.setValueAt("fieldData.testBigInteger", "123456578");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("embeddedKCollDataFmt");
		fmt.mapContents(embeddedCtx, fieldDataCtx);
		Assert.assertEquals(new BigInteger("123456578"), fieldDataCtx.getValueAt("testField"));
		Assert.assertEquals(new BigInteger("234565789"), fieldDataCtx.getValueAt("testBigInteger"));
		fieldDataCtx.setValueAt("testField", "345657890");
		fieldDataCtx.setValueAt("testBigInteger", "1234565780");
		fmt = (DataMapperFormat)FormatElement.readObject("embeddedKCollDataReverseFmt");
		fmt.mapContents(fieldDataCtx, embeddedCtx);
		Assert.assertEquals(new BigInteger("1234565780"), embeddedCtx.getValueAt("fieldData.testField"));
		Assert.assertEquals(new BigInteger("345657890"), embeddedCtx.getValueAt("fieldData.testBigInteger"));
	}
	
	/**
	 * Data/Field <-> Data/Field using byReference=true/false
	 */
	public void testByReferenceMapping() throws Exception {
		Context from = ContextFactory.createContext("dateKCollCtx");
		Context to = ContextFactory.createContext("dateKCollCtx");
		Date date = new Date();
		from.setValueAt("testDate", date);
		from.setValueAt("testField", date);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("byReferenceFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals(date, to.getValueAt("testDate"));
		Assert.assertEquals(date, to.getValueAt("testField"));
		Assert.assertFalse(to.getValueAt("testDate") == date);
		Assert.assertFalse(to.getValueAt("testField") == date);
		fmt = (DataMapperFormat)FormatElement.readObject("byReferenceFmt1");
		fmt.mapContents(from, to);
		Assert.assertEquals(date, to.getValueAt("testDate"));
		Assert.assertEquals(date, to.getValueAt("testField"));
		Assert.assertTrue(to.getValueAt("testDate") == date);
		Assert.assertTrue(to.getValueAt("testField") == date);
		fmt = (DataMapperFormat)FormatElement.readObject("byReferenceFmt2");
		fmt.mapContents(from, to);
		Assert.assertEquals(date, to.getValueAt("testDate"));
		Assert.assertEquals(date, to.getValueAt("testField"));
		Assert.assertFalse(to.getValueAt("testDate") == date);
		Assert.assertFalse(to.getValueAt("testField") == date);
		fmt = (DataMapperFormat)FormatElement.readObject("byReferenceFmt3");
		fmt.mapContents(from, to);
		Assert.assertEquals(date, to.getValueAt("testDate"));
		Assert.assertEquals(date, to.getValueAt("testField"));
		Assert.assertTrue(to.getValueAt("testDate") == date);
		Assert.assertTrue(to.getValueAt("testField") == date);
	}
	
	/**
	 * Field in IColl <-> Field
	 */
	public void testEmbeddedFieldICollMapping() throws Exception {
		Context iCollCtx = ContextFactory.createContext("embeddedFieldICollCtx");
		Context fieldDataCtx = ContextFactory.createContext("fieldDataCtx");
		iCollCtx.setValueAt("fieldIColl.0", new BigInteger("56478"));
		iCollCtx.setValueAt("fieldIColl.1", new BigInteger("56479"));
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("embeddedFieldICollFmt");
		fmt.mapContents(iCollCtx, fieldDataCtx);
		Assert.assertEquals(new BigInteger("56478"), fieldDataCtx.getValueAt("testBigInteger"));
		Assert.assertEquals(new BigInteger("56479"), fieldDataCtx.getValueAt("testField"));
		fmt = (DataMapperFormat)FormatElement.readObject("embeddedFieldICollReverseFmt");
		fieldDataCtx.setValueAt("testBigInteger", new BigInteger("12345"));
		fieldDataCtx.setValueAt("testField", "12345");
		fmt.mapContents(fieldDataCtx, iCollCtx);
		Assert.assertEquals(new BigInteger("12345"), iCollCtx.getValueAt("fieldIColl.0"));
		Assert.assertEquals(new BigInteger("12345"), iCollCtx.getValueAt("fieldIColl.1"));
	}
	
	/**
	 * Field in KColl of IColl <-> Field in IColl.
	 */
	public void testEmbeddedKCollICollMapping() throws Exception {
		Context iCollCtx = ContextFactory.createContext("embeddedKCollICollCtx");
		Context fieldDataCtx = ContextFactory.createContext("fieldDataCtx");
		iCollCtx.setValueAt("kCollIColl.0.testBigInteger", new BigInteger("56478"));
		iCollCtx.setValueAt("kCollIColl.0.testField", new BigInteger("12345"));
		iCollCtx.setValueAt("kCollIColl.1.testBigInteger", new BigInteger("56479"));
		iCollCtx.setValueAt("kCollIColl.1.testField", "12346");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("embeddedKCollICollFmt");
		fmt.mapContents(iCollCtx, fieldDataCtx);
		Assert.assertEquals(new BigInteger("56478"), fieldDataCtx.getValueAt("testBigInteger"));
		Assert.assertEquals("12346", fieldDataCtx.getValueAt("testField"));
		fmt = (DataMapperFormat)FormatElement.readObject("embeddedKCollICollReverseFmt");
		fieldDataCtx.setValueAt("testBigInteger", new BigInteger("444444"));
		fieldDataCtx.setValueAt("testField", "Monkey");
		fmt.mapContents(fieldDataCtx, iCollCtx);
		Assert.assertEquals(new BigInteger("444444"), iCollCtx.getValueAt("kCollIColl.0.testBigInteger"));
		Assert.assertEquals("Monkey", iCollCtx.getValueAt("kCollIColl.1.testField"));
	}
}
