package com.jdevelopstation.commons.data.xml;

import org.apache.log4j.Logger;

/**
 * @author VISTALL
 * @date 8:59/19.05.2011
 */
public abstract class LoggerObject
{
	private final Logger _log = Logger.getLogger(getClass());

	public void error(String st, Exception e)
	{
		_log.error(getClass().getSimpleName() + ": " + st, e);
	}

	public void error(String st)
	{
		_log.error(getClass().getSimpleName() + ": " + st);
	}

	public void warn(String st, Exception e)
	{
		_log.warn(getClass().getSimpleName() + ": " + st, e);
	}

	public void warn(String st)
	{
		_log.warn(getClass().getSimpleName() + ": " + st);
	}

	public void info(String st, Exception e)
	{
		_log.info(getClass().getSimpleName() + ": " + st, e);
	}

	public void info(String st)
	{
		_log.info(getClass().getSimpleName() + ": " + st);
	}
}
