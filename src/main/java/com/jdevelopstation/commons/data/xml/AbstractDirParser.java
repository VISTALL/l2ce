package com.jdevelopstation.commons.data.xml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

/**
 * @author VISTALL
 * @date 9:04/19.05.2011
 */
public abstract class AbstractDirParser<H extends AbstractHolder> extends AbstractParser<H>
{
	protected AbstractDirParser(H holder)
	{
		super(holder);
	}

	public abstract File getXMLDir();

	public abstract boolean isIgnored(File f);

	@Override
	protected final void parse()
	{
		File dir = getXMLDir();

		if(!dir.exists())
		{
			warn("Dir " + dir.getAbsolutePath() + " not exists");
			return;
		}

		try
		{
			Collection<File> files = FileUtils.listFiles(dir, FileFilterUtils.suffixFileFilter(".xml"), FileFilterUtils.directoryFileFilter());

			for(File f : files)
				if(!f.isHidden())
					if(!isIgnored(f))
						try
						{
							parseDocument(new FileInputStream(f), f.getName());
						}
						catch(Exception e)
						{
							info("Exception: " + e + " in file: " + f.getName(), e);
						}
		}
		catch(Exception e)
		{
			warn("Exception: " + e, e);
		}
	}
}