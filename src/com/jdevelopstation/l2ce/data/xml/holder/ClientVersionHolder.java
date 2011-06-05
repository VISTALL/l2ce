package com.jdevelopstation.l2ce.data.xml.holder;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.jdevelopstation.commons.data.xml.AbstractHolder;
import com.jdevelopstation.l2ce.properties.GeneralProperties;
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

	public ClientVersion getCurrentVersion()
	{
		return getVersion(GeneralProperties.CURRENT_VERSION);
	}

	public ClientVersion getVersion(String name)
	{
		return name == null ? null : _versionMap.get(name);
	}

	public Map<String, ClientVersion> getVersionMap()
	{
		return _versionMap;
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
}
