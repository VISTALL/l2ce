package com.jdevelopstation.commons.logging;

import java.net.URL;

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
	}
}
