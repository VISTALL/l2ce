package com.jdevelopstation.l2ce.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import acmi.l2.clientmod.crypt.L2Crypt;

/**
 * @author VISTALL
 * @since 2018-05-06
 */
public class L2CryptBuildIn implements L2CryptSupport
{
	private static final Logger log = Logger.getLogger(L2CryptBuildIn.class);

	public File decode(File encryptedFile, String code)
	{
		try
		{
			File d = new File(System.getProperty("java.io.tmpdir"));
			File out = new File(d, "dec-" + encryptedFile.getName());
			if(out.exists())
			{
				out.delete();
			}

			try (InputStream is = L2Crypt.getInputStream(encryptedFile); OutputStream os = new FileOutputStream(out))
			{
				byte[] buffer = new byte[0x1000];
				int r;
				while((r = is.read(buffer)) != -1)
				{
					os.write(buffer, 0, r);
				}
			}
		}
		catch(IOException e)
		{
			log.error(e);
		}
		return null;
	}

	public void encode(File in, File out, String code, int encoding)
	{
		try
		{
			File d = new File(System.getProperty("java.io.tmpdir"));
			File temp = new File(d, "enc-" + in.getName());
			if(temp.exists())
			{
				temp.delete();
			}

			try (OutputStream os = L2Crypt.getOutputStream(temp, encoding); InputStream is = Files.newInputStream(in.toPath()))
			{
				byte[] buffer = new byte[0x1000];
				int r;
				while((r = is.read(buffer)) != -1)
				{
					os.write(buffer, 0, r);
				}
			}
			FileUtils.copyFile(temp, out);
		}
		catch(IOException e)
		{
			log.error(e);
		}
	}
}
