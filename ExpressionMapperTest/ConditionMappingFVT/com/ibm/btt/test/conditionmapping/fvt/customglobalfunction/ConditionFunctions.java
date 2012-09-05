package com.ibm.btt.test.conditionmapping.fvt.customglobalfunction;

import java.util.Vector;

import com.ibm.btt.base.DSEObjectNotFoundException;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;


public class ConditionFunctions {
	/**
	 * calculate the average age
	 * 
	 * @param strAges
	 * @return
	 * @throws DSEObjectNotFoundException 
	 */
	public static IndexedCollection sortByName(IndexedCollection iColl,String name) throws DSEObjectNotFoundException {
			Vector<KeyedCollection> vector= iColl.getElements();
			for (int i = 0; i < vector.size()-1; i++) {
				for (int j = i+1; j < vector.size(); j++) {
					KeyedCollection k1=vector.get(i);
					KeyedCollection k2=vector.get(j);
					if (k1 != null && k2 != null) {
						Double d1=Double.parseDouble(k1.getValueAt(name).toString());
						Double d2=Double.parseDouble(k2.getValueAt(name).toString());
						if (d1>d2) {
							vector.set(i, k2);
							vector.set(j, k1);
						}
						
						
					}
				}
			}
			iColl.setElements((com.ibm.btt.base.Vector) vector);
		
		return iColl;
	}
	
}
