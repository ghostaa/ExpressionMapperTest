package com.ibm.btt.test.fvt.scenario8;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegInfiniteAppendMapping extends CommonTestCase {

	protected boolean isLogVerificationEnabled() {
		return true;
	}

	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}

	@Test
	public void testAppendFalse2LvlsICollMapping() {
		try {
			Context source = getContextByName("SrcComputerCtxt");
			Context target = getContextByName("DestComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_InfiniteAppendFalse2LvlsICollMapFmt");

			int M1 = 4, M2 = 8, N1 = 8, N2 = 16;
			// initialize the source context
			initializeSourceContext(source, M1, M2);

			// initialize the target context
			initializeTargetContext(target, N1, N2);

			// mapping the source to target
			fmt.mapContents(source, target);

			// checking the target context
			IndexedCollection destComputerList2 = (IndexedCollection) target.getElementAt("destComputerList2");
			Assert.assertEquals(M1, destComputerList2.size());
			for (int c = 0; c < M1; c++) {
				IndexedCollection destComputerList1 = (IndexedCollection) destComputerList2.getElementAt(c);
				Assert.assertEquals(M2, destComputerList1.size());
				for (int i = 0; i < M2; i++) {
					Assert.assertEquals("IBM-TPD-X9" + (i < 10 ? "0" + i : "" + i), destComputerList1.getValueAt(i + ".destBrand"));
					Assert.assertEquals(Integer.MAX_VALUE - i, destComputerList1.getValueAt(i + ".destCpuFreq"));
					Assert.assertEquals(new BigInteger(String.valueOf(1048576 + i)), destComputerList1.getValueAt(i + ".destMemSize"));
					Assert.assertEquals(new BigDecimal(1024 + i).setScale(9), destComputerList1.getValueAt(i + ".destPrice"));
					Assert.assertEquals(0.5D, destComputerList1.getValueAt(i + ".destDiscount"));
					Assert.assertEquals((long) 1 + i, destComputerList1.getValueAt(i + ".destQuantity"));
					Assert.assertEquals("" + i, destComputerList1.getValueAt(i + ".destComments"));
				}
			}
		} catch (Throwable e) {
			// e.printStackTrace();
			Assert.assertEquals(StackOverflowError.class, e.getClass());
		}
	}

	private void initializeSourceContext(Context source, int CONSTO, int CONSTI) throws Exception {
		IndexedCollection srcComputerList2 = (IndexedCollection) source.getElementAt("srcComputerList2");
		srcComputerList2.removeAll();
		for (int c = 0; c < CONSTO; c++) {
			IndexedCollection srcComputerList1 = (IndexedCollection) srcComputerList2.createElement(true);
			srcComputerList1.removeAll();
			for (int i = 0; i < CONSTI; i++) {
				KeyedCollection computer = (KeyedCollection) srcComputerList1.createElement(true);
				computer.setValueAt("srcBrand", "IBM-TPD-X9" + (i < 10 ? "0" + i : "" + i));
				computer.setValueAt("srcCpuFreq", Integer.MAX_VALUE - i);
				computer.setValueAt("srcMemSize", new BigInteger(String.valueOf(1048576 + i)));
				computer.setValueAt("srcPrice", new BigDecimal(1024 + i));
				computer.setValueAt("srcDiscount", 0.5D);
				computer.setValueAt("srcQuantity", (long) 1 + i);
				computer.setValueAt("srcComments", "" + i);
				srcComputerList1.addElement(computer);
			}
			srcComputerList2.addElement(srcComputerList1);
		}
	}

	private void initializeTargetContext(Context target, int CONSTO, int CONSTI) throws Exception {
		IndexedCollection destComputerList2 = (IndexedCollection) target.getElementAt("destComputerList2");
		destComputerList2.removeAll();
		for (int c = 0; c < CONSTO; c++) {
			IndexedCollection destComputerList1 = (IndexedCollection) destComputerList2.createElement(true);
			destComputerList1.removeAll();
			for (int i = 0; i < CONSTI; i++) {
				KeyedCollection computer = (KeyedCollection) destComputerList1.createElement(true);
				computer.setValueAt("destBrand", "BTT-TPD-X" + (i < 10 ? "0" + i : "" + i));
				computer.setValueAt("destCpuFreq", i);
				computer.setValueAt("destMemSize", new BigInteger(String.valueOf(1048576 + i)));
				computer.setValueAt("destPrice", new BigDecimal(1024 + i));
				computer.setValueAt("destDiscount", 0.7D);
				computer.setValueAt("destQuantity", (long) 1 + i);
				computer.setValueAt("destComments", "000" + (i < 10 ? "0" + i : "" + i));
				destComputerList1.addElement(computer);
			}
			destComputerList2.addElement(destComputerList1);
		}
	}
}
