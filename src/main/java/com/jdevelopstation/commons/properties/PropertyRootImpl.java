package com.jdevelopstation.commons.properties;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.jdevelopstation.commons.properties.ann.Property;

/**
 * @author VISTALL
 * @date 0:16/06.06.2011
 */
public class PropertyRootImpl
{
	private final String _name;
	private Map<Property, Field> _fieldMap = new HashMap<Property, Field>();

	public PropertyRootImpl(String name)
	{
		_name = name;
	}

	public void addField(Property p, Field f)
	{
		_fieldMap.put(p, f);
	}

	public Map<Property, Field> getFieldMap()
	{
		return _fieldMap;
	}

	public String getName()
	{
		return _name;
	}
}
