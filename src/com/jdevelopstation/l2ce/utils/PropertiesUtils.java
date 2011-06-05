package com.jdevelopstation.l2ce.utils;

import com.jdevelopstation.commons.properties.PropertyInOut;
import com.jdevelopstation.l2ce.properties.GeneralProperties;

/**
 * @author VISTALL
 * @date 23:55/05.06.2011
 */
public class PropertiesUtils
{
	private static final PropertiesUtils _instance = new PropertiesUtils();

	private PropertyInOut _propertyInOut = new PropertyInOut();

	public static PropertiesUtils getInstance()
	{
		return _instance;
	}

	PropertiesUtils()
	{
		_propertyInOut.addMappingClass(GeneralProperties.class);
	}

	public void load()
	{
		_propertyInOut.read("properties.xml");
	}

	public void save()
	{
		_propertyInOut.write("properties.xml");
	}
}
