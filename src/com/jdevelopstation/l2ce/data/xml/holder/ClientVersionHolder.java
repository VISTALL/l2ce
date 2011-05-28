package com.jdevelopstation.l2ce.data.xml.holder;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.jdevelopstation.commons.data.xml.AbstractHolder;
import com.jdevelopstation.l2ce.version.ClientVersion;

/**
 * @author VISTALL
 * @date 8:10/18.05.2011
 */
public class ClientVersionHolder  extends AbstractHolder
{
	private static final Logger _log = Logger.getLogger(ClientVersionHolder.class);
	private static ClientVersionHolder _instance = new ClientVersionHolder();

	private Map<String, ClientVersion> _versionMap = new HashMap<String, ClientVersion>();

	public static ClientVersionHolder getInstance()
	{
		return _instance;
	}

	private ClientVersionHolder()
	{
	}

	public void addVersion(ClientVersion v)
	{
		_versionMap.put(v.getName(), v);
	}

	public ClientVersion getVersion(String name)
	{
		return _versionMap.get(name);
	}

	@Override
	public int size()
	{
		return _versionMap.size();
	}

	@Override
	public void clear()
	{
		_versionMap.clear();
	}

	public Map<String, ClientVersion> getVersionMap()
	{
		return _versionMap;
	}
}
