package com.jdevelopstation.commons.properties.listeners;

/**
 * @author VISTALL
 * @date 1:31/06.06.2011
 */
public class PropertyEvent
{
	private final Class<?> _propertyClass;

	public PropertyEvent(Class<?> propertyClass)
	{
		_propertyClass = propertyClass;
	}

	public Class<?> getPropertyClass()
	{
		return _propertyClass;
	}
}
