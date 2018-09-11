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
		int output = 0;
		boolean signed = false;
		for(int i = 0; i < 5; i++)
		{
			int x = f.get() & 0xFF;

			if(i == 0)
			{
				if((x & 0x80) > 0)
				{
					signed = true;
				}
				output |= x & 0x3F;
				if((x & 0x40) == 0)
				{
					break;
				}
			}
			else if(i == 4)
			{
				output |= (x & 0x1F) << 27;
			}
			else
			{
				output |= (x & 0x7F) << 6 + (i - 1) * 7;
				if((x & 0x80) == 0)
				{
					break;
				}
			}
		}
		if(signed)
		{
			output *= -1;
		}
		return output;
	}

	@Override
	public void write(Object val, ByteBuffer buff)
	{
		long longVal = 0;
		if(val instanceof Number)
		{
			longVal = ((Number) val).longValue();
		}
		else if(val instanceof String)
		{
			longVal = Long.decode((String) val);
		}

		byte[] b = compactIntToByteArray((int) longVal);
		buff.put(b);
	}

	private static byte[] compactIntToByteArray(int v)
	{
		boolean negative = v < 0;
		v = Math.abs(v);
		int[] bytes = {
				v & 0x3F,
				v >> 6 & 0x7F,
				v >> 13 & 0x7F,
				v >> 20 & 0x7F,
				v >> 27 & 0x7F
		};
		if(negative)
		{
			bytes[0] |= 0x80;
		}
		int size = 5;
		for(int i = 4; i > 0; i--)
		{
			if(bytes[i] != 0)
			{
				break;
			}
			size--;
		}
		byte[] res = new byte[size];
		for(int i = 0; i < size; i++)
		{
			if(i != size - 1)
			{
				bytes[i] |= (i == 0 ? 64 : 128);
			}
			res[i] = ((byte) bytes[i]);
		}
		return res;
	}
}
