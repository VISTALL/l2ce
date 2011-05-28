package com.jdevelopstation.l2ce.version.node.data.impl;

import org.dom4j.Element;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;

/**
 * @author VISTALL
 * @date 9:24/18.05.2011
 */
public class ClientDataForNodeImpl extends ClientNodeContainer<ClientDataNode> implements ClientDataNode
{
	private final String _forName;

	private ClientDataNode _sizeNode;

	public ClientDataForNodeImpl(String forName)
	{
		_forName = forName;
	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public Object getValue()
	{
		return null;
	}

	@Override
	public void setValue(Object val)
	{

	}

	@Override
	public void toXML(Element e)
	{
		for(ClientDataNode node : getNodes())
			node.toXML(e);
	}

	@Override
	public void setHidden(boolean hidden)
	{

	}

	@Override
	public boolean isHidden()
	{
		return false;
	}

	@Override
	public String toString()
	{
		return getClass().getSimpleName() + ": " + _forName;
	}

	public ClientDataNode getSizeNode()
	{
		return _sizeNode;
	}

	public void setSizeNode(ClientDataNode sizeNode)
	{
		_sizeNode = sizeNode;
	}

	public String getForName()
	{
		return _forName;
	}
}
