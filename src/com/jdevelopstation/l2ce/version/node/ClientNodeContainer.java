package com.jdevelopstation.l2ce.version.node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author VISTALL
 * @date 9:00/18.05.2011
 */
public abstract class ClientNodeContainer<NODE extends ClientNode>  extends ArrayList<NODE> implements Iterable<NODE>
{
	public NODE getNodeByName(String name)
	{
		for(NODE node : this)
		{
			String name2 = node.getName();
			if(name2 != null && name.equals(name2))
				return node;
		}
		return null;
	}

	public List<NODE> getNodes()
	{
		return this;
	}
}
