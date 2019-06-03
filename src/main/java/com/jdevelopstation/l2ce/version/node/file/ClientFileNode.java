package com.jdevelopstation.l2ce.version.node.file;

import com.jdevelopstation.l2ce.gui.etc.FileLoadInfo;
import com.jdevelopstation.l2ce.version.node.ClientNode;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;
import com.jdevelopstation.l2ce.version.node.file.reader.ReadWriteType;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * @author VISTALL
 * @date 9:13/18.05.2011
 */
public interface ClientFileNode extends ClientNode
{
	public void parse(ClientNodeContainer<ClientDataNode> parent, ByteBuffer buff, long index, Set<FileLoadInfo> fileLoadInfos);

	public String getName();

	ReadWriteType getReaderType();

	String getValue();

	boolean isHex();
}
