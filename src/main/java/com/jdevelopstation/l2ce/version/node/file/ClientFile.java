package com.jdevelopstation.l2ce.version.node.file;

import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientData;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author VISTALL
 * @date 8:04/18.05.2011
 */
public class ClientFile extends ClientNodeContainer<ClientFileNode> implements Comparable<ClientFile>
{
	private final Pattern _pattern;

	public ClientFile(String pattern)
	{
		_pattern = Pattern.compile(pattern.toLowerCase());
	}

	public ClientData parse(File file)
	{
		try
		{
			return parse(file.getName(), Files.readAllBytes(file.toPath()));
		}
		catch(IOException e)
		{
			log.error(e);
			return null;
		}
	}

	public ClientData parse(String name, byte[] data)
	{
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		ClientData clientData = new ClientData(name);
		for(ClientFileNode node : getNodes())
		{
			node.parse(clientData, buf, -1);
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
