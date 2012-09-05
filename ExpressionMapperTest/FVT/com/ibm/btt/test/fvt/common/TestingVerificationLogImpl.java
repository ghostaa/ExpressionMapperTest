package com.ibm.btt.test.fvt.common;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Hashtable;

import org.apache.commons.logging.Log;

public class TestingVerificationLogImpl implements Log, Serializable {
	private static final long serialVersionUID = 1L;
	public static final int TRACE = 0, DEBUG = 1, INFO = 2, WARN = 3, ERROR = 4, FATAL = 5;
	private static Hashtable<Integer, Boolean> levelSwitchs = new Hashtable<Integer, Boolean>();
	private static StringBuilder sb = new StringBuilder(1048576);

	private String logName = "";

	static {
		levelSwitchs.put(TRACE, Boolean.FALSE);
		levelSwitchs.put(DEBUG, Boolean.FALSE);
		levelSwitchs.put(INFO, Boolean.FALSE);
		levelSwitchs.put(WARN, Boolean.FALSE);
		levelSwitchs.put(ERROR, Boolean.TRUE);
		levelSwitchs.put(FATAL, Boolean.TRUE);
	}

	public TestingVerificationLogImpl(String name) {
		this.logName = name;
	}

	public static void setTraceLevel(int traceLevel) {

		switch (traceLevel) {
		case TRACE: {
			levelSwitchs.put(TRACE, Boolean.TRUE);
			levelSwitchs.put(DEBUG, Boolean.TRUE);
			levelSwitchs.put(INFO, Boolean.TRUE);
			levelSwitchs.put(WARN, Boolean.TRUE);
			levelSwitchs.put(ERROR, Boolean.TRUE);
			levelSwitchs.put(FATAL, Boolean.TRUE);
			break;
		}
		case DEBUG: {
			levelSwitchs.put(TRACE, Boolean.FALSE);
			levelSwitchs.put(DEBUG, Boolean.TRUE);
			levelSwitchs.put(INFO, Boolean.TRUE);
			levelSwitchs.put(WARN, Boolean.TRUE);
			levelSwitchs.put(ERROR, Boolean.TRUE);
			levelSwitchs.put(FATAL, Boolean.TRUE);
			break;
		}
		case INFO: {
			levelSwitchs.put(TRACE, Boolean.FALSE);
			levelSwitchs.put(DEBUG, Boolean.FALSE);
			levelSwitchs.put(INFO, Boolean.TRUE);
			levelSwitchs.put(WARN, Boolean.TRUE);
			levelSwitchs.put(ERROR, Boolean.TRUE);
			levelSwitchs.put(FATAL, Boolean.TRUE);
			break;
		}
		case WARN: {
			levelSwitchs.put(TRACE, Boolean.FALSE);
			levelSwitchs.put(DEBUG, Boolean.FALSE);
			levelSwitchs.put(INFO, Boolean.FALSE);
			levelSwitchs.put(WARN, Boolean.TRUE);
			levelSwitchs.put(ERROR, Boolean.TRUE);
			levelSwitchs.put(FATAL, Boolean.TRUE);
			break;
		}
		case ERROR: {
			levelSwitchs.put(TRACE, Boolean.FALSE);
			levelSwitchs.put(DEBUG, Boolean.FALSE);
			levelSwitchs.put(INFO, Boolean.FALSE);
			levelSwitchs.put(WARN, Boolean.FALSE);
			levelSwitchs.put(ERROR, Boolean.TRUE);
			levelSwitchs.put(FATAL, Boolean.TRUE);
			break;
		}
		case FATAL: {
			levelSwitchs.put(TRACE, Boolean.FALSE);
			levelSwitchs.put(DEBUG, Boolean.FALSE);
			levelSwitchs.put(INFO, Boolean.FALSE);
			levelSwitchs.put(WARN, Boolean.FALSE);
			levelSwitchs.put(ERROR, Boolean.FALSE);
			levelSwitchs.put(FATAL, Boolean.TRUE);
			break;
		}
		default: {
			levelSwitchs.put(TRACE, Boolean.FALSE);
			levelSwitchs.put(DEBUG, Boolean.FALSE);
			levelSwitchs.put(INFO, Boolean.FALSE);
			levelSwitchs.put(WARN, Boolean.FALSE);
			levelSwitchs.put(ERROR, Boolean.TRUE);
			levelSwitchs.put(FATAL, Boolean.TRUE);
		}
		}
	}

