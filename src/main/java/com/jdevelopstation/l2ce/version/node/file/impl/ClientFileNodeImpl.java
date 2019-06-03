package com.jdevelopstation.l2ce.version.node.file.impl;

import com.jdevelopstation.l2ce.gui.etc.FileLoadInfo;
import com.jdevelopstation.l2ce.version.node.ClientNodeAttribute;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.data.ClientData;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;
import com.jdevelopstation.l2ce.version.node.data.impl.ClientDataForNodeImpl;
import com.jdevelopstation.l2ce.version.node.data.impl.ClientDataNodeImpl;
import com.jdevelopstation.l2ce.version.node.file.ClientFileNode;
import com.jdevelopstation.l2ce.version.node.file.ClientFileNodeModifier;
import com.jdevelopstation.l2ce.version.node.file.reader.ReadWriteType;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

/**
 * @author VISTALL
 * @date 8:07/18.05.2011
 */
public class ClientFileNodeImpl implements ClientFileNode
{
	private ReadWriteType<?> _partType;
	private String _name;
	private String _value;
	private ClientFileNodeModifier _modifier;
	private boolean hex;
	private final List<ClientNodeAttribute> myAttributes;

	public ClientFileNodeImpl(String name, String value, String reader, ClientFileNodeModifier modifier, boolean hex, List<ClientNodeAttribute> attributes)
	{
		_name = name;
		_value = value;
		_modifier = modifier;
		this.hex = hex;
		myAttributes = attributes;
		try
		{
			Class<?> clazz = Class.forName("com.jdevelopstation.l2ce.version.node.file.reader." + reader);
			_partType = (ReadWriteType) clazz.newInstance();
		}
		catch(Exception e)
		{
			throw new Error(e);
		}
	}

	public List<ClientNodeAttribute> getAttributes()
	{
		return myAttributes;
	}

	@Override
	public void parse(ClientNodeContainer<ClientDataNode> parent, ByteBuffer buff, long index, Set<FileLoadInfo> fileLoadInfos)
	{
		ClientDataNodeImpl node = new ClientDataNodeImpl(this);
		node.setValue(_value != null ? _value : _partType.read(buff));

		parent.add(node);

		if(!myAttributes.isEmpty())
		{
			for(ClientNodeAttribute attribute : myAttributes)
			{
				String expression = attribute.getExpression();
				if("$i".equals(expression))
				{
					node.setAttribute(attribute.getName(), String.valueOf(index));
				}
				else if("$l2gamedataname".equals(expression))
				{
					Object value = node.getValue();
					if(!(value instanceof Number))
					{
						continue;
					}

					int intValue = ((Number) value).intValue();

					ClientData loadedClientData = null;
					for(FileLoadInfo info : fileLoadInfos)
					{
						File file = info.getFile();
						if(StringUtils.containsIgnoreCase(file.getName(), "l2gamedataname"))
						{
							ClientData clientData = info.getClientData();
							if(clientData != null)
							{
								loadedClientData = clientData;
								break;
							}
						}
					}

					if(loadedClientData == null)
					{
						continue;
					}

					for(ClientDataNode dataNode : loadedClientData.getNodes())
					{
						if(dataNode instanceof ClientDataForNodeImpl)
						{
							List<ClientDataNode> nodes = ((ClientDataForNodeImpl) dataNode).getNodes();

							if(intValue < nodes.size() && intValue >= 0)
							{
								ClientDataNode l2gameValue = nodes.get(intValue);

								node.setAttribute(attribute.getName(), String.valueOf(l2gameValue.getValue()));
							}
						}
					}
				}
				else
				{
					throw new UnsupportedOperationException();
				}
			}
		}
	}

	@Override
	public String getName()
	{
		return _name;
	}

	@Override
	public ReadWriteType getReaderType()
	{
		return _partType;
	}

	@Override
	public String getValue()
	{
		return _value;
	}

	@Override
	public boolean isHex()
	{
		return hex;
	}

	public ClientFileNodeModifier getModifier()
	{
		return _modifier;
	}
}
