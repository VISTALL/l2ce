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
		int size = buff.getInt();
		int real = size / 2;

		StringBuilder stringBuilder = new StringBuilder(size);

		for (int i = 0; i < real; i++)
			stringBuilder.append(buff.getChar());

		return stringBuilder.toString();
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof String)
		{
			String stringVal = (String)val;
			buff.putInt(stringVal.length() / 2);
			for(char c : stringVal.toCharArray())
				buff.putChar(c);
		}
	}
}
