package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @date 10:08/19.05.2011
 */
public class SHORT_SHORT implements ReadWriteType<Integer>
{
	@Override
	public Integer read(ByteBuffer f)
	{
		int tmp = f.get() & 0xFF;
		int len = tmp & 0x3F;
		if((tmp & 0x40) > 0)
		{
			tmp = f.get() & 0xFF;
			len += tmp << 6;
		}
		return len;
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		long longVal = 0;
		if(val instanceof Number)
			longVal = ((Number) val).longValue();
		else if(val instanceof String)
			longVal = Long.parseLong((String) val);

		if(longVal > 0x3F)
		{
			long LByte = longVal & 0x3F;
			long HByte = longVal >> 6;
			buff.put((byte) (LByte | 0x40));
			buff.put((byte) HByte);
		}
		else
		{
			buff.put((byte) longVal);
		}
	}
}
