package com.jdevelopstation.l2ce.utils;


import acmi.l2.clientmod.crypt.L2Crypt;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;

/**
 * @author VISTALL
 * @since 2018-05-06
 */
public class L2CryptBuildIn implements L2CryptSupport
{
	private static final Logger log = Logger.getLogger(L2CryptBuildIn.class);

	public Pair<String, byte[]> decode(File encryptedFile, String code)
	{
		try
		{
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			try (InputStream is = L2Crypt.getInputStream(encryptedFile))
			{
				is.transferTo(stream);
			}
			return Pair.of(encryptedFile.getName(), stream.toByteArray());
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
