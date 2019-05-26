package com.jdevelopstation.l2ce;

import com.jdevelopstation.commons.logging.Log4JHelper;
import com.jdevelopstation.l2ce.data.xml.holder.ClientVersionHolder;
import com.jdevelopstation.l2ce.data.xml.parser.ClientVersionParser;
import com.jdevelopstation.l2ce.utils.BundleUtils;
import com.jdevelopstation.l2ce.utils.L2CryptSupport;
import com.jdevelopstation.l2ce.utils.PropertiesUtils;
import com.jdevelopstation.l2ce.version.ClientVersion;
import com.jdevelopstation.l2ce.version.node.data.ClientData;
import com.jdevelopstation.l2ce.version.node.file.ClientFile;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @author VISTALL
 * @since 2019-05-26
 */
public class CommandLine
{
	public static void main(String[] args) throws Exception
	{
		if(args.length != 1)
		{
			System.out.println("expected <clientVer>");
			System.exit(-1);
		}

		Log4JHelper.load();
		BundleUtils.getInstance();
		PropertiesUtils.getInstance();
		//ThreadPoolManager.getInstance();
		//ClientVersionParser.getInstance().load();
		PropertiesUtils.getInstance().load();

		String clientVer = args[0];

		File clientVerFile = new File(clientVer + ".xml");

		ClientVersionParser.getInstance().parseFile(clientVerFile);

		ClientVersion clientVersion = ClientVersionHolder.getInstance().getVersion(clientVer);
		if(clientVersion == null)
		{
			System.out.println("Can't find client version");
			System.exit(-1);
		}

		File xmlDirectory = new File("xml");
		File datDirectory = new File("dat");
		FileUtils.deleteDirectory(datDirectory);
		datDirectory.mkdir();

		for(File xmlFile : xmlDirectory.listFiles(pathname -> pathname.getName().endsWith(".xml")))
		{
			String datName = findDatName(xmlFile);

			System.out.println("processing: " + xmlFile);

			ClientFile clientFile = clientVersion.findClientFile(datName);
			if(clientFile == null)
			{
				throw new IllegalArgumentException("No dat info for file: " + datName);
			}

			ClientData clientData = new ClientData(datName);

			if(!clientData.fromXML(clientVersion, xmlFile))
			{
				throw new IllegalArgumentException("Failed read file: " + xmlFile);
			}

			File tempFile = File.createTempFile("l2ce", "dat");

			clientData.toDat(tempFile);

			File newFile = new File(datDirectory, datName);

			L2CryptSupport.getInstance().encode(tempFile, newFile, "-h", 413);
		}
	}

	private static String findDatName(File file) throws Exception
	{
		SAXReader reader = new SAXReader();
		reader.setValidation(false);

		Document document = reader.read(file);

		Element rootElement = document.getRootElement();

		String name = rootElement.attributeValue("name");
		if(name == null)
		{
			throw new IllegalArgumentException("Name is null");
		}
		return name;
	}
}
