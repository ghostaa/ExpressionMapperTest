package com.ibm.btt.test.fvt.scenario2;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ibm.btt.base.KeyedCollection;

public class CustomerGlobalFunctions {

	public static String kCollToString(KeyedCollection kColl) {

		Map<?, ?> kCollMap = kColl.getElements();
		Set<?> kCollKey = kCollMap.keySet();
		Iterator<?> kCollIt = kCollKey.iterator();
		StringBuffer kCollBuf = new StringBuffer();
		while (kCollIt.hasNext()) {
			String keyString = kCollIt.next().toString();
			String valueString = kCollMap.get(keyString).toString();
			kCollBuf.append(keyString + ":" + valueString);
			if (kCollIt.hasNext()) {
				kCollBuf.append(";");
			}
		}
		return kCollBuf.toString();
	}
}
