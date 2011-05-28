package com.jdevelopstation.l2ce.test;

import java.io.File;

import com.jdevelopstation.l2ce.utils.L2EncDec;

/**
 * @author VISTALL
 * @date 1:24/26.05.2011
 */
public class MainTest
{
	public static void main(String... arg)
	{
		File f2 = L2EncDec.decode(new File("D:\\MyTests\\l2encdec\\actionname-k.dat"), "-l");

		System.out.println(f2.getAbsolutePath());

		L2EncDec.encode(f2, new File("C:/actionname-k.dat"), "-h", 413);
	}
}
