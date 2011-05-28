package com.jdevelopstation.l2ce.version.node.data.impl;

import org.dom4j.Element;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;
import com.jdevelopstation.l2ce.version.node.file.impl.ClientFileForNodeImpl;

/**
 * @author VISTALL
 * @date 13:22/18.05.2011
 */
public class ClientDataBlockNodeImpl extends ClientNodeContainer<ClientDataNode> implements ClientDataNode
{
	private ClientFileForNodeImpl _forNode;
	private long _index;

	public ClientDataBlockNodeImpl(ClientFileForNodeImpl forNode, long index)
	{
		_forNode = forNode;
		_index = index;
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
		Element next = e.addElement(_forNode.getForName());
		for(ClientDataNode node : getNodes())
			node.toXML(next);
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
		return getClass().getSimpleName() + ": " + _forNode.getForName() + "; i: " + _index;
	}

	public ClientFileForNodeImpl getForNode()
	{
		return _forNode;
	}

	public void setForNode(ClientFileForNodeImpl forNode)
	{
		_forNode = forNode;
	}
}
