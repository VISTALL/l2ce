package com.jdevelopstation.l2ce.utils;


import acmi.l2.clientmod.crypt.L2Crypt;
import org.apache.commons.io.IOUtils;
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

	public void encode(File in, File out, String code, int encoding) throws Exception
	{
		if(out.exists())
		{
			out.delete();
		}

		try (OutputStream os = L2Crypt.getOutputStream(out, encoding); InputStream is = Files.newInputStream(in.toPath()))
		{
			IOUtils.copy(is, os);
		}
	}
}
