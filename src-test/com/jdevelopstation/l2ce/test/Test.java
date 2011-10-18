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
		InputStream stream = new FileInputStream("W:\\MyTests\\l2encdec\\dec-etcitemgrp.dat");
		byte[] data = new byte[stream.available()];
		stream.read(data);
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(ByteOrder.LITTLE_ENDIAN);
/**
200AC8DA   PUSH Engine.205C63F8                      UNICODE "item_begin"
200AC94F   PUSH Engine.205C6410                      UNICODE "object_id"
200AC9C2   PUSH Engine.205C7BC4                      UNICODE "object_name"
200ACA35   PUSH Engine.205C7BDC                      UNICODE "drop_type"
200ACAA8   PUSH Engine.205C7BF0                      UNICODE "drop_anim_type"
200ACB1B   PUSH Engine.205C7C10                      UNICODE "drop_radius"
200ACB8E   PUSH Engine.205C7C28                      UNICODE "drop_height"
200ACC01   PUSH Engine.205C7C40                      UNICODE "drop_mesh"
200ACC79   PUSH Engine.205C7C54                      UNICODE "drop_texture"
200ACCF5   PUSH Engine.205C7C70                      UNICODE "droptexnum"
200ACD71   PUSH Engine.205C7C88                      UNICODE "icon"
200ACDED   PUSH Engine.205C7C94                      UNICODE "icon_panel"
200ACE69   PUSH Engine.205C7CAC                      UNICODE "mesh"
200ACEE5   PUSH Engine.205C7CB8                      UNICODE "texture"
200ACF61   PUSH Engine.205C7CC8                      UNICODE "crystallizable"
200ACFDD   PUSH Engine.205C7CE8                      UNICODE "etcitem_type"
200AD059   PUSH Engine.205C7D04                      UNICODE "weight"
200AD0D5   PUSH Engine.205C7D14                      UNICODE "consume_type"
200AD151   PUSH Engine.205C7D30                      UNICODE "material_type"
200AD1CD   PUSH Engine.205C7D4C                      UNICODE "crystal_type"
200AD249   PUSH Engine.205C7D68                      UNICODE "durability"
200AD2C5   PUSH Engine.205C7D80                      UNICODE "drop_sound"
200AD341   PUSH Engine.205C7D98                      UNICODE "equip_sound"
200AD3BD   PUSH Engine.205C7DB0                      UNICODE "related_quest_id"
200AD439   PUSH Engine.205C7DD4                      UNICODE "is_attribution"
200AD4B5   PUSH Engine.205C6570                      UNICODE "item_end"
*/
		int val = buf.getInt();
		System.out.println("SIZE: " + val);
		for(int i = 0; i < val; i++)
		{
			System.out.print("tag: " + buf.getInt());
			int itemId = buf.getInt();
			System.out.print(" item_id: " + itemId);
			System.out.print(" drop_type: " + buf.getInt());
			System.out.print(" drop_anim_type: " + buf.getInt());
			System.out.print(" drop_radius: " + buf.getInt());
			System.out.print(" drop_height: " + buf.getInt());
			System.out.print(" UNK_0: " + buf.getInt());

			System.out.print(" drop_mesh1: " + ReadUnicode(buf));
			System.out.print(" drop_mesh2: " + ReadUnicode(buf));
			System.out.print(" drop_mesh3: " + ReadUnicode(buf));

			System.out.print(" drop_tex1: " + ReadUnicode(buf));
			System.out.print(" drop_tex2: " + ReadUnicode(buf));
			System.out.print(" drop_tex3: " + ReadUnicode(buf));
			System.out.print(" drop_extratex1: " + ReadUnicode(buf));

			System.out.print(" newdata1: " + buf.getInt());
			System.out.print(" newdata2: " + buf.getInt());
			System.out.print(" newdata3: " + buf.getInt());
			System.out.print(" newdata4: " + buf.getInt());
			System.out.print(" newdata5: " + buf.getInt());
			System.out.print(" newdata6: " + buf.getInt());
			System.out.print(" newdata7: " + buf.getInt());
			System.out.print(" newdata8: " + buf.getInt());

			System.out.print(" icon1: " + ReadUnicode(buf));
			System.out.print(" icon2: " + ReadUnicode(buf));
			System.out.print(" icon3: " + ReadUnicode(buf));
			System.out.print(" icon4: " + ReadUnicode(buf));
			System.out.print(" icon5: " + ReadUnicode(buf));

			System.out.print(" durability: " + buf.getInt());
			System.out.print(" weight: " + buf.getInt());
			System.out.print(" material: " + buf.getInt());
			System.out.print(" crystallizable: " + buf.getInt());
			System.out.print(" UNK_1: " + buf.getInt());
			int questSize = buf.getInt();
			for(int a = 0; a < questSize; a++)
				System.out.print(" related_quest_id: " + buf.getInt());

			System.out.print(" UNK_2: " + buf.getInt());
			System.out.print(" UNK_3: " + buf.getInt());
			System.out.print(" UNK_4: " + buf.getInt());
			System.out.print(" fort: " + ReadUnicode(buf));


			/*if(itemId == 9593)
			{
				System.out.println(printData(buf, 100));
				break;
			}  */
			int meshTextures1 = buf.getInt();

			for(int a = 0; a < meshTextures1; a++)
				System.out.print(" mex_tex: " + ReadUnicode(buf));

			int meshTextures2 = buf.getInt();
			for(int a = 0; a < meshTextures2; a++)
				System.out.print(" mex_tex: " + ReadUnicode(buf));

			System.out.print(" item_sound: " + ReadUnicode(buf));
			System.out.print(" equip_sound: " + ReadUnicode(buf));
			System.out.print(" stackable: " + buf.getInt());
			System.out.print(" family: " + buf.getInt());
			System.out.print(" grade: " + buf.getInt());
			System.out.println();

			//System.out.println(printData(buf, 100));
			//break;
		}

		System.out.println("last: "+ buf.remaining());
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
			stringBuilder.append(b.getChar());

		return stringBuilder.toString();
	}

	public static String ReadASCF(ByteBuffer buff)
	{
		int size = buff.get() & 0xFF;
		if(size == 0)
			return "";

		if(size >= 192)
		{
			buff.position(buff.position() - 1);
			size = buff.getShort() & 0xFFFF;
		}

		if(size >= Short.MAX_VALUE)
			buff.position(buff.position() + 1);


		if(size >= 128)
		{
			if(buff.getChar(buff.position()) == 0)
				return "";

			StringBuilder b = new StringBuilder();

			//size = buff.get() & 0xFF;
			char d = 0;
			while((d = buff.getChar()) != 0)
				b.append(d);

			return b.toString();
		}
		else
		{
			StringBuilder b = new StringBuilder();

			//size = buff.get() & 0xFF;
			byte d = 0;
			while((d = buff.get()) != 0)
				b.append((char) d);

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
