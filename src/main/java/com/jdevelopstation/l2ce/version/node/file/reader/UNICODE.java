package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @date 10:14/18.05.2011
 */
public class UNICODE implements ReadWriteType<String>
{
	@Override
	public String read(ByteBuffer buff)
	{
		int bytesCount = buff.getInt();

		int charsCount = bytesCount / 2;

		char[] chars = new char[charsCount];

		for(int i = 0; i < charsCount; i ++)
		{
			chars[i] = buff.getChar();
		}

		// normalize Windows lineseparator
		String text = new String(chars);
		text = text.replace("\r\n", "\n");
		return text;
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof String)
		{
			String stringVal = (String) val;
			stringVal = stringVal.replace("\n", "\r\n");

			buff.putInt(stringVal.length() * 2);
			for(int i = 0; i < stringVal.length(); i++)
			{
				char c = stringVal.charAt(i);
				buff.putChar(c);
			}
		}
	}
}
