package com.jdevelopstation.l2ce.version.node.file;

import java.nio.ByteBuffer;

import com.jdevelopstation.l2ce.version.node.ClientNode;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;

/**
 * @author VISTALL
 * @date 9:13/18.05.2011
 */
public interface ClientFileNode extends ClientNode
{
	public void parse(ClientNodeContainer<ClientDataNode> parent, ByteBuffer buff);

	public String getName();

	String getValue();
}
