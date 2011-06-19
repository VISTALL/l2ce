package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

import org.apache.commons.lang.StringUtils;

/**
 * @author VISTALL
 * @date 9:29/18.05.2011
 */
public class ASCF implements ReadWriteType<String>
{
	@Override
	public String read(ByteBuffer buff)
	{
	 	int size = buff.get() & 0xFF;
		if(size == 0)
			return StringUtils.EMPTY;

		if(size >= 192)
		{
			buff.position(buff.position() - 1);
			size = buff.getShort() & 0xFFFF;
		}

		if(size >= Short.MAX_VALUE)
			buff.position(buff.position() + 1);

		if(size >= 128)
		{
			if(buff.getChar(buff.position()) == 0)
				return StringUtils.EMPTY;

			StringBuilder b = new StringBuilder();

			char d = 0;
			while ((d = buff.getChar()) != 0)
				b.append(d);

			return b.toString();
		}
		else
		{
			StringBuilder b = new StringBuilder();

			byte d = 0;
			while ((d = buff.get()) != 0)
				b.append((char)d);

			return b.toString();
		}
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if (val instanceof String)
		{
			String value = (String)val;
			int length = value.length();
			
			if (length == 0)
				buff.put((byte) 0); //empty
			else if (length < 192)
				buff.put((byte) length);
			else if (length < Short.MAX_VALUE)
				buff.putShort((short) length);
			else 
			{
				buff.putShort((short) length);
				buff.put((byte) 0); //?? unknown
			}
				
			if (length >= 128)
			{
				for (char part : value.toCharArray())
					buff.putChar(part);
				
				buff.put((byte) 0);
			}
			else
			{
				for (byte part : value.getBytes())
					buff.put(part);
			}
			
			buff.put((byte) 0); //term
		}
	}
}