	public static void disableTrace() {
		levelSwitchs.put(TRACE, Boolean.FALSE);
		levelSwitchs.put(DEBUG, Boolean.FALSE);
		levelSwitchs.put(INFO, Boolean.FALSE);
		levelSwitchs.put(WARN, Boolean.FALSE);
		levelSwitchs.put(ERROR, Boolean.FALSE);
		levelSwitchs.put(FATAL, Boolean.FALSE);
	}

	@Override
	public boolean isDebugEnabled() {
		return levelSwitchs.get(DEBUG);
	}

	@Override
	public boolean isErrorEnabled() {
		return levelSwitchs.get(ERROR);
	}

	@Override
	public boolean isFatalEnabled() {
		return levelSwitchs.get(FATAL);
	}

	@Override
	public boolean isInfoEnabled() {
		return levelSwitchs.get(INFO);
	}

	@Override
	public boolean isTraceEnabled() {
		return levelSwitchs.get(TRACE);
	}

	@Override
	public boolean isWarnEnabled() {
		return levelSwitchs.get(WARN);
	}

	@Override
	public void trace(Object message) {
		doLog("[TRACE]" + message);
	}

	@Override
	public void trace(Object message, Throwable t) {
		doLog("[TRACE]" + message, t);
	}

	@Override
	public void debug(Object message) {
		doLog("[DEBUG]" + message);
	}

	@Override
	public void debug(Object message, Throwable t) {
		doLog("[DEBUG]" + message, t);

	}

	@Override
	public void info(Object message) {
		doLog("[INFO]" + message);

	}

	@Override
	public void info(Object message, Throwable t) {
		doLog("[INFO]" + message, t);
	}

	@Override
	public void warn(Object message) {
		doLog("[WARN]" + message);
	}

	@Override
	public void warn(Object message, Throwable t) {
		doLog("[WARN]" + message, t);
	}

	@Override
	public void error(Object message) {
		doLog("[ERROR]" + message);
	}

	@Override
	public void error(Object message, Throwable t) {
		doLog("[ERROR]" + message, t);
	}

	@Override
	public void fatal(Object message) {
		doLog("[FATAL]" + message);
	}

	@Override
	public void fatal(Object message, Throwable t) {
		doLog("[FATAL]" + message, t);
	}

	private void doLog(Object message) {
		doLog(message, null);
	}

	private void doLog(Object message, Throwable t) {
		sb.append(logName);
		sb.append(" ");
		sb.append(message);
		sb.append(null == t ? "" : t.getMessage());
		sb.append("\n");
		PrintStream ps = isWarnEnabled() ? System.err : System.out;
		ps.print(logName + " " + message);
		ps.println(null == t ? "" : t.getMessage());
	}

	public static void resetLog() {
		sb = null;
		sb = new StringBuilder(1048576);
		levelSwitchs.put(TRACE, Boolean.FALSE);
		levelSwitchs.put(DEBUG, Boolean.FALSE);
		levelSwitchs.put(INFO, Boolean.FALSE);
		levelSwitchs.put(WARN, Boolean.FALSE);
		levelSwitchs.put(ERROR, Boolean.TRUE);
		levelSwitchs.put(FATAL, Boolean.TRUE);
	}

	public static String getLogContents() {
		return sb.toString();
	}

	public static String[] getLogContentsInLines() {
		return sb.toString().split("\n");
	}
}
