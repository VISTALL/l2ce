package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @since 2019-06-03
 */
public class DOUBLE implements ReadWriteType<Double>
{
	@Override
	public Double read(ByteBuffer buffer)
	{
		return buffer.getDouble();
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof Number)
		{
			buff.putDouble(((Number) val).doubleValue());
		}
		else if(val instanceof String)
		{
			buff.putDouble(Double.parseDouble((String) val));
		}
	}
}