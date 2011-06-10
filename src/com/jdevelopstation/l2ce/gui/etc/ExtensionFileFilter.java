package com.jdevelopstation.l2ce.gui.etc;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter
{
	protected String description;
	protected String extensions[];

	public ExtensionFileFilter(String description, String extension)
	{
		this(description, new String[]
		{
			extension
		});
	}

	public ExtensionFileFilter(String description, String extensions[])
	{
		if (description == null)
			this.description = extensions[0];
		else
			this.description = description;
		this.extensions = extensions.clone();
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public boolean accept(File f)
	{
		if (f.isDirectory())
		{
			return true;
		}
		else
		{
			String path = f.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++)
			{
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.'))
					return true;
			}
		}
		return false;
	}
	
	public String getFirstExtension()
	{
		return extensions[0];
	}
}