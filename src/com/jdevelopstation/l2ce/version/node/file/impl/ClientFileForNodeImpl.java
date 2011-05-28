package com.jdevelopstation.l2ce.version.node.file.impl;

import java.nio.ByteBuffer;

import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;
import com.jdevelopstation.l2ce.version.node.file.ClientFileNode;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.impl.ClientDataBlockNodeImpl;
import com.jdevelopstation.l2ce.version.node.data.impl.ClientDataForNodeImpl;

/**
 * @author VISTALL
 * @date 8:53/18.05.2011
 */
public class ClientFileForNodeImpl extends ClientNodeContainer<ClientFileNode> implements ClientFileNode
{
	private final String _forName;

	public ClientFileForNodeImpl(String name)
	{
		_forName = name;
	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public String getValue()
	{
		return null;
	}

	@Override
	public void parse(ClientNodeContainer<ClientDataNode> parent, ByteBuffer buff)
	{
		ClientDataNode node = parent.getNodeByName(_forName);
		if(node == null || !(node.getValue() instanceof Number))
			return;

		node.setHidden(true);
		long val = ((Number) node.getValue()).longValue();

		ClientDataForNodeImpl forNode = new ClientDataForNodeImpl(_forName);
		parent.add(forNode);

		for(long i = 0; i < val; i++)
		{
			ClientDataBlockNodeImpl blockNode = new ClientDataBlockNodeImpl(this, i);
			forNode.add(blockNode);

			for(ClientFileNode sub : this)
				sub.parse(blockNode, buff);
		}
	}

	public String getForName()
	{
		return _forName;
	}
}
