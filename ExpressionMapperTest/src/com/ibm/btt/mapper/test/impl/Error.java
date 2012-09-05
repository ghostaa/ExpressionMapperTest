package com.ibm.btt.mapper.test.impl;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.mapper.test.MapperTestCase;

public class Error extends MapperTestCase {

	protected ErrorHandler errorHandler = new ErrorHandler();
	protected Logger logger = null;
	
	protected class ErrorHandler extends Handler {

		private StringBuilder errorMessage;
		
		@Override
		public void publish(LogRecord record) {
			if (record.getLevel() == Level.SEVERE) {
				if (errorMessage == null) {
					errorMessage = new StringBuilder();
					errorMessage.append(record.getMessage());
				} else
					errorMessage.append('\n').append(record.getMessage());
			}
		}

		@Override
		public void flush() {
		}

		@Override
		public void close() throws SecurityException {
		}

		public String getErrorMessage() {
			return errorMessage.toString();
		}
		
		public void reset() {
			errorMessage = null;
		}
	
	};
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		if (logger == null) {
			logger = LogManager.getLogManager().getLogger("#FUNC");
			if (logger == null) 
				logger = Logger.getLogger("#FUNC");
		}
		logger.addHandler(errorHandler);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		logger.removeHandler(errorHandler);
		errorHandler.reset();
	}

	public void testE006() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = from;
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("dataElementNotFoundFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E006"));
	}
	
	public void testE010() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = from;
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("incompatibleTypeFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E010"));
	}
	
	public void testE011() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = from;
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("invalidExpressionFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E011"));
	}
	
	public void testE013() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("incompatibleElementFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E013"));
	}
	
	public void testE014_1() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("invalidWildcardTargetFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E014"));
	}
	
	public void testE014_2() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("wildcardTargetNotFoundFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E014"));
	}
	
	public void testE014_3() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("inconsistentWildcardNumberFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E014"));
	}
	
	public void testE014_4() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("inconsistentPathFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E014"));
	}
	
	public void testE014_5() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("wildcardCharErrorFmt");
		fmt.mapContents(from, to);
		Assert.assertTrue(errorHandler.getErrorMessage().contains("BTT-E014"));
	}
}
