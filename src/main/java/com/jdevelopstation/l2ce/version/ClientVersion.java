package com.jdevelopstation.l2ce.version;

import java.util.Set;
import java.util.TreeSet;

import com.jdevelopstation.l2ce.version.node.file.ClientFile;

/**
 * @author VISTALL
 * @date 8:02/18.05.2011
 */
public class ClientVersion
{
	private final Set<ClientFile> _clientFiles = new TreeSet<ClientFile>();
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

	@Override
	public String toString()
	{
		return _name;
	}
}
