package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @since 2019-06-01
 */
public class UBYTE implements ReadWriteType<Long>
{
	@Override
	public Long read(ByteBuffer buffer)
	{
		return buffer.get() & 0xFFL;
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof Number)
		{
			buff.put(((Number) val).byteValue());
		}
		else if(val instanceof String)
		{
			Long l = Long.decode((String) val);
			buff.put(l.byteValue());
		}
	}
}

