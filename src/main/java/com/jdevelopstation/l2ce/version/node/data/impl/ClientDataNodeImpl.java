package com.jdevelopstation.l2ce.version.node.data.impl;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.dom4j.Element;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;
import com.jdevelopstation.l2ce.version.node.file.ClientFileNode;

/**
 * @author VISTALL
 * @date 8:42/18.05.2011
 */
public class ClientDataNodeImpl implements ClientDataNode
{
	private Object _value;

	private boolean _hidden;

	private final ClientFileNode _fileNode;

	private Map<String, String> myAttributes = Collections.emptyMap();

	public ClientDataNodeImpl(ClientFileNode node)
	{
		_fileNode = node;
	}

	public void setAttribute(String key, String value)
	{
		if(myAttributes.isEmpty())
		{
			myAttributes = new LinkedHashMap<>();
		}
		myAttributes.put(key, value);
	}

	@Override
	public String getName()
	{
		return _fileNode.getName();
	}

	public Object getValue()
	{
		return _value;
	}

	@Override
	public void toXML(Element element)
	{
		if(isHidden())
		{
			return;
		}
		if(_fileNode.getValue() != null)
		{
			return;
		}

		Element e = element.addElement(getName());
		Object temp = getValue();
		String value = String.valueOf(temp);
		for(Map.Entry<String, String> entry : myAttributes.entrySet())
		{
			e.addAttribute(entry.getKey(), entry.getValue());
		}

		if(_fileNode.isHex())
		{
			if(temp instanceof Long)
			{
				value = "0x" + Long.toHexString((Long) temp).toUpperCase();
			}
		}
		e.setText(value);
	}

	public void write(ByteBuffer buffer)
	{
		_fileNode.getReaderType().write(_value, buffer);
	}

	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "[name: " + getName() + "; value: " + _value + "]";
	}

	@Override
	public boolean isHidden()
	{
		return _hidden;
	}

	@Override
	public void setHidden(boolean hidden)
	{
		_hidden = hidden;
	}

	@Override
	public void setValue(Object value)
	{
		_value = value;
	}

	public ClientFileNode getFileNode()
	{
		return _fileNode;
	}
}
