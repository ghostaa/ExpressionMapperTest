package com.ibm.btt.test.fvt.scenario6;

import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;

public class Scenario6CollectionFunctions {

	/**
	 * Get an KeyedCollection form specified parent KeyedCollection
	 * 
	 * @param parentKColl
	 * @param key
	 * @return
	 */
	public static KeyedCollection getKeyedCollectionByKey(KeyedCollection parentKColl, String key) {
		KeyedCollection retKColl = null;
		String className = "";
		try {
			className = parentKColl.getElementAt(key).getClass().getName();
			retKColl = (KeyedCollection) parentKColl.getElementAt(key);
		} catch (ClassCastException e) {
			e.printStackTrace();
			throw new RuntimeException("The key [" + key + "] pointed element is of [" + className + "], not a KeyedCollection",
					e.fillInStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.fillInStackTrace());
		}
		return retKColl;
	}

	/**
	 * Get specified element from parent indexed collection, if the specified
	 * index is greater than size(), return default index specified element
	 * 
	 * @param parentIColl
	 * @param specifiedIndex
	 * @param defaultIndex
	 * @return
	 */
	public static KeyedCollection getSpecifiedEleOrDefaultEleFormIColl(IndexedCollection parentIColl, String kCollName,
			int specifiedIndex, int defaultIndex) {
		KeyedCollection retKColl = null;
		try {
			retKColl = (KeyedCollection) parentIColl.getElementAt(
					specifiedIndex >= parentIColl.size() ? defaultIndex : specifiedIndex).getElementAt(kCollName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.fillInStackTrace());
		}
		return retKColl;
	}
}
