package com.jdevelopstation.l2ce.data.xml.parser;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import com.jdevelopstation.commons.data.xml.AbstractDirParser;
import com.jdevelopstation.l2ce.data.xml.holder.ClientVersionHolder;
import com.jdevelopstation.l2ce.version.ClientVersion;
import com.jdevelopstation.l2ce.version.node.file.ClientFile;
import com.jdevelopstation.l2ce.version.node.file.ClientFileNode;
import com.jdevelopstation.l2ce.version.node.ClientNodeContainer;
import com.jdevelopstation.l2ce.version.node.file.ClientFileNodeModifier;
import com.jdevelopstation.l2ce.version.node.file.impl.ClientFileForNodeImpl;
import com.jdevelopstation.l2ce.version.node.file.impl.ClientFileNodeImpl;

/**
 * @author VISTALL
 * @date 8:12/18.05.2011
 */
public class ClientVersionParser extends AbstractDirParser<ClientVersionHolder>
{
	private static final Logger _log = Logger.getLogger(ClientVersionParser.class);
	private static ClientVersionParser _instance = new ClientVersionParser();

	public static ClientVersionParser getInstance()
	{
		return _instance;
	}

	private ClientVersionParser()
	{
		super(ClientVersionHolder.getInstance());
	}

	@Override
	public File getXMLDir()
	{
		return new File("./version/");
	}

	@Override
	public boolean isIgnored(File f)
	{
		return false;
	}

	@Override
	protected void readData(Element rootElement) throws Exception
	{
		String name = rootElement.attributeValue("name");
		ClientVersion v = new ClientVersion(name);

		Loop:
		for(Element fileElement : rootElement.elements())
		{
			String pattern = fileElement.attributeValue("pattern");
			ClientFile file = new ClientFile(pattern);

			parseNodes(file, fileElement);


			for(ClientFileNode node : file)
				if(!validate(file, node))
				{
					info("File - pattern: " + pattern + " is invalid.");
					continue Loop;
				}

			v.addFile(file);
		}

		getHolder().addVersion(v);
	}

	private boolean validate(ClientNodeContainer<ClientFileNode> container, ClientFileNode node)
	{
		if(node instanceof ClientFileForNodeImpl)
		{
			ClientFileForNodeImpl forNode = (ClientFileForNodeImpl)node;
			if(forNode.getFixed() > 0)
				return true;

			ClientFileNode forSizeNode = container.getNodeByName(forNode.getForName());
			if(forSizeNode == null)
				return false;
			else
			{
				for(ClientFileNode sub : forNode.getNodes())
					if(!validate(forNode, sub))
						return false;
			}
		}
		return true;
	}

	private void parseNodes(ClientNodeContainer<ClientFileNode> con, Element e)
	{
		for(Element nodeElement : e.elements())
		{
			if(nodeElement.getName().equalsIgnoreCase("node"))
			{
				String name = nodeElement.attributeValue("name");
				String reader = nodeElement.attributeValue("reader");
				String value = nodeElement.attributeValue("value");

				ClientFileNodeModifier modifier = null;
				Element modifierElement = nodeElement.element("modifier");
				if(modifierElement != null)
				{
					modifier = new ClientFileNodeModifier(modifierElement.attributeValue("pattern"));
					for(Element sub : modifierElement.elements())
						modifier.getNodeList().add(sub.attributeValue("name"));
				}
				con.add(new ClientFileNodeImpl(name, value, reader, modifier));
			}
			else if(nodeElement.getName().equalsIgnoreCase("for"))
			{
				String name = nodeElement.attributeValue("name");
				String fixed = nodeElement.attributeValue("fixed");
				ClientFileForNodeImpl forNode = new ClientFileForNodeImpl(name, fixed == null ? 0 : Integer.parseInt(fixed));
				con.add(forNode);

				parseNodes(forNode, nodeElement);
			}
		}
	}
}
