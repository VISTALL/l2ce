package com.jdevelopstation.l2ce.version.node.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.regex.Pattern;

import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientData;

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

	public ClientData parse(File f)
	{
		try
		{
			InputStream stream = new FileInputStream(f);
			byte[] data = new byte[stream.available()];
			stream.read(data);
			stream.close();

			ByteBuffer buf = ByteBuffer.wrap(data);
			buf.order(ByteOrder.LITTLE_ENDIAN);

			ClientData clientData = new ClientData(f.getName());
			for(ClientFileNode node : getNodes())
				node.parse(clientData, buf);

			return clientData;
		}
		catch(IOException e)
		{
			log.error(e);
			return null;
		}
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
