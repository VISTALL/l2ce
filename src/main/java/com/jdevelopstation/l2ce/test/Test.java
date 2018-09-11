package com.jdevelopstation.l2ce.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author VISTALL
 * @date 2:58/27.06.2011
 */
public class Test
{
	public static void main(String... arg) throws Exception
	{
		InputStream stream = new FileInputStream("W:\\_github.com\\VISTALL\\l2ce\\dist\\dec-pawnanimdata.dat");
		byte[] data = new byte[stream.available()];
		stream.read(data);
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		int val = buf.getInt();
		System.out.println("SIZE: " + val);
		for(int i = 0; i < val; i++)
		{
			//long character = new UINT().read(buf);

			System.out.println(printData(buf, 800));
			break;
		}

		System.out.println("last: " + buf.remaining());
	}

	public static int ReadByteCount(ByteBuffer f)
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

	public static String ReadUnicode(ByteBuffer b)
	{
		int size = b.getInt();
		int real = size / 2;

		StringBuilder stringBuilder = new StringBuilder();

		for(int i = 0; i < real; i++)
		{
			stringBuilder.append(b.getChar());
		}

		return stringBuilder.toString();
	}

	public static String ReadASCF(ByteBuffer buff)
	{
		int size = buff.get() & 0xFF;
		if(size == 0)
		{
			return "";
		}

		if(size >= 192)
		{
			buff.position(buff.position() - 1);
			size = buff.getShort() & 0xFFFF;
		}

		if(size >= Short.MAX_VALUE)
		{
			buff.position(buff.position() + 1);
		}


		if(size >= 128)
		{
			if(buff.getChar(buff.position()) == 0)
			{
				return "";
			}

			StringBuilder b = new StringBuilder();

			//size = buff.get() & 0xFF;
			char d = 0;
			while((d = buff.getChar()) != 0)
			{
				b.append(d);
			}

			return b.toString();
		}
		else
		{
			StringBuilder b = new StringBuilder();

			//size = buff.get() & 0xFF;
			byte d = 0;
			while((d = buff.get()) != 0)
			{
				b.append((char) d);
			}

			return b.toString();
		}
	}

	public static String printData(ByteBuffer b, int l)
	{
		byte[] d = new byte[l];
		b.get(d);
		return printData(d, d.length);
	}

	public static String printData(byte[] data)
	{
		return printData(data, data.length);
	}

	public static String printData(byte[] data, int len)
	{
		StringBuilder result = new StringBuilder("\n");

		int counter = 0;

		for(int i = 0; i < len; i++)
		{
			if(counter % 16 == 0)
			{
				result.append(fillHex(i, 4) + ": ");
			}

			result.append(fillHex(data[i] & 0xff, 2) + " ");
			counter++;
			if(counter == 16)
			{
				result.append("   ");

				int charpoint = i - 15;
				for(int a = 0; a < 16; a++)
				{
					int t1 = data[charpoint++];
					if(t1 > 0x1f && t1 < 0x80)
					{
						result.append((char) t1);
					}
					else
					{
						result.append('.');
					}
				}

				result.append("\n");
				counter = 0;
			}
		}

		int rest = data.length % 16;
		if(rest > 0)
		{
			for(int i = 0; i < 17 - rest; i++)
			{
				result.append("   ");
			}

			int charpoint = data.length - rest;
			for(int a = 0; a < rest; a++)
			{
				int t1 = data[charpoint++];
				if(t1 > 0x1f && t1 < 0x80)
				{
					result.append((char) t1);
				}
				else
				{
					result.append('.');
				}
			}

			result.append("\n");
		}


		return result.toString();
	}

	public static String fillHex(int data, int digits)
	{
		String number = Integer.toHexString(data);

		for(int i = number.length(); i < digits; i++)
		{
			number = "0" + number;
		}

		return number;
	}
}
