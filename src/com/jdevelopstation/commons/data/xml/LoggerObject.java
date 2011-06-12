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
		_log.error(st, e);
	}

	public void error(String st)
	{
		_log.error(st);
	}

	public void warn(String st, Exception e)
	{
		_log.warn(st, e);
	}

	public void warn(String st)
	{
		_log.warn(st);
	}

	public void info(String st, Exception e)
	{
		_log.info(st, e);
	}

	public void info(String st)
	{
		_log.info(st);
	}
}
