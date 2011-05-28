package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @date 8:08/18.05.2011
 */
public class UINT implements ReadWriteType<Long>
{
	@Override
	public Long read(ByteBuffer buffer)
	{
		return buffer.getInt() & 0xFFFFFFFFL;
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof Number)
			buff.putInt(((Number) val).intValue());
		else if(val instanceof String)
			buff.putInt((int)Long.parseLong((String)val));
	}
}
