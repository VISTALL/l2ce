package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author VISTALL
 * @date 9:29/18.05.2011
 */
public class ASCF implements ReadWriteType<String>
{
	private static final SHORT_SHORT compactImpl = new SHORT_SHORT();
	private static Charset utf16leCharset = Charset.forName("utf-16le");
	private static Charset defaultCharset = Charset.forName("cp1252");

	private static final boolean isRaw = true;

	@Override
	public String read(ByteBuffer buff)
	{
		int len = compactImpl.read(buff);
		if(len == 0)
		{
			return "";
		}
		byte[] bytes = new byte[len > 0 ? len : -2 * len];
		buff.get(bytes);
		return checkAndReplaceNewLine(isRaw, new String(bytes, 0, bytes.length - (len > 0 ? 1 : 2), len > 0 ? defaultCharset : utf16leCharset).intern());
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(!(val instanceof String))
		{
			throw new UnsupportedOperationException();
		}

		String stringValue = (String) val;
		if(stringValue.isEmpty())
		{
			compactImpl.write(0, buff);
			return;
		}

		if(!isRaw)
		{
			stringValue = checkAndReplaceNewLine(stringValue);
		}
		stringValue = stringValue + '\000';

		boolean def = defaultCharset.newEncoder().canEncode(stringValue);
		byte[] bytes = stringValue.getBytes(def ? defaultCharset : utf16leCharset);
		byte[] bSize = compactImpl.compactIntToByteArray(def ? bytes.length : -bytes.length / 2);

		buff.put(bSize);
		buff.put(bytes);
	}

	public static String checkAndReplaceNewLine(String str)
	{
		if(str.contains("\\r\\n"))
		{
			str = str.replace("\\r\\n", "\r\n");
		}
		return str;
	}

	public static String checkAndReplaceNewLine(boolean isRaw, String str)
	{
		if(!isRaw && str.contains("\r\n"))
		{
			str = str.replace("\r\n", "\\r\\n");
		}
		return str;
	}
}
