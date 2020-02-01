package com.jdevelopstation.l2ce.version.node.file;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import com.jdevelopstation.l2ce.gui.etc.FileLoadInfo;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientData;

/**
 * @author VISTALL
 * @date 8:04/18.05.2011
 */
public class ClientFile extends ClientNodeContainer<ClientFileNode> implements Comparable<ClientFile>
{
	private final Pattern _pattern;

	private final String dataNodeName;

	public ClientFile(String pattern)
	{
		this(pattern, "data");
	}

	public ClientFile(String pattern, String dataNodeName)
	{
		this.dataNodeName = dataNodeName;
		_pattern = Pattern.compile(pattern.toLowerCase());
	}

	public String getDataNodeName()
	{
		return dataNodeName;
	}

	public ClientData parse(File file, Set<FileLoadInfo> fileLoadInfos)
	{
		try
		{
			return parse(file.getName(), Files.readAllBytes(file.toPath()), fileLoadInfos);
		}
		catch(IOException e)
		{
			log.error(e);
			return null;
		}
	}

	public ClientData parse(String name, byte[] data, Set<FileLoadInfo> fileLoadInfos)
	{
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		ClientData clientData = new ClientData(name);
		for(ClientFileNode node : getNodes())
		{
			node.parse(clientData, buf, -1, fileLoadInfos, new ClientFileNodeContext(clientData));
		}

		return clientData;
	}

	public boolean match(String fileName)
	{
		return getPattern().matcher(fileName.toLowerCase(Locale.US)).find();
	}

	@Override
	public int compareTo(ClientFile o)
	{
		return o._pattern.pattern().compareTo(_pattern.pattern());
	}

	public Pattern getPattern()
	{
		return _pattern;
	}
}
