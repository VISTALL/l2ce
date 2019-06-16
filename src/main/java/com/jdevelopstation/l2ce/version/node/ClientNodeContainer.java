package com.jdevelopstation.l2ce.version.node;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author VISTALL
 * @date 9:00/18.05.2011
 */
public abstract class ClientNodeContainer<NODE extends ClientNode> implements Iterable<NODE>
{
	protected static final Logger log = Logger.getLogger(ClientNodeContainer.class);

	private final List<NODE> list = new ArrayList<>();

	public NODE getNodeByName(String name)
	{
		for(NODE node : this)
		{
			String name2 = node.getName();
			if(name2 != null && name.equals(name2))
			{
				return node;
			}
		}
		return null;
	}

	public NODE get(int i)
	{
		return list.get(i);
	}

	public int size()
	{
		return list.size();
	}

	public void add(NODE node)
	{
		list.add(node);
	}

	public List<NODE> getNodes()
	{
		return list;
	}

	@Override
	public Iterator<NODE> iterator()
	{
		return list.iterator();
	}
}
