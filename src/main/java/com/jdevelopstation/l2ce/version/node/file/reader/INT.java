package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @date 8:08/18.05.2011
 */
public class INT implements ReadWriteType<Integer>
{
	@Override
	public Integer read(ByteBuffer buffer)
	{
		return buffer.getInt();
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof Number)
		{
			buff.putInt(((Number) val).intValue());
		}
		else if(val instanceof String)
		{
			buff.putInt(Integer.decode((String) val));
		}
	}
}
