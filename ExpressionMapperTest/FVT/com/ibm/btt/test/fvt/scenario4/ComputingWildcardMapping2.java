package com.ibm.btt.test.fvt.scenario4;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class ComputingWildcardMapping2 extends CommonTestCase {

	/**
	 * StringFunc.replace(Str,StrOld,StrNew) =>String<br>
	 * DateFunc.parseDate(Str,pattern) => Date<br>
	 * NumberFunc.absolute(Long) => Double
	 */
	@Test
	public void testBTTPredefinedFuncsMapping1() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Context source = getContextByName("FuncsUsedCtxt");
			Context target = getContextByName("FuncsUsedCtxt");
			DataMapperFormat fmt = getFormatByName("PreDefinedBttFuncsFmt");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 2; j++)
					for (int k = 0; k < 4; k++)
						for (int ii = 0; ii < 2; ii++)
							for (int jj = 0; jj < 3; jj++) {
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str1Field", "ILIKE"
										+ i + j + k + ii + jj + "BTT" + i + j + k + ii + jj);
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str2Field", "LIKE"
										+ i + j + k + ii + jj);
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str3Field", "LOVE");
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".DateStrField", "201"
										+ i + "0" + (j + 1) + "0" + (k + 1));
								long l = 1048576 * 8 + (i + j + k + ii + jj);
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".MinusLongField", -1
										* l);
							}
			// System.out.println(source.getKeyedCollection());
			fmt.mapContents(source, target);

			// check the mapped values
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 2; j++)
					for (int k = 0; k < 4; k++)
						for (int ii = 0; ii < 2; ii++)
							for (int jj = 0; jj < 3; jj++) {
								Assert.assertEquals("ILOVEBTT" + i + j + k + ii + jj,
										target.getValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".PlainField"));
								Assert.assertEquals(
										"201" + i + "0" + (j + 1) + "0" + (k + 1),
										sdf.format((Date) target.getValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj
												+ ".DateField")));
								long l = 1048576 * 8 + (i + j + k + ii + jj);
								Assert
										.assertEquals(
												(double) l,
												target.getValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj
														+ ".AbsDoubleField"));
							}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * CollectionFunc.getRowByIndex(ICOLL,Number)=>DataElement
	 */
	@Test
	public void testBTTPredefinedFuncsMapping2() {
		try {
			Context source = getContextByName("FuncsUsedCtxt");
			Context target = getContextByName("FuncsUsedCtxt");
			DataMapperFormat fmt = getFormatByName("PreDefinedBttFuncsFmt2");
			source.getKeyedCollection().setDynamic(true);
			int CONSTANTII = 5;

			// initialize the source context
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 2; j++)
					for (int k = 0; k < 4; k++)
						for (int ii = 0; ii < CONSTANTII; ii++)
							for (int jj = 0; jj < 3; jj++) {
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str1Field", "ILIKE"
										+ i + j + k + ii + jj + "BTT" + i + j + k + ii + jj);
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str2Field", "LIKE"
										+ i + j + k + ii + jj);
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str3Field", "LOVE");
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".DateStrField", "201"
										+ i + "0" + (j + 1) + "0" + (k + 1));
								long l = 1048576 * 8 + (i + j + k + ii + jj);
								source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".MinusLongField", -1
										* l);
							}
			// System.out.println(source.getKeyedCollection());
			fmt.mapContents(source, target);
			// System.out.println(target.getKeyedCollection());

			// check the mapped values
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 2; j++)
					for (int k = 0; k < 4; k++)
						for (int ii = 0; ii < CONSTANTII; ii++) {
							int jj = 1;
							Assert.assertEquals("ILIKE" + i + j + k + ii + jj + "BTT" + i + j + k + ii + jj,
									target.getValueAt("L1IC." + i + ".L2IC." + j + ".L3IC." + k + ".L4IC." + ii + ".Str1Field"));
							Assert.assertEquals("LIKE" + i + j + k + ii + jj,
									target.getValueAt("L1IC." + i + ".L2IC." + j + ".L3IC." + k + ".L4IC." + ii + ".Str2Field"));
							Assert.assertEquals("LOVE",
									target.getValueAt("L1IC." + i + ".L2IC." + j + ".L3IC." + k + ".L4IC." + ii + ".Str3Field"));
							Assert.assertEquals("201" + i + "0" + (j + 1) + "0" + (k + 1),
									target.getValueAt("L1IC." + i + ".L2IC." + j + ".L3IC." + k + ".L4IC." + ii + ".DateStrField"));
							long l = 1048576 * 8 + (i + j + k + ii + jj);
							Assert.assertEquals(-1 * l,
									target.getValueAt("L1IC." + i + ".L2IC." + j + ".L3IC." + k + ".L4IC." + ii + ".MinusLongField"));
							Assert.assertNull("Default value is null.",
									target.getValueAt("L1IC." + i + ".L2IC." + j + ".L3IC." + k + ".L4IC." + ii + ".DateField"));
							Assert.assertNull("Default value is null.",
									target.getValueAt("L1IC." + i + ".L2IC." + j + ".L3IC." + k + ".L4IC." + ii + ".AbsDoubleField"));
						}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}
}
