package com.jdevelopstation.l2ce.version.node.file;

import java.util.HashMap;
import java.util.Map;

import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;

/**
 * @author VISTALL
 * @since 2020-01-24
 */
public class ClientFileNodeContext
{
	private Map<String, Object> map = new HashMap<>();

	private ClientNodeContainer<ClientDataNode> fileNode;

	public ClientFileNodeContext(ClientNodeContainer<ClientDataNode> fileNode)
	{
		this.fileNode = fileNode;
	}

	public ClientNodeContainer<ClientDataNode> getFileNode()
	{
		return fileNode;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key)
	{
		return (T) map.get(key);
	}

	public void put(String key, Object value)
	{
		map.put(key, value);
	}
}
