package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @since 2018-05-06
 */
public class SHORT implements ReadWriteType<Long>
{
	@Override
	public Long read(ByteBuffer buffer)
	{
		return (long) buffer.getShort();
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof Number)
		{
			buff.putShort(((Number) val).shortValue());
		}
		else if(val instanceof String)
		{
			Long l = Long.decode((String) val);
			buff.putShort(l.shortValue());
		}
	}
}
