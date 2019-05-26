package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @since 2018-10-21
 */
public class LONG implements ReadWriteType<Long>
{
	@Override
	public Long read(ByteBuffer buffer)
	{
		return buffer.getLong() & 0xFFFFFFFFL;
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof Number)
		{
			buff.putLong(((Number) val).longValue());
		}
		else if(val instanceof String)
		{
			Long l = Long.decode((String) val);
			buff.putLong(l);
		}
	}
}