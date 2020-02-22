package com.jdevelopstation.l2ce.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jdevelopstation.l2ce.version.node.file.reader.*;

/**
 * @author VISTALL
 * @date 2:58/27.06.2011
 */
public class TestReading
{
	public static void main(String... arg) throws Exception
	{
		InputStream stream = new FileInputStream("W:\\_github.com\\VISTALL\\l2ce\\dist\\l2encdec\\dec-OneDayReward-e.dat");
		byte[] data = new byte[stream.available()];
		stream.read(data);
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		int val = buf.getInt();
		System.out.println("SIZE: " + val);
		for(int i = 0; i < val; i++)
		{

			long id = new UINT().read(buf);
			long reward_id = new UINT().read(buf);
			String name = new ASCF().read(buf);
			int rCount = new UINT().read(buf).intValue();
			String desc = new ASCF().read(buf);
			String target = new ASCF().read(buf);

			int classFilter = new BYTE().read(buf).byteValue();
			Integer[] classFilters = new Integer[classFilter];
			for(int j = 0; j < classFilter; j++)
			{
				classFilters[j] = new INT().read(buf);
			}

			long reset_period = new UINT().read(buf);
			long condition_count = new UINT().read(buf);
			long condition_level = new UINT().read(buf);
			long a4 = new UINT().read(buf);
			long a5 = new UINT().read(buf);
			long a6 = new UINT().read(buf);


			long location = new UINT().read(buf);
			int a8 = new BYTE().read(buf).byteValue();
			Integer[] can_condition_day = new Integer[a8];
			for(int j = 0; j < a8; j++)
			{
				can_condition_day[j] = new UINT().read(buf).intValue();
			}

			long unk1 = new UINT().read(buf);

			// a7
			// bottom3

			
			List<AbstractMap.SimpleEntry> items = new ArrayList<>();
			for(int j = 0; j < rCount; j++)
			{
				int itemId = new INT().read(buf);
				int count = new INT().read(buf);

				items.add(new AbstractMap.SimpleEntry(itemId, count));
			}

			for(int j = 0; j < location; j++)
			{
				System.out.println(new FLOAT().read(buf));
				System.out.println(new FLOAT().read(buf));
				System.out.println(new FLOAT().read(buf));
				System.out.println(new FLOAT().read(buf));

			}

			print("id", id, "reward_id", reward_id, "target", target, "reset_period", reset_period, "condition_count", condition_count, "condition_level", condition_level, "a4", a4, "a5", a5, "a6", a6, "a7", location, "bottom3", unk1, "can_condition_day", Arrays.asList(can_condition_day), "items", items, Arrays.asList(classFilters), name, desc);

//			if(i == 107)
//			{
//				System.out.println(printData(buf, 800));
//				break;
//			}
		}

		System.out.println("last: " + buf.remaining());
	}

	private static void print(Object... arg)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < arg.length; i++)
		{
			if(i != 0)
			{
				builder.append(" | ");
			}

			builder.append(arg[i]);
		}

		System.out.println(builder);
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
