package com.jdevelopstation.l2ce.version;

import com.jdevelopstation.l2ce.version.node.file.ClientFile;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author VISTALL
 * @date 8:02/18.05.2011
 */
public class ClientVersion
{
	private final Set<ClientFile> _clientFiles = new TreeSet<>();
	private final String _name;

	public ClientVersion(String name)
	{
		_name = name;
	}

	public void addFile(ClientFile f)
	{
		_clientFiles.add(f);
	}

	public String getName()
	{
		return _name;
	}

	public Set<ClientFile> getClientFiles()
	{
		return _clientFiles;
	}

	public ClientFile findClientFile(String name)
	{
		for(ClientFile clientFile : _clientFiles)
		{
			if(clientFile.match(name))
			{
				return clientFile;
			}
		}
		return null;
	}

	@Override
	public String toString()
	{
		return _name;
	}
}
