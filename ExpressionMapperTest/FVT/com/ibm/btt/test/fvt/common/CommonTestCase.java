package com.ibm.btt.test.fvt.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.After;
import org.junit.Before;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DSEInvalidRequestException;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.config.InitManager;

public abstract class CommonTestCase {
	private static final String LOGIMPL = "org.apache.commons.logging.Log";
	private static final String LOGIMPLCLASS = "com.ibm.btt.test.fvt.common.TestingVerificationLogImpl";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void setUp() throws Exception {
//		System.setProperty("user.timezone",  "America/New_York");//"Asia/Shanghai");
//		System.setProperty("user.regional",  "US");
//		System.setProperty("user.language",  "en");
		if (isLogVerificationEnabled())
			System.setProperty(LOGIMPL, LOGIMPLCLASS);
		setDefaultTraceLevel();
		InitManager.reset("jar:///definitions/btt.xml");
	}

	@After
	public void tearDown() throws Exception {
		TestingVerificationLogImpl.resetLog();
		InitManager.cleanUp();
		if (isLogVerificationEnabled())
			System.getProperties().remove(LOGIMPL);
	}

	/**
	 * If return true, that means you want to redirect logs to String instance. In
	 * that way, you can verify the log contents.
	 * 
	 * @return default false
	 */
	protected boolean isLogVerificationEnabled() {
		return false;
	}

	protected String getLogContents() {
		return TestingVerificationLogImpl.getLogContents();
	}

	protected String[] getLogContentsInLines() {
		return TestingVerificationLogImpl.getLogContentsInLines();
	}

	/**
	 * <b>Trace level set here will overwrite the trace levels defined in the
	 * btt.xml</b> <br>
	 * Validated trace levels are:<BR>
	 * TestingVerificationLogImpl.TRACE<BR>
	 * TestingVerificationLogImpl.DEBUG<BR>
	 * TestingVerificationLogImpl.INFO<BR>
	 * TestingVerificationLogImpl.WARN<BR>
	 * TestingVerificationLogImpl.ERROR<BR>
	 * TestingVerificationLogImpl.FATAL
	 * 
	 * @param level
	 */
	protected void setTraceLevel(int level) {
		if (level < TestingVerificationLogImpl.TRACE || level > TestingVerificationLogImpl.FATAL)
			return;
		TestingVerificationLogImpl.setTraceLevel(level);
	}

	/**
	 * Default trace level is INFO, you can override this method to set the
	 * default trace level
	 */
	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.INFO);
	}

	/**
	 * Get a context instance by passing the context's name(id)
	 * 
	 * @param contextName
	 * @return
	 * @throws DSEInvalidRequestException
	 */
	public Context getContextByName(String contextName) throws DSEInvalidRequestException {
		return ContextFactory.createContext(contextName, false);
	}

	/**
	 * Get a format instance by passing the formatter's name(id)
	 * 
	 * @param formatName
	 * @return
	 * @throws IOException
	 */
	public DataMapperExpressionConverterFormat getFormatByName(String formatName) throws IOException {
		return (DataMapperExpressionConverterFormat) FormatElement.readObject(formatName);
	}

	/**
	 * Parse a String value to a Date instance, the parameter must follow the
	 * pattern of "yyyy-MM-dd"
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public Date parseStringToDate(String dateStr) throws ParseException {
		return sdf.parse(dateStr);
	}

	/**
	 * Parse a String value to a XMLGregorianCalendar instance, the parameter must
	 * follow the pattern of "yyyy-MM-dd"
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 * @throws DatatypeConfigurationException
	 */
	public XMLGregorianCalendar parseStringToXMLGregorianCalendar(String dateStr) throws ParseException,
			DatatypeConfigurationException {
		Date theDate = parseStringToDate(dateStr);
		XMLGregorianCalendar xgc = null;
		TimeZone.getTimeZone("Aisa/Shanghai");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(theDate);
		xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		return xgc;
	}
}
