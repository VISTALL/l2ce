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
		String text = new String(bytes, 0, bytes.length - (len > 0 ? 1 : 2), len > 0 ? defaultCharset : utf16leCharset).intern();
		text = text.replace("\r\n", "\n");
		return text;
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

		stringValue = stringValue.replace("\n", "\r\n");

		stringValue = stringValue + '\000';

		boolean def = defaultCharset.newEncoder().canEncode(stringValue);
		byte[] bytes = stringValue.getBytes(def ? defaultCharset : utf16leCharset);
		byte[] bSize = compactImpl.compactIntToByteArray(def ? bytes.length : -bytes.length / 2);

		buff.put(bSize);
		buff.put(bytes);
	}
}
