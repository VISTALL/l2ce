package com.jdevelopstation.l2ce.version.node.file.impl;

import java.nio.ByteBuffer;

import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;
import com.jdevelopstation.l2ce.version.node.file.ClientFileNode;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.impl.ClientDataBlockNodeImpl;
import com.jdevelopstation.l2ce.version.node.data.impl.ClientDataForNodeImpl;
import com.jdevelopstation.l2ce.version.node.file.reader.ReadWriteType;

/**
 * @author VISTALL
 * @date 8:53/18.05.2011
 */
public class ClientFileForNodeImpl extends ClientNodeContainer<ClientFileNode> implements ClientFileNode
{
	private final String _forName;
	private final int _fixed;

	public ClientFileForNodeImpl(String name, int fixed)
	{
		_forName = name;
		_fixed = fixed;
	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public ReadWriteType getReaderType()
	{
		return null;
	}

	@Override
	public String getValue()
	{
		return null;
	}

	@Override
	public boolean isHex()
	{
		return false;
	}

	@Override
	public void parse(ClientNodeContainer<ClientDataNode> parent, ByteBuffer buff, long index)
	{
		long val;
		if(_fixed > 0)
			val = _fixed;
		else
		{
			ClientDataNode node = parent.getNodeByName(_forName);
			if(node == null || !(node.getValue() instanceof Number))
				return;

			node.setHidden(true);
			val = ((Number) node.getValue()).longValue();
		}

		ClientDataForNodeImpl forNode = new ClientDataForNodeImpl(this);
		parent.add(forNode);

		for(long i = 0; i < val; i++)
		{
			ClientDataBlockNodeImpl blockNode = new ClientDataBlockNodeImpl(this, i);
			forNode.add(blockNode);

			for(ClientFileNode sub : this)
			{
				sub.parse(blockNode, buff, i);
			}
		}
	}

	public String getForName()
	{
		return _forName;
	}

	public int getFixed()
	{
		return _fixed;
	}
}
