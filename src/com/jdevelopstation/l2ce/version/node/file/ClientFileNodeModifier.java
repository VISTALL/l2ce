package com.jdevelopstation.l2ce.version.node.file;

import java.util.ArrayList;
import java.util.List;

import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;

/**
 * @author VISTALL
 * @date 1:42/27.06.2011
 */
public class ClientFileNodeModifier
{
	private List<String> _nodeList = new ArrayList<String>();
 	private String _pattern;

	public ClientFileNodeModifier(String pattern)
	{
		_pattern = pattern;
	}

	public void modify(ClientDataNode data, ClientNodeContainer<ClientDataNode> container)
	{
		Object[] values = new Object[_nodeList.size()];
		for(int i = 0; i < values.length; i++)
			values[i] = container.getNodeByName(_nodeList.get(i)).getValue();

		data.setValue(String.format(_pattern, values));
	}

	public List<String> getNodeList()
	{
		return _nodeList;
	}

	public String getPattern()
	{
		return _pattern;
	}
}
