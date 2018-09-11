package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @date 9:31/19.05.2011
 */
public class FLOAT implements ReadWriteType<Float>
{
	@Override
	public Float read(ByteBuffer buffer)
	{
		return buffer.getFloat();
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		if(val instanceof Number)
			buff.putFloat(((Number) val).floatValue());
		else if(val instanceof String)
			buff.putFloat(Float.parseFloat((String)val));
	}
}
