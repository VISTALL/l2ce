package com.jdevelopstation.l2ce.utils;

import java.io.File;

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
			File out = new File(d, "dec-" + f.getName());
			if(out.exists())
				out.delete();

			ProcessBuilder p = new ProcessBuilder("l2encdec\\l2encdec.exe", code, f.getAbsolutePath());
			p.directory(d);

			//System.out.println("Command: l2encdec\\l2encdec.exe"+" "+ code+" "+ f.getAbsolutePath());
			Process process = p.start();

			//int retVal = process.waitFor();
			/*System.out.print("Output: ");
			while (process.getInputStream().available() > 0)
			{
				byte[] a = new byte[process.getInputStream().available()];
				process.getInputStream().read(a);
				String text = new String(a);
				text = text.replace("\n\r\n\r", "\n\r");
				text = text.replace("\r\n\r\n", "\r\n");
				System.out.print(text);
			}   */
			
		//	System.out.println("Ret val: "+retVal);
		//	if(retVal == 0)

			Thread.sleep(1000L);
			return out.exists() ? out : null;
			
			/*Process process = Runtime.getRuntime().exec("l2encdec\\l2encdec.exe "+ code+" "+ f.getAbsolutePath());
			process.waitFor();
			int retVal = process.waitFor();
			if(retVal == 0)
				return new File("dec-" + f.getName());*/
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
			File temp = new File(d, "enc-" + in.getName());
			if(temp.exists())
				temp.delete();

			ProcessBuilder p = new ProcessBuilder("l2encdec\\l2encdec.exe", code, String.valueOf(encoding), in.getAbsolutePath());
			p.directory(d);

			Process process = p.start();

			//int retVal = process.waitFor();
			//if(retVal == 0)

			Thread.sleep(5000L);
			if(!temp.exists())
				return;

			FileUtils.copyFile(temp, out);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
