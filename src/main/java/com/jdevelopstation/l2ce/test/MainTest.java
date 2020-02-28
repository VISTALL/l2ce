package com.jdevelopstation.l2ce.test;

import java.io.File;
import java.util.Collections;

import com.jdevelopstation.commons.logging.Log4JHelper;
import com.jdevelopstation.l2ce.data.xml.holder.ClientVersionHolder;
import com.jdevelopstation.l2ce.data.xml.parser.ClientVersionParser;
import com.jdevelopstation.l2ce.version.ClientVersion;
import com.jdevelopstation.l2ce.version.node.data.ClientData;
import com.jdevelopstation.l2ce.version.node.file.ClientFile;

/**
 * @author VISTALL
 * @date 1:24/26.05.2011
 */
public class MainTest
{
	public static void main(String... arg)
	{
		Log4JHelper.load();
		ClientVersionParser.getInstance().load();

		ClientVersion version = ClientVersionHolder.getInstance().getVersion("GoD_Salvation");
		ClientFile f = null;
		for(ClientFile f2 : version.getClientFiles())
			if(f2.match("msconditiondata.dat"))
				f = f2;
		if(f == null)
			return;

		ClientData data = f.parse(new File("W:\\_github.com\\VISTALL\\l2ce\\dist\\dec-msconditiondata.dat"), Collections.emptySet());
		data.toXML("C:/msconditiondata.xml");
	}
}
