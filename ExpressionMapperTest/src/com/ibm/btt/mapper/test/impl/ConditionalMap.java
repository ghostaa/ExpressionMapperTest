package com.ibm.btt.mapper.test.impl;

import org.junit.Test;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.mapper.test.MapperTestCase;

public class ConditionalMap extends MapperTestCase {

	@Test
	public void testConditionalMap() throws Exception {
		Context from = ContextFactory.createContext("conditionCtx");
		Context to = ContextFactory.createContext("conditionCtx");
		from.setValueAt("testInt", 2);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("testConditionFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals(1, to.getValueAt("testInt"));
		from.setValueAt("testInt", 8);
		fmt.mapContents(from, to);
		Assert.assertEquals(2, to.getValueAt("testInt"));
		from.setValueAt("testInt", 12);
		fmt.mapContents(from, to);
		Assert.assertEquals(3, to.getValueAt("testInt"));
		from.setValueAt("testInt", 16);
		fmt.mapContents(from, to);
		Assert.assertEquals(4, to.getValueAt("testInt"));
	}
	
	@Test
	public void testConditionalIcollMap() throws Exception {
		Context from = ContextFactory.createContext("conditionCtx3");
		// Context to = ContextFactory.createContext("conditionCtx");
		Context to=from;
		
		from.setValueAt("iCollA.0.testInt",  -88);
	
		from.setValueAt("iCollA.1.testInt", 1);
		from.setValueAt("iCollA.2.testInt", 3);
		from.setValueAt("iCollA.3.testInt", 7);
		from.setValueAt("iCollA.4.testInt", 13);
		from.setValueAt("iCollA.5.testInt", 17);
		from.setValueAt("iCollA.6.testInt", 99);
		
		
		System.out.println("############ input Context"+ from.getKeyedCollection());
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("testICollConditionFmt");
		fmt.mapContents(from, to);
		 
		System.out.println("############ output Context"+ to.getKeyedCollection());
		
		
		Assert.assertEquals(-99, to.getValueAt("iCollB.0.testInt"));
		Assert.assertEquals(1, to.getValueAt("iCollB.1.testInt"));
		Assert.assertEquals(6, to.getValueAt("iCollB.2.testInt"));
		Assert.assertEquals(7, to.getValueAt("iCollB.3.testInt"));
		Assert.assertEquals(4, to.getValueAt("iCollB.4.testInt"));
		Assert.assertEquals(5, to.getValueAt("iCollB.5.testInt"));
		Assert.assertEquals(6, to.getValueAt("iCollB.6.testInt"));
		
		Assert.assertEquals(1, to.getValueAt("iCollA.1.testInt"));
		Assert.assertEquals(2, to.getValueAt("iCollA.2.testInt"));
		Assert.assertEquals(3, to.getValueAt("iCollA.3.testInt"));
	}
	
	@Test
	public void testConditionalIcollMap2() throws Exception {
		Context from = ContextFactory.createContext("conditionCtx2");
		// Context to = ContextFactory.createContext("conditionCtx2");
		Context to=from;
		
		from.setValueAt("iCollA.0.testInt", 10);
		from.setValueAt("iCollA.1.testInt", 111);
		
		from.setValueAt("iCollB.0.testInt",  8);
		from.setValueAt("iCollB.1.testInt", 3);
		
		from.setValueAt("ICWrap.iCollC.0.testInt",  33);
		from.setValueAt("ICWrap.iCollC.1.testInt", 5);
		 		
		
		System.out.println("############ input Context"+ from.getKeyedCollection());
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("testICollConditionFmt21");
		fmt.mapContents(from, to);
		 
		System.out.println("############ output Context"+ to.getKeyedCollection());
			
		Assert.assertEquals(51, to.getValueAt("iCollA.0.testInt"));
		Assert.assertEquals(69, to.getValueAt("iCollA.1.testInt"));
		Assert.assertEquals(54, to.getValueAt("ICWrap.iCollC.1.testInt")); 
		Assert.assertEquals(667, to.getValueAt("testInt")); 
	}
}
