package com.jdevelopstation.l2ce.utils;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

/**
 * @author VISTALL
 * @date 1:24/26.05.2011
 */
public class L2EncDec
{
	public static File decode(File f, String code)
	{
		try
		{
			File d = new File(System.getProperty("java.io.tmpdir"));

			ProcessBuilder p = new ProcessBuilder("l2encdec\\l2encdec.exe", code, f.getAbsolutePath());
			p.directory(d);

			Process process = p.start();

			int retVal = process.waitFor();
			if(retVal == 0)
				return new File(d, "dec-" + f.getName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void encode(File in, File out, String code, int encoding)
	{
		try
		{
			File d = new File(System.getProperty("java.io.tmpdir"));

			ProcessBuilder p = new ProcessBuilder("l2encdec\\l2encdec.exe", code, String.valueOf(encoding), in.getAbsolutePath());
			p.directory(d);

			Process process = p.start();

			int retVal = process.waitFor();
			if(retVal == 0)
				FileUtils.copyFile(new File(d, "enc-" + in.getName()), out);

			InputStream st = process.getInputStream();
			byte[] bytes = new byte[st.available()];
			st.read(bytes);

			System.out.println(new String(bytes));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}