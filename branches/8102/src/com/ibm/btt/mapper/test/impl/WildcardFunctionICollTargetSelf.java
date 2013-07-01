package com.ibm.btt.mapper.test.impl;

import com.ibm.btt.base.Context;

public class WildcardFunctionICollTargetSelf extends WildcardFunctionICollTarget {

	@Override
	protected Context composeWildcardTargetContext(Context source) throws Exception {
		return source;
	}

}
