package com.jdevelopstation.commons.logging;

import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author VISTALL
 * @date 9:10/19.05.2011
 */
public class Log4JHelper
{
	public static void load()
	{
		try
		{
			URL url = Log4JHelper.class.getResource("log4j.xml");
			DOMConfigurator.configure(url);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}

		Logger logger = LogManager.getLogManager().getLogger("");
		for (Handler h : logger.getHandlers())
			logger.removeHandler(h);
	}
}
