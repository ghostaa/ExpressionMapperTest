package com.ibm.btt.test.fvt.scenario5;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class ComputingWildcardWithOpersMapping2 extends CommonTestCase {

	/**
	 * For String typed data, execute
	 * <code>Str1Field + Str2Field + Str3Field</code><br>
	 * For number typed data, execute
	 * <code>ByteField + (IntegerField - LongField) * DoubleField / BigIntegerField<code><br>
	 * For boolean typed data, execute <code>ByteField >= IntegerField</code> and
	 * <code>!BooleanField</code>
	 */
	@Test
	public void testDeepICollsWithMultiOperators() {
		try {
			Context source = getContextByName("OperatorsUsedCtxt");
			Context target = getContextByName("OperatorsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("DeepICollsWithMultipleOperators");

			// initialize the source context
			initializeSourceContext(source);

			fmt.mapContents(source, target);

			int CONSTANTII = 5;
			// initialize the source context
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 2; j++)
					for (int k = 0; k < 4; k++)
						for (int ii = 0; ii < CONSTANTII; ii++)
							for (int jj = 0; jj < 3; jj++) {
								StringBuilder sb = new StringBuilder();
								sb.append("I DO ").append(i).append(j).append(k).append(ii).append(jj).append("BTT").append(i).append(j)
										.append(k).append(ii).append(jj);
								sb.append("LOVE ").append(i).append(j).append(k).append(ii).append(jj);
								sb.append("BTT");
								Assert.assertEquals(sb.toString(),
										target.getValueAt("L1OI." + i + "." + j + "." + k + "." + ii + "." + jj + ".Str1Field"));
								Assert.assertNull("Not included in the mapping definitions.",
										target.getValueAt("L1OI." + i + "." + j + "." + k + "." + ii + "." + jj + ".Str2Field"));
								Assert.assertNull("Not included in the mapping definitions.",
										target.getValueAt("L1OI." + i + "." + j + "." + k + "." + ii + "." + jj + ".Str3Field"));

								byte bn = (byte) ((i + j + k) * (ii + jj) % Byte.MAX_VALUE);
								int in = (i + j - k) * (ii + jj);
								long ln = (long) 8192 * (i + j + k + ii + jj);
								BigInteger bin = new BigInteger(String.valueOf(Integer.MAX_VALUE));
								double dn = 1.024D * (i + j + k + ii + jj) * 1024;
								// ByteField + (IntegerField - LongField) * DoubleField /
								// BigIntegerField
								dn = bn + (in - ln) * dn / bin.doubleValue();
								BigDecimal bd = new BigDecimal(dn);
								bd = bd.setScale(9, BigDecimal.ROUND_HALF_UP);
								//js engine case,now it is ignored for mvel
								/*Assert.assertEquals(bd,
										target.getValueAt("L1OI." + i + "." + j + "." + k + "." + ii + "." + jj + ".BigDecimalField"));
*/
								Assert.assertEquals(bn <= in,
										target.getValueAt("L1OI." + i + "." + j + "." + k + "." + ii + "." + jj + ".BooleanField"));
								boolean flag = !((i + j + k + ii + jj) % 2 == 0);
								Assert.assertEquals(flag,
										target.getValueAt("L1OI." + i + "." + j + "." + k + "." + ii + "." + jj + ".AnotherBooleanField"));
								Assert.assertEquals(flag,
										target.getValueAt("L1OI." + i + "." + j + "." + k + "." + ii + "." + jj + ".PlainField"));

							}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	private void initializeSourceContext(Context source) throws Exception {
		source.setValueAt("BigIntegerField", new BigInteger(String.valueOf(Integer.MAX_VALUE)));
		int CONSTANTII = 5;
		// initialize the source context
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 4; k++)
					for (int ii = 0; ii < CONSTANTII; ii++)
						for (int jj = 0; jj < 3; jj++) {
							// string
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str1Field", "I DO "
									+ i + j + k + ii + jj + "BTT" + i + j + k + ii + jj);
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str2Field", "LOVE "
									+ i + j + k + ii + jj);
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str3Field", "BTT");

							// numbers
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".ByteField", (byte) ((i
									+ j + k)
									* (ii + jj) % Byte.MAX_VALUE));
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".IntegerField",
									(i + j - k) * (ii + jj));
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".LongField",
									(long) 8192 * (i + j + k + ii + jj));
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".DoubleField",
									1.024D * (i + j + k + ii + jj) * 1024);

							// boolean
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".BooleanField", (i + j
									+ k + ii + jj) % 2 == 0);
						}
	}
}
